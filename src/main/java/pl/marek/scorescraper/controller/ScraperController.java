package pl.marek.scorescraper.controller;

import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marek.scorescraper.LeagueLink;
import pl.marek.scorescraper.LeagueLinkRepository;
import pl.marek.scorescraper.scrapeResults.LeagueTable;
import pl.marek.scorescraper.scraper.ScrapeTableStrategy;
import pl.marek.scorescraper.scraper.ScraperStrategy;
import pl.marek.scorescraper.scraper.ScraperUtil;

import java.util.List;

@RestController
@RequestMapping("/scraper")
class ScraperController {
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ScraperController.class);
    private final LeagueLinkRepository leagueLinkRepository;

    public ScraperController(LeagueLinkRepository leagueLinkRepository) {
        this.leagueLinkRepository = leagueLinkRepository;
    }

    @GetMapping("/leagues")
    ResponseEntity<List<LeagueLink>> getLeagueLinks() {
        return ResponseEntity.ok(leagueLinkRepository.findAll());
    }

    @GetMapping("/league-table")
    ResponseEntity<LeagueTable> scrapeLeagueTable(String leagueName) {
        LeagueLink leagueToScrape = leagueLinkRepository.findAll().stream()
                .filter(league -> leagueName.equals(league.getName()))
                .findFirst()
                .orElseThrow(LeagueNotFoundException::new);
        String link = leagueToScrape.getLink();
        log.info("Getting html document from {}", link);
        Document documentFromUrl = ScraperUtil.getDocumentFromUrl(link);

        ScraperStrategy<LeagueTable> scraper = new ScrapeTableStrategy();
        log.info("Scraping the information about {}", leagueName);
        LeagueTable scraped = scraper.scrape(documentFromUrl);
        log.info(scraped);
        return ResponseEntity.ok(scraped);
    }
}

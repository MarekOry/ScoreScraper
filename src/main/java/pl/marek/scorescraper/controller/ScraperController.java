package pl.marek.scorescraper.controller;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.marek.scorescraper.LeagueLink;
import pl.marek.scorescraper.LeagueLinkRepository;
import pl.marek.scorescraper.scrapeResults.ScrapeResult;
import pl.marek.scorescraper.scraper.ScrapeTableStrategy;
import pl.marek.scorescraper.scraper.Scraper;

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
    ResponseEntity<ScrapeResult> scrapeLeagueTable(String leagueName) {
        LeagueLink leagueToScrape = leagueLinkRepository.findAll().stream()
                .filter(league -> leagueName.equals(league.getName()))
                .findFirst()
                .orElseThrow(LeagueNotFoundException::new);

        Scraper scraper = new Scraper();
        scraper.scraperStrategy = new ScrapeTableStrategy();
        ScrapeResult scraped = scraper.scrape(leagueToScrape.getLink());
        log.info(scraped);
        return ResponseEntity.ok(scraped);
    }
}

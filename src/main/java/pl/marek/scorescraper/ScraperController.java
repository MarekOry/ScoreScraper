package pl.marek.scorescraper;

import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/scraper")
class ScraperController {
    private static final Logger log = org.apache.logging.log4j.LogManager.getLogger(ScraperController.class);
    LeagueLinkRepository leagueLinkRepository;

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
                .orElseThrow(RuntimeException::new);//todo handle exception

        Scraper scraper = new Scraper();
        scraper.scraperStrategy = new ScrapeTableStrategy();
        ScrapeResult scraped = scraper.scrape(leagueToScrape.getLink());
        log.error(scraped);
        return ResponseEntity.ok(scraped);
    }
}

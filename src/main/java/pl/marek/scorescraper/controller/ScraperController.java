package pl.marek.scorescraper.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.marek.scorescraper.link.ScrapeableLink;
import pl.marek.scorescraper.link.ScrapeableLinkRepository;
import pl.marek.scorescraper.link.ScrapeableLinkType;
import pl.marek.scorescraper.scrapeResults.ClubResults;
import pl.marek.scorescraper.scrapeResults.ClubSquad;
import pl.marek.scorescraper.scrapeResults.LeagueTable;
import pl.marek.scorescraper.scraper.ScraperService;

import java.util.List;

@RestController
@RequestMapping("/scraper")
class ScraperController {
    private static final Logger log = LogManager.getLogger(ScraperController.class);
    private final ScrapeableLinkRepository scrapeableLinkRepository;
    private final ScraperService scraperService;

    public ScraperController(ScrapeableLinkRepository scrapeableLinkRepository, ScraperService scraperService) {
        this.scrapeableLinkRepository = scrapeableLinkRepository;
        this.scraperService = scraperService;
    }

    @GetMapping("/leagues")
    ResponseEntity<List<ScrapeableLink>> getLeagueLinks() {
        return ResponseEntity.ok(scrapeableLinkRepository.findByScrapeableLinkType(ScrapeableLinkType.LEAGUE));
    }

    @GetMapping("/league-table")
    ResponseEntity<LeagueTable> scrapeLeagueTable(@RequestParam String leagueName) {
        LeagueTable scraped = scraperService.scrapeLeagueTable(leagueName);
        log.info(scraped);
        return ResponseEntity.ok(scraped);
    }

    @GetMapping("/clubs")
    ResponseEntity<List<ScrapeableLink>> getClubResultsLinks() {
        return ResponseEntity.ok(scrapeableLinkRepository.findByScrapeableLinkType(ScrapeableLinkType.CLUB_RESULT));
    }

    @GetMapping("/club-results")
    ResponseEntity<ClubResults> scrapeClubResults(@RequestParam String clubName) {
        ClubResults scraped = scraperService.scrapeClubResults(clubName);
        log.info(scraped);
        return ResponseEntity.ok(scraped);
    }

    @GetMapping("/club-squads")
    ResponseEntity<List<ScrapeableLink>> getClubSquadLinks() {
        return ResponseEntity.ok(scrapeableLinkRepository.findByScrapeableLinkType(ScrapeableLinkType.CLUB_SQUAD));
    }

    @GetMapping("/club-squad")
    ResponseEntity<ClubSquad> scrapeClubSquad(@RequestParam String clubName) {
        ClubSquad scraped = scraperService.scrapeClubSquad(clubName);
        log.info(scraped);
        return ResponseEntity.ok(scraped);
    }
}

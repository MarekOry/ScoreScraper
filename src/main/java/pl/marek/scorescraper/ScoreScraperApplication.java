package pl.marek.scorescraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreScraperApplication {
    private static final Logger log = LogManager.getLogger(ScoreScraperApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScoreScraperApplication.class, args);
        log.info("Application started.");

        String urlLeague = "https://betsapi.com/lt/125/Poland-Ekstraklasa";
        String urlTeamResults = "https://betsapi.com/te/88/Widzew-Lodz";

        Scraper tableScraper = new Scraper();
        tableScraper.scraperStrategy = new ScrapeTableStrategy();
        tableScraper.scrape(urlLeague);

        Scraper teamResultsScraper = new Scraper();
        teamResultsScraper.scraperStrategy = new ScrapeTeamResultsStrategy();
        teamResultsScraper.scrape(urlTeamResults);

    }
}
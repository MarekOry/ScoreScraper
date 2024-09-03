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

        String URL = "https://betsapi.com/lt/125/Poland-Ekstraklasa";

        Scraper scraper = new Scraper();
        scraper.scrape(URL);
    }
}

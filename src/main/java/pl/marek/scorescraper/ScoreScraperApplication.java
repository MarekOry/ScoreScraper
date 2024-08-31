package pl.marek.scorescraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScoreScraperApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScoreScraperApplication.class, args);

        String URL = "https://betsapi.com/lt/125/Poland-Ekstraklasa";

        Scraper scraper = new Scraper();
        scraper.scrape(URL);
    }
}

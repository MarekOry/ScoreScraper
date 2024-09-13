package pl.marek.scorescraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.marek.scorescraper.scrapeResults.ClubResults;
import pl.marek.scorescraper.scrapeResults.LeagueTable;
import pl.marek.scorescraper.scraper.ScrapeTableStrategy;
import pl.marek.scorescraper.scraper.ScrapeTeamResultsStrategy;
import pl.marek.scorescraper.scraper.ScraperStrategy;
import pl.marek.scorescraper.scraper.ScraperUtil;

@SpringBootApplication
public class ScoreScraperApplication {
    private static final Logger log = LogManager.getLogger(ScoreScraperApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ScoreScraperApplication.class, args);
        log.info("Application started.");

        String urlLeague = "https://betsapi.com/lt/125/Poland-Ekstraklasa";
        String urlTeamResults = "https://betsapi.com/te/88/Widzew-Lodz";


        ScraperStrategy<LeagueTable> tableScraper = new ScrapeTableStrategy();
        tableScraper.scrape(ScraperUtil.getDocumentFromUrl(urlLeague));

        ScraperStrategy<ClubResults> teamResultsScraper = new ScrapeTeamResultsStrategy();
        teamResultsScraper.scrape(ScraperUtil.getDocumentFromUrl(urlTeamResults));

    }
}
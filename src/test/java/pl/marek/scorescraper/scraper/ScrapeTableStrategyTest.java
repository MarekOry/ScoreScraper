package pl.marek.scorescraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pl.marek.scorescraper.scrapeResults.LeagueClubPosition;
import pl.marek.scorescraper.scrapeResults.LeagueTable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class ScrapeTableStrategyTest {

    private static Document document;

    @BeforeAll
    public static void init() throws IOException{
        Path html = Paths.get("src/test/resources/htmlTests/league-table-html-example.txt");
        ScrapeTableStrategyTest.document = Jsoup.parse(html, "UTF-8", "http://test.com/");
    }

    @Test
    public void givenNewScrapeTableStrategyWhenScrapingFromGivenHtmlThenProperResultSize() {
        //Given
        //When
        ScraperStrategy<LeagueTable> scraperStrategy = new ScrapeTableStrategy();
        LeagueTable scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals(18, scraped.leagueClubPositions().size());
    }

    @Test
    public void givenNewScrapeTableStrategyWhenScrapingFromGivenHtmlThenFirstResultCorrect() {
        //Given
        //When
        ScraperStrategy<LeagueTable> scraperStrategy = new ScrapeTableStrategy();
        LeagueTable scraped = scraperStrategy.scrape(document);
        LeagueClubPosition expected = new LeagueClubPosition.ClubPositionBuilder()
                .setClubName("Lech Poznan")
                .setPlace(1)
                .setWins(5)
                .setDraws(1)
                .setLoses(1)
                .setGoals("11 : 3")
                .setPoints(16)
                .build();
        //Then
        Assertions.assertEquals(expected, scraped.leagueClubPositions().get(0));
    }

    @Test
    public void givenNewScrapeTableStrategyWhenScrapingFromGivenHtmlThenLastResultCorrect() {
        //Given
        //When
        ScraperStrategy<LeagueTable> scraperStrategy = new ScrapeTableStrategy();
        LeagueTable scraped = scraperStrategy.scrape(document);
        LeagueClubPosition expected = new LeagueClubPosition.ClubPositionBuilder()
                .setClubName("Stal Mielec")
                .setPlace(18)
                .setWins(1)
                .setDraws(1)
                .setLoses(5)
                .setGoals("4 : 9")
                .setPoints(4)
                .build();
        //Then
        Assertions.assertEquals(expected, scraped.leagueClubPositions().get(17));
    }

    @Test
    public void givenNewScrapeClubResultsStrategyWhenScrapingFromGivenHtmlThenLeagueNameCorrect() {
        //Given
        //When
        ScraperStrategy<LeagueTable> scraperStrategy = new ScrapeTableStrategy();
        LeagueTable scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals("Poland Ekstraklasa", scraped.leagueName());
    }
}

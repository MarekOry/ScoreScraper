package pl.marek.scorescraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import pl.marek.scorescraper.scrapeResults.LeagueClubPosition;
import pl.marek.scorescraper.scrapeResults.LeagueTable;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

class ScrapeTableStrategyTest {

    @Test
    public void givenNewScrapeTableStrategyWhenScrapingFromGivenHtmlExampleThenProperResultSize() throws IOException {
        //Given
        Path html = Paths.get("src/test/resources/htmlTests/league-table-html-example.txt");
        Document document = Jsoup.parse(html, "UTF-8", "http://test.com/");
        //When
        ScraperStrategy<LeagueTable> scraperStrategy = new ScrapeTableStrategy();
        LeagueTable scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals(18, scraped.getLeagueClubPositions().size());
    }

    @Test
    public void givenNewScrapeTableStrategyWhenScrapingFromGivenHtmlExampleThenFirstResultCorrect() throws IOException {
        //Given
        Path html = Paths.get("src/test/resources/htmlTests/league-table-html-example.txt");
        Document document = Jsoup.parse(html, "UTF-8", "http://test.com/");
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
        Assertions.assertEquals(expected, scraped.getLeagueClubPositions().get(0));
    }

    @Test
    public void givenNewScrapeTableStrategyWhenScrapingFromGivenHtmlExampleThenLastResultCorrect() throws IOException {
        //Given
        Path html = Paths.get("src/test/resources/htmlTests/league-table-html-example.txt");
        Document document = Jsoup.parse(html, "UTF-8", "http://test.com/");
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
        Assertions.assertEquals(expected, scraped.getLeagueClubPositions().get(17));
    }
}

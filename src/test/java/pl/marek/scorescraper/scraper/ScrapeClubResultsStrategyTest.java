package pl.marek.scorescraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.marek.scorescraper.scrapeResults.ClubMatchResult;
import pl.marek.scorescraper.scrapeResults.ClubResults;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

class ScrapeClubResultsStrategyTest {

    private static Document document;

    @BeforeAll
    public static void init() throws IOException {
        Path html = Paths.get("src/test/resources/htmlTests/club-results-html-example.txt");
        ScrapeClubResultsStrategyTest.document = Jsoup.parse(html, "UTF-8", "http://test.com/");
    }

    @Test
    public void givenNewScrapeClubResultsStrategyWhenScrapingFromGivenHtmlThenProperResultSize() {
        //Given
        //When
        ScraperStrategy<ClubResults> scraperStrategy = new ScrapeClubResultsStrategy();
        ClubResults scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals(30, scraped.clubMatchResults().size());
    }

    @Test
    public void givenNewScrapeClubResultsStrategyWhenScrapingFromGivenHtmlThenFirstResultCorrect() {
        //Given
        //When
        ScraperStrategy<ClubResults> scraperStrategy = new ScrapeClubResultsStrategy();
        ClubResults scraped = scraperStrategy.scrape(document);
        ClubMatchResult expected = new ClubMatchResult.ClubResultBuilder()
                .setLeague("Poland Ekstraklasa")
                .setDateOfMatch(Instant.parse("2024-09-13T18:30:00Z"))
                .setRound("8")
                .setTeamsPlaying("[9] GKS Katowice v Widzew Lodz [8]")
                .setResult("2-2")
                .build();
        //Then
        Assertions.assertEquals(expected, scraped.clubMatchResults().get(0));
    }

    @Test
    public void givenNewScrapeClubResultsStrategyWhenScrapingFromGivenHtmlThenLastResultCorrect() {
        //Given
        //When
        ScraperStrategy<ClubResults> scraperStrategy = new ScrapeClubResultsStrategy();
        ClubResults scraped = scraperStrategy.scrape(document);
        ClubMatchResult expected = new ClubMatchResult.ClubResultBuilder()
                .setLeague("Club Friendly List")
                .setDateOfMatch(Instant.parse("2024-01-31T14:00:00Z"))
                .setRound("-")
                .setTeamsPlaying("Widzew Lodz v Slovan Liberec")
                .setResult("0-2")
                .build();
        //Then
        Assertions.assertEquals(expected, scraped.clubMatchResults().get(29));
    }

    @Test
    public void givenNewScrapeClubResultsStrategyWhenScrapingFromGivenHtmlThenClubNameCorrect() {
        //Given
        //When
        ScraperStrategy<ClubResults> scraperStrategy = new ScrapeClubResultsStrategy();
        ClubResults scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals("Widzew Lodz", scraped.clubName());
    }
}

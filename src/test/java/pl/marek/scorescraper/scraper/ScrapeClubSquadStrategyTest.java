package pl.marek.scorescraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pl.marek.scorescraper.scrapeResults.ClubSquad;
import pl.marek.scorescraper.scrapeResults.ClubSquadPlayer;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

class ScrapeClubSquadStrategyTest {

    static Document document;

    @BeforeAll
    public static void init() throws IOException {
        Path html = Paths.get("src/test/resources/htmlTests/club-squad-html-example.txt");
        ScrapeClubSquadStrategyTest.document = Jsoup.parse(html, "UTF-8", "http://test.com/");
    }

    @Test
    public void givenNewScrapeClubSquadStrategyWhenScrapingFromGivenHtmlThenProperResultSize() {
        //Given
        //When
        ScraperStrategy<ClubSquad> scraperStrategy = new ScrapeClubSquadStrategy();
        ClubSquad scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals(27, scraped.clubSquadPlayer().size());
    }

    @Test
    public void givenNewScrapeClubSquadStrategyWhenScrapingFromGivenHtmlThenFirstResultCorrect() {
        //Given
        //When
        ScraperStrategy<ClubSquad> scraperStrategy = new ScrapeClubSquadStrategy();
        ClubSquad scraped = scraperStrategy.scrape(document);
        ClubSquadPlayer expected = new ClubSquadPlayer.ClubSquadPlayerBuilder()
                .setNumber("1")
                .setPlayerName("Rafal Gikiewicz")
                .setBirthDate(LocalDate.parse("1987-10-26"))
                .setAge(36)
                .setCountryShort("pl")
                .setPosition("Guard")
                .setHeight(190)
                .setWeight(81)
                .setPreferredFoot("Right")
                .build();
        //Then
        Assertions.assertEquals(expected, scraped.clubSquadPlayer().get(0));
    }

    @Test
    public void givenNewScrapeClubSquadStrategyWhenScrapingFromGivenHtmlThenLastResultCorrect() {
        //Given
        //When
        ScraperStrategy<ClubSquad> scraperStrategy = new ScrapeClubSquadStrategy();
        ClubSquad scraped = scraperStrategy.scrape(document);
        ClubSquadPlayer expected = new ClubSquadPlayer.ClubSquadPlayerBuilder()
                .setNumber("")
                .setPlayerName("Kajetan Radomski")
                .setBirthDate(LocalDate.parse("2005-06-21"))
                .setAge(19)
                .setCountryShort("pl")
                .setPosition("")
                .setHeight(0)
                .setWeight(0)
                .setPreferredFoot("")
                .build();
        //Then
        Assertions.assertEquals(expected, scraped.clubSquadPlayer().get(26));
    }

    @Test
    public void givenNewScrapeClubSquadStrategyWhenScrapingFromGivenHtmlThenClubNameCorrect() {
        //Given
        //When
        ScraperStrategy<ClubSquad> scraperStrategy = new ScrapeClubSquadStrategy();
        ClubSquad scraped = scraperStrategy.scrape(document);
        //Then
        Assertions.assertEquals("Widzew Lodz", scraped.clubName());
    }
}

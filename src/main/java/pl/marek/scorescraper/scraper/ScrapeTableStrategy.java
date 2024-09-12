package pl.marek.scorescraper.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.marek.scorescraper.scrapeResults.LeagueClubPosition;
import pl.marek.scorescraper.scrapeResults.LeagueTable;
import pl.marek.scorescraper.scrapeResults.ScrapeResult;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeTableStrategy implements ScraperStrategy{
    private static final Logger log = LogManager.getLogger(ScrapeTableStrategy.class);

    @Override
    public ScrapeResult scrape(String url) {
        try {
            log.info("Scraping the {}", url);

            Document document = Jsoup.connect(url).get();
            String leagueName = document.select("#overall > div:nth-child(1) > div > div > h3").text();
            Elements leaderboard = document.select("#overall > div:nth-child(1) > div > table> tbody >tr");

            List<LeagueClubPosition> leagueClubPositions = new ArrayList<>();
            for(int i = 1; i < leaderboard.size(); i++) {
                Elements rows = leaderboard.get(i).select("td");
                try{
                    leagueClubPositions.add(new LeagueClubPosition.ClubPositionBuilder()
                            .setClubName(rows.get(3).text())
                            .setPlace(Short.parseShort(rows.get(1).text()))
                            .setWins(Short.parseShort(rows.get(4).text()))
                            .setDraws(Short.parseShort(rows.get(5).text()))
                            .setLoses(Short.parseShort(rows.get(6).text()))
                            .setGoals(rows.get(7).text())
                            .setPoints(Short.parseShort(rows.get(8).text()))
                            .build());
                } catch (NumberFormatException ex) {
                throw new ScraperException(ex);
                }
            }
            ScrapeResult leagueTable = new LeagueTable(leagueName, leagueClubPositions);

            System.out.println(leagueTable);
            return leagueTable;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new ScrapeResult();
    }
}

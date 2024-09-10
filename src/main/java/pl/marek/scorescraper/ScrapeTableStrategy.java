package pl.marek.scorescraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

class ScrapeTableStrategy implements ScraperStrategy{
    private static final Logger log = LogManager.getLogger(ScrapeTableStrategy.class);

    @Override
    public ScrapeResult scrape(String url) {
        try {
            log.info("Scraping the {}", url);

            Document document = Jsoup.connect(url).get();
            String leagueName = document.select("#overall > div:nth-child(1) > div > div > h3").text();
            Elements leaderboard = document.select("#overall > div:nth-child(1) > div > table> tbody >tr");

            List<LeagueClubPosition> leagueClubPositions = createClubPositions(leaderboard);
            ScrapeResult leagueTable = new LeagueTable(leagueName, leagueClubPositions);

            System.out.println(leagueTable);
            return leagueTable;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new ScrapeResult();
    }

    private static List<LeagueClubPosition> createClubPositions(Elements leaderboard) throws ScraperException{
        try {
            return leaderboard.stream()
                    .skip(1)
                    .map(element -> {
                        Elements rows = leaderboard.select("td");
                        return new LeagueClubPosition.ClubPositionBuilder()
                                .setClubName(rows.get(3).text())
                                .setPlace(Short.parseShort(rows.get(1).text()))
                                .setWins(Short.parseShort(rows.get(4).text()))
                                .setDraws(Short.parseShort(rows.get(5).text()))
                                .setLoses(Short.parseShort(rows.get(6).text()))
                                .setGoals(rows.get(7).text())
                                .setPoints(Short.parseShort(rows.get(8).text()))
                                .build();
                    }).toList();
        } catch (NumberFormatException ex) {
            throw new ScraperException(ex);
        }
    }
}

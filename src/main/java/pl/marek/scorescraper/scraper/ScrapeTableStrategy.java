package pl.marek.scorescraper.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.marek.scorescraper.scrapeResults.LeagueClubPosition;
import pl.marek.scorescraper.scrapeResults.LeagueTable;

import java.util.ArrayList;
import java.util.List;

public class ScrapeTableStrategy implements ScraperStrategy<LeagueTable>{
    private static final Logger log = LogManager.getLogger(ScrapeTableStrategy.class);

    @Override
    public LeagueTable scrape(Document document) {
        String leagueName = document.select("#overall > div:nth-child(1) > div > div > h3").text();
        Elements leaderboard = document.select("#overall > div:nth-child(1) > div > table> tbody >tr");

        List<LeagueClubPosition> leagueClubPositions = getLeagueClubPositions(leaderboard);
        LeagueTable leagueTable = new LeagueTable(leagueName, leagueClubPositions);

        log.info(leagueTable);
        return leagueTable;
    }

    private List<LeagueClubPosition> getLeagueClubPositions(Elements leaderboard) {
        List<LeagueClubPosition> leagueClubPositions = new ArrayList<>();
        for(int i = 1; i < leaderboard.size(); i++) {
            Elements rows = leaderboard.get(i).select("td");
            try{
                leagueClubPositions.add(new LeagueClubPosition.ClubPositionBuilder()
                        .setClubName(rows.get(3).text())
                        .setPlace(Integer.valueOf(rows.get(1).text()))
                        .setWins(Integer.valueOf(rows.get(4).text()))
                        .setDraws(Integer.valueOf(rows.get(5).text()))
                        .setLoses(Integer.valueOf(rows.get(6).text()))
                        .setGoals(rows.get(7).text())
                        .setPoints(Integer.valueOf(rows.get(8).text()))
                        .build());
            } catch (NumberFormatException e) {
            throw new ScraperException(e);
            }
        }
        return leagueClubPositions;
    }
}

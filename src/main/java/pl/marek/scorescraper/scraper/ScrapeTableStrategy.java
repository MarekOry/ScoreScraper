package pl.marek.scorescraper.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.marek.scorescraper.scrapeResults.LeagueClubPosition;
import pl.marek.scorescraper.scrapeResults.LeagueTable;

import java.util.ArrayList;
import java.util.List;

public class ScrapeTableStrategy implements ScraperStrategy<LeagueTable> {

    @Override
    public LeagueTable scrape(Document document) {
        String leagueName = document.select("body > div > div > div.my-3.my-md-5 > div.container > div.row.g-2 > div.col > h1").text();
        Elements leaderboard = document.select("#overall > div:nth-child(1) > div > table> tbody >tr");

        List<LeagueClubPosition> leagueClubPositions = getLeagueClubPositions(leaderboard);
        return new LeagueTable(leagueName, leagueClubPositions);
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

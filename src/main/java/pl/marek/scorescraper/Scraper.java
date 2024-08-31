package pl.marek.scorescraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

class Scraper {

    public void scrape(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            String leagueName = document.select("#overall > div:nth-child(1) > div > div > h3").text();
            Elements leaderboard = document.select("#overall > div:nth-child(1) > div > table> tbody >tr");

            List<ClubPosition> clubPositions = createClubPositions(leaderboard);
            LeagueTable leagueTable = new LeagueTable(leagueName, clubPositions);


            System.out.println(leagueTable);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static List<ClubPosition> createClubPositions(Elements leaderboard) {
        return leaderboard.stream()
                .skip(1)
                .map(element -> {
                    Elements rows = leaderboard.select("td");
                    return new ClubPosition.ClubPositionBuilder()
                            .setClubName(rows.get(3).text())
                            .setPlace(Short.parseShort(rows.get(1).text()))
                            .setWins(Short.parseShort(rows.get(4).text()))
                            .setDraws(Short.parseShort(rows.get(5).text()))
                            .setLoses(Short.parseShort(rows.get(6).text()))
                            .setGoals(rows.get(7).text())
                            .setPoints(Short.parseShort(rows.get(8).text()))
                            .build();
                }).toList();
    }
}

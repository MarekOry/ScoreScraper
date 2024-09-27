package pl.marek.scorescraper.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.marek.scorescraper.scrapeResults.ClubSquad;
import pl.marek.scorescraper.scrapeResults.ClubSquadPlayer;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeClubSquadStrategy implements ScraperStrategy<ClubSquad> {

    @Override
    public ClubSquad scrape(Document document) {
        Elements results = document.select("body > div.page > div.page-main > div.my-3.my-md-5 > div > div.tab-content > div > table > tbody > tr");
        String clubName = document.select("body > div.page > div.page-main > div.my-3.my-md-5 > div > div.row.g-2 > div.col > h1").text();

        List<ClubSquadPlayer> clubSquadPlayers = getClubSquadPlayers(results);
        ClubSquad clubSquad = new ClubSquad(clubName, clubSquadPlayers);
        System.out.println(clubSquad);
        return clubSquad;
    }

    private static List<ClubSquadPlayer> getClubSquadPlayers(Elements results) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        List<ClubSquadPlayer> clubSquadPlayers = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Elements rows = results.get(i).select("td");
            try {
                clubSquadPlayers.add(new ClubSquadPlayer.ClubSquadPlayerBuilder()
                        .setNumber(rows.get(0).text())
                        .setPlayerName(rows.get(1).text())
                        .setBirthDate(LocalDate.parse(rows.get(2).text(), formatter))
                        .setAge(Integer.valueOf(rows.get(3).text()))
                        .setCountryShort(rows.select("span").attr("class").substring(10))
                        .setPosition(rows.get(5).text())
                        .setHeight(Integer.valueOf(rows.get(6).text()))
                        .setWeight(Integer.valueOf(rows.get(7).text()))
                        .setPreferredFoot(rows.get(8).text())
                        .build());
            } catch (DateTimeParseException e) {
                throw new ScraperException(e);
            }
        }
        return clubSquadPlayers;
    }
}

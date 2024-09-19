package pl.marek.scorescraper.scraper;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.marek.scorescraper.scrapeResults.ClubMatchResult;
import pl.marek.scorescraper.scrapeResults.ClubResults;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeClubResultsStrategy implements ScraperStrategy<ClubResults> {

    @Override
    public ClubResults scrape(Document document) {
        Elements results = document.select("body > div > div > div.my-3.my-md-5 > div.container > div.tab-content > div > div.row > table > tbody > tr");
        String clubNameWithDiscipline = document.select("head > meta:nth-child(3)").attr("content");
        String clubName = clubNameWithDiscipline.split(",")[0];

        List<ClubMatchResult> clubMatchResults = getClubMatchResults(results);
        return new ClubResults(clubName, clubMatchResults);
    }

    private static List<ClubMatchResult> getClubMatchResults(Elements results) {
        List<ClubMatchResult> clubMatchResults = new ArrayList<>();
        for (int i = 0; i < results.size(); i++) {
            Elements rows = results.get(i).select("td");
            try {
                clubMatchResults.add(new ClubMatchResult.ClubResultBuilder()
                        .setLeague(rows.get(0).text())
                        .setDateOfMatch(Instant.parse(rows.get(1).attr("data-dt")))
                        .setRound(rows.get(2).text())
                        .setTeamsPlaying(rows.get(3).text())
                        .setResult(rows.get(5).text())
                        .build());
            } catch (DateTimeParseException e) {
                throw new ScraperException(e);
            }
        }
        return clubMatchResults;
    }
}

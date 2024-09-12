package pl.marek.scorescraper.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import pl.marek.scorescraper.scrapeResults.ClubMatchResult;
import pl.marek.scorescraper.scrapeResults.ClubResults;
import pl.marek.scorescraper.scrapeResults.ScrapeResult;


import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ScrapeTeamResultsStrategy implements ScraperStrategy{
    private static final Logger log = LogManager.getLogger(ScrapeTeamResultsStrategy.class);

    @Override
    public ScrapeResult scrape(String url) {
        try {
            Document document = Jsoup.connect(url).get();
            Elements results = document.select("body > div > div > div.my-3.my-md-5 > div.container > div.tab-content > div > div.row > table > tbody > tr");

            List<ClubMatchResult> clubMatchResults = new ArrayList<>();

            for(int i = 0; i < results.size(); i++){
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

            ScrapeResult scrapeResult = new ClubResults("", clubMatchResults);

            System.out.println(scrapeResult);
            return scrapeResult;
        } catch (IOException e) {
            log.error(e.getMessage(), e);
        }
        return new ScrapeResult();
    }
}

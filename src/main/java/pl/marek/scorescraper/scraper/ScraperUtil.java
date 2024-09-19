package pl.marek.scorescraper.scraper;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class ScraperUtil {

    public static Document getDocumentFromUrl(String url) throws ScraperException {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new ScraperException(e);
        }
    }
}

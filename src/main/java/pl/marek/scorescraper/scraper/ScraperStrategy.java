package pl.marek.scorescraper.scraper;

import org.jsoup.nodes.Document;

public interface ScraperStrategy<T> {
    T scrape(Document document);
}

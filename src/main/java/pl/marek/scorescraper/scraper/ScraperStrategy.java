package pl.marek.scorescraper.scraper;

import pl.marek.scorescraper.scrapeResults.ScrapeResult;

public interface ScraperStrategy {
    ScrapeResult scrape(String url);
}

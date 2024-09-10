package pl.marek.scorescraper;

public interface ScraperStrategy {
    ScrapeResult scrape(String url);
}

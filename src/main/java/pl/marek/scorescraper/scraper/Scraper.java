package pl.marek.scorescraper.scraper;

import pl.marek.scorescraper.scrapeResults.ScrapeResult;

public class Scraper implements ScraperStrategy{
    public ScraperStrategy scraperStrategy;
    @Override
    public ScrapeResult scrape(String url) {
        return scraperStrategy.scrape(url);
    }
}

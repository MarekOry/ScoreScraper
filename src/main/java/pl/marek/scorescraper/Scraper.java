package pl.marek.scorescraper;

class Scraper implements ScraperStrategy{
    public ScraperStrategy scraperStrategy;
    @Override
    public ScrapeResult scrape(String url) {
        return scraperStrategy.scrape(url);
    }
}

package pl.marek.scorescraper.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import pl.marek.scorescraper.link.LinkNotFoundException;
import pl.marek.scorescraper.link.ScrapeableLink;
import pl.marek.scorescraper.link.ScrapeableLinkRepository;
import pl.marek.scorescraper.scrapeResults.ClubResults;
import pl.marek.scorescraper.scrapeResults.LeagueTable;

@Service
public class ScraperService {
    private static final Logger log = LogManager.getLogger(ScraperService.class);
    private final ScrapeableLinkRepository scrapeableLinkRepository;

    public ScraperService(ScrapeableLinkRepository scrapeableLinkRepository) {
        this.scrapeableLinkRepository = scrapeableLinkRepository;
    }

    public LeagueTable scrapeLeagueTable(String leagueName) {
        ScrapeableLink leagueToScrape = getScrapeableLink(leagueName);
        String link = leagueToScrape.getLink();

        log.info("Getting html document from {}", link);
        Document documentFromUrl = ScraperUtil.getDocumentFromUrl(link);

        ScraperStrategy<LeagueTable> scraper = new ScrapeTableStrategy();
        log.info("Scraping the information about {}", leagueName);
        return scraper.scrape(documentFromUrl);
    }

    public ClubResults scrapeClubResults(String clubName) {
        ScrapeableLink leagueToScrape = getScrapeableLink(clubName);
        String link = leagueToScrape.getLink();

        log.info("Getting html document from {}", link);
        Document documentFromUrl = ScraperUtil.getDocumentFromUrl(link);

        ScraperStrategy<ClubResults> scraper = new ScrapeTeamResultsStrategy();
        log.info("Scraping the information about {}", clubName);
        return  scraper.scrape(documentFromUrl);
    }

    private ScrapeableLink getScrapeableLink(String leagueName) {
        return scrapeableLinkRepository.findAll().stream()
                .filter(league -> leagueName.equals(league.getName()))
                .findFirst()
                .orElseThrow(LinkNotFoundException::new);
    }

}

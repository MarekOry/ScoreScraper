package pl.marek.scorescraper.scraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;
import pl.marek.scorescraper.link.LinkNotFoundException;
import pl.marek.scorescraper.link.ScrapeableLink;
import pl.marek.scorescraper.link.ScrapeableLinkRepository;
import pl.marek.scorescraper.link.ScrapeableLinkType;
import pl.marek.scorescraper.scrapeResults.ClubResults;
import pl.marek.scorescraper.scrapeResults.ClubSquad;
import pl.marek.scorescraper.scrapeResults.LeagueTable;

@Service
public class ScraperService {
    private static final Logger log = LogManager.getLogger(ScraperService.class);
    private final ScrapeableLinkRepository scrapeableLinkRepository;

    public ScraperService(ScrapeableLinkRepository scrapeableLinkRepository) {
        this.scrapeableLinkRepository = scrapeableLinkRepository;
    }

    public LeagueTable scrapeLeagueTable(String name) {
        Document documentFromUrl = getHtml(name, ScrapeableLinkType.LEAGUE);

        ScraperStrategy<LeagueTable> scraper = new ScrapeTableStrategy();
        log.info("Scraping the information about {}", name);
        return scraper.scrape(documentFromUrl);
    }

    public ClubResults scrapeClubResults(String name) {
        Document documentFromUrl = getHtml(name, ScrapeableLinkType.CLUB_RESULT);

        ScraperStrategy<ClubResults> scraper = new ScrapeClubResultsStrategy();
        log.info("Scraping the information about {}", name);
        return scraper.scrape(documentFromUrl);
    }

    public ClubSquad scrapeClubSquad(String name) {
        Document documentFromUrl = getHtml(name, ScrapeableLinkType.CLUB_SQUAD);

        ScraperStrategy<ClubSquad> scraper = new ScrapeClubSquadStrategy();
        log.info("Scraping the information about {}", name);
        return scraper.scrape(documentFromUrl);
    }

    private Document getHtml(String name, ScrapeableLinkType scrapeableLinkType) {
        ScrapeableLink toScrape = getScrapeableLink(name, scrapeableLinkType);
        String link = toScrape.getLink();

        log.info("Getting html document from {}", link);
        return ScraperUtil.getDocumentFromUrl(link);
    }

    private ScrapeableLink getScrapeableLink(String name, ScrapeableLinkType scrapeableLinkType) {
        return scrapeableLinkRepository.findAll().stream()
                .filter(scrapeableLink -> scrapeableLinkType.equals(scrapeableLink.getScrapeableLinkType()))
                .filter(scrapeableLink -> name.equals(scrapeableLink.getName()))
                .findFirst()
                .orElseThrow(LinkNotFoundException::new);
    }
}

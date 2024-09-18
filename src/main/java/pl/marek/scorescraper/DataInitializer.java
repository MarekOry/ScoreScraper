package pl.marek.scorescraper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import pl.marek.scorescraper.link.ScrapeableLink;
import pl.marek.scorescraper.link.ScrapeableLinkRepository;
import pl.marek.scorescraper.link.ScrapeableLinkType;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Component
class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private static final Logger log = LogManager.getLogger(DataInitializer.class);
    private boolean setupIsDone = false;

    private final ScrapeableLinkRepository scrapeableLinkRepository;

    DataInitializer(ScrapeableLinkRepository scrapeableLinkRepository) {
        this.scrapeableLinkRepository = scrapeableLinkRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupIsDone) return;
        for (ScrapeableLink scrapeableLink : leagues) {
            createScrapeableLinkIfNotFound(scrapeableLink);
        }
        List<ScrapeableLink> clubResultsLinks = createClubLinks();
        for (ScrapeableLink scrapeableLink : clubResultsLinks) {
            createScrapeableLinkIfNotFound(scrapeableLink);
        }
        setupIsDone = true;
        log.info("Setup is done");
    }

    private ScrapeableLink createScrapeableLinkIfNotFound(ScrapeableLink scrapeableLinkToMake) {
        ScrapeableLink scrapeableLink = scrapeableLinkRepository.findByName(scrapeableLinkToMake.getName());
        if (scrapeableLink == null) {
            scrapeableLink = new ScrapeableLink(scrapeableLinkToMake.getName(), scrapeableLinkToMake.getScrapeableLinkType(), scrapeableLinkToMake.getLink());
        }
        scrapeableLink = scrapeableLinkRepository.save(scrapeableLink);
        return scrapeableLink;
    }

    //todo add source data of scrapable links for club results
    private List<ScrapeableLink> createClubLinks() {
        try {
            List<ScrapeableLink> clubLinks = new ArrayList<>();
            Path html = Paths.get("src/test/resources/htmlTests/league-table-html-example.txt");
            Document document = Jsoup.parse(html, "UTF-8", "https://betsapi.com/");
            Elements links = document.select("#overall > div:nth-child(1) > div > table> tbody >tr >td:nth-child(4)");
            for (Element link : links) {
                String row = link.select("a").attr("abs:href");
                String clubName = link.select("a").text();
                String clubResultsLink = row.substring(0, 21) + "e" + row.substring(21);
                ScrapeableLink scrapeableLink = new ScrapeableLink(clubName, ScrapeableLinkType.CLUB_RESULT, clubResultsLink);
                clubLinks.add(scrapeableLink);
            } return clubLinks;
        } catch(IOException e) {
            log.error(e);
        } return new ArrayList<>();
    }

    private final List<ScrapeableLink> leagues = List.of(
            new ScrapeableLink("Albania First Division", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/6915/Albania-First-Division")
            , new ScrapeableLink("Andorra Premier Division", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/12754/Andorra-Premier-Division")
            , new ScrapeableLink("Armenia Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/107/Armenia-Premier-League")
            , new ScrapeableLink("Austria Bundesliga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/166/Austria-Bundesliga")
            , new ScrapeableLink("Azerbaijan Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/860/Azerbaijan-Premier-League")
            , new ScrapeableLink("Belarus Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/79/Belarus-Premier-League")
            , new ScrapeableLink("Belgium First Division A", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/95/Belgium-First-Division-A")
            , new ScrapeableLink("Bosnia & Herzegovina Premier Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/181/Bosnia-%26-Herzegovina-Premier-Liga")
            , new ScrapeableLink("Bulgaria First League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/203/Bulgaria-First-League")
            , new ScrapeableLink("Croatia HNL", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/30613/Croatia-HNL")
            , new ScrapeableLink("Cyprus Division 1", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/183/Cyprus-Division-1")
            , new ScrapeableLink("Czech Republic First League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/178/Czech-Republic-First-League")
            , new ScrapeableLink("Denmark Superligaen", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/49/Denmark-Superligaen")
            , new ScrapeableLink("England Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/94/England-Premier-League")
            , new ScrapeableLink("Estonia Meistriliiga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/867/Estonia-Meistriliiga")
            , new ScrapeableLink("Faroe Islands Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/4228/Faroe-Islands-Premier-League")
            , new ScrapeableLink("Finland Veikkausliiga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/189/Finland-Veikkausliiga")
            , new ScrapeableLink("France Ligue 1", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/99/France-Ligue-1")
            , new ScrapeableLink("Georgia Erovnuli Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/942/Georgia-Erovnuli-Liga")
            , new ScrapeableLink("Germany Bundesliga I", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/123/Germany-Bundesliga-I")
            , new ScrapeableLink("Gibraltar National League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/18138/Gibraltar-National-League")
            , new ScrapeableLink("Greece Football League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/910/Greece-Super-League-1")
            , new ScrapeableLink("Hungary NB I", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/920/Hungary-NB-I")
            , new ScrapeableLink("Iceland Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/222/Iceland-Premier-League")
            , new ScrapeableLink("Israel Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/230/Israel-Premier-League")
            , new ScrapeableLink("Italy Serie A", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/199/Italy-Serie-A")
            , new ScrapeableLink("Netherlands Eredivisie", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/66/Netherlands-Eredivisie")
            , new ScrapeableLink("Norway Eliteserien", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/126/Norway-Eliteserien")
            , new ScrapeableLink("Poland Ekstraklasa", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/125/Poland-Ekstraklasa")
            , new ScrapeableLink("Portugal Primeira Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/172/Portugal-Primeira-Liga")
            , new ScrapeableLink("Republic of Ireland Premier Division", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/398/Republic-of-Ireland-Premier-Division")
            , new ScrapeableLink("Romania Liga I", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/173/Romania-Liga-I")
            , new ScrapeableLink("Russia Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/153/Russia-Premier-League")
            , new ScrapeableLink("Scotland Premiership", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/901/Scotland-Premiership")
            , new ScrapeableLink("Serbia Super Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/175/Serbia-Super-Liga")
            , new ScrapeableLink("Slovakia Super Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/218/Slovakia-Super-Liga")
            , new ScrapeableLink("Slovenia Prva Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/174/Slovenia-Prva-Liga")
            , new ScrapeableLink("Spain Primera Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/207/Spain-Primera-Liga")
            , new ScrapeableLink("Sweden Allsvenskan", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/100/Sweden-Allsvenskan")
            , new ScrapeableLink("Switzerland Super League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/74/Switzerland-Super-League")
            , new ScrapeableLink("Turkey Super Lig", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/215/Turkey-Super-Lig")
            , new ScrapeableLink("Ukraine Vyscha Liga", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/151/Ukraine-Vyscha-Liga")
            , new ScrapeableLink("Wales Premier League", ScrapeableLinkType.LEAGUE, "https://betsapi.com/lt/156/Wales-Premier-League")
    );
}

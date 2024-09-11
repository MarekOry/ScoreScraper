package pl.marek.scorescraper;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
class DataInitializer implements ApplicationListener<ContextRefreshedEvent> {
    private boolean setupIsDone = false;

    private final LeagueLinkRepository leagueLinkRepository;

    DataInitializer(LeagueLinkRepository leagueLinkRepository) {
        this.leagueLinkRepository = leagueLinkRepository;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (setupIsDone) return;
        for (LeagueLink leagueLink:leagues) {
            createLeagueLinkIfNotFound(leagueLink);
        }
        setupIsDone = true;
    }

    private LeagueLink createLeagueLinkIfNotFound(LeagueLink leagueLinkToMake) {
        LeagueLink leagueLink = leagueLinkRepository.findByName(leagueLinkToMake.getName());
        if (leagueLink == null) {
            leagueLink = new LeagueLink(leagueLinkToMake.getName(), leagueLinkToMake.getLink());
        }
        leagueLink = leagueLinkRepository.save(leagueLink);
        return leagueLink;
    }

    List<LeagueLink> leagues = List.of(
            new LeagueLink("Albania First Division", "https://betsapi.com/lt/6915/Albania-First-Division")
            ,new LeagueLink("Andorra Premier Division","https://betsapi.com/lt/12754/Andorra-Premier-Division")
            ,new LeagueLink("Armenia Premier League","https://betsapi.com/lt/107/Armenia-Premier-League")
            ,new LeagueLink("Austria Bundesliga","https://betsapi.com/lt/166/Austria-Bundesliga")
            ,new LeagueLink("Azerbaijan Premier League","https://betsapi.com/lt/860/Azerbaijan-Premier-League")
            ,new LeagueLink("Belarus Premier League","https://betsapi.com/lt/79/Belarus-Premier-League")
            ,new LeagueLink("Belgium First Division A","https://betsapi.com/lt/95/Belgium-First-Division-A")
            ,new LeagueLink("Bosnia & Herzegovina Premier Liga","https://betsapi.com/lt/181/Bosnia-%26-Herzegovina-Premier-Liga")
            ,new LeagueLink("Bulgaria First League","https://betsapi.com/lt/203/Bulgaria-First-League")
            ,new LeagueLink("Croatia HNL","https://betsapi.com/lt/30613/Croatia-HNL")
            ,new LeagueLink("Cyprus Division 1","https://betsapi.com/lt/183/Cyprus-Division-1")
            ,new LeagueLink("Czech Republic First League","https://betsapi.com/lt/178/Czech-Republic-First-League")
            ,new LeagueLink("Denmark Superligaen","https://betsapi.com/lt/49/Denmark-Superligaen")
            ,new LeagueLink("England Premier League","https://betsapi.com/lt/94/England-Premier-League")
            ,new LeagueLink("Estonia Meistriliiga","https://betsapi.com/lt/867/Estonia-Meistriliiga")
            ,new LeagueLink("Faroe Islands Premier League","https://betsapi.com/lt/4228/Faroe-Islands-Premier-League")
            ,new LeagueLink("Finland Veikkausliiga","https://betsapi.com/lt/189/Finland-Veikkausliiga")
            ,new LeagueLink("France Ligue 1","https://betsapi.com/lt/99/France-Ligue-1")
            ,new LeagueLink("Georgia Erovnuli Liga","https://betsapi.com/lt/942/Georgia-Erovnuli-Liga")
            ,new LeagueLink("Germany Bundesliga I","https://betsapi.com/lt/123/Germany-Bundesliga-I")
            ,new LeagueLink("Gibraltar National League","https://betsapi.com/lt/18138/Gibraltar-National-League")
            ,new LeagueLink("Greece Football League","https://betsapi.com/lt/910/Greece-Super-League-1")
            ,new LeagueLink("Hungary NB I","https://betsapi.com/lt/920/Hungary-NB-I")
            ,new LeagueLink("Iceland Premier League","https://betsapi.com/lt/222/Iceland-Premier-League")
            ,new LeagueLink("Israel Premier League","https://betsapi.com/lt/230/Israel-Premier-League")
            ,new LeagueLink("Italy Serie A","https://betsapi.com/lt/199/Italy-Serie-A")
            ,new LeagueLink("Netherlands Eredivisie","https://betsapi.com/lt/66/Netherlands-Eredivisie")
            ,new LeagueLink("Norway Eliteserien","https://betsapi.com/lt/126/Norway-Eliteserien")
            ,new LeagueLink("Poland Ekstraklasa","https://betsapi.com/lt/125/Poland-Ekstraklasa")
            ,new LeagueLink("Portugal Primeira Liga","https://betsapi.com/lt/172/Portugal-Primeira-Liga")
            ,new LeagueLink("Republic of Ireland Premier Division","https://betsapi.com/lt/398/Republic-of-Ireland-Premier-Division")
            ,new LeagueLink("Romania Liga I","https://betsapi.com/lt/173/Romania-Liga-I")
            ,new LeagueLink("Russia Premier League","https://betsapi.com/lt/153/Russia-Premier-League")
            ,new LeagueLink("Scotland Premiership","https://betsapi.com/lt/901/Scotland-Premiership")
            ,new LeagueLink("Serbia Super Liga","https://betsapi.com/lt/175/Serbia-Super-Liga")
            ,new LeagueLink("Slovakia Super Liga","https://betsapi.com/lt/218/Slovakia-Super-Liga")
            ,new LeagueLink("Slovenia Prva Liga","https://betsapi.com/lt/174/Slovenia-Prva-Liga")
            ,new LeagueLink("Spain Primera Liga","https://betsapi.com/lt/207/Spain-Primera-Liga")
            ,new LeagueLink("Sweden Allsvenskan","https://betsapi.com/lt/100/Sweden-Allsvenskan")
            ,new LeagueLink("Switzerland Super League","https://betsapi.com/lt/74/Switzerland-Super-League")
            ,new LeagueLink("Turkey Super Lig","https://betsapi.com/lt/215/Turkey-Super-Lig")
            ,new LeagueLink("Ukraine Vyscha Liga","https://betsapi.com/lt/151/Ukraine-Vyscha-Liga")
            ,new LeagueLink("Wales Premier League","https://betsapi.com/lt/156/Wales-Premier-League")
    );
}

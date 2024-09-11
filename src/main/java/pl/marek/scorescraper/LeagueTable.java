package pl.marek.scorescraper;

import java.util.List;

class LeagueTable extends ScrapeResult {
    private final String leagueName;
    private final List<LeagueClubPosition> leagueClubPositions;

    public LeagueTable(String leagueName, List<LeagueClubPosition> leagueClubPositions) {
        this.leagueName = leagueName;
        this.leagueClubPositions = leagueClubPositions;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public List<LeagueClubPosition> getLeagueClubPositions() {
        return leagueClubPositions;
    }

    @Override
    public String toString() {
        return "LeagueTable{" +
                "leagueName='" + leagueName + '\'' +
                ", clubPositions=" + leagueClubPositions +
                '}';
    }
}

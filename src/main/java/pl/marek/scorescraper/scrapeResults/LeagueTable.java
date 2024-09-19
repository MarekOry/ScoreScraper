package pl.marek.scorescraper.scrapeResults;

import java.util.List;
import java.util.Objects;

public record LeagueTable(String leagueName, List<LeagueClubPosition> leagueClubPositions) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueTable that = (LeagueTable) o;
        return Objects.equals(leagueName, that.leagueName) && Objects.equals(leagueClubPositions, that.leagueClubPositions);
    }

    @Override
    public String toString() {
        return "LeagueTable{" +
                "leagueName='" + leagueName + '\'' +
                ", clubPositions=" + leagueClubPositions +
                '}';
    }
}

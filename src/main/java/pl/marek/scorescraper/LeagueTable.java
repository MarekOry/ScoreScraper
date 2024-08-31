package pl.marek.scorescraper;

import java.util.List;

class LeagueTable {
    private final String leagueName;
    private final List<ClubPosition> clubPositions;

    public LeagueTable(String leagueName, List<ClubPosition> clubPositions) {
        this.leagueName = leagueName;
        this.clubPositions = clubPositions;
    }

    @Override
    public String toString() {
        return "LeagueTable{" +
                "leagueName='" + leagueName + '\'' +
                ", clubPositions=" + clubPositions +
                '}';
    }
}

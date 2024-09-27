package pl.marek.scorescraper.scrapeResults;

import java.util.List;
import java.util.Objects;

public record ClubSquad(String clubName, List<ClubSquadPlayer> clubSquadPlayer) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubSquad clubSquad = (ClubSquad) o;
        return Objects.equals(clubName, clubSquad.clubName) && Objects.equals(clubSquadPlayer, clubSquad.clubSquadPlayer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, clubSquadPlayer);
    }

    @Override
    public String toString() {
        return "ClubSquad{" +
                "clubName='" + clubName + '\'' +
                ", clubSquadPlayer=" + clubSquadPlayer +
                '}';
    }
}

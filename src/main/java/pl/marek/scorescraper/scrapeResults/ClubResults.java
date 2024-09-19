package pl.marek.scorescraper.scrapeResults;

import java.util.List;
import java.util.Objects;

public record ClubResults(String clubName, List<ClubMatchResult> clubMatchResults) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubResults that = (ClubResults) o;
        return Objects.equals(clubName, that.clubName) && Objects.equals(clubMatchResults, that.clubMatchResults);
    }

    @Override
    public String toString() {
        return "ClubResults{" +
                "clubName='" + clubName + '\'' +
                ", clubMatchResults=" + clubMatchResults +
                '}';
    }
}

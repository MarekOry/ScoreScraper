package pl.marek.scorescraper.scrapeResults;

import java.util.List;

public class ClubResults{
    private final String clubName;
    private final List<ClubMatchResult> clubMatchResults;

    public ClubResults(String clubName, List<ClubMatchResult> clubMatchResults) {
        this.clubName = clubName;
        this.clubMatchResults = clubMatchResults;
    }

    public String getClubName() {
        return clubName;
    }

    public List<ClubMatchResult> getClubMatchResults() {
        return clubMatchResults;
    }

    @Override
    public String toString() {
        return "ClubResults{" +
                "clubName='" + clubName + '\'' +
                ", clubMatchResults=" + clubMatchResults +
                '}';
    }
}

package pl.marek.scorescraper;

import java.util.List;

class ClubResults extends ScrapeResult {
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

package pl.marek.scorescraper.scrapeResults;

import java.time.Instant;
import java.util.Objects;

public class ClubMatchResult {
    private final String league;
    private final Instant dateOfMatch;
    private final String round;
    private final String teamsPlaying;
    private final String result;

    private ClubMatchResult(String league, Instant dateOfMatch, String round, String teamsPlaying, String result) {
        this.league = league;
        this.dateOfMatch = dateOfMatch;
        this.round = round;
        this.teamsPlaying = teamsPlaying;
        this.result = result;
    }

    public static class ClubResultBuilder {
        private String league;
        private Instant dateOfMatch;
        private String round;
        private String teamsPlaying;
        private String result;

        public ClubResultBuilder setLeague(String league) {
            this.league = league;
            return this;
        }

        public ClubResultBuilder setDateOfMatch(Instant dateOfMatch) {
            this.dateOfMatch = dateOfMatch;
            return this;
        }

        public ClubResultBuilder setRound(String round) {
            this.round = round;
            return this;
        }

        public ClubResultBuilder setTeamsPlaying(String teamsPlaying) {
            this.teamsPlaying = teamsPlaying;
            return this;
        }

        public ClubResultBuilder setResult(String result) {
            this.result = result;
            return this;
        }

        public ClubMatchResult build() {
            return new ClubMatchResult(league, dateOfMatch, round, teamsPlaying, result);
        }
    }

    public String getLeague() {
        return league;
    }

    public Instant getDateOfMatch() {
        return dateOfMatch;
    }

    public String getRound() {
        return round;
    }

    public String getTeamsPlaying() {
        return teamsPlaying;
    }

    public String getResult() {
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubMatchResult that = (ClubMatchResult) o;
        return Objects.equals(league, that.league) && Objects.equals(dateOfMatch, that.dateOfMatch) && Objects.equals(round, that.round) && Objects.equals(teamsPlaying, that.teamsPlaying) && Objects.equals(result, that.result);
    }

    @Override
    public int hashCode() {
        return Objects.hash(league, dateOfMatch, round, teamsPlaying, result);
    }

    @Override
    public String toString() {
        return "ClubResult{" +
                "league='" + league + '\'' +
                ", dateOfMatch=" + dateOfMatch +
                ", round='" + round + '\'' +
                ", teamsPlaying='" + teamsPlaying + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

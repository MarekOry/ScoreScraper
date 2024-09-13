package pl.marek.scorescraper.scrapeResults;

import java.util.Objects;

public class LeagueClubPosition {
    private final String clubName;
    private final Integer place;
    private final Integer wins;
    private final Integer draws;
    private final Integer loses;
    private final String goals;
    private final Integer points;

    private LeagueClubPosition(String clubName, Integer place, Integer wins, Integer draws, Integer loses, String goals, Integer points) {
        this.clubName = clubName;
        this.place = place;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.goals = goals;
        this.points = points;
    }

    public static class ClubPositionBuilder {
        private String clubName;
        private Integer place;
        private Integer wins;
        private Integer draws;
        private Integer loses;
        private String goals;
        private Integer points;

        public ClubPositionBuilder setClubName(String clubName) {
            this.clubName = clubName;
            return this;
        }

        public ClubPositionBuilder setPlace(Integer place) {
            this.place = place;
            return this;
        }

        public ClubPositionBuilder setWins(Integer wins) {
            this.wins = wins;
            return this;
        }

        public ClubPositionBuilder setDraws(Integer draws) {
            this.draws = draws;
            return this;
        }

        public ClubPositionBuilder setLoses(Integer loses) {
            this.loses = loses;
            return this;
        }

        public ClubPositionBuilder setGoals(String goals) {
            this.goals = goals;
            return this;
        }

        public ClubPositionBuilder setPoints(Integer points) {
            this.points = points;
            return this;
        }

        public LeagueClubPosition build(){
            return new LeagueClubPosition(clubName, place, wins, draws, loses, goals, points);
        }
    }

    public String getClubName() {
        return clubName;
    }

    public Integer getPlace() {
        return place;
    }

    public Integer getWins() {
        return wins;
    }

    public Integer getDraws() {
        return draws;
    }

    public Integer getLoses() {
        return loses;
    }

    public String getGoals() {
        return goals;
    }

    public Integer getPoints() {
        return points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueClubPosition that = (LeagueClubPosition) o;
        return Objects.equals(clubName, that.clubName) && Objects.equals(place, that.place) && Objects.equals(wins, that.wins) && Objects.equals(draws, that.draws) && Objects.equals(loses, that.loses) && Objects.equals(goals, that.goals) && Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clubName, place, wins, draws, loses, goals, points);
    }

    @Override
    public String toString() {
        return "ClubPosition{" +
                "clubName='" + clubName + '\'' +
                ", place=" + place +
                ", wins=" + wins +
                ", draws=" + draws +
                ", loses=" + loses +
                ", goals='" + goals + '\'' +
                ", points=" + points +
                '}';
    }
}

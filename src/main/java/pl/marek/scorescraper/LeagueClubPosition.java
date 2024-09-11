package pl.marek.scorescraper;

class LeagueClubPosition {
    private final String clubName;
    private final Short place;
    private final Short wins;
    private final Short draws;
    private final Short loses;
    private final String goals;
    private final Short points;

    private LeagueClubPosition(String clubName, Short place, Short wins, Short draws, Short loses, String goals, Short points) {
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
        private Short place;
        private Short wins;
        private Short draws;
        private Short loses;
        private String goals;
        private Short points;

        public ClubPositionBuilder setClubName(String clubName) {
            this.clubName = clubName;
            return this;
        }

        public ClubPositionBuilder setPlace(Short place) {
            this.place = place;
            return this;
        }

        public ClubPositionBuilder setWins(Short wins) {
            this.wins = wins;
            return this;
        }

        public ClubPositionBuilder setDraws(Short draws) {
            this.draws = draws;
            return this;
        }

        public ClubPositionBuilder setLoses(Short loses) {
            this.loses = loses;
            return this;
        }

        public ClubPositionBuilder setGoals(String goals) {
            this.goals = goals;
            return this;
        }

        public ClubPositionBuilder setPoints(Short points) {
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

    public Short getPlace() {
        return place;
    }

    public Short getWins() {
        return wins;
    }

    public Short getDraws() {
        return draws;
    }

    public Short getLoses() {
        return loses;
    }

    public String getGoals() {
        return goals;
    }

    public Short getPoints() {
        return points;
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

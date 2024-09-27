package pl.marek.scorescraper.scrapeResults;

import java.time.LocalDate;
import java.util.Objects;

public class ClubSquadPlayer {
    private final String number;
    private final String playerName;
    private final LocalDate birthDate;
    private final Integer age;
    private final String countryShort;
    private final String position;
    private final Integer height;
    private final Integer weight;
    private final String preferredFoot;

    private ClubSquadPlayer(String number, String playerName, LocalDate birthDate,
                            Integer age, String countryShort, String position,
                            Integer height, Integer weight, String preferredFoot) {
        this.number = number;
        this.playerName = playerName;
        this.birthDate = birthDate;
        this.age = age;
        this.countryShort = countryShort;
        this.position = position;
        this.height = height;
        this.weight = weight;
        this.preferredFoot = preferredFoot;
    }

    public static class ClubSquadPlayerBuilder {
        private String number;
        private String playerName;
        private LocalDate birthDate;
        private Integer age;
        private String countryShort;
        private String position;
        private Integer height;
        private Integer weight;
        private String preferredFoot;

        public ClubSquadPlayerBuilder setNumber(String number) {
            this.number = number;
            return this;
        }

        public ClubSquadPlayerBuilder setPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public ClubSquadPlayerBuilder setBirthDate(LocalDate birthDate) {
            this.birthDate = birthDate;
            return this;
        }

        public ClubSquadPlayerBuilder setAge(Integer age) {
            this.age = age;
            return this;
        }

        public ClubSquadPlayerBuilder setCountryShort(String countryShort) {
            this.countryShort = countryShort;
            return this;
        }

        public ClubSquadPlayerBuilder setPosition(String position) {
            this.position = position;
            return this;
        }

        public ClubSquadPlayerBuilder setHeight(Integer height) {
            this.height = height;
            return this;
        }

        public ClubSquadPlayerBuilder setWeight(Integer weight) {
            this.weight = weight;
            return this;
        }

        public ClubSquadPlayerBuilder setPreferredFoot(String preferredFoot) {
            this.preferredFoot = preferredFoot;
            return this;
        }

        public ClubSquadPlayer build() {
            return new ClubSquadPlayer(number, playerName, birthDate, age, countryShort, position, height, weight, preferredFoot);
        }
    }

    public String getNumber() {
        return number;
    }

    public String getPlayerName() {
        return playerName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public Integer getAge() {
        return age;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public String getPosition() {
        return position;
    }

    public Integer getHeight() {
        return height;
    }

    public Integer getWeight() {
        return weight;
    }

    public String getPreferredFoot() {
        return preferredFoot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClubSquadPlayer that = (ClubSquadPlayer) o;
        return Objects.equals(number, that.number) && Objects.equals(playerName, that.playerName) && Objects.equals(birthDate, that.birthDate) && Objects.equals(age, that.age) && Objects.equals(countryShort, that.countryShort) && Objects.equals(position, that.position) && Objects.equals(height, that.height) && Objects.equals(weight, that.weight) && Objects.equals(preferredFoot, that.preferredFoot);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, playerName, birthDate, age, countryShort, position, height, weight, preferredFoot);
    }

    @Override
    public String toString() {
        return "ClubSquadPlayer{" +
                "number='" + number + '\'' +
                ", playerName='" + playerName + '\'' +
                ", birthDate=" + birthDate +
                ", age=" + age +
                ", countryShort='" + countryShort + '\'' +
                ", position='" + position + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", preferredFoot='" + preferredFoot + '\'' +
                '}';
    }
}

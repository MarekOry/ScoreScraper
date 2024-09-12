package pl.marek.scorescraper;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public
class LeagueLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String link;

    public LeagueLink(String name, String link) {
        this.name = name;
        this.link = link;
    }

    public LeagueLink() {
    }

    public static LeagueBuilder builder() {
        return new LeagueBuilder();
    }

    public String getName() {
        return name;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeagueLink leagueLink = (LeagueLink) o;
        return Objects.equals(name, leagueLink.name) && Objects.equals(link, leagueLink.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, link);
    }

    @Override
    public String toString() {
        return "League{" +
                "name='" + name + '\'' +
                ", link='" + link + '\'' +
                '}';
    }

    public static class LeagueBuilder {
        private String name;
        private String link;

        LeagueBuilder() {
        }

        public LeagueBuilder name(String name) {
            this.name = name;
            return this;
        }

        public LeagueBuilder link(String link) {
            this.link = link;
            return this;
        }

        public LeagueLink build() {
            return new LeagueLink(this.name, this.link);
        }

        public String toString() {
            return "League.LeagueBuilder(name=" + this.name + ", link=" + this.link + ")";
        }
    }
}
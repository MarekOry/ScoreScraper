package pl.marek.scorescraper.link;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class ScrapeableLink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private ScrapeableLinkType scrapeableLinkType;

    @Column
    private String link;

    public ScrapeableLink(String name, ScrapeableLinkType scrapeableLinkType, String link) {
        this.name = name;
        this.scrapeableLinkType = scrapeableLinkType;
        this.link = link;
    }

    public ScrapeableLink() {
    }

    public static ScrapeableLinkBuilder builder() {
        return new ScrapeableLinkBuilder();
    }


    public String getName() {
        return name;
    }

    public ScrapeableLinkType getScrapeableLinkType() {
        return scrapeableLinkType;
    }

    public String getLink() {
        return link;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setScrapeableLinkType(ScrapeableLinkType scrapeableLinkType) {
        this.scrapeableLinkType = scrapeableLinkType;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScrapeableLink that = (ScrapeableLink) o;
        return Objects.equals(id, that.id) && Objects.equals(name, that.name) && scrapeableLinkType == that.scrapeableLinkType && Objects.equals(link, that.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, scrapeableLinkType, link);
    }

    @Override
    public String toString() {
        return "ScrapeableLink{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", scrapeableLinkType=" + scrapeableLinkType +
                ", link='" + link + '\'' +
                '}';
    }

    public static class ScrapeableLinkBuilder {
        private String name;
        private ScrapeableLinkType scrapeableLinkType;
        private String link;

        ScrapeableLinkBuilder() {
        }

        public ScrapeableLinkBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ScrapeableLinkBuilder scrapeableLinkType(ScrapeableLinkType scrapeableLinkType) {
            this.scrapeableLinkType = scrapeableLinkType;
            return this;
        }

        public ScrapeableLinkBuilder link(String link) {
            this.link = link;
            return this;
        }

        public ScrapeableLink build() {
            return new ScrapeableLink(this.name, this.scrapeableLinkType, this.link);
        }

        public String toString() {
            return "ScrapeableLink.ScrapeableLinkBuilder(name=" + this.name + ", scrapeableLinkType=" + this.scrapeableLinkType + ", link=" + this.link + ")";
        }
    }
}
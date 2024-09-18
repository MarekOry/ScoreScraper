package pl.marek.scorescraper.link;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.REQUIRES_NEW, isolation =  Isolation.READ_COMMITTED)
public interface ScrapeableLinkRepository extends JpaRepository<ScrapeableLink, Long> {
    ScrapeableLink findByName(String name);
    List<ScrapeableLink> findByScrapeableLinkType(ScrapeableLinkType scrapeableLinkType);
}

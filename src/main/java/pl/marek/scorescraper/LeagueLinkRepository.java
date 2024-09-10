package pl.marek.scorescraper;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(propagation = Propagation.MANDATORY, isolation =  Isolation.READ_COMMITTED)
public interface LeagueLinkRepository extends JpaRepository<LeagueLink, Long> {
    LeagueLink findByName(String name);
}

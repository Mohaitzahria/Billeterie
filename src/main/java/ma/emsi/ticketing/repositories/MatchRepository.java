package ma.emsi.ticketing.repositories;

import ma.emsi.ticketing.entities.Match;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchRepository extends JpaRepository<Match,Integer> {
    Page<Match> findAll(Pageable pageable);
}

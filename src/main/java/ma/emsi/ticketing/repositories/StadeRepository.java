package ma.emsi.ticketing.repositories;

import ma.emsi.ticketing.entities.Stade;
import ma.emsi.ticketing.entities.Ticket;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StadeRepository extends JpaRepository<Stade,Integer> {
    Page<Stade> findByNameContains(String name, Pageable pageable);
}

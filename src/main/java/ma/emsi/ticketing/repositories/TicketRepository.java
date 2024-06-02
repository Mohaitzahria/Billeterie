package ma.emsi.ticketing.repositories;

import ma.emsi.ticketing.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Integer> {
    // No custom query needed for now
}

package ma.emsi.ticketing.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idTicket;
    private double prix;
    private String userReference;
    @ManyToOne
    private Match match;
}
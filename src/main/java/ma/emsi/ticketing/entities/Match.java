package ma.emsi.ticketing.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "matches") // Changer le nom de la table pour éviter le mot-clé réservé "match"
@Data
public class Match {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDateTime date;
    private String equipeA;
    private String equipeB;
    @ManyToOne
    private Stade stade;
}

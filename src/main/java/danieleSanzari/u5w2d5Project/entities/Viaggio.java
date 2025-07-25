package danieleSanzari.u5w2d5Project.entities;

import danieleSanzari.u5w2d5Project.enums.Stato;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "viaggi")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Viaggio {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String destinazione;
    private String data;
    @Enumerated(EnumType.STRING)
    private Stato stato;

    public Viaggio(String destinazione, String data, Stato stato) {
        this.destinazione = destinazione;
        this.data = data;
        this.stato = stato;
    }
}

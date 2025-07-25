package danieleSanzari.u5w2d5Project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "prenotazioni")
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "viaggio_id")
    private Viaggio viaggio;
    @ManyToOne
    @JoinColumn(name = "dipendente_id")
    private Dipendente dipendente;
    @Column(name = "data_prenotazione")
    private String dataPrenotazione;
    @Column(name = "note_prenotazione")
    private String notePrenotazione;

    public Prenotazione(Viaggio viaggio, Dipendente dipendente, String dataPrenotazione, String notePrenotazione) {
        this.viaggio = viaggio;
        this.dipendente = dipendente;
        this.dataPrenotazione = dataPrenotazione;
        this.notePrenotazione = notePrenotazione;
    }
}

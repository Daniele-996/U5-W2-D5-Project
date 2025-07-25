package danieleSanzari.u5w2d5Project.repositories;

import danieleSanzari.u5w2d5Project.entities.Prenotazione;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PrenotazioniRepo extends JpaRepository<Prenotazione, Integer> {
    boolean existsByDipendenteIdAndDataPrenotazione(int dipendenteId, String dataPrenotazione);

    List<Prenotazione> findByDipendenteId(int dipendenteId);

    List<Prenotazione> findByViaggioId(int viaggioId);
}

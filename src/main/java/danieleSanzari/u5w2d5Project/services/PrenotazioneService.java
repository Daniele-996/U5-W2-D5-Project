package danieleSanzari.u5w2d5Project.services;

import danieleSanzari.u5w2d5Project.entities.Dipendente;
import danieleSanzari.u5w2d5Project.entities.Prenotazione;
import danieleSanzari.u5w2d5Project.entities.Viaggio;
import danieleSanzari.u5w2d5Project.exceptions.BadRequestException;
import danieleSanzari.u5w2d5Project.exceptions.NotFoundException;
import danieleSanzari.u5w2d5Project.repositories.DipendenteRepo;
import danieleSanzari.u5w2d5Project.repositories.PrenotazioniRepo;
import danieleSanzari.u5w2d5Project.repositories.ViaggioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioniRepo prenotazioniRepo;

    @Autowired
    private DipendenteRepo dipendenteRepo;

    @Autowired
    private ViaggioRepo viaggioRepo;

    public List<Prenotazione> getAll() {
        return prenotazioniRepo.findAll();
    }

    public Prenotazione getById(int id) {
        return prenotazioniRepo.findById(id)
                .orElseThrow(() -> new NotFoundException(id));
    }

    public Prenotazione create(Prenotazione prenotazione) {
        int dipendenteId = prenotazione.getDipendente().getId();
        String data = prenotazione.getDataPrenotazione();

        if (prenotazioniRepo.existsByDipendenteIdAndDataPrenotazione(dipendenteId, data)) {
            throw new BadRequestException("Il dipendente ha già una prenotazione per la data: " + data);
        }

        Dipendente dipendente = dipendenteRepo.findById(dipendenteId)
                .orElseThrow(() -> new NotFoundException(dipendenteId));

        int viaggioId = prenotazione.getViaggio().getId();
        Viaggio viaggio = viaggioRepo.findById(viaggioId)
                .orElseThrow(() -> new NotFoundException(viaggioId));

        prenotazione.setDipendente(dipendente);
        prenotazione.setViaggio(viaggio);

        return prenotazioniRepo.save(prenotazione);
    }

    public Prenotazione update(int id, Prenotazione prenotazione) {
        Prenotazione found = this.getById(id);

        found.setDataPrenotazione(prenotazione.getDataPrenotazione());
        found.setNotePrenotazione(prenotazione.getNotePrenotazione());

        Dipendente dipendente = dipendenteRepo.findById(prenotazione.getDipendente().getId())
                .orElseThrow(() -> new NotFoundException(prenotazione.getDipendente().getId()));

        Viaggio viaggio = viaggioRepo.findById(prenotazione.getViaggio().getId())
                .orElseThrow(() -> new NotFoundException(prenotazione.getViaggio().getId()));

        found.setDipendente(dipendente);
        found.setViaggio(viaggio);

        return prenotazioniRepo.save(found);
    }

    public void delete(int id) {
        Prenotazione found = this.getById(id);
        prenotazioniRepo.delete(found);
    }
}

package danieleSanzari.u5w2d5Project.services;

import danieleSanzari.u5w2d5Project.entities.Viaggio;
import danieleSanzari.u5w2d5Project.enums.Stato;
import danieleSanzari.u5w2d5Project.exceptions.NotFoundException;
import danieleSanzari.u5w2d5Project.repositories.ViaggioRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ViaggioService {
    @Autowired
    private ViaggioRepo viaggioRepo;

    public List<Viaggio> getAll() {
        return viaggioRepo.findAll();
    }

    public Viaggio getById(int id) {
        return viaggioRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Viaggio save(Viaggio viaggio) {
        return viaggioRepo.save(viaggio);
    }

    public Viaggio searchAndUpdate(int id, Viaggio viaggio) {
        Viaggio found = this.getById(id);
        found.setDestinazione(viaggio.getDestinazione());
        found.setData(viaggio.getData());
        found.setStato(viaggio.getStato());
        return viaggioRepo.save(found);
    }

    public void searchAndDelete(int id) {
        Viaggio found = this.getById(id);
        viaggioRepo.delete(found);
    }

    public Viaggio aggiornaStato(int id, Stato newStato) {
        Viaggio viaggio = this.getById(id);
        viaggio.setStato(newStato);
        return viaggioRepo.save(viaggio);
    }
}

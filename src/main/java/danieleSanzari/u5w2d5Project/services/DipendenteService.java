package danieleSanzari.u5w2d5Project.services;

import danieleSanzari.u5w2d5Project.entities.Dipendente;
import danieleSanzari.u5w2d5Project.exceptions.NotFoundException;
import danieleSanzari.u5w2d5Project.repositories.DipendenteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DipendenteService {
    @Autowired
    private DipendenteRepo dipendenteRepo;

    public List<Dipendente> getAll() {
        return dipendenteRepo.findAll();
    }

    public Dipendente getById(int id) {
        return dipendenteRepo.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public Dipendente save(Dipendente dipendente) {
        return dipendenteRepo.save(dipendente);
    }

    public Dipendente searchAndUpdate(int id, Dipendente dipendente) {
        Dipendente found = this.getById(id);
        found.setUsername(dipendente.getUsername());
        found.setNome(dipendente.getNome());
        found.setCognome(dipendente.getCognome());
        found.setEmail(dipendente.getEmail());
        return dipendenteRepo.save(found);
    }

    public void searchAndDelete(int id) {
        Dipendente found = this.getById(id);
        dipendenteRepo.delete(found);
    }
}

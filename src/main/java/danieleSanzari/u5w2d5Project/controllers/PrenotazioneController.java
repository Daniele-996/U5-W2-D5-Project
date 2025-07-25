package danieleSanzari.u5w2d5Project.controllers;

import danieleSanzari.u5w2d5Project.entities.Dipendente;
import danieleSanzari.u5w2d5Project.entities.Prenotazione;
import danieleSanzari.u5w2d5Project.entities.Viaggio;
import danieleSanzari.u5w2d5Project.exceptions.ValidationException;
import danieleSanzari.u5w2d5Project.payloads.PrenotazioneDTO;
import danieleSanzari.u5w2d5Project.services.DipendenteService;
import danieleSanzari.u5w2d5Project.services.PrenotazioneService;
import danieleSanzari.u5w2d5Project.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @Autowired
    private DipendenteService dipendenteService;

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Prenotazione> getAll() {
        return prenotazioneService.getAll();
    }

    @GetMapping("/{id}")
    public Prenotazione getById(@PathVariable int id) {
        return prenotazioneService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione create(@RequestBody @Validated PrenotazioneDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(err -> err.getDefaultMessage()).toList());
        }

        Dipendente dipendente = dipendenteService.getById(payload.dipendenteId());
        Viaggio viaggio = viaggioService.getById(payload.viaggioId());

        Prenotazione nuova = new Prenotazione(viaggio, dipendente, payload.dataPrenotazione(), payload.notePrenotazione()
        );
        return prenotazioneService.create(nuova);
    }

    @PutMapping("/{id}")
    public Prenotazione update(@PathVariable int id, @RequestBody @Validated PrenotazioneDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(err -> err.getDefaultMessage()).toList());
        }

        Dipendente dipendente = dipendenteService.getById(payload.dipendenteId());
        Viaggio viaggio = viaggioService.getById(payload.viaggioId());

        Prenotazione aggiornata = new Prenotazione(viaggio, dipendente, payload.dataPrenotazione(), payload.notePrenotazione()
        );
        return prenotazioneService.update(id, aggiornata);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        prenotazioneService.delete(id);
    }
}

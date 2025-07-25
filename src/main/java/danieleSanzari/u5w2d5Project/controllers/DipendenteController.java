package danieleSanzari.u5w2d5Project.controllers;

import danieleSanzari.u5w2d5Project.entities.Dipendente;
import danieleSanzari.u5w2d5Project.exceptions.ValidationException;
import danieleSanzari.u5w2d5Project.payloads.DipendenteDTO;
import danieleSanzari.u5w2d5Project.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {
    @Autowired
    private DipendenteService dipendenteService;

    @GetMapping
    public List<Dipendente> getDipendenti() {
        return this.dipendenteService.getAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente save(@RequestBody @Validated DipendenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        } else {
            Dipendente newDipendente = this.dipendenteService.save(new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email()));
            return newDipendente;
        }
    }

    @GetMapping("/{id}")
    public Dipendente getById(@PathVariable int id) {
        return this.dipendenteService.getById(id);
    }

    @PutMapping("/{id}")
    public Dipendente update(@PathVariable int id, @RequestBody @Validated DipendenteDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream().map(fieldError -> fieldError.getDefaultMessage()).toList());
        }

        Dipendente updatedDipendente = new Dipendente(payload.username(), payload.nome(), payload.cognome(), payload.email());
        return this.dipendenteService.searchAndUpdate(id, updatedDipendente);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        this.dipendenteService.searchAndDelete(id);
    }
}

package danieleSanzari.u5w2d5Project.controllers;

import danieleSanzari.u5w2d5Project.entities.Viaggio;
import danieleSanzari.u5w2d5Project.exceptions.ValidationException;
import danieleSanzari.u5w2d5Project.payloads.ViaggioDTO;
import danieleSanzari.u5w2d5Project.payloads.ViaggioStatoDTO;
import danieleSanzari.u5w2d5Project.services.ViaggioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/viaggi")
public class ViaggioController {

    @Autowired
    private ViaggioService viaggioService;

    @GetMapping
    public List<Viaggio> getAll() {
        return viaggioService.getAll();
    }

    @GetMapping("/{id}")
    public Viaggio getById(@PathVariable int id) {
        return viaggioService.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggio create(@RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(err -> err.getDefaultMessage()).toList());
        }

        Viaggio viaggio = new Viaggio(payload.destinazione(), payload.data(), payload.stato());
        return viaggioService.save(viaggio);
    }

    @PutMapping("/{id}")
    public Viaggio update(@PathVariable int id, @RequestBody @Validated ViaggioDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(err -> err.getDefaultMessage()).toList());
        }

        Viaggio viaggio = new Viaggio(payload.destinazione(), payload.data(), payload.stato());
        return viaggioService.searchAndUpdate(id, viaggio);
    }

    @PatchMapping("/{id}/stato")
    public Viaggio updateStato(@PathVariable int id, @RequestBody @Validated ViaggioStatoDTO payload, BindingResult validationResult) {
        if (validationResult.hasErrors()) {
            throw new ValidationException(validationResult.getFieldErrors().stream()
                    .map(err -> err.getDefaultMessage()).toList());
        }

        return viaggioService.aggiornaStato(id, payload.stato());
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable int id) {
        viaggioService.searchAndDelete(id);
    }
}

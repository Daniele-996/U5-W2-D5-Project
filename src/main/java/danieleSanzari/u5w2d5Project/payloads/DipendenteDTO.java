package danieleSanzari.u5w2d5Project.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record DipendenteDTO(
        @NotEmpty(message = "L'username è obbligatorio")
        @Size(min = 3, max = 20, message = "L'username deve avere min 2 caratteri massimo 20!")
        String username,
        @NotEmpty(message = "Il nome è obbligatorio")
        @Size(min = 3, max = 20, message = "Il nome deve avere min 2 caratteri massimo 20!")
        String nome,
        @NotEmpty(message = "Il cognome è obbligatorio")
        @Size(min = 3, max = 20, message = "Il cognome deve avere min 2 caratteri massimo 20!")
        String cognome,
        @NotEmpty(message = "L'indirizzo email è obbligatorio!")
        @Email(message = "La mail non è del formato corretto!")
        String email
) {
}

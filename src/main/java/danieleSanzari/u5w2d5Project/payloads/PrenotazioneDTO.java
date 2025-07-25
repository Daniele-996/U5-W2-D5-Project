package danieleSanzari.u5w2d5Project.payloads;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PrenotazioneDTO(
        @NotNull(message = "Id viaggio obbligatorio")
        Integer viaggioId,

        @NotNull(message = "Id dipendente obbligatorio")
        Integer dipendenteId,

        @NotNull(message = "Data prenotazione obbligatoria")
        String dataPrenotazione,

        @Size(max = 255, message = "Le note non possono superare 255 caratteri")
        String notePrenotazione
) {
}

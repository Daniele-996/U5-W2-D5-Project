package danieleSanzari.u5w2d5Project.payloads;

import danieleSanzari.u5w2d5Project.enums.Stato;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record ViaggioDTO(
        @NotEmpty(message = "La destinazione è obbligatoria")
        @Size(min = 2, max = 50, message = "La destinazione deve avere tra 2 e 50 caratteri")
        String destinazione,

        @NotEmpty(message = "La data è obbligatoria")
        @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "La data deve essere nel formato YYYY-MM-DD")
        String data,

        @NotNull(message = "Lo stato è obbligatorio")
        Stato stato
) {
}

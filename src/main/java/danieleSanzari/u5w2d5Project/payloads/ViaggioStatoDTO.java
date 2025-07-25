package danieleSanzari.u5w2d5Project.payloads;

import danieleSanzari.u5w2d5Project.enums.Stato;
import jakarta.validation.constraints.NotNull;

public record ViaggioStatoDTO(
        @NotNull(message = "Lo stato è obbligatorio")
        Stato stato
) {
}

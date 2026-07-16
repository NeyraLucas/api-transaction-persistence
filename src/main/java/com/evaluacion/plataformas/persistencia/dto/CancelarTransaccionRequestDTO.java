package com.evaluacion.plataformas.persistencia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(description = "Solicitud para cancelar una transaccion")
public record CancelarTransaccionRequestDTO(

        @NotBlank
        @Schema(description = "Identificador de la transaccion", example = "2376")
        String id,

        @NotBlank
        @Schema(description = "Referencia aleatoria de 6 digitos", example = "262737")
        String referencia,

        @Schema(description = "Nuevo estatus solicitado", example = "cancelar")
        String estatus
) {
}

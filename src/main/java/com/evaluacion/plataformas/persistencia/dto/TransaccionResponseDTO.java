package com.evaluacion.plataformas.persistencia.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resultado de la transaccion cancelada")
public record TransaccionResponseDTO(

        @Schema(description = "Identificador de la transaccion", example = "2376")
        String id,

        @Schema(description = "Estatus de la transaccion", example = "Cancelada")
        String estatus,

        @Schema(description = "Referencia aleatoria de 6 digitos", example = "262737")
        String referencia,

        @Schema(description = "Tipo de operacion", example = "venta")
        String operacion
) {
}

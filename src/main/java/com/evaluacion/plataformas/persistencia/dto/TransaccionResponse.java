package com.evaluacion.plataformas.persistencia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Resultado de la transaccion persistida")
public class TransaccionResponse {

    @Schema(description = "Identificador generado en base de datos", example = "1")
    private Long id;

    @Schema(description = "Estatus de la transaccion", example = "Aprobada")
    private String estatus;

    @Schema(description = "Referencia aleatoria de 6 digitos", example = "815038")
    private String referencia;

    @Schema(description = "Tipo de operacion recibida", example = "COMPRA")
    private String operacion;
}

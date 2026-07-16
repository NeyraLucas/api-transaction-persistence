package com.evaluacion.plataformas.persistencia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Datos de la transaccion enviados por la API 1")
public class TransaccionRequest {

    @NotBlank
    @Schema(description = "Tipo de operacion", example = "COMPRA")
    private String operacion;

    @NotNull
    @DecimalMin(value = "0.01")
    @Schema(description = "Monto de la transaccion", example = "150.50")
    private BigDecimal importe;

    @NotBlank
    @Schema(description = "Nombre del cliente", example = "Juan Perez")
    private String cliente;

    @NotBlank
    @Schema(description = "Secreto ya descifrado por la API 1", example = "valor-descifrado-de-prueba")
    private String secreto;
}

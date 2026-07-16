package com.evaluacion.plataformas.persistencia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Transaccion tal como se expone en el listado paginado")
public class TransaccionDTO {

    @Schema(description = "Identificador de la transaccion", example = "2376")
    private String id;

    @Schema(description = "Tipo de operacion", example = "venta")
    private String operacion;

    @Schema(description = "Monto de la transaccion", example = "100.00")
    private String importe;

    @Schema(description = "Nombre del cliente", example = "Angel")
    private String cliente;

    @Schema(description = "Referencia aleatoria de 6 digitos", example = "262737")
    private String referencia;

    @Schema(description = "Estatus de la transaccion", example = "Aprobada")
    private String estatus;

    @Schema(description = "Secreto asociado a la transaccion", example = "secreto")
    private String secreto;
}

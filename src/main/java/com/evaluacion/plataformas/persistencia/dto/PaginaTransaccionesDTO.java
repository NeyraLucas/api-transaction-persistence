package com.evaluacion.plataformas.persistencia.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Pagina de transacciones. Se usa un DTO plano en lugar de Page<T> porque este ultimo no se deserializa bien del lado de un Feign client")
public class PaginaTransaccionesDTO {

    @Schema(description = "Transacciones de la pagina actual")
    private List<TransaccionDTO> contenido;

    @Schema(description = "Numero de pagina, base 0", example = "0")
    private int pagina;

    @Schema(description = "Tamanio de la pagina", example = "10")
    private int tamanio;

    @Schema(description = "Total de elementos en todas las paginas", example = "42")
    private long totalElementos;

    @Schema(description = "Total de paginas", example = "5")
    private int totalPaginas;
}

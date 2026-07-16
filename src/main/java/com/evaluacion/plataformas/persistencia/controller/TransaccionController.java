package com.evaluacion.plataformas.persistencia.controller;

import com.evaluacion.plataformas.persistencia.dto.CancelarTransaccionRequestDTO;
import com.evaluacion.plataformas.persistencia.dto.PaginaTransaccionesDTO;
import com.evaluacion.plataformas.persistencia.dto.TransaccionRequest;
import com.evaluacion.plataformas.persistencia.dto.TransaccionResponse;
import com.evaluacion.plataformas.persistencia.dto.TransaccionResponseDTO;
import com.evaluacion.plataformas.persistencia.exception.ErrorResponse;
import com.evaluacion.plataformas.persistencia.service.TransaccionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transacciones")
@RequiredArgsConstructor
@Tag(name = "Transacciones", description = "Persistencia de transacciones recibidas desde la API 1")
public class TransaccionController {

    private final TransaccionService transaccionService;

    @PostMapping
    @Operation(summary = "Registra una transaccion", description = "Guarda la transaccion, genera una referencia de 6 digitos y regresa el resultado con estatus Aprobada")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Transaccion creada correctamente"),
            @ApiResponse(responseCode = "400", description = "Datos invalidos", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public ResponseEntity<TransaccionResponse> create(@Valid @RequestBody TransaccionRequest request) {
        TransaccionResponse response = transaccionService.process(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    @Operation(summary = "Lista transacciones paginadas", description = "Soporta los parametros estandar de Spring Pageable: page, size y sort")
    public PaginaTransaccionesDTO list(@PageableDefault(size = 10) Pageable pageable) {
        return transaccionService.list(pageable);
    }

    @PatchMapping
    @Operation(summary = "Cancela una transaccion", description = "Busca por id y referencia a la vez; si no hay match responde 404")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Transaccion cancelada correctamente"),
            @ApiResponse(responseCode = "404", description = "No existe una transaccion con ese id y referencia", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
    })
    public TransaccionResponseDTO cancel(@Valid @RequestBody CancelarTransaccionRequestDTO request) {
        return transaccionService.cancel(request);
    }
}

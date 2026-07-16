package com.evaluacion.plataformas.persistencia.service;

import com.evaluacion.plataformas.persistencia.dto.CancelarTransaccionRequestDTO;
import com.evaluacion.plataformas.persistencia.dto.PaginaTransaccionesDTO;
import com.evaluacion.plataformas.persistencia.dto.TransaccionDTO;
import com.evaluacion.plataformas.persistencia.dto.TransaccionRequest;
import com.evaluacion.plataformas.persistencia.dto.TransaccionResponse;
import com.evaluacion.plataformas.persistencia.dto.TransaccionResponseDTO;
import com.evaluacion.plataformas.persistencia.entity.Transaccion;
import com.evaluacion.plataformas.persistencia.exception.TransaccionNotFoundException;
import com.evaluacion.plataformas.persistencia.repository.TransaccionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.RoundingMode;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Service
@RequiredArgsConstructor
public class TransaccionService {

    private static final String ESTATUS_APROBADA = "Aprobada";

    private final TransaccionRepository transaccionRepository;

    @Transactional
    public TransaccionResponse process(TransaccionRequest request) {
        Transaccion transaction = Transaccion.builder()
                .operacion(request.getOperacion())
                .importe(request.getImporte())
                .cliente(request.getCliente())
                .secreto(request.getSecreto())
                .referencia(generateUniqueReference())
                .estatus(ESTATUS_APROBADA)
                .build();

        Transaccion savedTransaction = transaccionRepository.save(transaction);

        return new TransaccionResponse(
                savedTransaction.getId(),
                savedTransaction.getEstatus(),
                savedTransaction.getReferencia(),
                savedTransaction.getOperacion());
    }

    private String generateUniqueReference() {
        String reference;
        do {
            reference = generateReference();
        } while (transaccionRepository.existsByReferencia(reference));
        return reference;
    }

    private String generateReference() {
        int numberReference = ThreadLocalRandom.current().nextInt(0, 1_000_000);
        return String.format("%06d", numberReference);
    }

    public PaginaTransaccionesDTO list(Pageable pageable) {
        Page<Transaccion> page = transaccionRepository.findAll(pageable);
        List<TransaccionDTO> contenido = page.getContent().stream()
                .map(this::toDto)
                .toList();

        return new PaginaTransaccionesDTO(
                contenido,
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages());
    }

    @Transactional
    public TransaccionResponseDTO cancel(CancelarTransaccionRequestDTO request) {
        Long id = Long.valueOf(request.id());
        int updatedRows = transaccionRepository.cancel(id, request.referencia());
        if (updatedRows == 0) {
            throw new TransaccionNotFoundException("No existe una transaccion con ese id y referencia");
        }

        Transaccion updated = transaccionRepository.findById(id).orElseThrow();

        return new TransaccionResponseDTO(
                updated.getId().toString(),
                updated.getEstatus(),
                updated.getReferencia(),
                updated.getOperacion());
    }

    private TransaccionDTO toDto(Transaccion transaccion) {
        return new TransaccionDTO(
                transaccion.getId().toString(),
                transaccion.getOperacion(),
                transaccion.getImporte().setScale(2, RoundingMode.HALF_UP).toPlainString(),
                transaccion.getCliente(),
                transaccion.getReferencia(),
                transaccion.getEstatus(),
                transaccion.getSecreto());
    }
}

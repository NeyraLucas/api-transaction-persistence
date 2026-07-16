package com.evaluacion.plataformas.persistencia.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "transacciones")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String operacion;

    @Column(nullable = false)
    private BigDecimal importe;

    @Column(nullable = false)
    private String cliente;

    @Column(nullable = false, unique = true, length = 6)
    private String referencia;

    @Column(nullable = false)
    private String estatus;

    @Column(nullable = false)
    private String secreto;
}

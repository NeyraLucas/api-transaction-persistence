package com.evaluacion.plataformas.persistencia.repository;

import com.evaluacion.plataformas.persistencia.entity.Transaccion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransaccionRepository extends JpaRepository<Transaccion, Long> {

    boolean existsByReferencia(String referencia);

    @Modifying(clearAutomatically = true)
    @Query("UPDATE Transaccion t SET t.estatus = 'Cancelada' WHERE t.id = :id AND t.referencia = :referencia")
    int cancel(@Param("id") Long id, @Param("referencia") String referencia);
}

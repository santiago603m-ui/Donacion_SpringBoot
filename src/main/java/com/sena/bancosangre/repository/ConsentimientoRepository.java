package com.sena.bancosangre.repository;

import com.sena.bancosangre.domain.Consentimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ConsentimientoRepository extends JpaRepository<Consentimiento, Long> {
    Optional<Consentimiento> findByDonanteId(Long donanteId);

    boolean existsByDonanteId(Long donanteId);
}
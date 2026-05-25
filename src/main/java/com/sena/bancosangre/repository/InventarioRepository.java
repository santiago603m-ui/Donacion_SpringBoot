package com.sena.bancosangre.repository;

import com.sena.bancosangre.domain.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {
    Optional<Inventario> findByTipoSangre(String tipoSangre);
}
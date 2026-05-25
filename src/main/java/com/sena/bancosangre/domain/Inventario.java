package com.sena.bancosangre.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "inventario")
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_sangre", nullable = false, unique = true, length = 3)
    private String tipoSangre;

    @Column(name = "cantidad_disponible_ml", nullable = false)
    private Double cantidadDisponibleML;
}
package com.sena.bancosangre.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "donaciones")
public class Donacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "donante_id", nullable = false)
    private Donante donante;

    @Column(name = "cantidad_ml", nullable = false)
    private Double cantidadML;

    @Column(name = "fecha_donacion", nullable = false)
    private LocalDate fechaDonacion;

    @Column(length = 500)
    private String observaciones;

    @Column(name = "codigo_unico", nullable = false, unique = true, length = 50)
    private String codigoUnico;
}
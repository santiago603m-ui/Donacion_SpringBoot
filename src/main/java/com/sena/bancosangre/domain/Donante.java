package com.sena.bancosangre.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "donantes")
public class Donante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombres;

    @Column(nullable = false)
    private String apellidos;

    @Column(nullable = false, unique = true)
    private String documento;

    @Column(name = "fecha_nacimiento", nullable = false)
    private LocalDate fechaNacimiento;

    @Column(name = "tipo_sangre", nullable = false)
    private String tipoSangre;

    @Column(nullable = false)
    private Double peso;

    private String telefono;

    @Column(unique = true)
    private String correo;

    private String direccion;

    @Column(name = "fecha_ultima_donacion")
    private LocalDate fechaUltimaDonacion;

    @Column(name = "acepta_consentimiento", nullable = false)
    private Boolean aceptaConsentimiento;

    @Column(name = "firma_consentimiento", columnDefinition = "TEXT")
    private String firmaConsentimiento;
}
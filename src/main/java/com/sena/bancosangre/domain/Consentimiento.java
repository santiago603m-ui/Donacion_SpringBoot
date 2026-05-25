package com.sena.bancosangre.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "consentimiento")
public class Consentimiento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "donante_id", nullable = false, unique = true)
    private Donante donante;

    @Column(name = "firma_base64", columnDefinition = "LONGTEXT", nullable = false)
    private String firmaBase64;

    @Column(name = "fecha_firma", nullable = false)
    private LocalDate fechaFirma;
}
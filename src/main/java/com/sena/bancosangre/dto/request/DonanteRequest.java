package com.sena.bancosangre.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.time.LocalDate;

@Data
public class DonanteRequest {

    @NotBlank(message = "Los nombres son obligatorios")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    private String apellidos;

    @NotBlank(message = "El documento es obligatorio")
    private String documento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Past(message = "La fecha de nacimiento debe ser una fecha pasada")
    private LocalDate fechaNacimiento;

    @NotBlank(message = "El tipo de sangre es obligatorio")
    private String tipoSangre;

    @NotNull(message = "El peso es obligatorio")
    @Positive(message = "El peso debe ser un valor positivo")
    private Double peso;

    private String telefono;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Debe ser un formato de correo electrónico válido")
    private String correo;

    private String direccion;

    @NotNull(message = "Debe especificar si acepta el consentimiento")
    @AssertTrue(message = "Es obligatorio aceptar el consentimiento para registrarse")
    private Boolean aceptaConsentimiento;

    private String firmaConsentimiento;
}
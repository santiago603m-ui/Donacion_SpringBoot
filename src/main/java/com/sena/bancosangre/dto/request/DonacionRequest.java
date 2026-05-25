package com.sena.bancosangre.dto.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class DonacionRequest {

    @NotNull(message = "El ID del donante es obligatorio")
    private Long donanteId;

    @NotNull(message = "La cantidad de mililitros es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Double cantidadML;

    private String observaciones;
}
package com.sena.bancosangre.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ConsentimientoRequest {

    @NotNull(message = "El ID del donante es obligatorio")
    private Long donanteId;

    @NotBlank(message = "La firma en formato Base64 es obligatoria")
    private String firmaBase64;
}
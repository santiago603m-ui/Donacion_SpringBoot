package com.sena.bancosangre.dto.response;

import lombok.Data;

@Data
public class InventarioResponse {
    private String tipoSangre;
    private Double cantidadDisponibleML;
}
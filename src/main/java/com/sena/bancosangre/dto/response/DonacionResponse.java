package com.sena.bancosangre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DonacionResponse {
    private Long id;
    private Long donanteId;
    private String nombreCompletoDonante;
    private Double cantidadML;
    private LocalDate fechaDonacion;
    private String observaciones;
    private String codigoUnico;
}
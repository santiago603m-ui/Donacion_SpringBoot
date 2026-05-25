package com.sena.bancosangre.dto.response;

import lombok.Data;
import java.time.LocalDate;

@Data
public class DonanteResponse {
    private Long id;
    private String nombres;
    private String apellidos;
    private String documento;
    private String tipoSangre;
    private Double peso;
    private String correo;
    private LocalDate fechaUltimaDonacion;
}
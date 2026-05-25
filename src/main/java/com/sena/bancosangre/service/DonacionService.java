package com.sena.bancosangre.service;

import com.sena.bancosangre.dto.request.DonacionRequest;
import com.sena.bancosangre.dto.response.DonacionResponse;
import java.util.List;

public interface DonacionService {
    DonacionResponse registrarDonacion(DonacionRequest request);
    List<DonacionResponse> obtenerTodas();
    DonacionResponse obtenerPorId(Long id);
}
package com.sena.bancosangre.service;

import com.sena.bancosangre.dto.request.DonanteRequest;
import com.sena.bancosangre.dto.response.DonanteResponse;
import java.util.List;

public interface DonanteService {
    DonanteResponse registrarDonante(DonanteRequest request);

    List<DonanteResponse> obtenerTodos();

    DonanteResponse obtenerPorId(Long id);

    void eliminarDonante(Long id);
}
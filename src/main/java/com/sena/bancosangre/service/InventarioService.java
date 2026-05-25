package com.sena.bancosangre.service;

import com.sena.bancosangre.dto.response.InventarioResponse;
import java.util.List;

public interface InventarioService {
    List<InventarioResponse> obtenerInventarioCompleto();

    InventarioResponse obtenerPorTipoSangre(String tipoSangre);
}
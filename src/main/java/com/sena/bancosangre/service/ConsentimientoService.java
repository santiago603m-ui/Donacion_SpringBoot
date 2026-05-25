package com.sena.bancosangre.service;

import com.sena.bancosangre.dto.request.ConsentimientoRequest;

public interface ConsentimientoService {
    void registrarConsentimiento(ConsentimientoRequest request);
}
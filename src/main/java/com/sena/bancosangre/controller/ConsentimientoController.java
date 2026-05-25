package com.sena.bancosangre.controller;

import com.sena.bancosangre.dto.request.ConsentimientoRequest;
import com.sena.bancosangre.service.ConsentimientoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consentimientos")
@RequiredArgsConstructor
public class ConsentimientoController {

    private final ConsentimientoService consentimientoService;

    @PostMapping
    public ResponseEntity<String> registrarConsentimiento(@Valid @RequestBody ConsentimientoRequest request) {
        consentimientoService.registrarConsentimiento(request);
        return new ResponseEntity<>("Consentimiento firmado y registrado correctamente", HttpStatus.CREATED);
    }
}
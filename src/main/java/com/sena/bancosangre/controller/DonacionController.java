package com.sena.bancosangre.controller;

import com.sena.bancosangre.dto.request.DonacionRequest;
import com.sena.bancosangre.dto.response.DonacionResponse;
import com.sena.bancosangre.service.DonacionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donaciones")
@RequiredArgsConstructor
public class DonacionController {

    private final DonacionService donacionService;

    @PostMapping
    public ResponseEntity<DonacionResponse> registrarDonacion(@Valid @RequestBody DonacionRequest request) {
        DonacionResponse response = donacionService.registrarDonacion(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonacionResponse>> obtenerDonaciones() {
        return ResponseEntity.ok(donacionService.obtenerTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonacionResponse> obtenerDonacion(@PathVariable Long id) {
        return ResponseEntity.ok(donacionService.obtenerPorId(id));
    }
}
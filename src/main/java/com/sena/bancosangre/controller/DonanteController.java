package com.sena.bancosangre.controller;

import com.sena.bancosangre.dto.request.DonanteRequest;
import com.sena.bancosangre.dto.response.DonanteResponse;
import com.sena.bancosangre.service.DonanteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/donantes")
@RequiredArgsConstructor
public class DonanteController {

    private final DonanteService donanteService;

    @PostMapping
    public ResponseEntity<DonanteResponse> registrarDonante(@Valid @RequestBody DonanteRequest request) {
        DonanteResponse response = donanteService.registrarDonante(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<DonanteResponse>> obtenerTodos() {
        return ResponseEntity.ok(donanteService.obtenerTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DonanteResponse> obtenerPorId(@PathVariable Long id) {
        return ResponseEntity.ok(donanteService.obtenerPorId(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDonante(@PathVariable Long id) {
        donanteService.eliminarDonante(id);
        return ResponseEntity.noContent().build();
    }
}
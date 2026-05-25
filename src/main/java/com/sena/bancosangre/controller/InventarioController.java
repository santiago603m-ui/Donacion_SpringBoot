package com.sena.bancosangre.controller;

import com.sena.bancosangre.dto.response.InventarioResponse;
import com.sena.bancosangre.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService inventarioService;

    // Endpoint: GET /api/inventario
    @GetMapping
    public ResponseEntity<List<InventarioResponse>> consultarInventario() {
        return ResponseEntity.ok(inventarioService.obtenerInventarioCompleto());
    }

    // Endpoint: GET /api/inventario/{tipo}
    @GetMapping("/{tipo}")
    public ResponseEntity<InventarioResponse> consultarPorTipo(@PathVariable String tipo) {
        // En caso de que se pase el tipo por URL con espacios o caracteres especiales
        // (ej. O+ se manda como O%2B)
        return ResponseEntity.ok(inventarioService.obtenerPorTipoSangre(tipo));
    }
}
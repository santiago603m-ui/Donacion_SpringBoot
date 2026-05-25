package com.sena.bancosangre.service.impl;

import com.sena.bancosangre.domain.Inventario;
import com.sena.bancosangre.dto.response.InventarioResponse;
import com.sena.bancosangre.exception.ResourceNotFoundException;
import com.sena.bancosangre.mapper.InventarioMapper;
import com.sena.bancosangre.repository.InventarioRepository;
import com.sena.bancosangre.service.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InventarioServiceImpl implements InventarioService {

    private final InventarioRepository inventarioRepository;
    private final InventarioMapper inventarioMapper;

    @Override
    @Transactional(readOnly = true)
    public List<InventarioResponse> obtenerInventarioCompleto() {
        return inventarioRepository.findAll().stream()
                .map(inventarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public InventarioResponse obtenerPorTipoSangre(String tipoSangre) {
        Inventario inventario = inventarioRepository.findByTipoSangre(tipoSangre)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se encontró registro de inventario para el tipo de sangre: " + tipoSangre));

        return inventarioMapper.toDto(inventario);
    }
}
package com.sena.bancosangre.service.impl;

import com.sena.bancosangre.domain.Donante;
import com.sena.bancosangre.dto.request.DonanteRequest;
import com.sena.bancosangre.dto.response.DonanteResponse;
import com.sena.bancosangre.exception.BusinessException;
import com.sena.bancosangre.exception.ResourceNotFoundException;
import com.sena.bancosangre.mapper.DonanteMapper;
import com.sena.bancosangre.repository.DonanteRepository;
import com.sena.bancosangre.service.DonanteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonanteServiceImpl implements DonanteService {

    private final DonanteRepository donanteRepository;
    private final DonanteMapper donanteMapper;

    @Override
    @Transactional
    public DonanteResponse registrarDonante(DonanteRequest request) {
        if (donanteRepository.existsByDocumento(request.getDocumento())) {
            throw new BusinessException("Ya existe un donante con ese documento");
        }
        if (donanteRepository.existsByCorreo(request.getCorreo())) {
            throw new BusinessException("Ya existe un donante con ese correo");
        }

        Donante donante = donanteMapper.toEntity(request);
        Donante donanteGuardado = donanteRepository.save(donante);

        return donanteMapper.toDto(donanteGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonanteResponse> obtenerTodos() {
        return donanteRepository.findAll().stream()
                .map(donanteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DonanteResponse obtenerPorId(Long id) {
        Donante donante = donanteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donante no encontrado con ID: " + id));
        return donanteMapper.toDto(donante);
    }

    @Override
    @Transactional
    public void eliminarDonante(Long id) {
        if (!donanteRepository.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar. Donante no encontrado con ID: " + id);
        }
        donanteRepository.deleteById(id);
    }
}
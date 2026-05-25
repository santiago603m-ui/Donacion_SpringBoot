package com.sena.bancosangre.service.impl;

import com.sena.bancosangre.domain.Donacion;
import com.sena.bancosangre.domain.Donante;
import com.sena.bancosangre.dto.request.DonacionRequest;
import com.sena.bancosangre.dto.response.DonacionResponse;
import com.sena.bancosangre.exception.ConsentimientoNoFirmadoException;
import com.sena.bancosangre.exception.DonanteNoAptoException;
import com.sena.bancosangre.exception.ResourceNotFoundException;
import com.sena.bancosangre.mapper.DonacionMapper;
import com.sena.bancosangre.repository.DonacionRepository;
import com.sena.bancosangre.repository.DonanteRepository;
import com.sena.bancosangre.service.DonacionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DonacionServiceImpl implements DonacionService {

    private final DonacionRepository donacionRepository;
    private final DonanteRepository donanteRepository;
    private final DonacionMapper donacionMapper;

    @Override
    @Transactional
    public DonacionResponse registrarDonacion(DonacionRequest request) {
        // 1. Buscar al donante
        Donante donante = donanteRepository.findById(request.getDonanteId())
                .orElseThrow(
                        () -> new ResourceNotFoundException("Donante no encontrado con ID: " + request.getDonanteId()));

        // 2. Ejecutar validaciones médicas obligatorias
        validarReglasMedicas(donante);

        // 3. Crear la entidad Donacion
        Donacion donacion = donacionMapper.toEntity(request);
        donacion.setDonante(donante);
        donacion.setFechaDonacion(LocalDate.now()); // Regla: Registrar fecha automáticamente
        donacion.setCodigoUnico(UUID.randomUUID().toString().substring(0, 8).toUpperCase()); // Regla: Generar código
                                                                                             // único

        // 4. Actualizar al donante
        donante.setFechaUltimaDonacion(LocalDate.now()); // Regla: Actualizar fecha de última donación
        donanteRepository.save(donante); // Guardamos los cambios en el donante

        // 5. Guardar la donación en BD
        Donacion donacionGuardada = donacionRepository.save(donacion);

        return donacionMapper.toDto(donacionGuardada);
    }

    private void validarReglasMedicas(Donante donante) {
        // Validación 1: Menor de 18 años
        long edad = ChronoUnit.YEARS.between(donante.getFechaNacimiento(), LocalDate.now());
        if (edad < 18) {
            throw new DonanteNoAptoException("El donante no es apto: Es menor de 18 años.");
        }

        // Validación 2: Pesa menos de 50 kg
        if (donante.getPeso() < 50.0) {
            throw new DonanteNoAptoException("El donante no es apto: Pesa menos de 50 kg.");
        }

        // Validación 3: Han pasado menos de 3 meses desde la última donación
        if (donante.getFechaUltimaDonacion() != null) {
            long mesesDesdeUltima = ChronoUnit.MONTHS.between(donante.getFechaUltimaDonacion(), LocalDate.now());
            if (mesesDesdeUltima < 3) {
                throw new DonanteNoAptoException(
                        "El donante no es apto: Han pasado menos de 3 meses desde su última donación.");
            }
        }

        // Validación 4: No aceptó consentimiento
        if (!Boolean.TRUE.equals(donante.getAceptaConsentimiento())) {
            throw new ConsentimientoNoFirmadoException(
                    "El proceso no puede continuar: El donante no aceptó el consentimiento.");
        }

        // Validación 5: No tiene firma registrada
        if (donante.getFirmaConsentimiento() == null || donante.getFirmaConsentimiento().trim().isEmpty()) {
            throw new ConsentimientoNoFirmadoException(
                    "El proceso no puede continuar: El donante no tiene una firma registrada.");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<DonacionResponse> obtenerTodas() {
        return donacionRepository.findAll().stream()
                .map(donacionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public DonacionResponse obtenerPorId(Long id) {
        Donacion donacion = donacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Donación no encontrada con ID: " + id));
        return donacionMapper.toDto(donacion);
    }
}
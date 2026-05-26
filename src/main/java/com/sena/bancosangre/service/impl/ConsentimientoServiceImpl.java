package com.sena.bancosangre.service.impl;

import com.sena.bancosangre.domain.Consentimiento;
import com.sena.bancosangre.domain.Donante;
import com.sena.bancosangre.dto.request.ConsentimientoRequest;
import com.sena.bancosangre.exception.BusinessException;
import com.sena.bancosangre.exception.ResourceNotFoundException;
import com.sena.bancosangre.repository.ConsentimientoRepository;
import com.sena.bancosangre.repository.DonanteRepository;
import com.sena.bancosangre.service.ConsentimientoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class ConsentimientoServiceImpl implements ConsentimientoService {

    private final ConsentimientoRepository consentimientoRepository;
    private final DonanteRepository donanteRepository;

    @Override
    @Transactional
    public void registrarConsentimiento(ConsentimientoRequest request) {
        Donante donante = donanteRepository.findById(request.getDonanteId())
                .orElseThrow(() -> new ResourceNotFoundException("Donante no encontrado"));

        if (consentimientoRepository.existsByDonanteId(donante.getId())) {
            throw new BusinessException("Este donante ya tiene un consentimiento registrado");
        }

        Consentimiento consentimiento = new Consentimiento();
        consentimiento.setDonante(donante);
        consentimiento.setFirmaBase64(request.getFirmaBase64());
        consentimiento.setFechaFirma(LocalDate.now());

        donante.setAceptaConsentimiento(true);
        donante.setFirmaConsentimiento("REGISTRADO_EN_TABLA_CONSENTIMIENTO");
        donanteRepository.save(donante);

        consentimientoRepository.save(consentimiento);
    }
}
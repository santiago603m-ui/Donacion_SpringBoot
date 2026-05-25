package com.sena.bancosangre.mapper;

import com.sena.bancosangre.domain.Donacion;
import com.sena.bancosangre.dto.request.DonacionRequest;
import com.sena.bancosangre.dto.response.DonacionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonacionMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "donante", ignore = true)
    @Mapping(target = "fechaDonacion", ignore = true)
    @Mapping(target = "codigoUnico", ignore = true)
    Donacion toEntity(DonacionRequest request);

    @Mapping(target = "donanteId", source = "donante.id")
    @Mapping(target = "nombreCompletoDonante", expression = "java(donacion.getDonante().getNombres() + ' ' + donacion.getDonante().getApellidos())")
    DonacionResponse toDto(Donacion donacion);
}
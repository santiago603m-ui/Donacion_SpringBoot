package com.sena.bancosangre.mapper;

import com.sena.bancosangre.domain.Donante;
import com.sena.bancosangre.dto.request.DonanteRequest;
import com.sena.bancosangre.dto.response.DonanteResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DonanteMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "fechaUltimaDonacion", ignore = true)
    Donante toEntity(DonanteRequest request);

    DonanteResponse toResponse(Donante entity);
}
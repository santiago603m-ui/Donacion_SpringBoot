package com.sena.bancosangre.mapper;

import com.sena.bancosangre.domain.Donante;
import com.sena.bancosangre.dto.request.DonanteRequest;
import com.sena.bancosangre.dto.response.DonanteResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DonanteMapper {
    Donante toEntity(DonanteRequest request);

    DonanteResponse toDto(Donante donante);
}
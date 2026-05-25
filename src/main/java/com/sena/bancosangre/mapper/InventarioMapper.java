package com.sena.bancosangre.mapper;

import com.sena.bancosangre.domain.Inventario;
import com.sena.bancosangre.dto.response.InventarioResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventarioMapper {
    InventarioResponse toDto(Inventario inventario);
}
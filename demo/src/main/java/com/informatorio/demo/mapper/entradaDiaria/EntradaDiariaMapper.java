package com.informatorio.demo.mapper.entradaDiaria;

import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaDto;
import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaSimpleDto;
import com.informatorio.demo.mapper.usuario.UsuarioMapper;
import com.informatorio.demo.model.EntradaDiaria;

import java.util.List;

public final class EntradaDiariaMapper {
    public EntradaDiariaMapper(){}

    public static EntradaDiariaDto toFullDto(EntradaDiaria entradaDiaria){
        if (entradaDiaria == null)return null;
        EntradaDiariaDto dto = new EntradaDiariaDto();
        dto.setId(entradaDiaria.getId());
        dto.setFecha(entradaDiaria.getFecha());
        dto.setEmocion(entradaDiaria.getEmocion());
        dto.setReflexion(entradaDiaria.getReflexion());
        dto.setUsuarioDto(UsuarioMapper.toDto(entradaDiaria.getUsuario()));
        if (entradaDiaria.getHabitos() != null && !entradaDiaria.getHabitos().isEmpty()){
            dto.setHabitosDescripciones(
                    entradaDiaria.getHabitos().stream()
                            .map(habito -> habito.getDescripcion())
                            .toList()
            );
        }
        return dto;
    }

    public static List<EntradaDiariaDto> toFullDtoList(List<EntradaDiaria> entradas){
        return entradas.stream()
                .map(entradaDiaria -> toFullDto(entradaDiaria))
                .toList();
    }

    public static EntradaDiariaSimpleDto toSimpleDto(EntradaDiaria entradaDiaria){
        if(entradaDiaria==null)return null;
        EntradaDiariaSimpleDto dto = new EntradaDiariaSimpleDto();
        dto.setId(entradaDiaria.getId());
        dto.setUsuarioID(entradaDiaria.getUsuario().getId());
        dto.setFecha(entradaDiaria.getFecha());
        dto.setEmocion(entradaDiaria.getEmocion());
        dto.setReflexion(entradaDiaria.getReflexion());
        if (entradaDiaria.getHabitos() != null && !entradaDiaria.getHabitos().isEmpty()){
            dto.setHabitos(
                    entradaDiaria.getHabitos().stream()
                            .map(habito -> habito.getDescripcion())
                            .toList()
            );
        }
    return dto;
    };

    public static List<EntradaDiariaSimpleDto> toSimpleDtoList(List<EntradaDiaria> entradas){
        return entradas.stream()
                .map(entradaDiaria -> toSimpleDto(entradaDiaria))
                .toList();
    }





}

package com.informatorio.demo.mapper.habito;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;
import com.informatorio.demo.dto.habito.HabitoListDto;
import com.informatorio.demo.model.Habito;

public final class HabitoMapper {

    public HabitoMapper() {}

    public static HabitoDto toDto(Habito habito){
        if (habito==null)return null;
        HabitoDto habitoDto = new HabitoDto();
        habitoDto.setId(habito.getId());
        habitoDto.setDescripcion(habito.getDescripcion());
        habitoDto.setNivelDeImportanciaEnum(habito.getNivelDeImportanciaEnum());
        return habitoDto;
    }

    public static HabitoListDto toListDto(Habito habito){
        if (habito==null)return null;
        HabitoListDto habitoListDto = new HabitoListDto();
        habitoListDto.setId(habito.getId());
        habitoListDto.setDescripcion(habito.getDescripcion());
        return habitoListDto;
    }

    public static Habito toEntity (HabitoCreateDto habitoCreateDto){
        if (habitoCreateDto==null)return null;
        Habito habito  = new Habito();
        habito.setDescripcion(habitoCreateDto.getDescripcion());
        habito.setNivelDeImportanciaEnum((habitoCreateDto.getNivelDeImportanciaEnum()));
        return habito;
    }
}

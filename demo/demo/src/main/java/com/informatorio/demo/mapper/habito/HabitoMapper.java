package com.informatorio.demo.mapper.habito;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;
import com.informatorio.demo.model.Habito;

public final class HabitoMapper {

    public HabitoMapper() {}

    public static Habito toEntity (HabitoCreateDto habitoCreateDto){
        if (habitoCreateDto==null)return null;
        Habito habito  = new Habito();
        habito.setDescripcion(habitoCreateDto.getDescripcion());
        return habito;
    }

    public static HabitoDto toDto(Habito habito){
        if (habito==null)return null;
        HabitoDto habitoDto = new HabitoDto();
        habitoDto.setId(habito.getId());
        habitoDto.setDescripcion(habito.getDescripcion());
        return habitoDto;
    }


}

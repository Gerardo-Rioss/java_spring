package com.informatorio.demo.service.habito;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;
import com.informatorio.demo.dto.habito.HabitoListDto;

import java.util.List;

public interface HabitoService {
    HabitoDto crearHabito(HabitoCreateDto habitoCreateDto);
    List<HabitoListDto> listarTodosLosHabitos();
}

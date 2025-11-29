package com.informatorio.demo.service.habito;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;

public interface HabitoService {
    HabitoDto crearHabito(HabitoCreateDto habitoCreateDto);
}

package com.informatorio.demo.service.entradaDiaria;

import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaCreateDto;
import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaDto;

public interface EntradaDiariaService {
    EntradaDiariaDto create(EntradaDiariaCreateDto createDto);
}

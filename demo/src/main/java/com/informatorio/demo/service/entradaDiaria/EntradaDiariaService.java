package com.informatorio.demo.service.entradaDiaria;

import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaCreateDto;
import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaDto;
import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaSimpleDto;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface EntradaDiariaService {
    EntradaDiariaDto create(EntradaDiariaCreateDto createDto);
    List<EntradaDiariaSimpleDto> obtenerEntradasDeUsuario(UUID usuarioId, LocalDate desde, LocalDate hasta);
}

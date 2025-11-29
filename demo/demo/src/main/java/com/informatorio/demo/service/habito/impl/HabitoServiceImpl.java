package com.informatorio.demo.service.habito.impl;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;
import com.informatorio.demo.mapper.habito.HabitoMapper;
import com.informatorio.demo.model.Habito;
import com.informatorio.demo.repository.habito.HabitoRepository;
import com.informatorio.demo.service.habito.HabitoService;
import org.springframework.stereotype.Service;

@Service
public class HabitoServiceImpl implements HabitoService {
    private HabitoRepository habitoRepository;
    @Override
    public HabitoDto crearHabito(HabitoCreateDto habitoCreateDto) {
        Habito habito= HabitoMapper.toEntity(habitoCreateDto);
        habito = habitoRepository.save(habito);
        return HabitoMapper.toDto(habito);
    }
}

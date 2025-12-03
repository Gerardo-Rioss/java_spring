package com.informatorio.demo.service.habito.impl;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;
import com.informatorio.demo.dto.habito.HabitoListDto;
import com.informatorio.demo.mapper.habito.HabitoMapper;
import com.informatorio.demo.model.Habito;
import com.informatorio.demo.repository.habito.HabitoRepository;
import com.informatorio.demo.service.habito.HabitoService;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class HabitoServiceImpl implements HabitoService {
    private final HabitoRepository habitoRepository;
    @Override
    public HabitoDto crearHabito(HabitoCreateDto habitoCreateDto) {
        try{

            //controlar que la descripcion ingresada no este vacio
            if (habitoCreateDto.getDescripcion()==null || habitoCreateDto.getDescripcion().trim().isEmpty()){
                log.warn("Descripcion vacia");
                throw new ValidationException("la descripcion no puede estar vacia");
            }

            //controlo la existencia de uno igual
            boolean existe = habitoRepository.findAll().stream()
                    .anyMatch(habito -> habito.getDescripcion()
                            .equalsIgnoreCase(habitoCreateDto.getDescripcion()));
            if (existe){
                log.warn("Existe un habito con la misma descripción.");
                throw new ValidationException("Ya existe un hábito con esa descripción: "+ habitoCreateDto.getDescripcion());
            }

            Habito habito= HabitoMapper.toEntity(habitoCreateDto);
            habito = habitoRepository.save(habito);
            log.info("Habito generado exitosamente.");
            return HabitoMapper.toDto(habito);

        } catch (Exception e) {
            throw new RuntimeException("Error: ",e);
        }

    }

    @Override
    public List<HabitoListDto> listarTodosLosHabitos() {
        try{
            List<Habito> habitos = habitoRepository.findAll();
            return habitos.stream()
                    .map(HabitoMapper::toListDto)
                    .toList();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

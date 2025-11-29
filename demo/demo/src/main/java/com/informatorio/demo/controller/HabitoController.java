package com.informatorio.demo.controller;

import com.informatorio.demo.dto.habito.HabitoCreateDto;
import com.informatorio.demo.dto.habito.HabitoDto;
import com.informatorio.demo.service.habito.HabitoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/habitos")
@Slf4j
public class HabitoController {

    private final HabitoService habitoService;

    @PostMapping
    public ResponseEntity<HabitoDto>crearHabito (
            @Valid
            @RequestBody HabitoCreateDto habitoCreateDto
            ){
         HabitoDto habitoCreado = habitoService.crearHabito(habitoCreateDto);
        return ResponseEntity.ok(habitoCreado);
    }



}

package com.informatorio.demo.controller;

import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaCreateDto;
import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaDto;
import com.informatorio.demo.service.entradaDiaria.EntradaDiariaService;
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
@Slf4j
@RequestMapping("/api/v1/entrada-diaria")
public class EntradaController {

    private final EntradaDiariaService entradaDiariaService;

    @PostMapping
    public ResponseEntity<EntradaDiariaDto>crear(@Valid @RequestBody EntradaDiariaCreateDto createDto){
        try{
            EntradaDiariaDto entradaCreada = entradaDiariaService.create(createDto);
            return ResponseEntity.ok(entradaCreada);
        }catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            log.error("Error desconocido", e);
            return ResponseEntity.internalServerError().build();
        }
    }
}

package com.informatorio.demo.controller;

import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaCreateDto;
import com.informatorio.demo.dto.entradaDiaria.EntradaDiariaDto;
import com.informatorio.demo.service.entradaDiaria.EntradaDiariaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/entrada-diaria")
public class EntradaConroller {

    private final EntradaDiariaService entradaDiariaService;

    @PostMapping
    public ResponseEntity<EntradaDiariaDto>crear(@RequestBody EntradaDiariaCreateDto createDto){
        try{
            EntradaDiariaDto entradaCreada = entradaDiariaService.create(createDto);
            return ResponseEntity.ok(entradaCreada);
        }catch (IllegalArgumentException illegalArgumentException){
            return ResponseEntity.badRequest().build();
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}

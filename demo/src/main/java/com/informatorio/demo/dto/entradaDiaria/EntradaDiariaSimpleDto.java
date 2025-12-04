package com.informatorio.demo.dto.entradaDiaria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EntradaDiariaSimpleDto {
    private Long id;
    private UUID usuarioID;
    private LocalDate fecha;
    private String emocion;
    private String reflexion;
    private List<String> habitos;

}

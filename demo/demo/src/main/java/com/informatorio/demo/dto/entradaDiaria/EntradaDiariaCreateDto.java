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
public class EntradaDiariaCreateDto {
    private UUID usuarioId;
    private LocalDate fecha;
    private String reflexion;
    private String emocion;
    private List<Long> habitosIds;
}

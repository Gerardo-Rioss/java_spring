package com.informatorio.demo.dto.habito;

import com.informatorio.demo.model.NivelDeImportanciaEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabitoDto {
    private Long id;
    private String descripcion;
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;
}

package com.informatorio.demo.dto.habito;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabitoListDto {
    private Long id;
    private String descripcion;
}

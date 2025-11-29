package com.informatorio.demo.dto.habito;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabitoDto {
    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;
}

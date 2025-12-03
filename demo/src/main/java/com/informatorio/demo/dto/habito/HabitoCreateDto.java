package com.informatorio.demo.dto.habito;

import com.informatorio.demo.model.NivelDeImportanciaEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HabitoCreateDto {

    @NotBlank(message = "La descripción es obligatoria.")
    private String descripcion;

    @NotNull(message = "El nivel de importancia es obligatorio.")
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;

}

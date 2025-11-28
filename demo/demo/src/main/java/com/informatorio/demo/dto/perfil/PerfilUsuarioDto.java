package com.informatorio.demo.dto.perfil;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PerfilUsuarioDto {
    private Long id;

    @NotBlank(message = "La biografia no puede estar vacia.")
    private String bio;

    @NotBlank(message = "El color favorito no puede estar vacia.")
    private String colorFavorito;

    @NotBlank(message = "La frase del dia no puede estar vacia.")
    private String fraseDelDia;
}

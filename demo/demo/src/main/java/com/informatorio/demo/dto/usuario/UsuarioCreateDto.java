package com.informatorio.demo.dto.usuario;

import com.informatorio.demo.dto.perfil.PerfilUsuarioDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioCreateDto {
    private String nombre;
    private String email;
    private PerfilUsuarioDto perfilUsuarioDto;

}

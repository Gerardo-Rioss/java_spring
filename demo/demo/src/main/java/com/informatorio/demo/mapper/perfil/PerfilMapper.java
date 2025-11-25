package com.informatorio.demo.mapper.perfil;

import com.informatorio.demo.dto.perfil.PerfilUsuarioDto;
import com.informatorio.demo.model.PerfilUsuario;

public final class PerfilMapper {
    public PerfilMapper() {}
    public static PerfilUsuarioDto toDto (PerfilUsuario perfilUsuario){
        if (perfilUsuario==null)return null;
        PerfilUsuarioDto perfilUsuarioDto = new PerfilUsuarioDto();
        perfilUsuarioDto.setId(perfilUsuario.getId());
        perfilUsuarioDto.setBio(perfilUsuario.getBio());
        perfilUsuarioDto.setColorFavorito(perfilUsuario.getColorFavorito());
        perfilUsuarioDto.setFraseDelDia(perfilUsuario.getFraseDelDia());
        return perfilUsuarioDto;
    }
}

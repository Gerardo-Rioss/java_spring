package com.informatorio.demo.mapper.usuario;

import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.mapper.perfil.PerfilMapper;
import com.informatorio.demo.model.Usuario;

import java.util.List;

public final class UsuarioMapper {
    public UsuarioMapper() {}

    public static UsuarioDto toDto(Usuario usuario){
        if (usuario== null) return null;
        UsuarioDto usuarioDto= new UsuarioDto();
        usuarioDto.setId(usuario.getId());
        usuarioDto.setNombre(usuario.getNombre());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setPerfilUsuarioDto(PerfilMapper.toDto(usuario.getPerfil()));
        return usuarioDto;
    }

    public static List<UsuarioDto> toDtoList(List<Usuario> usuarios){
        return usuarios.stream()
                .map(usuario -> toDto(usuario))
                .toList();
    }
}

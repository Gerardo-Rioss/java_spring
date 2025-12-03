package com.informatorio.demo.service.usuario;

import com.informatorio.demo.dto.usuario.UsuarioCreateDto;
import com.informatorio.demo.dto.usuario.UsuarioDto;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioService {
    List<UsuarioDto> obtenerTodos(String nombre, String email, String colorFavorito);
    Optional<UsuarioDto> obtenerPorId(UUID id);
    UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto);
    UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto);
    boolean eliminarUsuario(UUID id);
}

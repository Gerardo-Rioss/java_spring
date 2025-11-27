package com.informatorio.demo.service.usuario.impl;

import com.informatorio.demo.dto.usuario.UsuarioCreateDto;
import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.mapper.usuario.UsuarioMapper;
import com.informatorio.demo.model.Usuario;
import com.informatorio.demo.repository.usuario.UsuarioRepository;
import com.informatorio.demo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServiceImpl  implements UsuarioService {

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        List<Usuario> usuarioList=usuarioRepository.findAll();
        return UsuarioMapper.toDtoList(usuarioList);
    }

    @Override
    public Optional<UsuarioDto> obtenerPorId(UUID id) {
        Optional<Usuario> usuario= usuarioRepository.findById(id);
        if (usuario.isPresent()){
            Usuario usuarioEntity = usuario.get();
            return Optional.of(UsuarioMapper.toDto(usuarioEntity));
        }
        return Optional.empty();
    }

    @Override
    public UsuarioDto crearUsuario(UsuarioCreateDto usuarioCreateDto) {
        Usuario usuario= UsuarioMapper.toEntity(usuarioCreateDto);
        usuario = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(usuario);
    }
}

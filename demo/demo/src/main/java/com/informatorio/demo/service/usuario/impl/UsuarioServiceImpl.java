package com.informatorio.demo.service.usuario.impl;

import com.informatorio.demo.dto.usuario.UsuarioCreateDto;
import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.mapper.perfil.PerfilMapper;
import com.informatorio.demo.mapper.usuario.UsuarioMapper;
import com.informatorio.demo.model.PerfilUsuario;
import com.informatorio.demo.model.Usuario;
import com.informatorio.demo.repository.usuario.UsuarioRepository;
import com.informatorio.demo.service.usuario.UsuarioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
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

    @Override
    public UsuarioDto updateUsuario(UUID id, UsuarioCreateDto usuarioCreateDto) {
        Optional<Usuario> usuario = usuarioRepository.findById(id);
        if (usuario.isPresent()){
            Optional<Usuario> usuarioExist = usuarioRepository.findByEmail(usuarioCreateDto.getEmail());
            if (usuarioExist.isPresent() && !usuarioExist.get().getId().equals(id)){
                throw new IllegalArgumentException("Mail no disponible");
            }
            Usuario usuarioEntity= usuario.get();
            usuarioEntity.setNombre(usuarioCreateDto.getNombre());
            usuarioEntity.setEmail(usuarioCreateDto.getEmail());
            PerfilUsuario perfilUsuario= usuario.get().getPerfil();
            if (perfilUsuario == null){
                perfilUsuario = PerfilMapper.toEntity(usuarioCreateDto.getPerfilUsuarioDto());
                usuarioEntity.setPerfil(perfilUsuario);
            }else {
                perfilUsuario.setBio(usuarioCreateDto.getPerfilUsuarioDto().getBio());
                perfilUsuario.setColorFavorito(usuarioCreateDto.getPerfilUsuarioDto().getColorFavorito());
                perfilUsuario.setFraseDelDia(usuarioCreateDto.getPerfilUsuarioDto().getFraseDelDia());
            }
            Usuario usuarioActualizado = usuarioRepository.save(usuarioEntity);
            log.info("Usuario actualizado con id {} ", usuarioActualizado.getId());
            return UsuarioMapper.toDto(usuarioActualizado);
        }
        return null;
    }

    @Override
    public boolean eliminarUsuario(UUID id) {
        if (usuarioRepository.existsById(id)){
            usuarioRepository.deleteById(id);
            return true;
        }
        return false;
    }


}

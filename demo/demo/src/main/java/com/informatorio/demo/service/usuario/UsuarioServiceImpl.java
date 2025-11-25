package com.informatorio.demo.service.usuario;

import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.model.Usuario;
import com.informatorio.demo.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl  implements UsuarioService{

    private UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<UsuarioDto> obtenerTodos() {
        List<Usuario> usuarioList=usuarioRepository.findAll();
    }
}

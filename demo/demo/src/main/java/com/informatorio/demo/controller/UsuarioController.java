package com.informatorio.demo.controller;

import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.model.Usuario;
import com.informatorio.demo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/usuarios")
public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public UsuarioController() {}

    @GetMapping
    public List<UsuarioDto>getUsuarios(){
        List<UsuarioDto> usuarios = usuarioService.obtenerTodos();
        return usuarios;
    };
}

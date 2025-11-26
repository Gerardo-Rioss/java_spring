package com.informatorio.demo.controller;

import com.informatorio.demo.dto.usuario.UsuarioCreateDto;
import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.model.Usuario;
import com.informatorio.demo.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDto>getUsuarioById( @PathVariable UUID id
    ){
        Optional<UsuarioDto> usuario = usuarioService.obtenerPorId(id);
        if (usuario.isPresent()){
            return ResponseEntity.ok(usuario.get());
        }else{
            return ResponseEntity.notFound().build();
        }
    };

    @PostMapping
    public ResponseEntity<UsuarioDto> createUsuario(
            @RequestBody UsuarioCreateDto usuarioCreateDto
            ){
        UsuarioDto usuarioCreado = usuarioService.crearUsuario(usuarioCreateDto);
        return ResponseEntity.ok(usuarioCreado);

    };


}

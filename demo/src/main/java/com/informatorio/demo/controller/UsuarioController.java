package com.informatorio.demo.controller;

import com.informatorio.demo.dto.usuario.UsuarioCreateDto;
import com.informatorio.demo.dto.usuario.UsuarioDto;
import com.informatorio.demo.service.usuario.UsuarioService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@Slf4j


public class UsuarioController {

    private UsuarioService usuarioService;

    @Autowired
    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }
    public UsuarioController() {}

    @GetMapping
    public List<UsuarioDto>getUsuarios(
            @RequestParam(required = false) String nombre,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String colorFavorito
    ){
        List<UsuarioDto> usuarios = usuarioService.obtenerTodos(nombre,email, colorFavorito);
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
            @Valid @RequestBody UsuarioCreateDto usuarioCreateDto
            ){
        UsuarioDto usuarioCreado = usuarioService.crearUsuario(usuarioCreateDto);
        return ResponseEntity
                .created(URI.create("/api/v1/usuarios"+ usuarioCreado.getId()))
                .body(usuarioCreado);
    };

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDto> updateUsuario(
            @PathVariable(name = "id") UUID id,
            @Valid @RequestBody UsuarioCreateDto usuarioCreateDto
    ){
    log.info("Solicitud para actualizar usuario con id {}", id);
    UsuarioDto usuarioDto = usuarioService.updateUsuario(id,usuarioCreateDto );
    if (usuarioDto==null){
        return ResponseEntity.notFound().build();
    }
    ResponseEntity.ok(usuarioDto);
    return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario (
            @PathVariable(name = "id") UUID id
    ){
        boolean wasDeleted =usuarioService.eliminarUsuario(id);
        if (!wasDeleted){
            ResponseEntity.notFound().build();
        }
        return ResponseEntity.noContent().build();
    }


}

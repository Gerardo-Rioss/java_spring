package com.informatorio.demo.repository.usuario;

import com.informatorio.demo.model.Usuario;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID>, JpaSpecificationExecutor<Usuario> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario>findAllByNombreEqualsIgnoreCase(String nombre);
    List<Usuario>findAllByEmailContainingIgnoreCase(String email);
    List<Usuario>findAllByEmailContainingIgnoreCaseAndNombreEqualsIgnoreCase(String email, String nombre);
    }

package com.informatorio.demo.repository.usuario.specification;

import com.informatorio.demo.model.Usuario;
import org.springframework.data.jpa.domain.Specification;

public final class UsuarioSpecifications {
    public UsuarioSpecifications() {}


    public static Specification<Usuario> nombre(final String nombre){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                        root.get("nombre")
                )
                , "%" + nombre.toLowerCase() + "%"
                ));

    }

    public static Specification<Usuario> email(final String email){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(
                                root.get("email")
                        )
                        , "%" + email.toLowerCase() + "%"
                ));

    }

    public static Specification<Usuario> colorFavorito(String colorFavorito){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        criteriaBuilder.lower(
                                root.join("perfil").get("colorFavorito")
                        ),
                        colorFavorito.toLowerCase()
                ));
    }
}

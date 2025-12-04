package com.informatorio.demo.repository.entradaDiaria.specification;

import com.informatorio.demo.model.EntradaDiaria;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.UUID;

public final class EntradaDiariaSpecification {
    private EntradaDiariaSpecification() {
    }

    public static Specification<EntradaDiaria> porUsuarioId(final UUID usuarioId) {
        return ((root, query, criteriaBuilder) ->
                // Hacemos un JOIN a 'usuario' y obtenemos su 'id'
                criteriaBuilder.equal(root.join("usuario").get("id"), usuarioId)
        );
    }

    public static Specification<EntradaDiaria> fechaDesde(final LocalDate desde){
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("fecha"),desde)
                );
    }

    public static Specification<EntradaDiaria> fechaHasta(final LocalDate hasta){
        return ((root, query, criteriaBuilder) ->
        criteriaBuilder.lessThanOrEqualTo(root.get("fecha"),hasta)
                );
    }

}
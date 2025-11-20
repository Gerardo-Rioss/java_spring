package com.informatorio.demo.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Habito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    @Enumerated(EnumType.STRING)
    private NivelDeImportanciaEnum nivelDeImportanciaEnum;
    @ManyToMany(mappedBy = "habitos")
    private List<EntradaDiaria> entradas;
}

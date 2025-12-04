package com.informatorio.demo.repository.entradaDiaria;

import com.informatorio.demo.model.EntradaDiaria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EntradaDiariaRepository extends JpaRepository<EntradaDiaria, Long>, JpaSpecificationExecutor<EntradaDiaria> {
}

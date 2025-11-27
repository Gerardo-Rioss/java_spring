package com.informatorio.demo.repository.habito;

import com.informatorio.demo.model.Habito;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitoRepository extends JpaRepository<Habito, Long> {
}

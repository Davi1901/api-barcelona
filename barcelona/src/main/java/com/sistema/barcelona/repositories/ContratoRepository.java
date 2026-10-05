package com.sistema.barcelona.repositories;

import com.sistema.barcelona.models.ContratoModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContratoRepository extends JpaRepository<ContratoModel, Long> {
}

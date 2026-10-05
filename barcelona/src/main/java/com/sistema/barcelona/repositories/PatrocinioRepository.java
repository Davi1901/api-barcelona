package com.sistema.barcelona.repositories;

import com.sistema.barcelona.models.PatrocinioModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatrocinioRepository extends JpaRepository<PatrocinioModel, Long> {
}

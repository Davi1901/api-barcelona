package com.sistema.barcelona.repositories;

import com.sistema.barcelona.models.TreinadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TreinadorRepository extends JpaRepository<TreinadorModel, Long> {
}

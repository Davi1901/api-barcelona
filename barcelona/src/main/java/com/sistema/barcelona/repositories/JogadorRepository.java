package com.sistema.barcelona.repositories;

import com.sistema.barcelona.enums.PosicaoEnum;
import com.sistema.barcelona.models.JogadorModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JogadorRepository extends JpaRepository<JogadorModel, Long>{
    List<JogadorModel> findByPosition(PosicaoEnum position);
}

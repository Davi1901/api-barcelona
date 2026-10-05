package com.sistema.barcelona.repositories;

import com.sistema.barcelona.models.TimeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TimeRepository extends JpaRepository<TimeModel, Long> {

}

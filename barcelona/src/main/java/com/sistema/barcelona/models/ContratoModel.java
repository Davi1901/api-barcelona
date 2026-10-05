package com.sistema.barcelona.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

import java.math.BigDecimal;

@Entity
public class ContratoModel extends RepresentationModel<ContratoModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String duracaoContrato;
    private BigDecimal salary;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDuracaoContrato() {
        return duracaoContrato;
    }

    public void setDuracaoContrato(String duracaoContrato) {
        this.duracaoContrato = duracaoContrato;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }
}

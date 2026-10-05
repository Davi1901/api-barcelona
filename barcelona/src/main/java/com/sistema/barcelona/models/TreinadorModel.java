package com.sistema.barcelona.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class TreinadorModel extends RepresentationModel<TreinadorModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nameTreinador;
    private int ageTreinador;
    private String nacionalidade;
    private int experiencia;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTreinador() {
        return nameTreinador;
    }

    public void setNameTreinador(String nameTreinador) {
        this.nameTreinador = nameTreinador;
    }

    public int getAgeTreinador() {
        return ageTreinador;
    }

    public void setAgeTreinador(int ageTreinador) {
        this.ageTreinador = ageTreinador;
    }

    public String getNacionalidade() {
        return nacionalidade;
    }

    public void setNacionalidade(String nacionalidade) {
        this.nacionalidade = nacionalidade;
    }

    public int getExperiencia() {
        return experiencia;
    }

    public void setExperiencia(int experiencia) {
        this.experiencia = experiencia;
    }
}

package com.sistema.barcelona.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import java.util.List;

@Entity
public class TimeModel extends RepresentationModel<TimeModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long id;
    private String nameTime;
    private String categoria;
    private String nameEstadio;
    private String paisOrigem;

    @JsonIgnore
    @OneToMany(mappedBy = "time", cascade = CascadeType.ALL)
    private List<JogadorModel> jogadores;

    // Relação Many-to-Many com Patrocínio (só aparece no JSON se não estiver vazia)
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    @ManyToMany
    @JoinTable(
            name = "tb_time_patrocinio",
            joinColumns = @JoinColumn(name = "time_id"),
            inverseJoinColumns = @JoinColumn(name = "patrocinio_id")
    )
    private List<PatrocinioModel> patrocinios;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameTime() {
        return nameTime;
    }

    public void setNameTime(String nameTime) {
        this.nameTime = nameTime;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getNameEstadio() {
        return nameEstadio;
    }

    public void setNameEstadio(String nameEstadio) {
        this.nameEstadio = nameEstadio;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }

    public List<JogadorModel> getJogadores() {
        return jogadores;
    }

    public void setJogadores(List<JogadorModel> jogadores) {
        this.jogadores = jogadores;
    }

    public List<PatrocinioModel> getPatrocinios() {
        return patrocinios;
    }

    public void setPatrocinios(List<PatrocinioModel> patrocinios) {
        this.patrocinios = patrocinios;
    }
}
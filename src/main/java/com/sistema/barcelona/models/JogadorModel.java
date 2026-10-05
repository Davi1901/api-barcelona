package com.sistema.barcelona.models;

import com.sistema.barcelona.enums.PosicaoEnum;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;

@Entity
public class JogadorModel extends RepresentationModel<JogadorModel> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private int numberShirt;

    @Enumerated(EnumType.STRING)
    private PosicaoEnum position;

    @OneToOne
    private ContratoModel contrato;

    // --- Adiciona esta parte para ligar ao Time ---
    @ManyToOne
    @JoinColumn(name = "time_id") // Cria a coluna "time_id" na tabela de jogadores
    private TimeModel time;

    public TimeModel getTime() {
        return time;
    }

    public void setTime(TimeModel time) {
        this.time = time;
    }
    // ----------------------------------------------

    public ContratoModel getContrato() {
        return contrato;
    }

    public void setContrato(ContratoModel contrato) {
        this.contrato = contrato;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberShirt() {
        return numberShirt;
    }

    public void setNumberShirt(int numberShirt) {
        this.numberShirt = numberShirt;
    }

    public PosicaoEnum getPosition() {
        return position;
    }

    public void setPosition(PosicaoEnum position) {
        this.position = position;
    }
}
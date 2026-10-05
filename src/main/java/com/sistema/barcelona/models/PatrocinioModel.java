package com.sistema.barcelona.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import org.springframework.hateoas.RepresentationModel;
import java.math.BigDecimal;
import java.util.List;

@Entity
public class PatrocinioModel extends RepresentationModel<PatrocinioModel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nameEmpresa;
    private BigDecimal valorContrato;
    private String tipoPatrocinio;
    private String dataVigencia;

    @JsonIgnore
    @ManyToMany(mappedBy = "patrocinios")
    private List<TimeModel> times;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEmpresa() {
        return nameEmpresa;
    }

    public void setNameEmpresa(String nameEmpresa) {
        this.nameEmpresa = nameEmpresa;
    }

    public BigDecimal getValorContrato() {
        return valorContrato;
    }

    public void setValorContrato(BigDecimal valorContrato) {
        this.valorContrato = valorContrato;
    }

    public String getTipoPatrocinio() {
        return tipoPatrocinio;
    }

    public void setTipoPatrocinio(String tipoPatrocinio) {
        this.tipoPatrocinio = tipoPatrocinio;
    }

    public String getDataVigencia() {
        return dataVigencia;
    }

    public void setDataVigencia(String dataVigencia) {
        this.dataVigencia = dataVigencia;
    }

    public List<TimeModel> getTimes() {
        return times;
    }

    public void setTimes(List<TimeModel> times) {
        this.times = times;
    }
}
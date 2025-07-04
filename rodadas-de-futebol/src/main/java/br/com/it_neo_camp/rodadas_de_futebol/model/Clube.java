package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;


@Entity
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    @Size(min = 2)
    private String nomeClube;
    private Integer totalPontos;
    private Integer totalGols;
    private Integer totalVitorias;
    private Integer totalJogos;
    @NotBlank
    private String siglaEstado;
    private String estadoClube;
    private String dataCriacao;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public Integer getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }

    public Integer getTotalGols() {
        return totalGols;
    }

    public void setTotalGols(Integer totalGols) {
        this.totalGols = totalGols;
    }

    public Integer getTotalVitorias() {
        return totalVitorias;
    }

    public void setTotalVitorias(Integer totalVitorias) {
        this.totalVitorias = totalVitorias;
    }

    public Integer getTotalJogos() {
        return totalJogos;
    }

    public void setTotalJogos(Integer totalJogos) {
        this.totalJogos = totalJogos;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }

    public String getEstadoClube() {
        return estadoClube;
    }

    public void setEstadoClube(String estadoClube) {
        this.estadoClube = estadoClube;
    }

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
}

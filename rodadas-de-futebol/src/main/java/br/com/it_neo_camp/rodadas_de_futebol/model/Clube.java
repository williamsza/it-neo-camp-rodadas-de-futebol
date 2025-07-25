package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

//@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clubes")
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   // @NotNull
   // @Size(min = 2)
   @Column(name = "nome_clube")
    private String nome;
    private Integer totalPontos;
    private Integer totalGols;
    private Integer totalVitorias;
    private Integer totalJogos;
    //@NotBlank
    private String siglaEstado;
    private String estadoClube;
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private Boolean ativo;

    public Clube(Long clubeMandanteId, String mandante, boolean b, LocalDateTime localDateTime) {
        this.id = clubeMandanteId;
        this.nome = mandante;
        this.ativo = b;
        this.dataCriacao = localDateTime;
        this.totalPontos = 0;
        this.totalGols = 0;
        this.totalVitorias = 0;
        this.totalJogos = 0;
    }


    public void setStatus(boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getStatus() {
        return this.ativo;
    }

    public boolean isAtivo() {
        return this.ativo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

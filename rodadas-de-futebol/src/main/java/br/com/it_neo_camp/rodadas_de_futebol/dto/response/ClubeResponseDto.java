package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;

import java.time.LocalDateTime;

public class ClubeResponseDto {

    private Long id;
    private String nomeClube;
    private String estadoClube;
    private String siglaEstado;
    private LocalDateTime dataCriacao;
    private Boolean ativo;

    public ClubeResponseDto(Long id, String nomeClube, String estadoDoClube, String siglaDoClube, String siglaEstado, LocalDateTime dataDeCriacao, Boolean statusClube, Clube clubeSalvo) {
        this.id = id;
        this.nomeClube = nomeClube;
        this.estadoClube = estadoDoClube;
        this.dataCriacao = dataDeCriacao;
        this.siglaEstado = siglaEstado;
        this.ativo = ativo;

    }

    public ClubeResponseDto(Clube clubeSalvo) {
        this.id = clubeSalvo.getId();
        this.nomeClube = clubeSalvo.getNome();
        this.estadoClube = clubeSalvo.getEstadoClube();
        this.dataCriacao = clubeSalvo.getDataCriacao();
        this.siglaEstado = clubeSalvo.getSiglaEstado();
        //this.statusClube = clubeSalvo.isStatusClube();
        this.ativo = clubeSalvo.isAtivo();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
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

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }
}

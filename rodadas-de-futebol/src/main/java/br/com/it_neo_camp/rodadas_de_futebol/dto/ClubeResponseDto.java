package br.com.it_neo_camp.rodadas_de_futebol.dto;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;

import java.time.LocalDate;

public class ClubeResponseDto {

    private Long id;
    private String nomeClube;
    private String estadoDoClube;
    private String siglaDoClube;
    private LocalDate dataDeCriacao;
    private Boolean statusClube;
    private Clube clubeSalvo;

    public ClubeResponseDto(Long id, String nomeClube, String estadoDoClube, String siglaDoClube, LocalDate dataDeCriacao, Boolean statusClube,Clube clubeSalvo) {
        this.id = id;
        this.nomeClube = nomeClube;
        this.estadoDoClube = estadoDoClube;
        this.siglaDoClube = siglaDoClube;
        this.dataDeCriacao = dataDeCriacao;
        this.statusClube = statusClube;

    }

    public ClubeResponseDto(Clube clubeSalvo) {
        this.id = clubeSalvo.getClubeId();
        this.nomeClube = clubeSalvo.getNomeClube();
        this.estadoDoClube = clubeSalvo.getEstadoClube();
        this.siglaDoClube = clubeSalvo.getSiglaEstado();
        this.dataDeCriacao = clubeSalvo.getDataCriacao();
        this.statusClube = clubeSalvo.isStatusClube();
    }
    public Clube getClubeSalvo() {
        return clubeSalvo;
    }

    public void setClubeSalvo(Clube clubeSalvo) {
        this.clubeSalvo = clubeSalvo;
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

    public String getEstadoDoClube() {
        return estadoDoClube;
    }

    public void setEstadoDoClube(String estadoDoClube) {
        this.estadoDoClube = estadoDoClube;
    }

    public String getSiglaDoClube() {
        return siglaDoClube;
    }

    public void setSiglaDoClube(String siglaDoClube) {
        this.siglaDoClube = siglaDoClube;
    }

    public LocalDate getDataDeCriacao() {
        return dataDeCriacao;
    }

    public void setDataDeCriacao(LocalDate dataDeCriacao) {
        this.dataDeCriacao = dataDeCriacao;
    }

    public Boolean getStatusClube() {
        return statusClube;
    }

    public void setStatusClube(Boolean statusClube) {
        this.statusClube = statusClube;
    }
}

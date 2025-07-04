package br.com.it_neo_camp.rodadas_de_futebol.model;

import java.time.LocalDateTime;

public class Partida {
    private long partidaId;
    private Clube clubeMandante;
    private Clube clubeVisitante;
    private Estadio estadio;
    private Integer golsMandante;
    private Integer placarVisitante;
    private LocalDateTime dataHora;

    public long getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(long partidaId) {
        this.partidaId = partidaId;
    }

    public Clube getClubeMandante() {
        return clubeMandante;
    }

    public void setClubeMandante(Clube clubeMandante) {
        this.clubeMandante = clubeMandante;
    }

    public Clube getClubeVisitante() {
        return clubeVisitante;
    }

    public void setClubeVisitante(Clube clubeVisitante) {
        this.clubeVisitante = clubeVisitante;
    }

    public Estadio getEstadio() {
        return estadio;
    }

    public void setEstadio(Estadio estadio) {
        this.estadio = estadio;
    }

    public Integer getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(Integer golsMandante) {
        this.golsMandante = golsMandante;
    }

    public Integer getPlacarVisitante() {
        return placarVisitante;
    }

    public void setPlacarVisitante(Integer placarVisitante) {
        this.placarVisitante = placarVisitante;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

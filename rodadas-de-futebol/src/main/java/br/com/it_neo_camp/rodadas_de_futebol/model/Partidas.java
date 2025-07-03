package br.com.it_neo_camp.rodadas_de_futebol.model;

import java.time.LocalDate;

public class Partidas {
    private long uuid;
    private Clube clubeMandante;
    private Clube clubeVisitante;
    private Integer golsMandante;
    private Integer clubeVistante;
    private LocalDate data;

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
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

    public Integer getGolsMandante() {
        return golsMandante;
    }

    public void setGolsMandante(Integer golsMandante) {
        this.golsMandante = golsMandante;
    }

    public Integer getClubeVistante() {
        return clubeVistante;
    }

    public void setClubeVistante(Integer clubeVistante) {
        this.clubeVistante = clubeVistante;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }
}

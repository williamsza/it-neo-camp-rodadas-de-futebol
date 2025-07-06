package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partidaId;
    @ManyToOne
    @JoinColumn(name = "clube_mandante_id", nullable = false)
    private Clube clubeMandante;
    @ManyToOne
    @JoinColumn(name = "clube_visitante_id", nullable = false)
    private Clube clubeVisitante;
    @ManyToOne
    @JoinColumn(name = "estadio_id", nullable = false)
    private Estadio estadio;
    private Integer golsMandante;
    private Integer placarVisitante;
    private LocalDateTime dataHora;

    public Partida() {
    }

    public Partida(long partidaId, Clube clubeMandante, Clube clubeVisitante, Estadio estadio, Integer golsMandante, Integer placarVisitante, LocalDateTime dataHora) {
        this.partidaId = partidaId;
        this.clubeMandante = clubeMandante;
        this.clubeVisitante = clubeVisitante;
        this.estadio = estadio;
        this.golsMandante = golsMandante;
        this.placarVisitante = placarVisitante;
        this.dataHora = dataHora;
    }

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

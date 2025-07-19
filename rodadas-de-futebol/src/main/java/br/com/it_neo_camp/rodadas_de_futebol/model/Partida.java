package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
@Entity
@Table(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long partidaId;

    @NotNull(message = "A data e hora da partida são obrigatórias.")
    @Column(nullable = false)
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "clube_mandante_id", nullable = false)
    private Clube clubeMandante;

    @ManyToOne
    @JoinColumn(name = "clube_visitante_id", nullable = false)
    private Clube clubeVisitante;

    @ManyToOne
    @JoinColumn(name = "estadio_id", nullable = false)
    private Estadio estadio;

    @NotNull(message = "Os gols do mandante são obrigatórios.")
    @Column(nullable = false)
    private Integer golsMandante;

    private Integer golsVisitante;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "O status da partida é obrigatório.")
    @Column(nullable = false)
    private StatusPartida statosPartida;

    public Partida() {
    }

    public Partida(long partidaId, Clube clubeMandante, Clube clubeVisitante, Estadio estadio, Integer golsMandante, Integer placarVisitante, LocalDateTime dataHora, StatusPartida statosPartida) {
        this.partidaId = partidaId;
        this.clubeMandante = clubeMandante;
        this.clubeVisitante = clubeVisitante;
        this.estadio = estadio;
        this.golsMandante = golsMandante;
        this.golsVisitante = placarVisitante;
        this.dataHora = dataHora;
        this.statosPartida = statosPartida;
    }

    public long getPartidaId() {
        return partidaId;
    }

    public void setPartidaId(LocalDateTime partidaId) {
        this.partidaId = partidaId;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
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

    public Integer getGolsVisitante() {
        return golsVisitante;
    }

    public void setGolsVisitante(Integer golsVisitante) {
        this.golsVisitante = golsVisitante;
    }

    public StatusPartida getStatusPartida() {
        return statosPartida;
    }

    public void setStatosPartida(StatusPartida statosPartida) {
        this.statosPartida = statosPartida;
    }
}

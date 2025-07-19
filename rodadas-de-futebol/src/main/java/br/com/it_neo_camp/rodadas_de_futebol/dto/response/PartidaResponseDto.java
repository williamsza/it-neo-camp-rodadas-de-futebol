package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import br.com.it_neo_camp.rodadas_de_futebol.model.StatusPartida;

import java.time.LocalDateTime;

public class PartidaResponseDto {
    private Long id;
    private LocalDateTime dataPartida;
    private String nomeMandante;
    private String nomeVisitante;
    private Long clubeMandanteId;
    private Long clubeVisistanteId;
    private Integer placarMandante;
    private Integer placarVisitante;
    private Long estadioId;
    private String estadioNome;
    private StatusPartida statusPartida;

    public PartidaResponseDto(Long id, LocalDateTime dataPartida, Long clubeMandanteId, String nomeMandante, Long clubeVisistanteId, String nomeVisitante, Long estadioId, String estadioNome, Integer placarMandante, Integer placarVisitante) {
        this.id = id;
        this.dataPartida = dataPartida;
        this.clubeMandanteId = clubeMandanteId;
        this.nomeMandante = nomeMandante;
        this.clubeVisistanteId = clubeVisistanteId;
        this.nomeVisitante = nomeVisitante;
        this.estadioId = estadioId;
        this.estadioNome = estadioNome;
        this.placarMandante = placarMandante;
        this.placarVisitante = placarVisitante;
        this.statusPartida = statusPartida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDateTime dataPartida) {
        this.dataPartida = dataPartida;
    }

    public String getNomeMandante() {
        return nomeMandante;
    }

    public void setNomeMandante(String nomeMandante) {
        this.nomeMandante = nomeMandante;
    }

    public String getNomeVisitante() {
        return nomeVisitante;
    }

    public void setNomeVisitante(String nomeVisitante) {
        this.nomeVisitante = nomeVisitante;
    }

    public Long getClubeMandanteId() {
        return clubeMandanteId;
    }

    public void setClubeMandanteId(Long clubeMandanteId) {
        this.clubeMandanteId = clubeMandanteId;
    }

    public Long getClubeVisistanteId() {
        return clubeVisistanteId;
    }

    public void setClubeVisistanteId(Long clubeVisistanteId) {
        this.clubeVisistanteId = clubeVisistanteId;
    }

    public Integer getPlacarMandante() {
        return placarMandante;
    }

    public void setPlacarMandante(Integer placarMandante) {
        this.placarMandante = placarMandante;
    }

    public Integer getPlacarVisitante() {
        return placarVisitante;
    }

    public void setPlacarVisitante(Integer placarVisitante) {
        this.placarVisitante = placarVisitante;
    }

    public Long getEstadioId() {
        return estadioId;
    }

    public void setEstadioId(Long estadioId) {
        this.estadioId = estadioId;
    }

    public String getEstadioNome() {
        return estadioNome;
    }

    public void setEstadioNome(String estadioNome) {
        this.estadioNome = estadioNome;
    }

    public StatusPartida getStatusPartida() {
        return statusPartida;
    }

    public void setStatusPartida(StatusPartida statusPartida) {
        this.statusPartida = statusPartida;
    }

    public PartidaResponseDto(Partida partida) {
        this.id = partida.getPartidaId();
        this.dataPartida = partida.getDataHora();
        this.nomeMandante = (partida.getClubeMandante() != null) ? partida.getClubeMandante().getNomeClube() : null;
        this.nomeVisitante = (partida.getGolsVisitante() != null) ? partida.getClubeVisitante().getNomeClube() : null;
        this.placarMandante = partida.getGolsMandante();
        this.placarVisitante = partida.getGolsVisitante();
        this.statusPartida = partida.getStatusPartida();


    }

}

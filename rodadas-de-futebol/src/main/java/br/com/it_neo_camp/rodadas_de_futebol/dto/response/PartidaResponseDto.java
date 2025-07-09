package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import java.time.LocalDateTime;

public class PartidaResponseDto {
    private Long id;
    private Long clubeMandanteId;
    private String clubeMandanteNome;
    private Long clubeVisistanteId;
    private String clubeVisitanteNome;
    private Long estadioId;
    private String estadioNome;
    private Integer placarMandante;
    private Integer placarVisitante;
    private LocalDateTime dataHora;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClubeMandanteId() {
        return clubeMandanteId;
    }

    public void setClubeMandanteId(Long clubeMandanteId) {
        this.clubeMandanteId = clubeMandanteId;
    }

    public String getClubeMandanteNome() {
        return clubeMandanteNome;
    }

    public void setClubeMandanteNome(String clubeMandanteNome) {
        this.clubeMandanteNome = clubeMandanteNome;
    }

    public Long getClubeVisistanteId() {
        return clubeVisistanteId;
    }

    public void setClubeVisistanteId(Long clubeVisistanteId) {
        this.clubeVisistanteId = clubeVisistanteId;
    }

    public String getClubeVisitanteNome() {
        return clubeVisitanteNome;
    }

    public void setClubeVisitanteNome(String clubeVisitanteNome) {
        this.clubeVisitanteNome = clubeVisitanteNome;
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

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}

package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import java.time.LocalDateTime;

public class PartidaResponseDto {
    private Long id;
    private LocalDateTime dataPartida;
    private Long clubeMandanteId;
    private String nomeMandante;
    private Long clubeVisistanteId;
    private String nomeVisitante;
    private Long estadioId;
    private String estadioNome;
    private Integer placarMandante;
    private Integer placarVisitante;

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
    }

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

    public String getNomeMandante() {
        return nomeMandante;
    }

    public void setNomeMandante(String nomeMandante) {
        this.nomeMandante = nomeMandante;
    }

    public Long getClubeVisistanteId() {
        return clubeVisistanteId;
    }

    public void setClubeVisistanteId(Long clubeVisistanteId) {
        this.clubeVisistanteId = clubeVisistanteId;
    }

    public String getNomeVisitante() {
        return nomeVisitante;
    }

    public void setNomeVisitante(String nomeVisitante) {
        this.nomeVisitante = nomeVisitante;
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

    public LocalDateTime getDataPartida() {
        return dataPartida;
    }

    public void setDataPartida(LocalDateTime dataPartida) {
        this.dataPartida = dataPartida;
    }
}

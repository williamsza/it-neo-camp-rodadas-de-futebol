package br.com.it_neo_camp.rodadas_de_futebol.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

import java.time.LocalDateTime;

public class PartidasRequestDto {

    @NotNull(message = "O ID do clube mandante é obrigatório.")
    private Long clubeMandanteId;
    @NotNull(message = "O ID do clube visitante é obrigatório.")
    private Long clubeVisitanteId;
    @NotNull(message = "O ID do estádio é obrigatório.")
    private Long estadioId;
    @NotNull(message = "O placar do mandante é obrigatório.")
    @Min(value = 0, message = "O placar do mandante não pode ser negativo.")
    private Integer placarMandante;

    @NotNull(message = "O placar do visitante é obrigatório.")
    @Min(value = 0, message = "O placar do visitante não pode ser negativo.")
    private Integer placarVisitante;
    @NotNull(message = "A data e hora da partida são obrigatórias.")
    @PastOrPresent(message = "A data e hora da partida não podem estar no futuro.")
    private LocalDateTime dataHora;

    public Long getClubeMandanteId() {
        return clubeMandanteId;
    }

    public void setClubeMandanteId(Long clubeMandanteId) {
        this.clubeMandanteId = clubeMandanteId;
    }

    public Long getClubeVisitanteId() {
        return clubeVisitanteId;
    }

    public void setClubeVisitanteId(Long clubeVisitanteId) {
        this.clubeVisitanteId = clubeVisitanteId;
    }

    public Long getEstadioId() {
        return estadioId;
    }

    public void setEstadioId(Long estadioId) {
        this.estadioId = estadioId;
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

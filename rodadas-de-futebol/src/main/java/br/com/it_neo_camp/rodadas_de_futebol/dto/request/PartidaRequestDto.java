package br.com.it_neo_camp.rodadas_de_futebol.dto.request;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PartidaRequestDto {


    @NotNull(message = "A data e hora da partida são obrigatórias.")
    @PastOrPresent(message = "A data e hora da partida não podem estar no futuro.")
    @FutureOrPresent(message = "A data da partida não pode ser no passado.")
    private LocalDateTime dataDaPartida;

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

    private String StatusPartida;

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


}

package br.com.it_neo_camp.rodadas_de_futebol.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
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
    //@PastOrPresent(message = "A data e hora da partida não podem estar no futuro.")
    @Future(message = "A data da partida não pode ser no passado.")
    private LocalDateTime dataHora;

}

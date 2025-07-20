package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import br.com.it_neo_camp.rodadas_de_futebol.model.StatusPartida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

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




}

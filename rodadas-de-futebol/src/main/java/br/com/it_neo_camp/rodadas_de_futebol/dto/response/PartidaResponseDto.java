package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import br.com.it_neo_camp.rodadas_de_futebol.model.StatusPartida;
import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@NoArgsConstructor

public class PartidaResponseDto {
    private Long id;
    private LocalDateTime dataHora;
    private String nomeMandante;
    private String nomeVisitante;
    private Long clubeMandanteId;
    private Long clubeVisistanteId;
    private Integer placarMandante;
    private Integer placarVisitante;
    //private Long estadioId;
    private String estadioNome;
    private StatusPartida statusPartida;
    private boolean goleada;

    public static PartidaResponseDto fromEntity(Partida partida) {
        PartidaResponseDto dto = new PartidaResponseDto();
        dto.setId(partida.getId());
        dto.setDataHora(partida.getDataHora());
        dto.setClubeMandanteId(partida.getClubeMandante().getId());
        dto.setNomeMandante(partida.getClubeMandante().getNome());
        dto.setClubeVisistanteId(partida.getClubeVisitante().getId());
        dto.setNomeVisitante(partida.getClubeVisitante().getNome());
        dto.setPlacarMandante(partida.getGolsMandante());
        dto.setPlacarVisitante(partida.getGolsVisitante());
        //dto.setEstadioId(partida.getEstadio().getId());
        dto.setEstadioNome(partida.getEstadio().getNome());
        //dto.setStatusPartida(partida.getStatusPartida());
        dto.setGoleada(partida.isGoleada());
        return dto;
    }





}

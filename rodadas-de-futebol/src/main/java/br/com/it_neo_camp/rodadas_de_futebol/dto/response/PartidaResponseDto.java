package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
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
    private String nomeMandante;
    private String nomeVisitante;
    private Long clubeMandanteId;
    private Long clubeVisistanteId;
    private Integer placarMandante;
    private Integer placarVisitante;
    private String estadioNome;
    private Long estadioId;
    private LocalDateTime dataHora;

    public PartidaResponseDto(Partida partida) {
        if (partida == null) {

            return;
        }

        this.id = partida.getId();
        this.nomeMandante = partida.getClubeMandante().getNome();
        this.nomeVisitante = partida.getClubeVisitante().getNome();
        this.clubeMandanteId = partida.getClubeMandante().getId();
        this.clubeVisistanteId = partida.getClubeVisitante().getId();
        this.placarMandante = partida.getPlacarMandante();
        this.placarVisitante = partida.getPlacarVisitante();
        this.estadioNome = partida.getEstadio().getNome();
        this.estadioId = partida.getEstadio().getId();
        this.dataHora = partida.getDataHora();
    }

    public static PartidaResponseDto fromEntity(Partida partidaSalva) {
        if (partidaSalva == null) return null;

        PartidaResponseDto dto = new PartidaResponseDto();

        dto.setId(partidaSalva.getId());
        dto.setNomeMandante(partidaSalva.getClubeMandante().getNome());
        dto.setNomeVisitante(partidaSalva.getClubeVisitante().getNome());
        dto.setClubeMandanteId(partidaSalva.getClubeMandante().getId());
        dto.setClubeVisistanteId(partidaSalva.getClubeVisitante().getId());
        dto.setPlacarMandante(partidaSalva.getPlacarMandante());
        dto.setPlacarVisitante(partidaSalva.getPlacarVisitante());
        dto.setEstadioNome(partidaSalva.getEstadio().getNome());
        dto.setEstadioId(partidaSalva.getEstadio().getId());
        dto.setDataHora(partidaSalva.getDataHora() != null ? partidaSalva.getDataHora() : null);

        return dto;
    }

}

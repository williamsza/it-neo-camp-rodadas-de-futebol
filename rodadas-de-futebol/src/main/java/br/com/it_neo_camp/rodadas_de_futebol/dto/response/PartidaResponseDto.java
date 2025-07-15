package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

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
    private LocalDate dataHora;

    public PartidaResponseDto(Partida partida) {
        if (partida == null) {
            // Se for nulo, talvez lançar uma exceção ou retornar um DTO com campos nulos.
            // Para "pesquisarPartidas", o caso de partida nula aqui não deveria ocorrer,
            // pois o Stream já filtraria. Mas é bom ter uma verificação defensiva.
            return; // Ou lançar new IllegalArgumentException("Partida não pode ser nula para mapeamento.");
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

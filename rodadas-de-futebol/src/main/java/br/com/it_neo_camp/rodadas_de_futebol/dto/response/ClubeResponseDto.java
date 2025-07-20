package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class ClubeResponseDto {

    private Long id;
    private String nome;
    //private String estadoDoClube;
    private String siglaDoClube;
    private LocalDateTime dataDeCriacao;
   // private Boolean statusClube;
    private Clube clubeSalvo;
    private Boolean ativo;


    public ClubeResponseDto(Clube clubeSalvo) {
        this.id = clubeSalvo.getId();
        this.nome = clubeSalvo.getNome();
       // this.estadoDoClube = clubeSalvo.getEstadoClube();
        this.siglaDoClube = clubeSalvo.getSiglaEstado();
        this.dataDeCriacao = clubeSalvo.getDataCriacao();
        //this.statusClube = clubeSalvo.isStatusClube();
    }

}

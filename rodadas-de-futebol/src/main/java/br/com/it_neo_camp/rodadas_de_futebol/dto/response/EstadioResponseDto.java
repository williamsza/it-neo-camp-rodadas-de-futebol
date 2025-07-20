package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor

public class EstadioResponseDto {
    private Long id;
    private String nome;

    public EstadioResponseDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public EstadioResponseDto(Estadio estadioSalvo) {
        this.id = estadioSalvo.getId();
        this.nome = estadioSalvo.getNome();
    }


}

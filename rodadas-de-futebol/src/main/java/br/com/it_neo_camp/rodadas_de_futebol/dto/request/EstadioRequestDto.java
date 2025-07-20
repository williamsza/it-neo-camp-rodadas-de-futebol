package br.com.it_neo_camp.rodadas_de_futebol.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EstadioRequestDto {

    @NotBlank(message = "Nome do estádio é obrigatório.")
    @Size(min = 3, message = "Nome do estádio deve ter ao menos 3 caracteres.")
    private String nomeEstadio;


}

package br.com.it_neo_camp.rodadas_de_futebol.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EstadioDto {

    @NotBlank(message = "Nome do estádio é obrigatório.")
    @Size(min = 3, message = "Nome do estádio deve ter ao menos 3 caracteres.")
    private String nomeEstadioDto;

}

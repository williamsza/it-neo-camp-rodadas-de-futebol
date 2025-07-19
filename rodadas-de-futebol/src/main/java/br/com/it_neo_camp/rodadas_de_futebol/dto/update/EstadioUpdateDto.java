package br.com.it_neo_camp.rodadas_de_futebol.dto.update;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EstadioUpdateDto {
    @NotBlank(message = "Nome do estádio é obrigatório para atualização")
    @Size(min = 3,max = 100, message = "Nome do estádio deve ter entre 3 e 100 caracteres")
    private String nomeEstadio;

    public EstadioUpdateDto() {
    }

    public EstadioUpdateDto(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
}

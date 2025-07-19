package br.com.it_neo_camp.rodadas_de_futebol.dto.request;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClubeRequestDto {

    @NotBlank(message = "Nome do clube é obrigatório.")
    @Size(min = 3, message = "Nome do clube deve ter ao menos 3 letras.")
    private String nomeClube;
    private String estadoClube;
    @NotBlank(message = "Sigla do Estado é obrigatória.")
    @Pattern(regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO", message = "Sigla do Estado deve ser uma UF válida do Brasil.")
    private String siglaEstado;
    @NotNull(message = "Data de criação é obrigatória.")
    @PastOrPresent(message = "Data de criação não pode estar no futuro.")
    private LocalDateTime dataCriacao;
    //private Boolean statusClube;
    private Boolean ativo;


}

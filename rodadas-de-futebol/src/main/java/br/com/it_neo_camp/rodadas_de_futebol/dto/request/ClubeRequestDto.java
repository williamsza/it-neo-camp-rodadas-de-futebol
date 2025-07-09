package br.com.it_neo_camp.rodadas_de_futebol.dto.request;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClubeRequestDto {

    @NotBlank
    @Size(min = 2, message = "Nome do clube deve ter ao menos 2 letras.")
    private String nomeClube;
    private String estadoDoClube;
    @NotBlank(message = "Sigla do Estado é obrigatória.")
    @Pattern(regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO", message = "Sigla do Estado deve ser uma UF válida do Brasil.")
    private String siglaEstado;
    @NotNull(message = "Data de criação é obrigatória.")
    @PastOrPresent(message = "Data de criação não pode estar no futuro.")
    private LocalDate dataCriacao;
    @NotNull(message = "O campo 'ativo' é obrigatório.")
    private Boolean statusClube;

    public String getNomeClube() {
        return nomeClube;
    }

    public void setNomeClube(String nomeClube) {
        this.nomeClube = nomeClube;
    }

    public String getSiglaEstado() {
        return siglaEstado;
    }

    public void setSiglaEstado(String siglaEstado) {
        this.siglaEstado = siglaEstado;
    }


    public String getEstadoDoClube() {
        return estadoDoClube;
    }

    public void setEstadoDoClube(String estadoDoClube) {
        this.estadoDoClube = estadoDoClube;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getStatusClube() {
        return statusClube;
    }

    public void setStatusClube(Boolean statusClube) {
        this.statusClube = statusClube;
    }


}

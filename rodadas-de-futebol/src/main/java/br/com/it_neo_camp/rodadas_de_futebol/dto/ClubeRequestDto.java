package br.com.it_neo_camp.rodadas_de_futebol.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public class ClubeRequestDto {

    @NotBlank
    @Size(min = 2, message = "Nome do clube deve ter ao menos 2 letras.")
    private String nomeClubeDto;
    private String estadoDoClubeDto;
    @NotBlank(message = "Sigla do Estado é obrigatória.")
    @Pattern(regexp = "AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO", message = "Sigla do Estado deve ser uma UF válida do Brasil.")
    private String siglaEstadoDto;
    @NotNull(message = "Data de criação é obrigatória.")
    @PastOrPresent(message = "Data de criação não pode estar no futuro.")
    private LocalDate dataCriacaoDto;
    @NotNull(message = "O campo 'ativo' é obrigatório.")
    private Boolean statusClubeDto;

    public String getNomeClubeDto() {
        return nomeClubeDto;
    }

    public void setNomeClubeDto(String nomeClubeDto) {
        this.nomeClubeDto = nomeClubeDto;
    }

    public String getSiglaEstadoDto() {
        return siglaEstadoDto;
    }

    public String getEstadoDoClubeDto() {
        return estadoDoClubeDto;
    }

    public void setEstadoDoClubeDto(String estadoDoClubeDto) {
        this.estadoDoClubeDto = estadoDoClubeDto;
    }

    public void setSiglaEstadoDto(String siglaEstadoDto) {
        this.siglaEstadoDto = siglaEstadoDto;
    }

    public LocalDate getDataCriacaoDto() {
        return dataCriacaoDto;
    }

    public void setDataCriacaoDto(LocalDate dataCriacaoDto) {
        this.dataCriacaoDto = dataCriacaoDto;
    }

    public Boolean getStatusClubeDto() {
        return statusClubeDto;
    }

    public void setStatusClubeDto(Boolean statusClubeDto) {
        this.statusClubeDto = statusClubeDto;
    }



}

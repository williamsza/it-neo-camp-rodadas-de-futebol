package br.com.it_neo_camp.rodadas_de_futebol.dto;

import java.time.LocalDate;

public class ClubeRequestDto {

    private String nome;
    private String estado;
    private String sigla;
    private LocalDate data;
    private Boolean status;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}

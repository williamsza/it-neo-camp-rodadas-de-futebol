package br.com.it_neo_camp.rodadas_de_futebol.model;

public class Sede {
    private String dataCriacao;
    private Boolean ativo = false;

    public String getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}

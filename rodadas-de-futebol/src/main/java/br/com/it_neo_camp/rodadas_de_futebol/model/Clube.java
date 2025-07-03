package br.com.it_neo_camp.rodadas_de_futebol.model;

public class Clube {

    private long uuid;
    private String nome;
    private Integer totalPontos;
    private Integer totalGols;
    private Integer totalVitorias;
    private Integer totalJogos;

    private String sigla;
    private String estado;
    private String data;

    public long getUuid() {
        return uuid;
    }

    public void setUuid(long uuid) {
        this.uuid = uuid;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getTotalPontos() {
        return totalPontos;
    }

    public void setTotalPontos(Integer totalPontos) {
        this.totalPontos = totalPontos;
    }

    public Integer getTotalGols() {
        return totalGols;
    }

    public void setTotalGols(Integer totalGols) {
        this.totalGols = totalGols;
    }

    public Integer getTotalVitorias() {
        return totalVitorias;
    }

    public void setTotalVitorias(Integer totalVitorias) {
        this.totalVitorias = totalVitorias;
    }

    public Integer getTotalJogos() {
        return totalJogos;
    }

    public void setTotalJogos(Integer totalJogos) {
        this.totalJogos = totalJogos;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}

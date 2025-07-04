package br.com.it_neo_camp.rodadas_de_futebol.model;

public class Estadio {

    private Long estadioId;
    private String nomeEstadio;

    public Long getEstadioId() {
        return estadioId;
    }

    public void setEstadioId(Long estadioId) {
        this.estadioId = estadioId;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
}

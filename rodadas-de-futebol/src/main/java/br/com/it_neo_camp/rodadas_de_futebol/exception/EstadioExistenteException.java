package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class EstadioExistenteException extends RuntimeException {
    public EstadioExistenteException(String nomeEstadio) {
        super("Já existe um estádio com o nome: " + nomeEstadio);
    }
}

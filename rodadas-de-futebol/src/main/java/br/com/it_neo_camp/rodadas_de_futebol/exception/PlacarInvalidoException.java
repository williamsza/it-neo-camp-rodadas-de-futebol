package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class PlacarInvalidoException extends RuntimeException {
    public PlacarInvalidoException(String message) {
        super(message);
    }
}
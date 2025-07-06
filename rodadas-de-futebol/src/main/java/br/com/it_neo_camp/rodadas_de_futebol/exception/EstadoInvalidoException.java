package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class EstadoInvalidoException extends RuntimeException {
    public EstadoInvalidoException(String sigla) {
        super("Sigla de estado inválida: " + sigla + " - deve conter 2 caracteres");
    }
}

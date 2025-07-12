package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class EstadioNaoEncontradoException extends RuntimeException {

    public EstadioNaoEncontradoException(Long id) {
        super("Estádio não encontrado com o ID: " + id);
    }
}

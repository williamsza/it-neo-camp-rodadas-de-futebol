package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class ClubeNaoEncontradoException extends RuntimeException{
    public ClubeNaoEncontradoException(Long id) {
        super("Clube não encontrado pelo id."+ id);
    }
}

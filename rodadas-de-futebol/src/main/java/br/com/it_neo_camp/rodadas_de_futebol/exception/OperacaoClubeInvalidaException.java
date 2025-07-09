package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class OperacaoClubeInvalidaException extends RuntimeException {
    public OperacaoClubeInvalidaException(String mensagem) {
        super(mensagem);
    }
}

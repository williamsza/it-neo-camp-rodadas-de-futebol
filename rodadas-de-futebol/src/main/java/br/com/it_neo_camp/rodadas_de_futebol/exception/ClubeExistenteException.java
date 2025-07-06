package br.com.it_neo_camp.rodadas_de_futebol.exception;

public class ClubeExistenteException extends RuntimeException{
    public ClubeExistenteException(String nomeClube) {
        super("Ja existe um clube com o nome");
    }
}

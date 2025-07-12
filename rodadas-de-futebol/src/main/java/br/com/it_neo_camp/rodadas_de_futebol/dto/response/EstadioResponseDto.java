package br.com.it_neo_camp.rodadas_de_futebol.dto.response;

public class EstadioResponseDto {
    private Long id;
    private String nome;

    public EstadioResponseDto(Long id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}

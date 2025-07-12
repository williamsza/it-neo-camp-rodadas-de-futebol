package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "estadios")
public class Estadio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long estadio;
    @NotBlank(message = "O nome do estádio é obrigatório.")
    @Size(min = 3, message = "O nome do estádio deve ter no mínimo 3 caracteres.")
    @Column(unique = true, nullable = false)
    private String nomeEstadio;

    public Long getEstadio() {
        return estadio;
    }

    public void setEstadio(Long estadio) {
        this.estadio = estadio;
    }

    public String getNomeEstadio() {
        return nomeEstadio;
    }

    public void setNomeEstadio(String nomeEstadio) {
        this.nomeEstadio = nomeEstadio;
    }
}

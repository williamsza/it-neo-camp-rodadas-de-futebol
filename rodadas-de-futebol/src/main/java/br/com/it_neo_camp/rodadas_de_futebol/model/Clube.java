package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "clubes")
public class Clube {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
   // @NotNull
   // @Size(min = 2)
    private String nome;
    private Integer totalPontos;
    private Integer totalGols;
    private Integer totalVitorias;
    private Integer totalJogos;
    //@NotBlank
    private String siglaEstado;
    private String estadoClube;
    private LocalDateTime dataCriacao;

    @Column(nullable = false)
    private boolean ativo;


}

package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Where(clause = "ativo = true")

@SQLDelete(sql = "UPDATE partida SET ativo = false WHERE id = ?")

@Table(name = "partidas")
public class Partida {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "clube_mandante_id", nullable = false)
    private Clube clubeMandante;

    @ManyToOne
    @JoinColumn(name = "clube_visitante_id", nullable = false)
    private Clube clubeVisitante;
    @Column(nullable = false)
    private Integer placarMandante;
    @Column(nullable = false)
    private Integer placarVisitante;
    @ManyToOne
    @JoinColumn(name = "estadio_id", nullable = false)
    private Estadio estadio;
    @Column(nullable = false)
    private LocalDateTime dataHora;
    @Enumerated(EnumType.STRING)
   // @NotNull
    private StatusPartida statusPartida;
    @Column(nullable = false)
    private Boolean ativo = true;

    public Partida(Clube clubeMandante, Clube clubeVisitante, Integer placarMandante,
                   Integer placarVisitante, Estadio estadio, LocalDateTime dataHora) {
        this.clubeMandante = clubeMandante;
        this.clubeVisitante = clubeVisitante;
        this.placarMandante = placarMandante;
        this.placarVisitante = placarVisitante;
        this.estadio = estadio;
        this.dataHora = dataHora;
        this.statusPartida = StatusPartida.AGENDADA; 
    }

}


package br.com.it_neo_camp.rodadas_de_futebol.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private LocalDate dataHora;
    @Enumerated(EnumType.STRING)
    @NotNull
    private StatusPartida statusPartida;

    public Partida(Clube clubeMandante, Clube clubeVisitante, Integer placarMandante,
                   Integer placarVisitante, Estadio estadio, LocalDate dataHora) {
        this.clubeMandante = clubeMandante;
        this.clubeVisitante = clubeVisitante;
        this.placarMandante = placarMandante;
        this.placarVisitante = placarVisitante;
        this.estadio = estadio;
        this.dataHora = dataHora;
        this.statusPartida = StatusPartida.AGENDADA; // Ou o status inicial padrão
    }
}


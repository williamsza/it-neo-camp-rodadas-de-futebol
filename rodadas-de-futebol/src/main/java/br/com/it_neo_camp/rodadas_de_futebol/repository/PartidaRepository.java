package br.com.it_neo_camp.rodadas_de_futebol.repository;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface PartidaRepository extends JpaRepository<Partida, Long> {

    @Query("SELECT p FROM Partida p " +
            "WHERE (p.clubeMandante = :clube OR p.clubeVisitante = :clube) " +
            "AND p.dataHora  =:dataJogo")
    List<Partida> findByClubeInvolvedAndDataBetween(@Param("clube") Clube clube,
                                                    @Param("dataJogo") LocalDate dataJogo);


   //List<Partida> findByEstadioAndDataBetween(Estadio estadio, LocalDate atStartOfDay);
}

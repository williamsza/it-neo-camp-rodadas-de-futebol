package br.com.it_neo_camp.rodadas_de_futebol.repository;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {
    boolean existsByNome(String nomeClube);

    @Override
    boolean existsById(Long iq);

    @Modifying
    @Query("UPDATE Clube c SET c.ativo = false WHERE c.id = :id")
    void desativarPorId(@Param("id") Long id);

    boolean existsByClubeMandanteIdAndDataHoraBetweenOrClubeVisitanteIdAndDataHoraBetween(long id, LocalDateTime inicioPeriodo, LocalDateTime fimPeriodo, long id1, LocalDateTime inicioPeriodo1, LocalDateTime fimPeriodo1);

   // boolean existEstadioAndDataHoraBetween(@NotNull(message = "O ID do estádio é obrigatório.") Long estadioId, LocalDateTime localDateTime, LocalDateTime localDateTime1);
}

package br.com.it_neo_camp.rodadas_de_futebol.repository;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {
    boolean existsByNome(String nomeClube);

    @Modifying
    @Query("UPDATE Clube c SET c.ativo = false WHERE c.id = :id")
    void desativarPorId(@Param("id") Long id);

}

package br.com.it_neo_camp.rodadas_de_futebol.repository;

import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClubeRepository extends JpaRepository<Clube, Long> {
}

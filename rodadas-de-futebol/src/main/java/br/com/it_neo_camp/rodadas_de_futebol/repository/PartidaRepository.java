package br.com.it_neo_camp.rodadas_de_futebol.repository;

import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PartidaRepository extends JpaRepository<Partida, Long> {
}

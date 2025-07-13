package br.com.it_neo_camp.rodadas_de_futebol.repository;

import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EstadioRepository extends JpaRepository<Estadio, Long> {
    boolean existsByNomeEstadioIgnoreCase(String nomeEstadio);

    Optional<Estadio> findById(Long id);

    boolean existsByEstadio(Long id);
}

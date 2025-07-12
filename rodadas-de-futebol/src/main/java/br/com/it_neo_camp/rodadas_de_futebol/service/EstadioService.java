package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.EstadioResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadioExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import br.com.it_neo_camp.rodadas_de_futebol.repository.EstadioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class EstadioService {
    private final EstadioRepository repository;

    public EstadioService(EstadioRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public EstadioResponseDto cadastrarEstadio(EstadioRequestDto request) throws EstadioExistenteException {

        if (repository.existsByNomeEstadioIgnoreCase(request.getNomeEstadio())) {
            throw new EstadioExistenteException(request.getNomeEstadio());

        }
        Estadio novoEstadio = new Estadio();
        novoEstadio.setNomeEstadio(request.getNomeEstadio());

        Estadio estadioSalvo = repository.save(novoEstadio);
        return new EstadioResponseDto(estadioSalvo);
    }

}

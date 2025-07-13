package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioUpdateDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.EstadioResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadioExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadioNaoEncontradoException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import br.com.it_neo_camp.rodadas_de_futebol.repository.EstadioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional
    public List<EstadioResponseDto> listarTodosEstadios() {
        return repository.findAll().stream()
                .map(EstadioResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public EstadioResponseDto buscarEstadioPorId(Long id) {
        Estadio estadio = repository.findById(id)
                .orElseThrow(() -> new EstadioNaoEncontradoException(id));
        return new EstadioResponseDto(estadio);
    }


    @Transactional
    public EstadioResponseDto atualizarEstadio(Long id, @Valid EstadioUpdateDto updateDto) {
        Estadio estadio = repository.findById(id)
                .orElseThrow(() -> new EstadioNaoEncontradoException(id));

        if (repository.existsByNomeEstadioIgnoreCase(updateDto.getNomeEstadio()) &&
                !estadio.getNomeEstadio().equalsIgnoreCase(updateDto.getNomeEstadio())) {
            throw new EstadioExistenteException(updateDto.getNomeEstadio());

        }
        estadio.setNomeEstadio(updateDto.getNomeEstadio());
        Estadio estadioAtualizado = repository.save(estadio);
        return new EstadioResponseDto(estadioAtualizado);
    }

    @Transactional
    public void deletarEstadio(Long id) {
        if (!repository.existsByEstadio(id)) {
            throw new EstadioNaoEncontradoException(id);

        }
        repository.deleteById(id);
    }
}

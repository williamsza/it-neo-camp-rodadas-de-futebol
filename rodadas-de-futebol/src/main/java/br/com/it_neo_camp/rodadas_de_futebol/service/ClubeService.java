package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadoInvalidoException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class ClubeService {
    private final ClubeRepository repository;

    public ClubeService(ClubeRepository clubeRepository) {
        this.repository = clubeRepository;
    }

    @Transactional
    public ClubeResponseDto cadastraNovoClube(ClubeRequestDto request) {
        if (repository.existsByNomeClube(request.getNomeClubeDto())) {
            throw new ClubeExistenteException(request.getNomeClubeDto());

        }
        String sigla = request.getSiglaEstadoDto();
        if (sigla == null || sigla.length() != 2) {
            throw new EstadoInvalidoException(sigla);
        }

        Clube novoClube = new Clube();
        novoClube.setNomeClube(request.getNomeClubeDto());
        novoClube.setSiglaEstado(sigla.toUpperCase());
        novoClube.setDataCriacao(request.getDataCriacaoDto());

        Clube clubeSalvo = repository.save(novoClube);

        return new ClubeResponseDto(clubeSalvo);

    }


}

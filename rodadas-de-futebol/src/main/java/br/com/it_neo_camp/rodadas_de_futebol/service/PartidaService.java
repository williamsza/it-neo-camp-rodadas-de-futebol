package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeNaoEncontradoException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.PartidaInvalidaException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.EstadioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

@Service
public class PartidaService {
    private ClubeRepository repository;
    private EstadioRepository estadioRepository;
    private EstadioRequestDto estadioRequestDto;
    private PartidaRequestDto request;


    @Transactional
    public PartidaResponseDto cadastrarPartida(@Valid PartidaRequestDto request) {
        Clube mandante = repository.findById(request.getClubeMandanteId())
                .filter(Clube::isAtivo)
                .orElseThrow(() -> new ClubeNaoEncontradoException(request.getClubeMandanteId()));

        Clube visitante = repository.findById(request.getClubeVisitanteId())
                .filter(Clube::isAtivo)
                .orElseThrow(() -> new ClubeNaoEncontradoException(request.getClubeVisitanteId()));


        if (mandante.getClubeId().equals(visitante.getClubeId())) {
            throw new PartidaInvalidaException("O clube mandante e o clube visitante não podem ser o mesmo.");
        }
        Partida novaPartida = new Partida();
        novaPartida.setPartidaId(request.getDataDaPartida());
        novaPartida.setClubeMandante(mandante);
        novaPartida.setClubeVisitante(visitante);

        if (request.getPlacarMandante() != null && request.getPlacarVisitante() != null) {
            novaPartida.setGolsMandante(request.getPlacarMandante());
            novaPartida.setGolsVisitante(request.getPlacarVisitante());

        }


        //return new PartidaResponseDto();
    }
}

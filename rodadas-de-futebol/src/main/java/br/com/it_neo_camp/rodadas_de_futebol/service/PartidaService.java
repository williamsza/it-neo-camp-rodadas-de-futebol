package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.*;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import br.com.it_neo_camp.rodadas_de_futebol.model.Partida;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.EstadioRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.PartidaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {
    private ClubeRepository repository;
    private EstadioRepository estadioRepository;
    private EstadioRequestDto estadioRequestDto;
    private PartidaRepository partidaRepository;
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
        return null;
    }

        //return new PartidaResponseDto();

        @Transactional
        public PartidaResponseDto cadastrarPartida (PartidaRequestDto request) throws ConflitoDadosException {
            if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
                throw new DadosInvalidosException("Os clubees mandante e visitante nao podem ser iguais! ");

            }
            Clube clubeMandante = repository.findById(request.getClubeMandanteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube mandante nao encontrado. "));
            Clube clubeVisitante = repository.findById(request.getClubeVisitanteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube vistante nao encontrado. "));

            Estadio estadio = estadioRepository.findById(request.getEstadioId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Estadio nao encontrado.  "));

            if (!clubeMandante.isAtivo()) {
                throw new ConflitoDadosException("Clube mandante esta inativo. ");

            }
            if (!clubeVisitante.isAtivo()) {
                throw new ConflitoDadosException("Clube visitante esta inativo. ");
            }
            if (request.getDataHora().isBefore((clubeMandante.getDataCriacao())) ||
                    request.getDataHora().isBefore((clubeVisitante.getDataCriacao()))) {
                throw new ConflitoDadosException("A data da partida náo pode ser anterior a data de criacao de um dos clubes envolvidos! ");

            }

            Partida novaPartida = new Partida(clubeMandante, clubeVisitante, request.getPlacarMandante(),
                    request.getPlacarVisitante(), estadio, request.getDataHora()
            );
            Partida partidaSalva = partidaRepository.save(novaPartida);
            return PartidaResponseDto.fromEntity(partidaSalva);


        }

        @Transactional
        public PartidaResponseDto pesquisarPartidaPorId (Long id){
            Partida partida = partidaRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));
            return new PartidaResponseDto(partida);

        }

        @Transactional
        public Page<PartidaResponseDto> listarPartidasPaginado (Pageable pageable){
            return partidaRepository.findAll(pageable).map(PartidaResponseDto::fromEntity);
        }

        @Transactional
        public PartidaResponseDto atualizarPartida (Long id, @Valid PartidaRequestDto request) throws
        ConflitoDadosException {
            Partida partida = partidaRepository.findById(id)
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));

            if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
                throw new DadosInvalidosException("Os clubes mandante e visitante nao podem ser iguais!");
            }
            Clube clubeMandante = repository.findById(request.getClubeMandanteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube mandante nao encontrado."));
            Clube clubeVisitante = repository.findById(request.getClubeVisitanteId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube visitante nao encontrado."));
            Estadio estadio = estadioRepository.findById(request.getEstadioId())
                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Estadio nao encontrado."));
            if (!clubeMandante.isAtivo()) {
                throw new ConflitoDadosException("Clube mandante esta inativo.");
            }
            if (!clubeVisitante.isAtivo()) {
                throw new ConflitoDadosException("Clube visitante esta inativo.");
            }
            if (request.getDataHora().isBefore(clubeMandante.getDataCriacao()) ||
                    request.getDataHora().isBefore(clubeVisitante.getDataCriacao())) {
                throw new ConflitoDadosException("A data da partida não pode ser anterior à data de criação de um dos clubes envolvidos!");
            }
            partida.setClubeMandante(clubeMandante);
            partida.setClubeVisitante(clubeVisitante);
            partida.setPlacarMandante(request.getPlacarMandante());
            partida.setPlacarVisitante(request.getPlacarVisitante());
            partida.setEstadio(estadio);
            partida.setDataHora(request.getDataHora());
            Partida partidaAtualizada = partidaRepository.save(partida);
            return PartidaResponseDto.fromEntity(partidaAtualizada);
        }

        @Transactional
        public List<PartidaResponseDto> pesquisarTodasPartidas () {
            return repository.findAll().stream()
                    .map(PartidaResponseDto::fromEntity)
                    .toList();
        }

        @Transactional
        public boolean deletarPartida (Long id){
            Optional<Partida> partida = partidaRepository.findById(id);
            if (partida.isPresent()) {
                repository.deleteById(id);
                return true;
            }
            return false;
        }

        private void validarPlacarNaoNegativo (PartidaRequestDto request){
            if (request.getPlacarMandante() < 0 || request.getPlacarVisitante() < 0) {
                throw new PlacarInvalidoException("Placar não pode ser negativo.");
            }
        }


    }

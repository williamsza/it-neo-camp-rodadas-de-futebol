package br.com.it_neo_camp.rodadas_de_futebol.service;


import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ConflitoDadosException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.DadosInvalidosException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.PlacarInvalidoException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.RecursoNaoEncontradoException;
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

@Service
public class PartidaService {
    private final PartidaRepository partidaRepository;
    private final ClubeRepository clubeRepository;
    private final EstadioRepository estadioRepository;

    public PartidaService(PartidaRepository partidaRepository, ClubeRepository clubeRepository, EstadioRepository estadioRepository) {
        this.partidaRepository = partidaRepository;
        this.clubeRepository = clubeRepository;
        this.estadioRepository = estadioRepository;
    }

    @Transactional
    public PartidaResponseDto cadastrarPartida(PartidaRequestDto request) throws ConflitoDadosException {
        if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
            throw new DadosInvalidosException("Os clubees mandante e visitante nao podem ser iguais! ");

        }
        Clube clubeMandante = clubeRepository.findById(request.getClubeMandanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube mandante nao encontrado. "));
        Clube clubeVisitante = clubeRepository.findById(request.getClubeVisitanteId())
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
        if (!estadio.isAtivo()) {
            throw new ConflitoDadosException("Estádio está inativo.");
        }

        boolean estadioOcupado = partidaRepository.existsByEstadioIdAndDataHora(request.getEstadioId(), request.getDataHora());
        if (estadioOcupado) {
            throw new ConflitoDadosException("Já existe uma partida agendada neste estádio para o horário informado.");
        }


        validarPlacarNaoNegativo(request);
        verificarPartidaDuplicada(request);

        Partida novaPartida = new Partida(clubeMandante, clubeVisitante, request.getPlacarMandante(),
                request.getPlacarVisitante(), estadio, request.getDataHora()
        );
        Partida partidaSalva = partidaRepository.save(novaPartida);

        atualizarEstatisticasClubes(clubeMandante, clubeVisitante, request.getPlacarMandante(), request.getPlacarVisitante());


        return PartidaResponseDto.fromEntity(partidaSalva);


    }

    @Transactional
    public PartidaResponseDto pesquisarPartidaPorId(Long id) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));
        return new PartidaResponseDto(partida);

    }

    @Transactional
    public Page<PartidaResponseDto> listarPartidasPaginado(Pageable pageable) {
        return partidaRepository.findAll(pageable).map(PartidaResponseDto::fromEntity);
    }

    public List<PartidaResponseDto> listarPartidasAtivas() {
        return partidaRepository.findByAtivoTrue().stream()
                .map(PartidaResponseDto::fromEntity)
                .toList();
    }

    @Transactional
    public PartidaResponseDto atualizarPartida(Long id, @Valid PartidaRequestDto request) throws ConflitoDadosException {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));

        if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
            throw new DadosInvalidosException("Os clubes mandante e visitante nao podem ser iguais!");
        }
        Clube clubeMandante = clubeRepository.findById(request.getClubeMandanteId())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube mandante nao encontrado."));
        Clube clubeVisitante = clubeRepository.findById(request.getClubeVisitanteId())
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

        atualizarEstatisticasClubes(clubeMandante, clubeVisitante, request.getPlacarMandante(), request.getPlacarVisitante());

        return PartidaResponseDto.fromEntity(partidaAtualizada);
    }

    @Transactional
    public List<PartidaResponseDto> pesquisarTodasPartidas() {
        return partidaRepository.findAll().stream()
                .map(PartidaResponseDto::fromEntity)
                .toList();
    }

    /* @Transactional
     public boolean deletarPartida(Long id) {
         Optional<Partida> partida = partidaRepository.findById(id);
         if (partida.isPresent()) {
             partidaRepository.deleteById(id);
             return true;
         }
         return false;
     }*/

    @Transactional
    public PartidaResponseDto inativarPartida(Long id) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));
        partida.setAtivo(false);
        Partida partidaInativada = partidaRepository.save(partida);
        return PartidaResponseDto.fromEntity(partidaInativada);

    }

    private void validarPlacarNaoNegativo(PartidaRequestDto request) {
        if (request.getPlacarMandante() < 0 || request.getPlacarVisitante() < 0) {
            throw new PlacarInvalidoException("Placar não pode ser negativo.");
        }
    }

    private void verificarPartidaDuplicada(PartidaRequestDto request) throws ConflitoDadosException {
        boolean existe = partidaRepository.existsByClubeMandanteIdAndClubeVisitanteIdAndDataHora(
                request.getClubeMandanteId(), request.getClubeVisitanteId(), request.getDataHora());
        if (existe) {
            throw new ConflitoDadosException("Já existe uma partida agendada entre os clubes para a data e hora informadas.");
        }
    }

    private void atualizarEstatisticasClubes(Clube mandante, Clube visitante, int placarMandante, int placarVisitante) {

        mandante.setTotalGols(mandante.getTotalGols() == null ? 0 : mandante.getTotalGols());
        visitante.setTotalGols(visitante.getTotalGols() == null ? 0 : visitante.getTotalGols());
        mandante.setTotalJogos(mandante.getTotalGols() == null ? 0 : mandante.getTotalGols());
        visitante.setTotalJogos(visitante.getTotalGols() == null ? 0 : visitante.getTotalGols());
        mandante.setTotalVitorias(mandante.getTotalVitorias() == null ? 0 : mandante.getTotalVitorias());
        visitante.setTotalVitorias(visitante.getTotalVitorias() == null ? 0 : visitante.getTotalVitorias());
        mandante.setTotalPontos(mandante.getTotalPontos() == null ? 0 : mandante.getTotalPontos());
        visitante.setTotalPontos(visitante.getTotalPontos() == null ? 0 : visitante.getTotalPontos());

        mandante.setTotalGols(mandante.getTotalGols() + placarMandante);
        visitante.setTotalGols(visitante.getTotalGols() + placarVisitante);
        mandante.setTotalJogos(mandante.getTotalJogos() + 1);
        visitante.setTotalJogos(visitante.getTotalJogos() + 1);


        if (placarMandante > placarVisitante) {
            mandante.setTotalVitorias(mandante.getTotalVitorias() + 1);
            mandante.setTotalPontos(mandante.getTotalPontos() + 3);

            //visitante.setTotalDerrotas(visitante.getTotalDerrotas() + 1);
        } else if (placarMandante < placarVisitante) {
            visitante.setTotalVitorias(visitante.getTotalVitorias() + 1);
            visitante.setTotalPontos(visitante.getTotalPontos() + 3);

            mandante.setTotalJogos(mandante.getTotalJogos() + 1);

            //mandante.setTotalEmpates(mandante.getTotalEmpates() + 1);
            //visitante.setTotalEmpates(visitante.getTotalEmpates() + 1);


        } else {
            mandante.setTotalPontos(mandante.getTotalPontos() + 1);
            visitante.setTotalPontos(visitante.getTotalPontos() + 1);
        }


    }


}

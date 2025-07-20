package br.com.it_neo_camp.rodadas_de_futebol.service;//package br.com.it_neo_camp.rodadas_de_futebol.service;

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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PartidaService {
    private ClubeRepository cRepository;
    private EstadioRepository eRepository;
    private PartidaRepository repository;
    private PartidaRequestDto request;

    public PartidaService(ClubeRepository cRepository, EstadioRepository eRepository, PartidaRepository repository) {
        this.cRepository = cRepository;
        this.eRepository = eRepository;
        this.repository = repository;
    }

    @Transactional
    public PartidaResponseDto cadastrarPartida(@Valid PartidaRequestDto request) throws ConflitoDadosException {
        validarPlacarNaoNegativo(request);

        if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
            throw new PartidaInvalidaException("O clube mandante e visitante não podem ser o mesmo");
        }
        Clube mandante = buscarClubeAtivo(request.getClubeMandanteId());
        Clube visitante = buscarClubeAtivo(request.getClubeVisitanteId());
        Estadio estadio = buscarEstadio(request.getEstadioId());

        //validarDatasPartida(request, mandante, visitante);
        //validarDisponibilidadeEstadio(request);
        //validarPartidasProximas(request, mandante, visitante);

        Partida partida = new Partida();
        partida.setDataHora(request.getDataHora());
        partida.setClubeMandante(mandante);
        partida.setClubeVisitante(visitante);
        partida.setEstadio(estadio);
        partida.setGolsMandante(request.getPlacarMandante());
        partida.setGolsVisitante(request.getPlacarVisitante());
        //partida.setStatusPartida(request.getStatusPartida());
        return PartidaResponseDto.fromEntity(repository.save(partida));
    }


    @Transactional
    public PartidaResponseDto buscarPartidaPorId(Long id) {
        Partida partida = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));
        return PartidaResponseDto.fromEntity(partida);

    }

    //    public PartidaResponseDto buscarPorId(Long id) {
//
//        Partida partida = repository.findById(id)
//                .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));
//
//    }



    private Clube buscarClubeAtivo(Long id) {
        return cRepository.findById(id).filter(Clube::isAtivo).orElseThrow(() -> new ClubeNaoEncontradoException(id));

    }

    private Estadio buscarEstadio(Long estadioId) {
        return eRepository.findById(estadioId).orElseThrow(() -> new EstadioNaoEncontradoException(estadioId));

    }


    private void validarDatasPartida(PartidaRequestDto request, Clube mandante, Clube visitante) throws ConflitoDadosException {
        if (request.getDataHora().isBefore(mandante.getDataCriacao())) {
            throw new ConflitoDadosException("Data anterior à criação do clube mandante");
        }
        if (request.getDataHora().isBefore(visitante.getDataCriacao())) {
            throw new ConflitoDadosException("Data anterior à criação do clube visitante");
        }

    }


    private void validarPartidasProximas(@Valid PartidaRequestDto request, Clube mandante, Clube visitante) throws ConflitoDadosException {
        LocalDateTime inicioPeriodo = request.getDataHora().minusHours(48);
        LocalDateTime fimPeriodo = request.getDataHora().plusHours(48);

        boolean mandanteOcupado = cRepository.existsByClubeMandanteIdAndDataHoraBetweenOrClubeVisitanteIdAndDataHoraBetween(
                mandante.getId(), inicioPeriodo, fimPeriodo,
                mandante.getId(), inicioPeriodo, fimPeriodo);

        boolean visitanteOcupado = cRepository.existsByClubeMandanteIdAndDataHoraBetweenOrClubeVisitanteIdAndDataHoraBetween(
                visitante.getId(), inicioPeriodo, fimPeriodo,
                visitante.getId(), inicioPeriodo, fimPeriodo
        );

        if (mandanteOcupado || visitanteOcupado) {
            throw new ConflitoDadosException("Um dos clubes já tem partida marcada nas próximas 48 horas");

        }


    }

    private void validarDisponibilidadeEstadio(@Valid PartidaRequestDto request) throws ConflitoDadosException {
        boolean estadioOcupado = repository.existEstadioAndDataHoraBetween(
                request.getEstadioId(),
                request.getDataHora().toLocalDate().atStartOfDay(),
                request.getDataHora().toLocalDate().plusDays(1).atStartOfDay());

        if (estadioOcupado) {
            throw new ConflitoDadosException("Estádio já possui partida neste dia");

        }


    }


//    private void validarDatasPartida(@Valid PartidaRequestDto request, Clube mandante, Clube visitante) throws ConflitoDadosException {
//
//        if (request.getDataHora().isBefore(mandante.getDataCriacao())) {
//            throw new ConflitoDadosException("Data anterior à criação do clube mandante");
//        }
//        if (request.getDataHora().isBefore(visitante.getDataCriacao())) {
//            throw new ConflitoDadosException("Data anterior à criação do clube visitante");
//
//        }
//    }

    private void validarPlacarNaoNegativo(PartidaRequestDto request) {
        if (request.getPlacarMandante() < 0 || request.getPlacarVisitante() < 0) {
            throw new PlacarInvalidoException("Placar não pode ser negativo.");
        }

    }


    @Transactional
    public PartidaResponseDto atualizarPartida(Long id, @Valid PartidaRequestDto request) throws ConflitoDadosException {
        Partida partida = repository.findById(id).orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "ID" + id));

        if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
            throw new DadosInvalidosException("Os clubes mandante e visitante nao podem ser iguais!");
        }
        Clube clubeMandante = cRepository.findById(request.getClubeMandanteId()).orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube mandante nao encontrado."));
        Clube clubeVisitante = cRepository.findById(request.getClubeVisitanteId()).orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube visitante nao encontrado."));
        Estadio estadio = eRepository.findById(request.getEstadioId()).orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Estadio nao encontrado."));
        if (!clubeMandante.isAtivo()) {
            throw new ConflitoDadosException("Clube mandante esta inativo.");
        }
        if (!clubeVisitante.isAtivo()) {
            throw new ConflitoDadosException("Clube visitante esta inativo.");
        }
        if (request.getDataHora().isBefore(clubeMandante.getDataCriacao()) || request.getDataHora().isBefore(clubeVisitante.getDataCriacao())) {
            throw new ConflitoDadosException("A data da partida não pode ser anterior à data de criação de um dos clubes envolvidos!");
        }
        partida.setClubeMandante(clubeMandante);
        partida.setClubeVisitante(clubeVisitante);
        partida.setGolsMandante(request.getPlacarMandante());
        partida.setGolsVisitante(request.getPlacarVisitante());
        partida.setEstadio(estadio);
        partida.setDataHora(request.getDataHora());
        Partida partidaAtualizada = repository.save(partida);
        return PartidaResponseDto.fromEntity(partidaAtualizada);
    }

    @Transactional
    public List<PartidaResponseDto> pesquisarTodasPartidas() {
        return repository.findAll().stream().map(PartidaResponseDto::fromEntity).toList();
    }

    @Transactional
    public Page<PartidaResponseDto> listarPartidasPaginado(Long clubeId, Long estadioId, Boolean goleada, Pageable pageable) {
        return repository.findAll(pageable).map(PartidaResponseDto::fromEntity);

    }

    @Transactional
    public boolean deletarPartida(Long id) {

        Optional<Partida> partida = repository.findById(id);
        if (partida.isPresent()) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }


}


//return new PartidaResponseDto();

//        @Transactional
//        public PartidaResponseDto cadastrarPartida (PartidaRequestDto request) throws ConflitoDadosException {
//            if (request.getClubeMandanteId().equals(request.getClubeVisitanteId())) {
//                throw new DadosInvalidosException("Os clubees mandante e visitante nao podem ser iguais! ");
//
//            }
//            Clube clubeMandante = repository.findById(request.getClubeMandanteId())
//                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube mandante nao encontrado. "));
//            Clube clubeVisitante = repository.findById(request.getClubeVisitanteId())
//                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Clube vistante nao encontrado. "));
//
//            Estadio estadio = estadioRepository.findById(request.getEstadioId())
//                    .orElseThrow(() -> new RecursoNaoEncontradoException("Partida ", "Estadio nao encontrado.  "));
//
//            if (!clubeMandante.isAtivo()) {
//                throw new ConflitoDadosException("Clube mandante esta inativo. ");
//
//            }
//            if (!clubeVisitante.isAtivo()) {
//                throw new ConflitoDadosException("Clube visitante esta inativo. ");
//            }
//            if (request.getDataHora().isBefore((clubeMandante.getDataCriacao())) ||
//                    request.getDataHora().isBefore((clubeVisitante.getDataCriacao()))) {
//                throw new ConflitoDadosException("A data da partida náo pode ser anterior a data de criacao de um dos clubes envolvidos! ");
//
//            }
//
//            Partida novaPartida = new Partida(clubeMandante, clubeVisitante, request.getPlacarMandante(),
//                    request.getPlacarVisitante(), estadio, request.getDataHora()
//            );
//            Partida partidaSalva = partidaRepository.save(novaPartida);
//            return PartidaResponseDto.fromEntity(partidaSalva);
//
//
//        }






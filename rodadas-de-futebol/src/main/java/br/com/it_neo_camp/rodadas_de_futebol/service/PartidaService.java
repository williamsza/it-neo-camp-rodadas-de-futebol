package br.com.it_neo_camp.rodadas_de_futebol.service;


import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ConflitoDodosException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.DadosInvalidosException;
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
    public PartidaResponseDto cadastrarPartida(PartidaRequestDto request) throws ConflitoDodosException {
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
            throw new ConflitoDodosException("Clube mandante esta inativo. ");

        }
        if (!clubeVisitante.isAtivo()) {
            throw new ConflitoDodosException("Clube visitante esta inativo. ");
        }
        if (request.getDataHora().isBefore((clubeMandante.getDataCriacao())) ||
                request.getDataHora().isBefore((clubeVisitante.getDataCriacao()))) {
            throw new ConflitoDodosException("A data da partida náo pode ser anterior a data de criacao de um dos clubes envolvidos! ");

        }

        Partida novaPartida = new Partida(clubeMandante, clubeVisitante, request.getPlacarMandante(),
                request.getPlacarVisitante(), estadio, request.getDataHora()
        );
        Partida partidaSalva = partidaRepository.save(novaPartida);
        return PartidaResponseDto.fromEntity(partidaSalva);


    }
    @Transactional
    public PartidaResponseDto pesquisarPartidaPorId(Long id) {
        Partida partida = partidaRepository.findById(id)
                .orElseThrow(()-> new RecursoNaoEncontradoException("Partida ","ID"+ id));
        return new PartidaResponseDto(partida);

    }
    @Transactional
    public Page<PartidaResponseDto> listarPartidasPaginado(Pageable pageable) {
        return partidaRepository.findAll(pageable).map(PartidaResponseDto::fromEntity);
    }

    @Transactional
    public PartidaResponseDto atualizarPartida(Long id, @Valid PartidaRequestDto request) throws ConflitoDodosException {
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
            throw new ConflitoDodosException("Clube mandante esta inativo.");
        }
        if (!clubeVisitante.isAtivo()) {
            throw new ConflitoDodosException("Clube visitante esta inativo.");
        }
        if (request.getDataHora().isBefore(clubeMandante.getDataCriacao()) ||
                request.getDataHora().isBefore(clubeVisitante.getDataCriacao())) {
            throw new ConflitoDodosException("A data da partida não pode ser anterior à data de criação de um dos clubes envolvidos!");
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
}

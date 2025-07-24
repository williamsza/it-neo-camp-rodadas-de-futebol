package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ConflitoDadosException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.DadosInvalidosException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.PlacarInvalidoException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.RecursoNaoEncontradoException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.EstadioRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.PartidaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class PartidaServiceTest {

    private PartidaRepository partidaRepository;
    private ClubeRepository clubeRepository;
    private EstadioRepository estadioRepository;
    private PartidaService partidaService;

    private Long clubeMandanteId = 2L;
    private Long clubeVisitanteId = 3L;
    private Long estadioId = 1L;
    private LocalDateTime dataHora;


    @BeforeEach
    void setUp() {

        partidaRepository = Mockito.mock(PartidaRepository.class);
        clubeRepository = Mockito.mock(ClubeRepository.class);
        clubeRepository = Mockito.mock(ClubeRepository.class);
        estadioRepository = Mockito.mock(EstadioRepository.class);
        partidaService = new PartidaService(partidaRepository, clubeRepository, estadioRepository);

        dataHora = LocalDateTime.now().plusDays(1);

        when(clubeRepository.findById(clubeVisitanteId))
                .thenReturn(Optional.of(new Clube(clubeVisitanteId, "Visitante", true, dataHora.minusYears(1))));
        when(estadioRepository.findById(estadioId))
                .thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));
        when(partidaRepository.existsByEstadioIdAndDataHora(any(Long.class), any(LocalDateTime.class)))
                .thenReturn(false);
        when(partidaRepository.existsByClubeMandanteIdAndClubeVisitanteIdAndDataHora(
                any(Long.class), any(Long.class), any(LocalDateTime.class)))
                .thenReturn(false);
        when(clubeRepository.findById(clubeMandanteId))
                .thenReturn(Optional.of(new Clube(clubeMandanteId, "Mandante", true, dataHora.minusYears(1))));

        // verify(partidaRepository, times(1)).save(any());


    }


    @Test
    void deveLancarExcecaoQuandoClubesMandanteEVisitanteSaoIguais() {

        Mockito.when(clubeRepository.findById(1L))
                .thenReturn(Optional.of(new Clube(1L, "Clube Teste", true, LocalDateTime.now().minusYears(1))));

        PartidaRequestDto request = new PartidaRequestDto(1L, 1L, estadioId, 0, 0, dataHora);
        assertThrows(DadosInvalidosException.class, () -> partidaService.cadastrarPartida(request));

    }

    @Test
    void deveLancarExcecaoQuandoEstadioOcupadoNoMesmoHorario() {

        when(partidaRepository.existsByEstadioIdAndDataHora(estadioId, dataHora)).thenReturn(true);

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> partidaService.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoClubeMandanteNaoExiste() {

        when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.empty());

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(RecursoNaoEncontradoException.class, () -> partidaService.cadastrarPartida(request));


    }

    @Test
    void deveLancarExcecaoQuandoClubeVisitanteNaoExiste() {

        when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", true, dataHora.minusYears(1))));
        when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.empty());
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(RecursoNaoEncontradoException.class, () -> partidaService.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoEstadioNaoExiste() {

        when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", true, dataHora.minusYears(1))));
        when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", true, dataHora.minusYears(1))));
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.empty());

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(RecursoNaoEncontradoException.class, () -> partidaService.cadastrarPartida(request));

    }

    @Test
    void deveLancarExcecaoQuandoClubeMandanteEstaInativo() {

        when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", false, dataHora.minusYears(1))));
        when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", true, dataHora.minusYears(1))));
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> partidaService.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoClubeVisitanteEstaInativo() {

        when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", true, dataHora.minusYears(1))));
        when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", false, dataHora.minusYears(1))));
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> partidaService.cadastrarPartida(request));

    }

    @Test
    void deveLancarExcecaoQuandoDataPartidaEhAnteriorDataCriacaoDeAlgumClube() {

        LocalDateTime dataCriacaoMandante = dataHora.plusDays(1);
        LocalDateTime dataCriacaoVisitante = dataHora.minusDays(1);

        when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "Mandante", true, dataCriacaoMandante)));
        when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "Visitante", true, dataCriacaoVisitante)));
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaRequestDto requet = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> partidaService.cadastrarPartida(requet));
    }

    @Test
    void deveLancarExcecaoQuandoPlacarMandanteEhNegativo() {

        when((clubeRepository.findById(clubeMandanteId))).thenReturn(Optional.empty());
        when((clubeRepository.findById(clubeVisitanteId))).thenReturn(Optional.empty());
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.empty());
        when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);
        when(partidaRepository.existsByEstadioIdAndDataHora(estadioId, dataHora)).thenReturn(false);

        assertThrows(RecursoNaoEncontradoException.class, () -> partidaService.cadastrarPartida(request));


    }

    @Test
    void deveLancarExcecaoQuandoPlacarVisitanteEhNegativo() {
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, -1, dataHora);

        assertThrows(PlacarInvalidoException.class, () -> partidaService.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoEstadioEstaInativo() {

        when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", false)));

        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> partidaService.cadastrarPartida(request));

    }


}


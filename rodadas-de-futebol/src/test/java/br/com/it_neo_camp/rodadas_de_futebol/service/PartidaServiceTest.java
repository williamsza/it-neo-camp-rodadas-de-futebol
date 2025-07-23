package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ConflitoDadosException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.DadosInvalidosException;
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

class PartidaServiceTest {

    private LocalDateTime dataHora;

    @BeforeEach
    void setUp() {
    }

    @Test
    void deveLancarExcecaoQuandoClubesMandanteEVisitanteSaoIguais() {
        PartidaService service = new PartidaService(null, null, null);
        PartidaRequestDto request = new PartidaRequestDto();
        request.setClubeMandanteId(1L);
        request.setClubeVisitanteId(1L);
        request.setEstadioId(1L);
        request.setPlacarMandante(0);
        request.setPlacarVisitante(0);
        request.setDataHora(LocalDateTime.now().plusDays(1));
        assertThrows(DadosInvalidosException.class, () -> service.cadastrarPartida(request));


    }

    @Test
    void deveLancarExcecaoQuandoEstadioOcupadoNoMesmoHorario() {
        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;
        dataHora = LocalDateTime.now().plusDays(1);

        Mockito.when(repository.existsByEstadioIdAndDataHora(estadioId, dataHora)).thenReturn(true);
        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "Mandante", true, dataHora.minusYears(1))));
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "Visitante", true, dataHora.minusYears(1))));
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto request = new PartidaRequestDto();

        request.setClubeMandanteId(clubeMandanteId);
        request.setClubeVisitanteId(clubeVisitanteId);
        request.setEstadioId(estadioId);
        request.setPlacarMandante(0);
        request.setPlacarVisitante(0);
        //request.setDataHora(LocalDateTime.now().plusDays(1));
        request.setDataHora(dataHora);
        assertThrows(ConflitoDadosException.class, () -> service.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoClubeMandanteNaoExiste() {
        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);

        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.empty());
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", true, dataHora.minusYears(1))));
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(RecursoNaoEncontradoException.class, () -> service.cadastrarPartida(request));


    }

    @Test
    void deveLancarExcecaoQuandoClubeVisitanteNaoExiste() {
        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);

        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", true, dataHora.minusYears(1))));
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.empty());
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(RecursoNaoEncontradoException.class, () -> service.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoEstadioNaoExiste() {

        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);

        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", true, dataHora.minusYears(1))));
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", true, dataHora.minusYears(1))));
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.empty());

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(RecursoNaoEncontradoException.class, () -> service.cadastrarPartida(request));


    }

    @Test
    void deveLancarExcecaoQuandoClubeMandanteEstaInativo() {
        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);

        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", false, dataHora.minusYears(1))));
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", true, dataHora.minusYears(1))));
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> service.cadastrarPartida(request));
    }

    @Test
    void deveLancarExcecaoQuandoClubeVisitanteEstaInativo() {
        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;
        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);

        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "mandante", true, dataHora.minusYears(1))));
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "visitante", false, dataHora.minusYears(1))));
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto request = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, () -> service.cadastrarPartida(request));

    }

    @Test
    void deveLancarExcecaoQuandoDataPartidaEhAnteriorDataCriacaoDeAlgumClube() {
        PartidaRepository repository = Mockito.mock(PartidaRepository.class);
        ClubeRepository clubeRepository = Mockito.mock(ClubeRepository.class);
        EstadioRepository estadioRepository = Mockito.mock(EstadioRepository.class);

        Long clubeMandanteId = 2L;
        Long clubeVisitanteId = 3L;
        Long estadioId = 1L;

        LocalDateTime dataHora = LocalDateTime.now().plusDays(1);
        LocalDateTime dataCriacaoMandante = dataHora.plusDays(1);
        LocalDateTime dataCriacaoVisitante = dataHora.minusDays(1);

        Mockito.when(clubeRepository.findById(clubeMandanteId)).thenReturn(Optional.of(new Clube(clubeMandanteId, "Mandante", true, dataCriacaoMandante)));
        Mockito.when(clubeRepository.findById(clubeVisitanteId)).thenReturn(Optional.of(new Clube(clubeVisitanteId, "Visitante", true, dataCriacaoVisitante)));
        Mockito.when(estadioRepository.findById(estadioId)).thenReturn(Optional.of(new Estadio(estadioId, "Estádio", true)));

        PartidaService service = new PartidaService(repository, clubeRepository, estadioRepository);
        PartidaRequestDto requet = new PartidaRequestDto(clubeMandanteId, clubeVisitanteId, estadioId, 0, 0, dataHora);

        assertThrows(ConflitoDadosException.class, ()-> service.cadastrarPartida(requet));
    }


}


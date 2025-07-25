package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.OperacaoClubeInvalidaException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.model.Estadio;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.EstadioRepository;
import br.com.it_neo_camp.rodadas_de_futebol.repository.PartidaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClubeServiceTest {

    private ClubeService clubeService;
    private PartidaRepository partidaRepository;
    private ClubeRepository clubeRepository;
    private EstadioRepository estadioRepository;
    private PartidaService partidaService;
    private PartidaResponseDto request;
    private PartidaResponseDto response;

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
        clubeService = new ClubeService(clubeRepository);

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

        when(clubeRepository.save(any(Clube.class))).thenReturn(new Clube(1L, "Clube A", true, LocalDateTime.now()));
        when(clubeRepository.existsByNome("Clube A")).thenReturn(false);
        when(clubeRepository.findById(1L)).thenReturn(Optional.of(new Clube(1L, "Clube A", true, LocalDateTime.now())));
        when(clubeRepository.findAll()).thenReturn(List.of(new Clube(1L, "Clube A", true, LocalDateTime.now())));
        when(clubeRepository.existsById(1L)).thenReturn(true);
        when(clubeRepository.findById(1L)).thenReturn(Optional.of(new Clube(1L, "Clube A", true, LocalDateTime.now())));
        when(clubeRepository.existsById(2L)).thenReturn(true);
        when(clubeRepository.findById(2L)).thenReturn(Optional.of(new Clube(2L, "Clube B", true, LocalDateTime.now())));

        Clube clube = new Clube(1L, "Clube A", true, LocalDateTime.now());

    }

    @Test
    void deveCadastrarNovoClubeComSucesso() {
        ClubeRequestDto request = new ClubeRequestDto("Clube A", "SP", "São Paulo", LocalDateTime.now(), true);

        when(clubeRepository.existsByNome("Clube A")).thenReturn(false);
        when(clubeRepository.save(any(Clube.class))).thenReturn(new Clube(1L, "Clube A", true, LocalDateTime.now()));

        assertEquals("Clube A", clubeService.cadastraNovoClube(request).getNomeClube());

    }

    @Test
    void deveLancarExcecaoAoCadastrarClubeExistente() {

        ClubeRequestDto request = new ClubeRequestDto("Clube A", "SP", "São Paulo", LocalDateTime.now(), true);

        when(clubeRepository.existsByNome("Clube A")).thenReturn(true);

        assertThrows(ClubeExistenteException.class, () -> clubeService.cadastraNovoClube(request));
    }

    @Test
    void deveLancarExcecaoAoAtualizarClubeComNomeDuplicado() {

        Clube clubeExistente = new Clube(1L, "Clube A", true, LocalDateTime.now());

        when(clubeRepository.findById(1L)).thenReturn(Optional.of(clubeExistente));

        when(clubeRepository.existsByNome("Clube B")).thenReturn(true);

        ClubeRequestDto request = new ClubeRequestDto("Clube B", "SP", "São Paulo", LocalDateTime.now(), true);

        assertThrows(ClubeExistenteException.class, () -> clubeService.atualizarClube(1L, request));


    }

    //Preciso fazer teste de integração para verificar se o nome do clube já existe no banco de dados.
    @Test
    void deveLancarExcecaoAoAtualizarClubeComNomeInvalido() {

        Clube clubeExistente = new Clube(1L, "Clube A", true, LocalDateTime.now());

        when(clubeRepository.findById(1L)).thenReturn(Optional.of(clubeExistente));

        ClubeRequestDto request = new ClubeRequestDto("A", "SP", "São Paulo", LocalDateTime.now(), null);

        assertThrows(ClubeExistenteException.class, () -> clubeService.atualizarClube(1L, request));


    }

    @Test
    void deveAtualizarClubeComSucesso() {

        Clube clubeExistente = new Clube(1L, "Clube B", true, LocalDateTime.now());

        when(clubeRepository.findById(1L)).thenReturn(Optional.of(clubeExistente));
        when(clubeRepository.existsByNome("Clube B")).thenReturn(false);

        when(clubeRepository.save(any(Clube.class))).thenAnswer(invocation -> invocation.getArgument(0));

        ClubeRequestDto request = new ClubeRequestDto("Clube B", "São Paulo", "SP", LocalDateTime.now(), null);

        var response = clubeService.atualizarClube(1L, request);

        assertEquals("Clube B", response.getNomeClube());
        assertEquals("SP", response.getSiglaEstado());
        assertTrue(response.getAtivo());
    }

    @Test
    void deveInativarClubeComSucesso() {
        Clube clubeAtivo = new Clube(1L, "Clube A", true, LocalDateTime.now());
        when(clubeRepository.findById(1L)).thenReturn(Optional.of(clubeAtivo));
        when(clubeRepository.save(any(Clube.class))).thenAnswer(invocation -> invocation.getArgument(0));

        clubeService.inativarClube(1L);

        assertFalse(clubeAtivo.isAtivo());

        Mockito.verify(clubeRepository).save(clubeAtivo);
    }

    @Test
    void deveLancarExcecaoAoInativarClubeJaInativo() {

        Clube clubeInativo = new Clube(1L, "CLUBE A", false, LocalDateTime.now());
        when(clubeRepository.findById(1L)).thenReturn(Optional.of(clubeInativo));

        assertThrows(OperacaoClubeInvalidaException.class, () -> clubeService.inativarClube(1L));
    }

    @Test
    void deveLancarExcecaoAoCadastrarClubeComSiglaEstadoInvalida(){
        ClubeRequestDto request = new ClubeRequestDto("Clube A", "XX", "Estado Invalido", LocalDateTime.now(), true);

        assertThrows(Exception.class, () -> clubeService.cadastraNovoClube(request));
        //when(clubeRepository.existsByNome("Clube A")).thenReturn(false);
    }
    @Test
    void deveLancarExcecaoAoCadastrarClubeComNomeDuplicadoCaseInsensitive(){

        ClubeRequestDto request = new ClubeRequestDto("Clube X", "SP", "São Paulo", LocalDateTime.now(), true);

        when(clubeRepository.existsByNome("Clube X")).thenReturn(true);

        assertThrows(ClubeExistenteException.class, ()-> clubeService.cadastraNovoClube(request));
    }



}
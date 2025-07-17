package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ConflitoDadosException;
import br.com.it_neo_camp.rodadas_de_futebol.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
    private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<PartidaResponseDto> cadastrarPartida(@Valid @RequestBody PartidaRequestDto request) throws ConflitoDadosException {
        PartidaResponseDto response = partidaService.cadastrarPartida(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponseDto> pesquisarPartidaPorId(@PathVariable Long id) {
        PartidaResponseDto response = partidaService.pesquisarPartidaPorId(id);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<PartidaResponseDto> atualizarPartida(@PathVariable Long id, @Valid @RequestBody PartidaRequestDto request) throws ConflitoDadosException {
        PartidaResponseDto response = partidaService.atualizarPartida(id, request);
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<PartidaResponseDto>> pesquisarTodasPartidas() {
        return ResponseEntity.ok(partidaService.pesquisarTodasPartidas());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPartida(@PathVariable Long id) {
        partidaService.deletarPartida(id);
        return ResponseEntity.noContent().build();
    }


}

package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeNaoEncontradoException;
import br.com.it_neo_camp.rodadas_de_futebol.service.ClubeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    private final ClubeService clubeService;

    public ClubeController(ClubeService clubeService) {
        this.clubeService = clubeService;
    }

    @PostMapping
    public ResponseEntity<Object> cadastrarNovoClube(@Valid @RequestBody ClubeRequestDto request) {

        try {
            ClubeResponseDto response = clubeService.cadastraNovoClube(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (ClubeExistenteException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<List<ClubeResponseDto>> listarTodosClubes() {
        List<ClubeResponseDto> clubes = clubeService.listarTodosClubes();
        return ResponseEntity.ok(clubes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarClubePorId(@PathVariable Long id) {
        try {
            ClubeResponseDto response = clubeService.buscarClubePorId(id);
            return ResponseEntity.ok(response);

        } catch (ClubeNaoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());

        }

    }


}

package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.ClubeResponseDto;
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
    public ResponseEntity<ClubeResponseDto> cadastrarNovoClube(@Valid @RequestBody ClubeRequestDto request) {
        ClubeResponseDto response = clubeService.cadastraNovoClube(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping
    public ResponseEntity<List<ClubeResponseDto>> listarTodosClubes() {
        List<ClubeResponseDto> clubes = clubeService.listarTodosClubes();
        return ResponseEntity.ok(clubes);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ClubeResponseDto> buscarClubePorId(@PathVariable Long id) {
        ClubeResponseDto response = clubeService.buscarClubePorId(id);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClubeResponseDto> atualizarClube(@PathVariable Long id, @Valid @RequestBody ClubeRequestDto request) {
        ClubeResponseDto response = clubeService.atualizarClube(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("inativar/{id}")
    public ResponseEntity<Void> inativarClube(@PathVariable Long id) {
        clubeService.inativarClube(id);
        return ResponseEntity.noContent().build();
    }


}




package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ConflitoDodosException;
import br.com.it_neo_camp.rodadas_de_futebol.service.PartidaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/partidas")
public class PartidaController {
   private final PartidaService partidaService;

    public PartidaController(PartidaService partidaService) {
        this.partidaService = partidaService;
    }

    @PostMapping
    public ResponseEntity<PartidaResponseDto> cadastrarPartida(@Valid @RequestBody PartidaRequestDto request) throws ConflitoDodosException {
        PartidaResponseDto response = partidaService.cadastrarPartida(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @GetMapping("/{id}")
    public ResponseEntity<PartidaResponseDto> pesquisarPartidaPorId(@PathVariable Long id){
        PartidaResponseDto response = partidaService.pesquisarPartidaPorId(id);
            return ResponseEntity.ok(response);

        }
    }


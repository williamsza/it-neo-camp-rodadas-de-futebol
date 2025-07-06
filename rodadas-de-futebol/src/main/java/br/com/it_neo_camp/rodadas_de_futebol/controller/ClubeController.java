package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.service.ClubeService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clubes")
public class ClubeController {

    private final ClubeService clubeService;

    public ClubeController(ClubeService clubeService) {
        this.clubeService = clubeService;
    }

    @PostMapping
    public ResponseEntity<?> cadastrarNovoClubestrarClube(@Valid @RequestBody ClubeRequestDto request) {

        try {
            ClubeResponseDto response = clubeService.cadastraNovoClube(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

package br.com.it_neo_camp.rodadas_de_futebol.controller;


import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.EstadioResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadioExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.service.EstadioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/estadoios")
public class EstadioController {
    private EstadioService estadioService;


    @PostMapping
    public ResponseEntity<EstadioResponseDto> cadastrarEstadio(@Valid @RequestBody EstadioRequestDto request) throws EstadioExistenteException {
    EstadioResponseDto response = estadioService.cadastrarEstadio(request);
    return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

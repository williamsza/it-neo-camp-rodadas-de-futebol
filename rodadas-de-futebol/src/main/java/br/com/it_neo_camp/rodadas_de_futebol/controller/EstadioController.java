package br.com.it_neo_camp.rodadas_de_futebol.controller;


import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.request.EstadioUpdateDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.EstadioResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadioExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.service.EstadioService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estadios")
public class EstadioController {

    private EstadioService estadioService;

    public EstadioController(EstadioService estadioService) {
        this.estadioService = estadioService;
    }

    @PostMapping
    public ResponseEntity<EstadioResponseDto> cadastrarEstadio(@Valid @RequestBody EstadioRequestDto request) throws EstadioExistenteException {
        EstadioResponseDto response = estadioService.cadastrarEstadio(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<EstadioResponseDto>> listarTodosEstadios() {
        List<EstadioResponseDto> estadios = estadioService.listarTodosEstadios();
        return ResponseEntity.ok(estadios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstadioResponseDto> boscarEstadioPorId(@PathVariable Long id) {
        EstadioResponseDto response = estadioService.buscarEstadioPorId(id);
        return ResponseEntity.ok(response);

    }

    @PutMapping("/{id}")
    public ResponseEntity<EstadioResponseDto> atualizarEstadio(@PathVariable Long id, @Valid @RequestBody EstadioUpdateDto updateDto) {
        EstadioResponseDto response = estadioService.atualizarEstadio(id, updateDto);
        return ResponseEntity.ok(response);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Valid> deletarEstadio(@PathVariable Long id) {
        estadioService.deletarEstadio(id);
        return ResponseEntity.noContent().build();

    }
}

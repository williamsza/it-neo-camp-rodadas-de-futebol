//package br.com.it_neo_camp.rodadas_de_futebol.controller;
//
//import br.com.it_neo_camp.rodadas_de_futebol.dto.request.PartidaRequestDto;
//import br.com.it_neo_camp.rodadas_de_futebol.dto.response.PartidaResponseDto;
//import br.com.it_neo_camp.rodadas_de_futebol.service.PartidaService;
//import jakarta.validation.Valid;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.HttpStatusCode;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/partidas")
//public class PartidaController {
//    private PartidaService partidaService;
//
//    public PartidaController(PartidaService partidaService) {
//        this.partidaService = partidaService;
//    }
//
//    @PostMapping
//    public ResponseEntity<PartidaResponseDto> cadastrarPartida(@Valid @RequestBody PartidaRequestDto request) {
//        PartidaResponseDto response = partidaService.cadastrarPartida(request);
//        return ResponseEntity.status(HttpStatus.CREATED).body(response);
//    }
//
//
//}

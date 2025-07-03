package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeRequestDto;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/clube")
public class ClubeController {

    @GetMapping("teste1")
    public String getMessage() {
        return "Ola controller";

    }
    @PostMapping
    public ClubeRequestDto cadastrar(@RequestBody ClubeRequestDto clubeRequestDto){

        return clubeRequestDto;


    }

}

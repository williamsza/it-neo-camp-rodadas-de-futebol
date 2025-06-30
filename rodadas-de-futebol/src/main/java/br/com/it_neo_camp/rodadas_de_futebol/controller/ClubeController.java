package br.com.it_neo_camp.rodadas_de_futebol.controller;

import br.com.it_neo_camp.rodadas_de_futebol.dto.ClubeRequestDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clube")
public class ClubeController {

    @GetMapping("teste1")
    public String getMessage() {
        return "Ola controller";

    }
    public ClubeRequestDto cadastrar(@RequestBody ClubeRequestDto clubeRequestDto){

        return clubeRequestDto;


    }

}

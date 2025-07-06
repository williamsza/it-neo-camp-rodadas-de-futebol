package br.com.it_neo_camp.rodadas_de_futebol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan("br.com.it_neo_camp.rodadas_de_futebol.model") // Adicione esta linha
@SpringBootApplication
class RodadasDeFutebolApplication {

	public static void main(String[] args) {
		SpringApplication.run(RodadasDeFutebolApplication.class, args);
	}

}

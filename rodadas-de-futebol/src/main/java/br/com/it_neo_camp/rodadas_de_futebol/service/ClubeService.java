package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.ClubeResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeNaoEncontradoException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.EstadoInvalidoException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.OperacaoClubeInvalidaException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClubeService {
    private final ClubeRepository repository;

    public ClubeService(ClubeRepository clubeRepository) {
        this.repository = clubeRepository;
    }

    @Transactional
    public ClubeResponseDto cadastraNovoClube(ClubeRequestDto request) {
        if (repository.existsByNomeClube(request.getNomeClube())) {
            throw new ClubeExistenteException(request.getNomeClube());

        }
        String sigla = request.getSiglaEstado();
        if (sigla == null || sigla.length() != 2) {
            throw new EstadoInvalidoException(sigla);
        }

        Clube novoClube = new Clube();
        novoClube.setNomeClube(request.getNomeClube());
        novoClube.setSiglaEstado(sigla.toUpperCase());
        novoClube.setDataCriacao(request.getDataCriacao());

        Clube clubeSalvo = repository.save(novoClube);

        return new ClubeResponseDto(clubeSalvo);

    }

    private String mapearSiglaParaEstado(String sigla) {
        switch (sigla.toUpperCase()) {
            case "AC":
                return "Acre";
            case "AL":
                return "Alagoas";
            case "AP":
                return "Amapá";
            case "AM":
                return "Amazonas";
            case "BA":
                return "Bahia";
            case "CE":
                return "Ceará";
            case "DF":
                return "Distrito Federal";
            case "ES":
                return "Espírito Santo";
            case "GO":
                return "Goiás";
            case "MA":
                return "Maranhão";
            case "MT":
                return "Mato Grosso";
            case "MS":
                return "Mato Grosso do Sul";
            case "MG":
                return "Minas Gerais";
            case "PA":
                return "Pará";
            case "PB":
                return "Paraíba";
            case "PR":
                return "Paraná";
            case "PE":
                return "Pernambuco";
            case "PI":
                return "Piauí";
            case "RJ":
                return "Rio de Janeiro";
            case "RN":
                return "Rio Grande do Norte";
            case "RS":
                return "Rio Grande do Sul";
            case "RO":
                return "Rondônia";
            case "RR":
                return "Roraima";
            case "SC":
                return "Santa Catarina";
            case "SP":
                return "São Paulo";
            case "SE":
                return "Sergipe";
            case "TO":
                return "Tocantins";
            default:
                return "Sigla não reconhecida";


        }

    }


    public List<ClubeResponseDto> listarTodosClubes() {
        return repository.findAll().stream()
                .map(ClubeResponseDto::new)
                .collect(Collectors.toList());
    }

    public ClubeResponseDto buscarClubePorId(Long id) {
        Clube clube = repository.findById(id).filter(Clube::isAtivo).orElseThrow(() -> new ClubeNaoEncontradoException(id));
        return new ClubeResponseDto(clube);


    }


    @Transactional
    public ClubeResponseDto atualizarClube(Long id, ClubeRequestDto request) {
        Clube clube = repository.findById(id).filter(Clube::isAtivo)
                .orElseThrow(() -> new ClubeNaoEncontradoException(id));

        if (repository.existsByNomeClube(request.getNomeClube()) && !clube.getNomeClube().equals(request.getNomeClube())) {
            throw new ClubeExistenteException(request.getNomeClube());


        }
        clube.setNomeClube(request.getNomeClube());
        clube.setSiglaEstado(request.getSiglaEstado());
        //clube.setEstadoClube(request.getEstadoDoClube());
        clube.setDataCriacao(request.getDataCriacao());
        clube.setStatusClube(request.getStatusClube());

        Clube clubeAtualizado = repository.save(clube);
        return new ClubeResponseDto(clubeAtualizado);

    }

    @Transactional
    public void deletarClube(Long id) {
        if (!repository.existsById(id)) {
            throw new ClubeNaoEncontradoException(id);
        }
        if (!repository.findById(id).get().isAtivo()) {
            throw new OperacaoClubeInvalidaException("Clube ja esta desativado");

        }
    }
}


package br.com.it_neo_camp.rodadas_de_futebol.service;

import br.com.it_neo_camp.rodadas_de_futebol.dto.request.ClubeRequestDto;
import br.com.it_neo_camp.rodadas_de_futebol.dto.response.ClubeResponseDto;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeExistenteException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.ClubeNaoEncontradoException;
import br.com.it_neo_camp.rodadas_de_futebol.exception.OperacaoClubeInvalidaException;
import br.com.it_neo_camp.rodadas_de_futebol.model.Clube;
import br.com.it_neo_camp.rodadas_de_futebol.repository.ClubeRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ClubeService {
    private final ClubeRepository repository;

    private static final Pattern UF_PATTERN = Pattern.compile("AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO");

    public ClubeService(ClubeRepository clubeRepository) {
        this.repository = clubeRepository;
    }

    @Transactional
    public ClubeResponseDto cadastraNovoClube(ClubeRequestDto request) {

        if (repository.existsByNome(request.getNomeClube())) {
            throw new ClubeExistenteException(request.getNomeClube());
        }

        Clube novoClube = new Clube();
        //novoClube.setAtivo(true);
        novoClube.setNome(request.getNomeClube());
        novoClube.setSiglaEstado(request.getSiglaEstado(/*sigla.toUpperCase()*/));
        novoClube.setEstadoClube(request.getEstadoClube()/*mapearSiglaParaEstado(request.getSiglaEstado())*/);
        novoClube.setDataCriacao(request.getDataCriacao());
        //novoClube.setStatusClube(request.getStatusClube(true));
        novoClube.setAtivo(true);

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


    @Transactional
    public List<ClubeResponseDto> listarTodosClubes() {
        return repository.findAll().stream()
                .filter(Clube::isAtivo)
                .map(ClubeResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public ClubeResponseDto buscarClubePorId(Long id) {
        Clube clube = repository.findById(id)
                .filter(Clube::isAtivo)
                .orElseThrow(() -> new ClubeNaoEncontradoException(id));
        return new ClubeResponseDto(clube);


    }


    @Transactional
    public ClubeResponseDto atualizarClube(Long id, ClubeRequestDto request) {
        Clube clube = repository.findById(id).filter(Clube::isAtivo)
                .orElseThrow(() -> new ClubeNaoEncontradoException(id));

        if (repository.existsByNome(request.getNomeClube()) && !clube.getNome().equals(request.getNomeClube())) {
            throw new ClubeExistenteException(request.getNomeClube());

        }
        clube.setNome(request.getNomeClube());
        clube.setSiglaEstado(request.getSiglaEstado().toUpperCase());
        clube.setEstadoClube(mapearSiglaParaEstado(request.getSiglaEstado()));
        clube.setEstadoClube(request.getEstadoClube());

        //clube.setStatusClube(request.getStatusClube()); Remover se 'ativo' for o único campo de status

        if (request.getAtivo() != null) {// Verifica se o campo ativo foi enviado na requisição
            clube.setAtivo(request.getAtivo());// Atualiza o status do clube
            throw new OperacaoClubeInvalidaException("Clube já está ativo");//
        }
        // clube.setAtivo(true);

        Clube clubeAtualizado = repository.save(clube);
        return new ClubeResponseDto(clubeAtualizado);

    }

    @Transactional
    public void inativarClube(Long id) {

        if (!repository.existsById(id)) {
            throw new ClubeNaoEncontradoException(id);

        }
        Clube clube = repository.findById(id)
                .orElseThrow(() -> new ClubeNaoEncontradoException(id));

        if (!clube.isAtivo()) {
            throw new OperacaoClubeInvalidaException("Clube já está desativado");
        }
        clube.setAtivo(false);
        repository.save(clube);
    }
}







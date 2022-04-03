package br.com.microsservicos.despesasms.view.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.microsservicos.despesasms.model.Despesas;
import br.com.microsservicos.despesasms.service.DespesasService;
import br.com.microsservicos.despesasms.shared.DespesasDto;
import br.com.microsservicos.despesasms.view.model.DespesasDtoDiferenciado;

@RestController
@RequestMapping("/api/despesas")
public class DespesasController {
    @Autowired
    private DespesasService service;

    @GetMapping(value="/status")
    public String statusServico(@Value("${local.server.port}") String porta) {
        return String.format("Serviço ativo e executando na porta %s", porta);
    }

    //CRIA UMA DESPESA
    @PostMapping
    public ResponseEntity<DespesasDto> criarDespesa(@RequestBody @Valid DespesasDto despesa) {
        ModelMapper mapper = new ModelMapper();
        DespesasDto dto = mapper.map(despesa, DespesasDto.class);
        dto = service.criarDespesa(dto);
        return new ResponseEntity<>(mapper.map(dto, DespesasDto.class), HttpStatus.CREATED);
    }

    //OBTEM TODAS AS DESPESAS
    @GetMapping
    public ResponseEntity<List<DespesasDto>> obterTodas() {
        List<DespesasDto> dtos = service.obterTodas();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<DespesasDto> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, DespesasDto.class))
                    .collect(Collectors.toList());
        return new ResponseEntity<>(resp, HttpStatus.OK);
    }

    //OBTEM UMA DESPESA POR ID
    @GetMapping(value="/{id}")
    public ResponseEntity<DespesasDto> obterPorId(@PathVariable String id) {
        Optional<DespesasDto> pessoa = service.obterPorId(id);

        if(pessoa.isPresent()) {
            return new ResponseEntity<>(
                new ModelMapper().map(pessoa.get(), DespesasDto.class), 
                HttpStatus.OK);
        } return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //ATUALIZA UMA DESPESA
    @PutMapping(value="/{id}")
    public ResponseEntity<DespesasDto> atualizarDespesa(@PathVariable String id,
        @Valid @RequestBody Despesas despesa) {ModelMapper mapper = new ModelMapper();
        DespesasDto dto = mapper.map(despesa, DespesasDto.class);
        dto = service.atualizarDespesa(id, dto);
        return new ResponseEntity<>(mapper.map(dto, DespesasDto.class), HttpStatus.OK);
    }

    //REMOVE UMA DESPESA
    @DeleteMapping(value="/{id}")
    public ResponseEntity<Void> removerDespesa(@PathVariable String id) {
        service.removerDespesa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } 

    //OBTEM A LISTAGEM COM O ID E A EMPRESA
    @GetMapping(value="/listagem")
    public ResponseEntity<List<DespesasDtoDiferenciado>> obterPorListagem() {
        List<DespesasDtoDiferenciado> dtos = service.obterPorListagem();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        ModelMapper mapper = new ModelMapper();
        List<DespesasDtoDiferenciado> resp = dtos.stream()
                    .map(dto -> mapper.map(dto, DespesasDtoDiferenciado.class))
                    .collect(Collectors.toList());

        return new ResponseEntity<>(resp, HttpStatus.OK);
    }
}

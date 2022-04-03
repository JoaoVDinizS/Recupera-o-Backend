package br.com.microsservicos.despesasms.service;

import org.springframework.stereotype.Service;

import br.com.microsservicos.despesasms.model.Despesas;
import br.com.microsservicos.despesasms.repository.DespesasRepository;
import br.com.microsservicos.despesasms.shared.DespesasDto;
import br.com.microsservicos.despesasms.view.model.DespesasDtoDiferenciado;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class DespesasServiceImpl implements DespesasService{
    @Autowired
    private DespesasRepository repository;

    //CRIA UMA DESPESA
    @Override
    public DespesasDto criarDespesa(DespesasDto despesa) {
        return salvarDespesa(despesa);
    }

    //OBTEM TODAS AS DESPESAS
    @Override
    public List<DespesasDto> obterTodas() {
        List<Despesas> despesas = repository.findAll();

        return despesas.stream()
            .map(despesa -> new ModelMapper().map(despesa, DespesasDto.class))
            .collect(Collectors.toList());
    }

    //OBTEM UMA DESPESA POR ID
    @Override
    public Optional<DespesasDto> obterPorId(String id) {
       Optional<Despesas> despesa = repository.findById(id);

       if(despesa.isPresent()) {
           DespesasDto dto = new ModelMapper().map(despesa.get(), DespesasDto.class);
           return Optional.of(dto);
       }
       return Optional.empty();
    }

    //REMOVE UMA DESPESA
    @Override
    public void removerDespesa(String id) {
        repository.deleteById(id);
    }

    //ATUALIZA UMA DESPESA
    @Override
    public DespesasDto atualizarDespesa(String id, DespesasDto despesa) {
        despesa.setId(id);
        return salvarDespesa(despesa);
    }

    private DespesasDto salvarDespesa(DespesasDto despesa) {
        ModelMapper mapper = new ModelMapper();
        Despesas despesaEntidade = mapper.map(despesa, Despesas.class);
        despesaEntidade = repository.save(despesaEntidade);

        return mapper.map(despesaEntidade, DespesasDto.class);
    }

    //OBTEM A LISTAGEM SOMENTO COM O ID E A EMPRESA
    @Override
    public List<DespesasDtoDiferenciado> obterPorListagem() {
        List<Despesas> despesas = repository.findAll();

        return despesas.stream()
            .map(despesa -> new ModelMapper().map(despesa, DespesasDtoDiferenciado.class))
            .collect(Collectors.toList());
    }
}

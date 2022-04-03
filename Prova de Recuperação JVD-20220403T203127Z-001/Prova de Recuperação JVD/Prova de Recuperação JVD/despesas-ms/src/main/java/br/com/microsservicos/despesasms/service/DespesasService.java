package br.com.microsservicos.despesasms.service;

import java.util.List;
import java.util.Optional;

import br.com.microsservicos.despesasms.shared.DespesasDto;
import br.com.microsservicos.despesasms.view.model.DespesasDtoDiferenciado; 

public interface DespesasService {
    DespesasDto criarDespesa(DespesasDto despesa);
    List<DespesasDto> obterTodas();
    Optional<DespesasDto> obterPorId(String id);
    void removerDespesa(String id);
    DespesasDto atualizarDespesa(String id, DespesasDto despesa);
    List<DespesasDtoDiferenciado> obterPorListagem();
}

package br.com.microsservicos.despesasms.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.microsservicos.despesasms.model.Despesas;

@Repository
public interface DespesasRepository extends MongoRepository<Despesas, String> {
    
}

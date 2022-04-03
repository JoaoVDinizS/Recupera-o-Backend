package br.com.microsservicos.despesasms.model;

import javax.xml.crypto.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("despesas")
public class Despesas {
    @Id
    private String id;
    private String descricao;
    private double valor;
    private Data dataVencimento;
    private Data dataPagamento;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public double getValor() {
        return valor;
    }
    public void setValor(double valor) {
        this.valor = valor;
    }
    public Data getDataVencimento() {
        return dataVencimento;
    }
    public void setDataVencimento(Data dataVencimento) {
        this.dataVencimento = dataVencimento;
    }
    public Data getDataPagamento() {
        return dataPagamento;
    }
    public void setDataPagamento(Data dataPagamento) {
        this.dataPagamento = dataPagamento;
    }
}

package Model;

import java.util.HashMap;
import java.util.Map;

public class FormaPagamento {

    private int id;
    private String descricao;

    private static final Map<Integer, FormaPagamento> idToFormaPagamentoMap = new HashMap<>();
    private static final Map<String, FormaPagamento> descricaoToFormaPagamentoMap = new HashMap<>();

    public FormaPagamento() {
    }

    public FormaPagamento(int id, String descricao) {
        this.id = id;
        this.descricao = descricao;
        // Adiciona a instância ao mapa estático
        idToFormaPagamentoMap.put(id, this);
        descricaoToFormaPagamentoMap.put(descricao, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        // Atualiza o mapa quando o id é alterado
        idToFormaPagamentoMap.put(id, this);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        // Atualiza o mapa quando a descrição é alterada
        descricaoToFormaPagamentoMap.put(descricao, this);
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static FormaPagamento valueOf(int id) {
        // Busca no mapa pela chave id
        return idToFormaPagamentoMap.get(id);
    }

    public static FormaPagamento fromDescricao(String descricao) {
        // Busca no mapa pela chave descrição
        return descricaoToFormaPagamentoMap.get(descricao);
    }
}

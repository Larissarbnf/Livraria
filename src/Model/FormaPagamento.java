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
        idToFormaPagamentoMap.put(id, this);
        descricaoToFormaPagamentoMap.put(descricao, this);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        idToFormaPagamentoMap.put(id, this);
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
        descricaoToFormaPagamentoMap.put(descricao, this);
    }

    @Override
    public String toString() {
        return descricao;
    }

    public static FormaPagamento valueOf(int id) {
        FormaPagamento forma = idToFormaPagamentoMap.get(id);
        if (forma == null) {
            throw new IllegalArgumentException("Forma de pagamento com ID " + id + " não encontrada.");
        }
        return forma;
    }

    public static FormaPagamento fromDescricao(String descricao) {
        FormaPagamento forma = descricaoToFormaPagamentoMap.get(descricao);
        if (forma == null) {
            throw new IllegalArgumentException("Forma de pagamento com descrição '" + descricao + "' não encontrada.");
        }
        return forma;
    }

    static {
        new FormaPagamento(1, "Dinheiro");
        new FormaPagamento(2, "Cartão de Crédito");
        new FormaPagamento(3, "Boleto");
    }
}

package Model;

public class RelatorioFactory {

    public static Relatorio criarRelatorio(String tipo) {
        if (tipo.equalsIgnoreCase("aluguel")) {
            return new RelatorioAluguel();
        } else if (tipo.equalsIgnoreCase("venda")) {
            return new RelatorioVenda();
        } else {
            throw new IllegalArgumentException("Tipo de relatório desconhecido: " + tipo);
        }
    }
}

package Model;

public class RelatorioFactory {

    public static Relatorio criarRelatorio() {
        return new RelatorioConcreto();
    }
}

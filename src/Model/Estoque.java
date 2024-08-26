package Model;

import java.sql.SQLException;

import DAO.EstoqueDAO;
import DTO.LivroDTO;

public class Estoque {

    private static Estoque instance;
    private EstoqueDAO estoqueDAO;
    
    private Estoque() {
        estoqueDAO = new EstoqueDAO();
    }

    public static synchronized Estoque getInstance() {
        if (instance == null) {
            instance = new Estoque();
        }
        return instance;
    }

    public void adicionarLivro(LivroDTO livro, int quantidade) throws SQLException {
        estoqueDAO.adicionarLivro(livro, quantidade);
    }

    public void removerLivro(LivroDTO livro, int quantidade) throws SQLException {
        estoqueDAO.removerLivro(livro, quantidade);
    }

    public int verificarQuantidade(LivroDTO livro) throws SQLException {
        return estoqueDAO.verificarQuantidade(livro);
    }
}

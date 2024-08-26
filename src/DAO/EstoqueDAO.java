package DAO;

import ConnectionFactory.ConexaoPostgres;
import DTO.LivroDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EstoqueDAO {

    public void adicionarLivro(LivroDTO livro, int quantidade) throws SQLException {
        String sql = "INSERT INTO estoque (id_livro, quantidade) VALUES (?, ?) " +
                     "ON CONFLICT (id_livro) DO UPDATE SET quantidade = estoque.quantidade + ?";
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, livro.getId());
            pst.setInt(2, quantidade);
            pst.setInt(3, quantidade);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao adicionar livro no estoque. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }

    public void removerLivro(LivroDTO livro, int quantidade) throws SQLException {
        String sql = "UPDATE estoque SET quantidade = quantidade - ? WHERE id_livro = ?";
        Connection con = null;
        PreparedStatement pst = null;
        
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, quantidade);
            pst.setInt(2, livro.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao remover livro do estoque. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }

    public int verificarQuantidade(LivroDTO livro) throws SQLException {
        String sql = "SELECT quantidade FROM estoque WHERE id_livro = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        int quantidade = 0;
        
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, livro.getId());
            rs = pst.executeQuery();
            if (rs.next()) {
                quantidade = rs.getInt("quantidade");
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao verificar a quantidade no estoque. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        
        return quantidade;
    }
}

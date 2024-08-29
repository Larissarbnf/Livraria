package DAO;

import ConnectionFactory.ConexaoPostgres;
import DTO.LivroDTO;
import Model.Livro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LivroDAO {

    public boolean create(Livro.LivroBuilder livroBuilder) throws SQLException {
        String sql = "INSERT INTO livro (titulo, autor, editora, ano_publicacao, isbn, preco, disponibilidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            Livro livro = livroBuilder.build();
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setString(3, livro.getEditora());
            pst.setInt(4, livro.getAnoPublicacao());
            pst.setString(5, livro.getIsbn());
            pst.setDouble(6, livro.getPreco());
            pst.setBoolean(7, livro.getDisponibilidade() != null);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir livro. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }

    public LivroDTO read(int id) throws SQLException {
        String sql = "SELECT * FROM livro WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        LivroDTO.LivroDTOBuilder builder = new LivroDTO.LivroDTOBuilder();
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                builder.setId(rs.getInt("id"))
                       .setTitulo(rs.getString("titulo"))
                       .setAutor(rs.getString("autor"))
                       .setEditora(rs.getString("editora"))
                       .setAnoPublicacao(rs.getInt("ano_publicacao"))
                       .setIsbn(rs.getString("isbn"))
                       .setPreco(rs.getDouble("preco"))
                       .setDisponibilidade(rs.getBoolean("disponibilidade"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar livro. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return builder.build();
    }

    public List<LivroDTO> readAll() throws SQLException {
        String sql = "SELECT * FROM livro";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<LivroDTO> livros = new ArrayList<>();
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while (rs.next()) {
                LivroDTO livro = new LivroDTO.LivroDTOBuilder()
                    .setId(rs.getInt("id"))
                    .setTitulo(rs.getString("titulo"))
                    .setAutor(rs.getString("autor"))
                    .setEditora(rs.getString("editora"))
                    .setAnoPublicacao(rs.getInt("ano_publicacao"))
                    .setIsbn(rs.getString("isbn"))
                    .setPreco(rs.getObject("preco") != null ? rs.getDouble("preco") : 0)
                    .setDisponibilidade(rs.getBoolean("disponibilidade"))
                    .build();
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todos os livros. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return livros;
    }

    public boolean update(LivroDTO.LivroDTOBuilder livroBuilder) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, ano_publicacao = ?, isbn = ?, preco = ?, disponibilidade = ? WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            LivroDTO livro = livroBuilder.build();
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setString(3, livro.getEditora());
            pst.setInt(4, livro.getAnoPublicacao());
            pst.setString(5, livro.getIsbn());
            pst.setDouble(6, livro.getPreco());
            pst.setBoolean(7, livro.getDisponibilidade());
            pst.setInt(8, livro.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar livro. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }


    public List<LivroDTO> listarLivrosDisponiveis() throws SQLException {
        List<LivroDTO> livros = new ArrayList<>();
        String sql = "SELECT * FROM livro WHERE disponibilidade = true";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();

            while (rs.next()) {
                LivroDTO livro = new LivroDTO.LivroDTOBuilder()
                    .setId(rs.getInt("id"))
                    .setTitulo(rs.getString("titulo"))
                    .setAutor(rs.getString("autor"))
                    .setEditora(rs.getString("editora"))
                    .setAnoPublicacao(rs.getInt("ano_publicacao"))
                    .setPreco(rs.getDouble("preco"))
                    .setDisponibilidade(rs.getBoolean("disponibilidade"))
                    .build();
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar livros disponíveis: " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return livros;
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM livro WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar livro. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }
}

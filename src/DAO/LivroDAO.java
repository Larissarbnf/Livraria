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

	public boolean create(Livro livro) throws SQLException {
	    String sql = "INSERT INTO livro (titulo, autor, editora, ano_publicacao, isbn, preco, disponibilidade) VALUES (?, ?, ?, ?, ?, ?, ?)";
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
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
        LivroDTO livro = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                livro = new LivroDTO();
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setIsbn(rs.getString("isbn"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar livro. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return livro;
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
                LivroDTO livro = new LivroDTO();
                livro.setId(rs.getInt("id"));  // Supondo que o campo no banco de dados é "id"
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setEditora(rs.getString("editora"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setIsbn(rs.getString("isbn"));
                
                // Verifique se o valor é nulo e trate-o adequadamente
                if (rs.getObject("preco") != null) {
                    livro.setPreco(rs.getDouble("preco"));
                } else {
                    livro.setPreco(0);  // ou defina um valor padrão se preferir
                }

                livro.setDisponibilidade(rs.getBoolean("disponibilidade"));
                livros.add(livro);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todos os livros. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return livros;
    }


    public boolean update(LivroDTO livro) throws SQLException {
        String sql = "UPDATE livro SET titulo = ?, autor = ?, editora = ?, ano_publicacao = ?, isbn = ? WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setString(1, livro.getTitulo());
            pst.setString(2, livro.getAutor());
            pst.setString(3, livro.getEditora());
            pst.setInt(4, livro.getAnoPublicacao());
            pst.setString(5, livro.getIsbn());
            pst.setInt(6, livro.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar livro. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
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
                LivroDTO livroDTO = new LivroDTO();
                livroDTO.setId(rs.getInt("id"));
                livroDTO.setTitulo(rs.getString("titulo"));
                livroDTO.setAutor(rs.getString("autor"));
                livroDTO.setEditora(rs.getString("editora"));  
                livroDTO.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livroDTO.setPreco(rs.getDouble("preco"));
                livroDTO.setDisponibilidade(rs.getBoolean("disponibilidade"));
                livros.add(livroDTO);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar livros disponíveis: " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return livros;
    }


}


package DAO;

import Interfaces.Bibliotecario;
import Interfaces.Observer;
import DTO.BibliotecarioDTO;
import DTO.LivroDTO;
import DTO.AluguelDTO;
import DTO.RelatorioDTO;
import Interfaces.Subject;
import ConnectionFactory.ConexaoPostgres;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BibliotecarioDAO implements Bibliotecario, Subject {

    private Connection connection;
    private List<Observer> observers = new ArrayList<>();

    public BibliotecarioDAO() {

    }

    public BibliotecarioDAO(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String mensagem) {
        for (Observer observer : observers) {
            observer.update(mensagem);
        }
    }

    @Override
    public void create(BibliotecarioDTO bibliotecario) {
        String sql = "INSERT INTO bibliotecarios (nome, ID, email,senha) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
             
            pst.setString(1, bibliotecario.getNome());
            pst.setLong(2, bibliotecario.getID());
            pst.setString(3, bibliotecario.getEmail());
            pst.setString(4, bibliotecario.getSenha());
            
            pst.executeUpdate();
            try (ResultSet rs = pst.getGeneratedKeys()) {
                if (rs.next()) {
                    bibliotecario.setID(rs.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
    }

    @Override
    public BibliotecarioDTO getBibliotecarioById(long id) throws SQLException {
        String sql = "SELECT * FROM bibliotecarios WHERE ID = ?";
        BibliotecarioDTO bibliotecario = null;
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    bibliotecario = new BibliotecarioDTO();
                    bibliotecario.setNome(rs.getString("nome"));
                    bibliotecario.setID(rs.getLong("ID"));
                    bibliotecario.setEmail(rs.getString("email"));
                    bibliotecario.setSenha(rs.getString("senha"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar bibliotecário. " + e.getMessage(), e);
        }
        return bibliotecario;
    }

    public BibliotecarioDTO login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM bibliotecarios WHERE email = ? AND senha = ?";
        BibliotecarioDTO bibliotecario = null;
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, email);
            pst.setString(2, senha);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    bibliotecario = new BibliotecarioDTO();
                    bibliotecario.setNome(rs.getString("nome"));
                    bibliotecario.setID(rs.getLong("ID"));
                    bibliotecario.setEmail(rs.getString("email"));
                    bibliotecario.setSenha(rs.getString("senha"));
                }
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao fazer login do bibliotecário. " + e.getMessage(), e);
        }
        return bibliotecario;
    }

    @Override
    public List<BibliotecarioDTO> getAllBibliotecarios() throws SQLException {
        List<BibliotecarioDTO> bibliotecarios = new ArrayList<>();
        String sql = "SELECT * FROM bibliotecarios";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                BibliotecarioDTO bibliotecario = new BibliotecarioDTO();
                bibliotecario.setNome(rs.getString("nome"));
                bibliotecario.setID(rs.getLong("ID"));
                bibliotecario.setEmail(rs.getString("email"));
                bibliotecario.setSenha(rs.getString("senha"));
                bibliotecarios.add(bibliotecario);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao listar bibliotecários. " + e.getMessage(), e);
        }
        return bibliotecarios;
    }

    @Override
    public void updateBibliotecario(BibliotecarioDTO bibliotecario) throws SQLException {
        String sql = "UPDATE bibliotecarios SET nome = ?, email = ?, senha = ? WHERE ID = ?";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setString(1, bibliotecario.getNome());
            pst.setString(2, bibliotecario.getEmail());
            pst.setString(4, bibliotecario.getSenha());
            pst.setLong(5, bibliotecario.getID());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar bibliotecário. " + e.getMessage(), e);
        }
    }

    @Override
    public void deleteBibliotecario(long id) throws SQLException {
        String sql = "DELETE FROM bibliotecarios WHERE ID = ?";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar bibliotecário. " + e.getMessage(), e);
        }
    }

    @Override
    public void registrarAluguel(AluguelDTO aluguel) throws SQLException {
        String sql = "INSERT INTO alugueis (livro_id, bibliotecario_id, data_aluguel, data_devolucao_prevista) VALUES (?, ?, ?, ?)";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pst = con.prepareStatement(sql)) {
             
            pst.setLong(1, aluguel.getLivro().getId());  
            pst.setLong(2, aluguel.getBibliotecario().getID()); 
            pst.setString(3, String.valueOf((aluguel.getDataRetirada())));  
            pst.setString(4, String.valueOf(aluguel.getDataDevolucao())); 
            
            pst.executeUpdate();
            notifyObservers("Aluguel registrado: " + aluguel.getIdAluguel()); 
        } catch (SQLException e) {
            throw new SQLException("Erro ao registrar aluguel. " + e.getMessage(), e);
        }
    }

    @Override
    public void devolverLivroAlugado(long aluguelId) throws SQLException {
        String sqlDevolucao = "UPDATE alugueis SET data_devolucao_real = ? WHERE id = ?";
        String sqlAtualizarEstoque = "UPDATE livros SET quantidade = quantidade + 1 WHERE id = (SELECT livro_id FROM alugueis WHERE id = ?)";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement pstDevolucao = con.prepareStatement(sqlDevolucao);
             PreparedStatement pstAtualizarEstoque = con.prepareStatement(sqlAtualizarEstoque)) {
             
            pstDevolucao.setDate(1, new java.sql.Date(System.currentTimeMillis()));
            pstDevolucao.setLong(2, aluguelId);
            pstDevolucao.executeUpdate();

            pstAtualizarEstoque.setLong(1, aluguelId);
            pstAtualizarEstoque.executeUpdate();
            
            notifyObservers("Livro devolvido: " + aluguelId);
        } catch (SQLException e) {
            throw new SQLException("Erro ao devolver livro alugado. " + e.getMessage(), e);
        }
    }

    @Override
    public List<RelatorioDTO> gerarRelatorioLivrosVendidos() throws SQLException {
        List<RelatorioDTO> relatorios = new ArrayList<>();
        String sql = "SELECT livro_id, COUNT(*) AS quantidade_vendida FROM vendas GROUP BY livro_id";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                RelatorioDTO relatorio = new RelatorioDTO();
                relatorio.setLivroId(rs.getLong("livro_id"));
                relatorio.setQuantidadeVendida(rs.getInt("quantidade_vendida"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao gerar relatório de livros vendidos. " + e.getMessage(), e);
        }
        return relatorios;
    }

    @Override
    public List<RelatorioDTO> gerarRelatorioLivrosAlugados() throws SQLException {
        List<RelatorioDTO> relatorios = new ArrayList<>();
        String sql = "SELECT livro_id, COUNT(*) AS quantidade_alugada FROM alugueis GROUP BY livro_id";
        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                RelatorioDTO relatorio = new RelatorioDTO();
                relatorio.setLivroId(rs.getLong("livro_id"));
                relatorio.setQuantidadeAlugada(rs.getInt("quantidade_alugada"));
                relatorios.add(relatorio);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao gerar relatório de livros alugados. " + e.getMessage(), e);
        }
        return relatorios;
    }

}

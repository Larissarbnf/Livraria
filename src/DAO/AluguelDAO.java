package DAO;
import ConnectionFactory.ConexaoPostgres;
import DTO.AluguelDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DTO.ClienteDTO;
import DTO.LivroDTO;
import Model.Cliente;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AluguelDAO {

/*	public void iniciarAluguel(AluguelDTO aluguel) throws SQLException {
	    String sql = "INSERT INTO aluguel (data_retirada, data_devolucao, estado, cliente_id, livro_id) VALUES (?, ?, ?, ?, ?)";
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	        con = ConexaoPostgres.getInstance().abrirConexao();
	        pst = con.prepareStatement(sql);
	        pst.setDate(1, new java.sql.Date(aluguel.getDataRetirada().getTime()));
	        pst.setDate(2, new java.sql.Date(aluguel.getDataDevolucao().getTime()));
	        pst.setString(3, aluguel.getEstado());
	        pst.setInt(4, aluguel.getCliente().getIdRegistro());
	        pst.setInt(5, aluguel.getLivro().getId());
	        pst.executeUpdate();
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao iniciar aluguel: " + e.getMessage());
	    } finally {
	        ConexaoPostgres.getInstance().fecharConexao(con, pst);
	    }
	}
*/
/*	public void iniciarAluguel(AluguelDTO aluguel) throws SQLException {
	    String sql = "INSERT INTO alugueis (data_retirada, data_devolucao, estado, cliente_id, livro_id) VALUES (?, ?, ?, ?, ?)";
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	        con = ConexaoPostgres.getInstance().abrirConexao();
	        pst = con.prepareStatement(sql); 
	        pst.setDate(1, new java.sql.Date(aluguel.getDataRetirada().getTime())); 
	        pst.setDate(2, new java.sql.Date(aluguel.getDataDevolucao().getTime())); 
	        pst.setString(3, aluguel.getEstado()); 
	        pst.setInt(4, aluguel.getCliente().getIdRegistro());
	        pst.setInt(5, aluguel.getLivro().getId()); 

	        pst.executeUpdate(); 
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao iniciar aluguel: " + e.getMessage());
	    } finally {
	        ConexaoPostgres.getInstance().fecharConexao(con, pst); 
	    }
	}
*/
	public void iniciarAluguel(AluguelDTO aluguel) throws SQLException {
	    String sql = "INSERT INTO alugueis (data_retirada, data_devolucao, estado, cliente_id, livro_id) VALUES (?, ?, ?, ?, ?)";
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	        if (aluguel.getCliente() == null || aluguel.getLivro() == null) {
	            throw new IllegalArgumentException("Cliente ou livro não pode ser nulo.");
	        }
	        if (aluguel.getCliente().getIdRegistro() <= 0) {
	            throw new IllegalArgumentException("ID do cliente inválido.");
	        }
	        if (aluguel.getLivro().getId() <= 0) {
	            throw new IllegalArgumentException("ID do livro inválido.");
	        }

	        con = ConexaoPostgres.getInstance().abrirConexao();
	        pst = con.prepareStatement(sql); 
	        pst.setDate(1, new java.sql.Date(aluguel.getDataRetirada().getTime())); 
	        pst.setDate(2, new java.sql.Date(aluguel.getDataDevolucao().getTime())); 
	        pst.setString(3, aluguel.getEstado()); 
	        pst.setInt(4, aluguel.getCliente().getIdRegistro());
	        pst.setInt(5, aluguel.getLivro().getId()); 

	        pst.executeUpdate(); 
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao iniciar aluguel: " + e.getMessage(), e);
	    } catch (IllegalArgumentException e) {
	        throw new SQLException("Erro de validação: " + e.getMessage(), e);
	    } finally {
	        ConexaoPostgres.getInstance().fecharConexao(con, pst); 
	    }
	}

    public void finalizarAluguel(AluguelDTO aluguel) throws SQLException {
        String sql = "UPDATE alugueis SET estado = ? WHERE id_aluguel = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setString(1, aluguel.getEstado());
            pst.setInt(2, aluguel.getIdAluguel());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao finalizar aluguel: " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }
    public List<AluguelDTO> obterAlugueisPorCliente(int clienteId) throws SQLException {
        String sql = "SELECT * FROM alugueis WHERE cliente_id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<AluguelDTO> alugueis = new ArrayList<>();
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();

            while (rs.next()) {
                AluguelDTO aluguel = new AluguelDTO();
                aluguel.setIdAluguel(rs.getInt("id_aluguel"));
                aluguel.setDataRetirada(rs.getDate("data_retirada"));
                aluguel.setDataDevolucao(rs.getDate("data_devolucao"));
                aluguel.setEstado(rs.getString("estado"));

                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdRegistro(rs.getInt("cliente_id"));
                aluguel.setCliente(cliente);

                LivroDTO livro = new LivroDTO.LivroDTOBuilder()
                    .setId(rs.getInt("livro_id"))
                    .build();
                aluguel.setLivro(livro);

                alugueis.add(aluguel);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar alugueis por cliente. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return alugueis;
    }

    public void create(AluguelDTO aluguel) throws SQLException {
        String sql = "INSERT INTO alugueis (data_retirada, data_devolucao, estado, cliente_id, livro_id) VALUES (?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setDate(1, new java.sql.Date(aluguel.getDataRetirada().getTime()));
            pst.setDate(2, new java.sql.Date(aluguel.getDataDevolucao().getTime()));
            pst.setString(3, aluguel.getEstado());
            pst.setInt(4, aluguel.getCliente().getIdRegistro());
            pst.setInt(5, aluguel.getLivro().getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao inserir aluguel no banco de dados: " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }
    public List<LivroDTO> obterLivrosAlugadosPorCliente(int clienteId) throws SQLException {
        List<LivroDTO> livrosAlugados = new ArrayList<>();
        String sql = "SELECT livro_id, titulo FROM alugueis WHERE cliente_id = ?";

        try (Connection con = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, clienteId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                LivroDTO livro = new LivroDTO();
                livro.setIsbn(rs.getString("livro_id"));
                livro.setTitulo(rs.getString("titulo"));
                livrosAlugados.add(livro);
            }
        }

        return livrosAlugados;
    }
    public void enviarNotificacao(int clienteId, String mensagem) {
        Cliente cliente = buscarClientePorId(clienteId); 
        if (cliente != null) {
            cliente.update(mensagem);
            
        }
    }

	private Cliente buscarClientePorId(int clienteId) {
		// TODO Auto-generated method stub
		return null;
	}

}

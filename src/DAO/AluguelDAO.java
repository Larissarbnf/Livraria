package DAO;
import ConnectionFactory.ConexaoPostgres;
import DTO.AluguelDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import DTO.ClienteDTO;
import DTO.LivroDTO;
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
	public void iniciarAluguel(AluguelDTO aluguel) throws SQLException {
	    String sql = "INSERT INTO aluguel (data_retirada, data_devolucao, estado, cliente_id, livro_id) VALUES (?, ?, ?, ?, ?)";
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	        con = ConexaoPostgres.getInstance().abrirConexao(); // Abre a conexão com o banco
	        pst = con.prepareStatement(sql); // Prepara a declaração SQL

	        // Define os parâmetros da declaração SQL
	        pst.setDate(1, new java.sql.Date(aluguel.getDataRetirada().getTime())); // Data de retirada
	        pst.setDate(2, new java.sql.Date(aluguel.getDataDevolucao().getTime())); // Data de devolução
	        pst.setString(3, aluguel.getEstado()); // Estado do aluguel
	        pst.setInt(4, aluguel.getCliente().getIdRegistro()); // ID do cliente
	        pst.setInt(5, aluguel.getLivro().getId()); // ID do livro

	        pst.executeUpdate(); // Executa a inserção no banco de dados
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao iniciar aluguel: " + e.getMessage());
	    } finally {
	        ConexaoPostgres.getInstance().fecharConexao(con, pst); // Fecha a conexão e o PreparedStatement
	    }
	}

    public void finalizarAluguel(AluguelDTO aluguel) throws SQLException {
        String sql = "UPDATE aluguel SET estado = ? WHERE id_aluguel = ?";
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
        String sql = "SELECT * FROM aluguel WHERE cliente_id = ?";
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
}

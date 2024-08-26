package DAO;
import ConnectionFactory.ConexaoPostgres;
import DTO.AluguelDTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AluguelDAO {

	public void iniciarAluguel(AluguelDTO aluguel) throws SQLException {
        String sql = "INSERT INTO aluguel (id_aluguel, data_retirada, data_devolucao, estado, cliente_id, livro_id) VALUES (?, ?, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, aluguel.getIdAluguel());
            pst.setDate(2, new java.sql.Date(aluguel.getDataRetirada().getTime()));
            pst.setDate(3, new java.sql.Date(aluguel.getDataDevolucao().getTime()));
            pst.setString(4, aluguel.getEstado());
            pst.setInt(5, aluguel.getCliente().getIdRegistro()); 
            pst.setInt(6, aluguel.getLivro().getId()); 
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao iniciar aluguel: " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
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
}

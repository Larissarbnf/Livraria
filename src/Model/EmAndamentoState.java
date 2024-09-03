package Model;

import java.sql.SQLException;
import DTO.AluguelDTO;
import DAO.AluguelDAO;
import Interfaces.AluguelState;

public class EmAndamentoState implements AluguelState {

    @Override
    public void iniciar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já está em andamento.");
    }

    @Override
    public void finalizar(AluguelDTO aluguel) throws SQLException {
        aluguel.setEstado("Finalizado");

     
        AluguelDAO dao = new AluguelDAO();
        dao.finalizarAluguel(aluguel);
    }
    public void salvar(AluguelDTO aluguelDTO, AluguelDAO aluguelDAO) throws SQLException {
       
        aluguelDTO.setEstado("Em Andamento");
        
        try {
            aluguelDAO.create(aluguelDTO);
        } catch (SQLException e) {
            
            System.err.println("Erro ao salvar aluguel em andamento: " + e.getMessage());
            throw e; 
        }
    }
}


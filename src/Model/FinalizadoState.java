package Model;

import java.sql.SQLException;

import DAO.AluguelDAO;
import DTO.AluguelDTO;
import Interfaces.AluguelState;

public class FinalizadoState implements AluguelState {

    @Override
    public void iniciar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já foi finalizado.");
    }

    @Override
    public void finalizar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já foi finalizado.");
    }
    public void salvar(AluguelDTO aluguelDTO, AluguelDAO aluguelDAO) throws SQLException {
        
        aluguelDTO.setEstado("Finalizado");
        
        try {
            aluguelDAO.create(aluguelDTO);
        } catch (SQLException e) {
            
            System.err.println("Erro ao salvar aluguel Finalizado" + e.getMessage());
            throw e; 
        }
    }
}

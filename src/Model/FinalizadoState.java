package Model;

import java.sql.SQLException;

import DTO.AluguelDTO;
import Interfaces.AluguelState;

public  class FinalizadoState implements AluguelState {
    
    public void iniciar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já foi finalizado.");
    }

  
    public void finalizar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já foi finalizado.");
    }
}

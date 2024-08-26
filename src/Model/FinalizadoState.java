package Model;

import java.sql.SQLException;

import DTO.AluguelDTO;
import Interfaces.AluguelState;

public abstract class FinalizadoState implements AluguelState {
    @Override
    public void iniciar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já foi finalizado.");
    }

    @Override
    public void finalizar(AluguelDTO aluguel) throws SQLException {
        throw new UnsupportedOperationException("O aluguel já foi finalizado.");
    }
}

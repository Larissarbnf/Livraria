package Model;

import java.sql.SQLException;

import DAO.AluguelDAO;
import DTO.AluguelDTO;
import Interfaces.AluguelState;

public class AluguelContext {
    private AluguelState state;
    private AluguelDAO aluguelDAO;

    public AluguelContext() {
        aluguelDAO = new AluguelDAO();
        state = new EmAndamentoState();
    }

    public void setState(AluguelState state) {
        this.state = state;
    }

    public void iniciar(AluguelDTO aluguel) throws SQLException {
        state.iniciar(aluguel);
    }

    public void finalizar(AluguelDTO aluguel) throws SQLException {
        state.finalizar(aluguel);
    }
}


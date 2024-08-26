package Model;


import java.sql.SQLException;

import DAO.AluguelDAO;
import DTO.AluguelDTO;
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
}

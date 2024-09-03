package Model;

import DTO.AluguelDTO;
import Interfaces.AluguelState;
import DAO.AluguelDAO;
import java.sql.SQLException;

public class Aluguel {
    private AluguelState estado;
    private AluguelDAO aluguelDAO;

    public Aluguel() {
        this.estado = new EmAndamentoState(); 
        this.aluguelDAO = new AluguelDAO();
    }

    public void iniciarAluguel(AluguelDTO aluguel) throws SQLException {
        estado.iniciar(aluguel);
        aluguelDAO.iniciarAluguel(aluguel);
    }

    public void finalizarAluguel(AluguelDTO aluguel) throws SQLException {
        estado.finalizar(aluguel);
        this.estado = new FinalizadoState();
    }

    public void setEstado(AluguelState estado) {
        this.estado = estado;
    }

    public AluguelState getEstado() {
        return estado;
    }
}

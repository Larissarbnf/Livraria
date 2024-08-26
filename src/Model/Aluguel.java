package Model;

import DTO.AluguelDTO;
import Interfaces.AluguelState;
import DAO.AluguelDAO;
import java.sql.SQLException;

public class Aluguel {
    private AluguelState estado;
    private AluguelDAO aluguelDAO;
    private int idAluguel;

    public Aluguel() {
        this.estado = new EmAndamentoState(); 
        this.aluguelDAO = new AluguelDAO();
    }

    public void iniciarAluguel() throws SQLException {
        estado.iniciar(this);
        aluguelDAO.iniciarAluguel(new AluguelDTO(this.idAluguel, estado.getEstado()));
    }

    public void finalizarAluguel() throws SQLException {
        estado.finalizar(this);
        aluguelDAO.finalizarAluguel(new AluguelDTO(this.idAluguel, estado.getEstado()));
    }

    public void setEstado(AluguelState estado) {
        this.estado = estado;
    }

    public AluguelState getEstado() {
        return estado;
    }

    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }
}

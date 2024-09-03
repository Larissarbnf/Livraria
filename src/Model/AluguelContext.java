package Model;

import DTO.AluguelDTO;
import Interfaces.AluguelState;
import DAO.AluguelDAO;

public class AluguelContext {
    private AluguelState estadoAtual;
    private AluguelDAO aluguelDAO;

    public AluguelContext() {
        this.estadoAtual = new EmAndamentoState(); 
        this.aluguelDAO = new AluguelDAO(); 
    }

    public void setEstado(AluguelState estado) {
        this.estadoAtual = estado;
    }

    public void iniciar(AluguelDTO aluguel) throws Exception {
        if (aluguel == null) {
            throw new IllegalArgumentException("O aluguel não pode ser nulo.");
        }
        if (aluguelDAO == null) {
            throw new IllegalStateException("DAO não está inicializado.");
        }
        estadoAtual.salvar(aluguel, aluguelDAO);
    }
}

package Interfaces;

import java.sql.SQLException;

import DAO.AluguelDAO;
import DTO.AluguelDTO;

public interface AluguelState {
    void iniciar(AluguelDTO aluguel) throws SQLException;
    void finalizar(AluguelDTO aluguel) throws SQLException;
    void salvar(AluguelDTO aluguelDTO, AluguelDAO aluguelDAO) throws Exception;
}

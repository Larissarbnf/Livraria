package Interfaces;


import java.sql.SQLException;

import DTO.AluguelDTO;
import Model.Aluguel;

public interface AluguelState {
    void iniciar(AluguelDTO aluguel) throws SQLException;
    void finalizar(AluguelDTO aluguel) throws SQLException;
}


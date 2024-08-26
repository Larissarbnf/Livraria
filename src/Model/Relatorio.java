package Model;

import DAO.AluguelDAO;
import DAO.VendaDAO;
import DTO.ClienteDTO;
import DTO.RelatorioDTO;
import java.sql.SQLException;

public abstract class Relatorio {

    protected AluguelDAO aluguelDAO;
    protected VendaDAO vendaDAO;

    public Relatorio() {
        this.aluguelDAO = new AluguelDAO();
        this.vendaDAO = new VendaDAO();
    }

    public abstract RelatorioDTO gerarRelatorio(ClienteDTO cliente) throws SQLException;
}
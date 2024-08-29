package Model;

import DAO.AluguelDAO;
import DAO.VendaDAO;
import DTO.ClienteDTO;
import DTO.RelatorioDTO;
import java.sql.SQLException;

public class RelatorioConcreto extends Relatorio {

    public RelatorioConcreto() {
        super();
    }

    @Override
    public RelatorioDTO gerarRelatorio(ClienteDTO cliente) throws SQLException {
        RelatorioDTO relatorio = new RelatorioDTO();
        
        relatorio.setLivrosAlugados(aluguelDAO.obterLivrosAlugadosPorCliente(cliente.getIdRegistro()));
        relatorio.setLivrosVendidos(vendaDAO.obterLivrosVendidosPorCliente(cliente.getIdRegistro()));

        return relatorio;
    }
}


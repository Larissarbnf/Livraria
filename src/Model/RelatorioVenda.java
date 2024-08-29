package Model;

import DAO.VendaDAO;
import DTO.ClienteDTO;
import DTO.RelatorioDTO;
import DTO.VendaDTO;

import java.sql.SQLException;
import java.util.List;

public class RelatorioVenda extends Relatorio {

    private final VendaDAO vendaDAO;

    public RelatorioVenda() {
        super();
        this.vendaDAO = new VendaDAO();
    }

    @Override
    public RelatorioDTO gerarRelatorio(ClienteDTO cliente) throws SQLException {
        RelatorioDTO relatorio = new RelatorioDTO();

        List<VendaDTO> vendas = vendaDAO.obterLivrosVendidosPorCliente(cliente.getIdRegistro());
        int totalVendas = vendas.size();
        relatorio.setTotalVendas(totalVendas);

        return relatorio;
    }
}


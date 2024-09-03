package Model;

import DAO.VendaDAO;
import DTO.AluguelDTO;
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

        List<VendaDTO> venda = VendaDAO.obterLivrosVendidosPorCliente(cliente.getIdRegistro());
        int totalVenda = venda.size();

        relatorio.setLivroId(0); // Pode ser usado para algo mais específico, como o ID do livro mais alugado
        relatorio.setTotalVendas(totalVenda);

        return relatorio;
    }
}


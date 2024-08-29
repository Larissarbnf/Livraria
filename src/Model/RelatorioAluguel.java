package Model;

import DAO.AluguelDAO;
import DTO.ClienteDTO;
import DTO.RelatorioDTO;
import DTO.AluguelDTO;

import java.sql.SQLException;
import java.util.List;

public class RelatorioAluguel extends Relatorio {

    public RelatorioAluguel() {
        super();
    }

    @Override
    public RelatorioDTO gerarRelatorio(ClienteDTO cliente) throws SQLException {
        RelatorioDTO relatorio = new RelatorioDTO();

        List<AluguelDTO> alugueis = aluguelDAO.obterAlugueisPorCliente(cliente.getIdRegistro());
        int totalAlugueis = alugueis.size();

        relatorio.setLivroId(0); // Pode ser usado para algo mais específico, como o ID do livro mais alugado
        relatorio.setTotalAlugueis(totalAlugueis);

        return relatorio;
    }
}

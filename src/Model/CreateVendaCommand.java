package Model;

import DAO.VendaDAO;
import DTO.VendaDTO;
import Interfaces.Command;

public class CreateVendaCommand implements Command {
    private VendaDTO venda;

    public CreateVendaCommand(VendaDTO venda) {
        this.venda = venda;
    }

    @Override
    public void execute() throws Exception {
        VendaDAO dao = new VendaDAO();
        dao.create(venda);
    }
}


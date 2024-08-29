package Model;


import DAO.VendaDAO;
import Interfaces.Command;
import DTO.VendaDTO;

public class ReadVendaCommand implements Command {
    private int id;
    private VendaDTO venda;

    public ReadVendaCommand(int id) {
        this.id = id;
    }

    public VendaDTO getVenda() {
        return venda;
    }

    @Override
    public void execute() throws Exception {
        VendaDAO dao = new VendaDAO();
        venda = dao.read(id);
    }
}

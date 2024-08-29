package Model;


import DAO.VendaDAO;
import Interfaces.Command;

public class DeletarVendaCommand implements Command {
    private int id;

    public DeletarVendaCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute() throws Exception {
        VendaDAO dao = new VendaDAO();
        dao.delete(id);
    }
}

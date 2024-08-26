package View;
import javax.swing.SwingUtilities;
import java.sql.SQLException;

public class Maininicial {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CadastroClienteGUI telaCadastro = new CadastroClienteGUI();
                telaCadastro.setVisible(true);
            }
        });
    }
}

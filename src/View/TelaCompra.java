package View;

import DTO.LivroDTO;
import DTO.ClienteDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaCompra extends JFrame {

    private LivroDTO livroDTO;
    private ClienteDTO clienteDTO;

    private JComboBox<String> cmbFormaPagamento;
    private JButton btnConfirmar;

    public TelaCompra(LivroDTO livroDTO, ClienteDTO clienteDTO) {
        this.livroDTO = livroDTO;
        this.clienteDTO = clienteDTO;
        initComponents();
    }

    private void initComponents() {
        setTitle("Compra de Livro");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(3, 2));

        add(new JLabel("Forma de Pagamento:"));
        cmbFormaPagamento = new JComboBox<>(new String[] {"Dinheiro", "Cartão de Crédito", "Boleto"});
        add(cmbFormaPagamento);

        btnConfirmar = new JButton("Confirmar Compra");
        add(btnConfirmar);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Adicione a lógica para salvar a compra
                JOptionPane.showMessageDialog(null, "Compra confirmada com sucesso!");
                dispose();
            }
        });
    }
}

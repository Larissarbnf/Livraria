package View;

import DTO.LivroDTO;
import Model.AluguelContext;
import DTO.AluguelDTO;
import DTO.ClienteDTO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TelaAluguel extends JFrame {

    private LivroDTO livroDTO;
    private ClienteDTO clienteDTO;
    private JTextField txtDataRetirada;
    private JTextField txtDataDevolucao;
    private JComboBox<String> cmbFormaPagamento;
    private JButton btnConfirmar;
    private JButton btnCancelar;

    public TelaAluguel(LivroDTO livroDTO, ClienteDTO clienteDTO) {
        this.livroDTO = livroDTO;
        this.clienteDTO = clienteDTO;
        initComponents();
    }

    private void initComponents() {
        setTitle("Aluguel de Livro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Adiciona os componentes com GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Data de Retirada:"), gbc);

        gbc.gridx = 1;
        txtDataRetirada = new JTextField(20);
        txtDataRetirada.setPreferredSize(new Dimension(200, 25));
        add(txtDataRetirada, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Data de Devolução:"), gbc);

        gbc.gridx = 1;
        txtDataDevolucao = new JTextField(20);
        txtDataDevolucao.setPreferredSize(new Dimension(200, 25));
        add(txtDataDevolucao, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Forma de Pagamento:"), gbc);

        gbc.gridx = 1;
        cmbFormaPagamento = new JComboBox<>(new String[] {"Dinheiro", "Cartão de Crédito", "Boleto"});
        cmbFormaPagamento.setPreferredSize(new Dimension(200, 25));
        add(cmbFormaPagamento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(34, 139, 34)); // Verde escuro
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 69, 69)); // Vermelho
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(btnCancelar);

        add(buttonPanel, gbc);

        // Ação do botão Confirmar
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataRetirada = sdf.parse(txtDataRetirada.getText());
                    Date dataDevolucao = sdf.parse(txtDataDevolucao.getText());

                    if (dataRetirada.after(dataDevolucao)) {
                        JOptionPane.showMessageDialog(null, "A data de retirada não pode ser após a data de devolução.");
                        return;
                    }

                    AluguelDTO aluguelDTO = new AluguelDTO();
                    aluguelDTO.setDataRetirada(dataRetirada);
                    aluguelDTO.setDataDevolucao(dataDevolucao);
                    aluguelDTO.setCliente(clienteDTO); 
                    aluguelDTO.setLivro(livroDTO); 
                    aluguelDTO.setEstado("Em Andamento");

                    AluguelContext contexto = new AluguelContext();
                    contexto.iniciar(aluguelDTO);

                    JOptionPane.showMessageDialog(null, "Aluguel confirmado com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao confirmar aluguel: " + ex.getMessage());
                }
            }
        });

        // Ação do botão Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela
            }
        });
   



        // Ação do botão Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a tela
            }
        });
    }
    
}

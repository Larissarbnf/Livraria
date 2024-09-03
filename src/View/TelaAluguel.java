package View;

import DTO.LivroDTO;
import DTO.AluguelDTO;
import DTO.ClienteDTO;
import Model.AluguelContext;
import Model.ClienteVip;
import Model.ClienteRegular;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
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
        setLocationRelativeTo(null); 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

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
        cmbFormaPagamento = new JComboBox<>(new String[]{"Dinheiro", "Cartão de Crédito", "Boleto"});
        cmbFormaPagamento.setPreferredSize(new Dimension(200, 25));
        add(cmbFormaPagamento, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setBackground(new Color(34, 139, 34));
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(btnConfirmar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBackground(new Color(255, 69, 69)); 
        btnCancelar.setForeground(Color.WHITE);
        btnCancelar.setFont(new Font("Arial", Font.BOLD, 14));
        buttonPanel.add(btnCancelar);

        add(buttonPanel, gbc);

        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (clienteDTO == null) {
                        JOptionPane.showMessageDialog(null, "Cliente não encontrado.");
                        return;
                    }

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    Date dataRetirada = null;
                    Date dataDevolucao = null;

                    try {
                        dataRetirada = sdf.parse(txtDataRetirada.getText());
                        dataDevolucao = sdf.parse(txtDataDevolucao.getText());
                    } catch (ParseException pe) {
                        JOptionPane.showMessageDialog(null, "Formato de data inválido. Use dd/MM/yyyy.");
                        return;
                    }

                    if (dataRetirada == null || dataDevolucao == null) {
                        JOptionPane.showMessageDialog(null, "Datas inválidas.");
                        return;
                    }

                    if (dataRetirada.after(dataDevolucao)) {
                        JOptionPane.showMessageDialog(null, "A data de retirada não pode ser após a data de devolução.");
                        return;
                    }

                   
                    AluguelDTO aluguelDTO = new AluguelDTO();
                    aluguelDTO.setDataRetirada(dataRetirada);
                    aluguelDTO.setDataDevolucao(dataDevolucao);
                    aluguelDTO.setLivro(livroDTO);
                    aluguelDTO.setCliente(clienteDTO);
                    aluguelDTO.setEstado("Em Andamento");

                    if (clienteDTO.isVip()) {
                        
                        ClienteVip clienteVIP = new ClienteVip();
                        clienteVIP.setNome(clienteDTO.getNome());
                        clienteVIP.setCpf(clienteDTO.getCpf());
                       
                        JOptionPane.showMessageDialog(null, "Cliente VIP: Condições especiais aplicáveis.");
                    } else {
                        
                        ClienteRegular clienteRegular = new ClienteRegular();
                        clienteRegular.setNome(clienteDTO.getNome());
                        clienteRegular.setCpf(clienteDTO.getCpf());
                      
                        JOptionPane.showMessageDialog(null, "Cliente Regular: Desconto padrão aplicado.");
                    }

                    AluguelContext contexto = new AluguelContext();
                    contexto.iniciar(aluguelDTO);

                    JOptionPane.showMessageDialog(null, "Aluguel confirmado com sucesso!");
                    dispose();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao confirmar aluguel: " + ex.getMessage());
                }
            }
        });

        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
            }
        });
    }
}


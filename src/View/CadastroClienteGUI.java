package View;

import javax.swing.*;
import DTO.ClienteDTO;
import Model.Cliente;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class CadastroClienteGUI extends JFrame {

    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtTelefone;
    private JTextField txtEndereco;
    private JTextField txtNumeroEndereco;
    private JTextField txtCep;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnSalvar;
    private JButton btnVoltar; 
    private Cliente cliente;

    public CadastroClienteGUI() {
        cliente = new Cliente() {
            @Override
            public double calcularDesconto(double preco) {
                return 0;
            }
        };

        setTitle("Cadastro de Cliente");
        setSize(600, 650); 
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblTitle = new JLabel("Cadastro de Cliente");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(34, 139, 34));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        addLabelAndField("Nome:", txtNome = new JTextField(), gbc);
        addLabelAndField("CPF:", txtCpf = new JTextField(), gbc);
        addLabelAndField("Telefone:", txtTelefone = new JTextField(), gbc);
        addLabelAndField("Endereço:", txtEndereco = new JTextField(), gbc);
        addLabelAndField("Número Endereço:", txtNumeroEndereco = new JTextField(), gbc);
        addLabelAndField("CEP:", txtCep = new JTextField(), gbc);
        addLabelAndField("Email:", txtEmail = new JTextField(), gbc);
        addLabelAndField("Senha:", txtSenha = new JPasswordField(), gbc);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34));
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setPreferredSize(new Dimension(150, 40));
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy++;
        add(btnSalvar, gbc);

        btnVoltar = new JButton("Voltar"); 
        btnVoltar.setBackground(new Color(255, 69, 0));  
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setFont(new Font("Arial", Font.BOLD, 14));
        btnVoltar.setPreferredSize(new Dimension(150, 40));
        gbc.gridy++;
        add(btnVoltar, gbc);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ClienteDTO clienteDTO = new ClienteDTO();
                    clienteDTO.setNome(txtNome.getText());
                    clienteDTO.setCpf(txtCpf.getText());
                    clienteDTO.setTelefone(txtTelefone.getText());
                    clienteDTO.setEndereco(txtEndereco.getText());
                    clienteDTO.setNumeroEndereco(Integer.parseInt(txtNumeroEndereco.getText()));
                    clienteDTO.setCep(txtCep.getText());
                    clienteDTO.setEmail(txtEmail.getText());
                    clienteDTO.setSenha(new String(txtSenha.getPassword()));

                    if (cliente.create(clienteDTO)) {
                        JOptionPane.showMessageDialog(null, "Cliente salvo com sucesso!");
                        dispose();
                        new TelaPrincipal().setVisible(true);  
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de SQL: " + ex.getMessage());
                }
            }
        });

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  
                new TelaPrincipal().setVisible(true);  
            }
        });
    }

    private void addLabelAndField(String labelText, JTextField textField, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        add(label, gbc);

        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(400, 30)); 
        textField.setBackground(Color.WHITE);
        gbc.gridx = 1;
        add(textField, gbc);

        gbc.gridy++;
    }

    private void clearFields() {
        txtNome.setText("");
        txtCpf.setText("");
        txtTelefone.setText("");
        txtEndereco.setText("");
        txtNumeroEndereco.setText("");
        txtCep.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
    }
}

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
    private Cliente cliente;

    public CadastroClienteGUI() {
        cliente = new Cliente() {};

        setTitle("Cadastro de Cliente");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); // Usar GridBagLayout para facilitar o alinhamento
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes
        
        // Centraliza a tela
        setLocationRelativeTo(null);

        // Define a cor de fundo para cinza claro
        getContentPane().setBackground(new Color(230, 230, 230));

        // Define a fonte Arial para todos os componentes
        Font arialFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Nome:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtNome = new JTextField(15);
        txtNome.setFont(arialFont);
        add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("CPF:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtCpf = new JTextField(15);
        txtCpf.setFont(arialFont);
        add(txtCpf, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Telefone:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtTelefone = new JTextField(15);
        txtTelefone.setFont(arialFont);
        add(txtTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Endereço:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtEndereco = new JTextField(15);
        txtEndereco.setFont(arialFont);
        add(txtEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Número Endereço:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtNumeroEndereco = new JTextField(15);
        txtNumeroEndereco.setFont(arialFont);
        add(txtNumeroEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("CEP:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtCep = new JTextField(15);
        txtCep.setFont(arialFont);
        add(txtCep, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Email:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtEmail = new JTextField(15);
        txtEmail.setFont(arialFont);
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Senha:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtSenha = new JPasswordField(15);
        txtSenha.setFont(arialFont);
        add(txtSenha, gbc);

        // Centraliza o botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(arialFont);
        btnSalvar.setBackground(new Color(34, 139, 34)); // Verde
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setPreferredSize(new Dimension(150, 40));

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
                        dispose();  // Fecha a tela de cadastro
                        TelaLogin login = new TelaLogin(null, null, null);  
                        login.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de SQL: " + ex.getMessage());
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSalvar, gbc);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
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

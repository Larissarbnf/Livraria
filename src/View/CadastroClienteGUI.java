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
        setLayout(new GridLayout(10, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("CPF:"));
        txtCpf = new JTextField();
        add(txtCpf);

        add(new JLabel("Telefone:"));
        txtTelefone = new JTextField();
        add(txtTelefone);

        add(new JLabel("Endereço:"));
        txtEndereco = new JTextField();
        add(txtEndereco);

        add(new JLabel("Número Endereço:"));
        txtNumeroEndereco = new JTextField();
        add(txtNumeroEndereco);

        add(new JLabel("CEP:"));
        txtCep = new JTextField();
        add(txtCep);

        add(new JLabel("Email:"));
        txtEmail = new JTextField();
        add(txtEmail);

        add(new JLabel("Senha:"));
        txtSenha = new JPasswordField();
        add(txtSenha);

        btnSalvar = new JButton("Salvar");
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
                        TelaLogin login = new TelaLogin();  
                        login.setVisible(true);
                    } else {
                        JOptionPane.showMessageDialog(null, "Erro ao salvar cliente.");
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de SQL: " + ex.getMessage());
                }
            }
        });
        add(btnSalvar);
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

package View;

import javax.swing.*;
import DTO.BibliotecarioDTO;
import DAO.BibliotecarioDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class CadastroBibliotecarioGUI extends JFrame {

    private JTextField txtNome;
    private JTextField txtID;
    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private JButton btnSalvar;
    private BibliotecarioDAO bibliotecarioDAO;

    public CadastroBibliotecarioGUI() {
        bibliotecarioDAO = new BibliotecarioDAO();

        setTitle("Cadastro de Bibliotecário");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 2));

        add(new JLabel("Nome:"));
        txtNome = new JTextField();
        add(txtNome);

        add(new JLabel("ID:"));
        txtID = new JTextField();
        add(txtID);

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
                    BibliotecarioDTO bibliotecarioDTO = new BibliotecarioDTO();
                    bibliotecarioDTO.setNome(txtNome.getText());
                    bibliotecarioDTO.setID(Long.parseLong(txtID.getText()));
                    bibliotecarioDTO.setEmail(txtEmail.getText());
                    bibliotecarioDTO.setSenha(new String(txtSenha.getPassword()));

                    bibliotecarioDAO.create(bibliotecarioDTO);
                    JOptionPane.showMessageDialog(null, "Bibliotecário salvo com sucesso!");
                    dispose();  
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro: ID deve ser um número.");
                }
            }
        });
        add(btnSalvar);
    }

    private void clearFields() {
        txtNome.setText("");
        txtID.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
    }

}
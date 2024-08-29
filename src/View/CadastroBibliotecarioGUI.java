package View;

import javax.swing.*;
import DTO.BibliotecarioDTO;
import DAO.BibliotecarioDAO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        add(createLabel("ID:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtID = new JTextField(15);
        txtID.setFont(arialFont);
        add(txtID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Email:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtEmail = new JTextField(15);
        txtEmail.setFont(arialFont);
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
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

        gbc.gridx = 0;
        gbc.gridy = 4;
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
        txtID.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
    }
}

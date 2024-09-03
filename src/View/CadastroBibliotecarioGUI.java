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
    private JButton btnVoltar;
    private BibliotecarioDAO bibliotecarioDAO;

    public CadastroBibliotecarioGUI() {
        bibliotecarioDAO = new BibliotecarioDAO();

        setTitle("Cadastro de Bibliotecário");
        setSize(500, 450); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout()); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); 

       
        setLocationRelativeTo(null);

        
        getContentPane().setBackground(new Color(230, 230, 230));

        
        Font arialFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Nome:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtNome = new JTextField();
        txtNome.setPreferredSize(new Dimension(300, 30));
        txtNome.setFont(arialFont);
        add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("ID:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtID = new JTextField();
        txtID.setPreferredSize(new Dimension(300, 30)); 
        txtID.setFont(arialFont);
        add(txtID, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Email:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(300, 30)); 
        txtEmail.setFont(arialFont);
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        add(createLabel("Senha:", arialFont), gbc);

        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        txtSenha = new JPasswordField();
        txtSenha.setPreferredSize(new Dimension(300, 30)); 
        txtSenha.setFont(arialFont);
        add(txtSenha, gbc);

        // Botão Salvar
        btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(arialFont);
        btnSalvar.setBackground(new Color(34, 139, 34)); 
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

        // Botão Voltar
        btnVoltar = new JButton("Voltar");
        btnVoltar.setFont(arialFont);
        btnVoltar.setBackground(new Color(255, 69, 0)); 
        btnVoltar.setForeground(Color.WHITE);
        btnVoltar.setPreferredSize(new Dimension(150, 40));

        btnVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                new TelaPrincipal().setVisible(true); 
            }
        });

        gbc.gridy = 5; 
        add(btnVoltar, gbc);
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

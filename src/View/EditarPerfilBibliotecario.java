package View;

import javax.swing.*;

import DAO.BibliotecarioDAO;
import DTO.BibliotecarioDTO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class EditarPerfilBibliotecario extends JFrame {
    private BibliotecarioDTO bibliotecario;
    private BibliotecarioDAO bibliotecarioDAO;

    public EditarPerfilBibliotecario(BibliotecarioDTO bibliotecario) {
        this.bibliotecario = bibliotecario;
        this.bibliotecarioDAO = new BibliotecarioDAO();

        setTitle("Editar Perfil - Bibliotecário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Campos de texto
        JTextField txtNome = new JTextField(bibliotecario.getNome(), 20);
        JTextField txtEmail = new JTextField(bibliotecario.getEmail(), 20);
        JPasswordField txtSenha = new JPasswordField(bibliotecario.getSenha(), 20);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1;
        add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Senha:"), gbc);
        gbc.gridx = 1;
        add(txtSenha, gbc);

        // Botão para salvar alterações
        JButton btnSalvar = new JButton("Salvar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(btnSalvar, gbc);

        // Ação do botão "Salvar"
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    bibliotecario.setNome(txtNome.getText());
                    bibliotecario.setEmail(txtEmail.getText());
                    bibliotecario.setSenha(new String(txtSenha.getPassword()));
                    
                    bibliotecarioDAO.updateBibliotecario(bibliotecario);
                    JOptionPane.showMessageDialog(null, "Perfil atualizado com sucesso!");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar perfil: " + ex.getMessage());
                }
            }
        });

        setLocationRelativeTo(null); 
        setVisible(true);
    }
}

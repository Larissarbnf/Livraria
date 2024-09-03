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
        setLocationRelativeTo(null); 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0;

        
        JTextField txtNome = new JTextField(bibliotecario.getNome(), 20);
        JTextField txtEmail = new JTextField(bibliotecario.getEmail(), 20);
        JPasswordField txtSenha = new JPasswordField(bibliotecario.getSenha(), 20);

        JLabel lblNome = new JLabel("Nome:");
        JLabel lblEmail = new JLabel("Email:");
        JLabel lblSenha = new JLabel("Senha:");

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(70, 130, 180)); // Steel Blue
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        btnSalvar.setFocusPainted(false);
        btnSalvar.setPreferredSize(new Dimension(100, 40));

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblNome, gbc);
        gbc.gridx = 1;
        add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblEmail, gbc);
        gbc.gridx = 1;
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblSenha, gbc);
        gbc.gridx = 1;
        add(txtSenha, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnSalvar, gbc);

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
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar perfil: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new EditarPerfilBibliotecario(new BibliotecarioDTO()).setVisible(true));
    }
}

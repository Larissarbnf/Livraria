package View;

import DTO.BibliotecarioDTO;
import javax.swing.*;
import java.awt.*;

public class TelaPerfilBibliotecario extends JFrame {

    private BibliotecarioDTO bibliotecarioDTO;

    public TelaPerfilBibliotecario(BibliotecarioDTO bibliotecarioDTO) {
        this.bibliotecarioDTO = bibliotecarioDTO;
        initComponents();
    }

    private void initComponents() {
        setTitle("Perfil do Bibliotecário");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Nome: " + bibliotecarioDTO.getNome()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Email: " + bibliotecarioDTO.getEmail()), gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        

        JButton btnEditarPerfil = new JButton("Editar Perfil");
        btnEditarPerfil.setBackground(new Color(70, 130, 180)); // Steel Blue
        btnEditarPerfil.setForeground(Color.WHITE);
        btnEditarPerfil.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(btnEditarPerfil, gbc);

        btnEditarPerfil.addActionListener(e -> {
            // Ação para editar perfil (adicionar a lógica conforme necessário)
            JOptionPane.showMessageDialog(this, "Editar Perfil clicado.");
        });
    }
}

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
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        getContentPane().setBackground(new Color(240, 240, 240)); 
        Font labelFont = new Font("Arial", Font.BOLD, 16);

        JLabel lblTitle = new JLabel("Perfil do Bibliotecário");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
        lblTitle.setForeground(new Color(70, 130, 180)); // Steel Blue
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        gbc.anchor = GridBagConstraints.CENTER;
        add(lblTitle, gbc);

        JLabel lblNome = new JLabel("Nome: " + bibliotecarioDTO.getNome());
        lblNome.setFont(labelFont);
        lblNome.setForeground(new Color(50, 50, 50)); 
        gbc.gridy = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        add(lblNome, gbc);

        JLabel lblEmail = new JLabel("Email: " + bibliotecarioDTO.getEmail());
        lblEmail.setFont(labelFont);
        lblEmail.setForeground(new Color(50, 50, 50));
        gbc.gridy = 2;
        gbc.gridwidth = GridBagConstraints.REMAINDER; 
        add(lblEmail, gbc);

        gbc.gridy = 3;
        gbc.gridheight = GridBagConstraints.REMAINDER;
        add(new JLabel(""), gbc);

        getContentPane().setBackground(new Color(245, 245, 245)); 

        setVisible(true);
    }

  
}

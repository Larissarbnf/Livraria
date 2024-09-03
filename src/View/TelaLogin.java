package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import DAO.ClienteDAO;
import DAO.BibliotecarioDAO;
import DAO.LivroDAO;
import DTO.ClienteDTO;
import DTO.BibliotecarioDTO;
import DTO.LivroDTO;

public class TelaLogin extends JFrame {

    private JTextField txtEmail;
    private JPasswordField txtSenha;
    private ClienteDAO clienteDAO;
    private LivroDAO livroDAO;
    private BibliotecarioDAO bibliotecarioDAO;

    public TelaLogin(ClienteDAO clienteDAO, BibliotecarioDAO bibliotecarioDAO, LivroDAO livroDAO) {
        this.clienteDAO = clienteDAO;
        this.bibliotecarioDAO = bibliotecarioDAO;
        this.livroDAO = livroDAO;
        initComponents();
    }
    public TelaLogin() {
    	    this.clienteDAO = new ClienteDAO();
    	    this.bibliotecarioDAO = new BibliotecarioDAO();
    	    this.livroDAO = new LivroDAO();
    	    initComponents();
    	}

    private void initComponents() {
        setTitle("Login");
        setSize(350, 220);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new GridBagLayout());
        contentPanel.setBackground(new Color(255, 255, 255));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.CENTER;

        JLabel lblWelcome = new JLabel("Bem-vindo à Livraria");
        lblWelcome.setFont(new Font("Arial", Font.BOLD, 18));
        lblWelcome.setForeground(new Color(0, 102, 204));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        contentPanel.add(lblWelcome, gbc);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        contentPanel.add(lblEmail, gbc);

        txtEmail = new JTextField(20);
        txtEmail.setPreferredSize(new Dimension(160, 25));
        gbc.gridx = 1;
        gbc.gridy = 1;
        contentPanel.add(txtEmail, gbc);

        JLabel lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        contentPanel.add(lblSenha, gbc);

        txtSenha = new JPasswordField(20);
        txtSenha.setPreferredSize(new Dimension(160, 25));
        gbc.gridx = 1;
        gbc.gridy = 2;
        contentPanel.add(txtSenha, gbc);

        JButton btnLogin = new JButton("Login");
        btnLogin.setFont(new Font("Arial", Font.BOLD, 14));
        btnLogin.setBackground(new Color(0, 102, 204));
        btnLogin.setForeground(Color.WHITE);
        btnLogin.setFocusPainted(false);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        contentPanel.add(btnLogin, gbc);

        add(contentPanel, BorderLayout.CENTER);

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String email = txtEmail.getText();
                    String senha = new String(txtSenha.getPassword());

                    ClienteDTO clienteDTO = clienteDAO.login(email, senha);
                    if (clienteDTO != null) {
                        dispose();
                        List<LivroDTO> livrosDisponiveis = livroDAO.listarLivrosDisponiveis();
                        TelaInicialCliente telaInicial = new TelaInicialCliente(livrosDisponiveis, clienteDTO);
                        telaInicial.setVisible(true);
                    } else {
                        BibliotecarioDTO bibliotecarioDTO = bibliotecarioDAO.login(email, senha);
                        if (bibliotecarioDTO != null) {
                            dispose();
                            TelaInicialBibliotecario telaInicialBibliotecario = new TelaInicialBibliotecario(bibliotecarioDTO);
                            telaInicialBibliotecario.setVisible(true);
                        } else {
                            JOptionPane.showMessageDialog(null, "Email ou senha incorretos.");
                        }
                    }
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro de SQL: " + ex.getMessage());
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ClienteDAO clienteDAO = new ClienteDAO();
                BibliotecarioDAO bibliotecarioDAO = new BibliotecarioDAO();
                LivroDAO livroDAO = new LivroDAO();
                new TelaLogin(clienteDAO, bibliotecarioDAO, livroDAO).setVisible(true);
            }
        });
    }
}

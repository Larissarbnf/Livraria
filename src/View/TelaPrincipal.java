package View;

import javax.swing.*;

import DAO.BibliotecarioDAO;
import DAO.ClienteDAO;
import DAO.LivroDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private JButton btnCadastroCliente;
    private JButton btnCadastroBibliotecario;
    private JButton btnLogin;
    
    public TelaPrincipal() {
        setTitle("Sistema de Biblioteca");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); 

        getContentPane().setBackground(new Color(240, 248, 255)); 

        btnCadastroCliente = criarBotao("Cadastro Cliente", new Color(100, 149, 237), Color.WHITE); 
        btnCadastroBibliotecario = criarBotao("Cadastro Bibliotecário", new Color(100, 149, 237), Color.WHITE);
        btnLogin = criarBotao("Login", new Color(85, 175, 85), Color.WHITE); 

        btnCadastroCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroClienteGUI cadastroClienteGUI = new CadastroClienteGUI();
                cadastroClienteGUI.setVisible(true);
                dispose();
            }
        });

        btnCadastroBibliotecario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CadastroBibliotecarioGUI cadastroBibliotecarioGUI = new CadastroBibliotecarioGUI();
                cadastroBibliotecarioGUI.setVisible(true);
                dispose();
            }
        });

        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaLogin telaLogin = new TelaLogin();
                telaLogin.setVisible(true);
                dispose();
            }
        });

        add(Box.createVerticalGlue()); 
        add(btnCadastroCliente);
        add(Box.createRigidArea(new Dimension(0, 10))); 
        add(btnCadastroBibliotecario);
        add(Box.createRigidArea(new Dimension(0, 10))); 
        add(btnLogin);
        add(Box.createVerticalGlue()); 
    }

    private JButton criarBotao(String texto, Color corFundo, Color corTexto) {
        JButton botao = new JButton(texto);
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT); 
        botao.setMaximumSize(new Dimension(200, 50)); 
        botao.setPreferredSize(new Dimension(200, 50)); 
        return botao;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaPrincipal().setVisible(true);
            }
        });
    }
}


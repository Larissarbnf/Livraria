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
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // Layout vertical

        // Define uma cor de fundo suave
        getContentPane().setBackground(new Color(240, 248, 255)); // AliceBlue

        // Cria os botões com o mesmo estilo e tamanho
        btnCadastroCliente = criarBotao("Cadastro Cliente", new Color(100, 149, 237), Color.WHITE); // CornflowerBlue
        btnCadastroBibliotecario = criarBotao("Cadastro Bibliotecário", new Color(100, 149, 237), Color.WHITE); // CornflowerBlue
        btnLogin = criarBotao("Login", new Color(85, 175, 85), Color.WHITE); 

        // Define os listeners dos botões
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

        // Adiciona os botões ao painel com espaçamento
        add(Box.createVerticalGlue()); // Adiciona espaço flexível acima
        add(btnCadastroCliente);
        add(Box.createRigidArea(new Dimension(0, 10))); // Adiciona espaço fixo entre os botões
        add(btnCadastroBibliotecario);
        add(Box.createRigidArea(new Dimension(0, 10))); // Adiciona espaço fixo entre os botões
        add(btnLogin);
        add(Box.createVerticalGlue()); // Adiciona espaço flexível abaixo
    }

    // Método para criar um botão estilizado com tamanho fixo
    private JButton criarBotao(String texto, Color corFundo, Color corTexto) {
        JButton botao = new JButton(texto);
        botao.setBackground(corFundo);
        botao.setForeground(corTexto);
        botao.setFont(new Font("Arial", Font.BOLD, 16));
        botao.setFocusPainted(false);
        botao.setAlignmentX(Component.CENTER_ALIGNMENT); // Centraliza o botão
        botao.setMaximumSize(new Dimension(200, 50)); // Define o tamanho máximo
        botao.setPreferredSize(new Dimension(200, 50)); // Define o tamanho preferido
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


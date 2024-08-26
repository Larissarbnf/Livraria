package View;

import javax.swing.*;

import DAO.BibliotecarioDAO;
import DAO.ClienteDAO;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaPrincipal extends JFrame {

    private JButton btnCadastroCliente;
    private JButton btnCadastroBibliotecario;
    private JButton btnLogin;
    private ClienteDAO clienteDAO;
	private BibliotecarioDAO bibliotecarioDAO;

    public TelaPrincipal() {
        setTitle("Sistema de Biblioteca");
        setSize(1000, 950);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(4, 4, 10, 10));

        btnCadastroCliente = new JButton("Cadastro Cliente");
        btnCadastroBibliotecario = new JButton("Cadastro Bibliotecário");
        btnLogin = new JButton("Login");

        
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

    
        add(btnCadastroCliente);
        add(btnCadastroBibliotecario);
        add(btnLogin);
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



package View;

import javax.swing.*;
import DAO.BibliotecarioDAO;
import DAO.LivroDAO;
import DTO.BibliotecarioDTO;
import DTO.LivroDTO;
import DTO.RelatorioDTO;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class TelaInicialBibliotecario extends JFrame {
    private BibliotecarioDTO bibliotecario;
    private LivroDAO livroDAO;
    private BibliotecarioDAO bibliotecarioDAO;

    public TelaInicialBibliotecario(BibliotecarioDTO bibliotecario) {
        this.bibliotecario = bibliotecario;
        this.livroDAO = new LivroDAO();
        this.bibliotecarioDAO = new BibliotecarioDAO(); 
        setTitle("Tela Inicial - Bibliotecário");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JButton btnAdicionarLivro = new JButton("Adicionar Livro");
        JButton btnListarLivros = new JButton("Listar Todos os Livros");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnClientes = new JButton("Clientes");
        JButton btnRelatorioVendas = new JButton("Relatório de Livros Vendidos");
        JButton btnRelatorioAluguel = new JButton("Relatório de Livros Alugados");
        JButton btnEditarPerfil = new JButton("Editar Perfil");
        JButton btnSair = new JButton("Sair");

        customizeButton(btnAdicionarLivro);
        customizeButton(btnListarLivros);
        customizeButton(btnPerfil);
        customizeButton(btnClientes);
        customizeButton(btnRelatorioVendas);
        customizeButton(btnRelatorioAluguel);
        customizeButton(btnEditarPerfil);
        customizeButton(btnSair);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 2, 10, 10));
        panel.add(btnAdicionarLivro);
        panel.add(btnListarLivros);
        panel.add(btnPerfil);
        panel.add(btnClientes);
        panel.add(btnRelatorioVendas);
        panel.add(btnRelatorioAluguel);
        panel.add(btnEditarPerfil);
        panel.add(btnSair);

        add(panel, BorderLayout.CENTER);

        btnListarLivros.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<LivroDTO> livrosDisponiveis = livroDAO.listarLivrosDisponiveis();
                    TelaListarLivros telaListarLivros = new TelaListarLivros();
                    telaListarLivros.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao listar livros disponíveis: " + ex.getMessage());
                }
            }
        });

        btnAdicionarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro(livroDAO);
                telaCadastroLivro.setVisible(true);
            }
        });

        btnPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPerfilBibliotecario telaPerfil = new TelaPerfilBibliotecario(bibliotecario);
                telaPerfil.setVisible(true);
            }
        });

        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaClientes telaClientes = new TelaClientes();
                telaClientes.setVisible(true);
            }
        });

        btnRelatorioVendas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<RelatorioDTO> relatorioVendas = bibliotecarioDAO.gerarRelatorioLivrosVendidos();
                    TelaRelatorioVendas telaRelatorioVendas = new TelaRelatorioVendas(relatorioVendas);
                    telaRelatorioVendas.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de vendas: " + ex.getMessage());
                }
            }
        });

        btnRelatorioAluguel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    List<RelatorioDTO> relatorioAluguel = bibliotecarioDAO.gerarRelatorioLivrosAlugados();
                    TelaRelatorioAluguel telaRelatorioAluguel = new TelaRelatorioAluguel(relatorioAluguel);
                    telaRelatorioAluguel.setVisible(true);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao gerar relatório de aluguel: " + ex.getMessage());
                }
            }
        });

        btnEditarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarPerfilBibliotecario telaEditarPerfil = new EditarPerfilBibliotecario(bibliotecario);
                telaEditarPerfil.setVisible(true);
            }
        });

        btnSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); 
                TelaPrincipal telaLogin = new TelaPrincipal(); 
                telaLogin.setVisible(true); 
            }
        });
      
        setLocationRelativeTo(null);

        setVisible(true);
    
    }

    private void customizeButton(JButton button) {
        button.setBackground(new Color(70, 130, 180));
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                BibliotecarioDTO bibliotecarioDTO = new BibliotecarioDTO();
                new TelaInicialBibliotecario(bibliotecarioDTO).setVisible(true);
            }
        });
    }
}

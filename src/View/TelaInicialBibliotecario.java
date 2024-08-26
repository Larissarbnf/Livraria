
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

        // Criação dos botões
        JButton btnAdicionarLivro = new JButton("Adicionar Livro");
        JButton btnListarLivros = new JButton("Listar Todos os Livros");
        JButton btnPerfil = new JButton("Perfil");
        JButton btnClientes = new JButton("Clientes");
        JButton btnRelatorioVendas = new JButton("Relatório de Livros Vendidos");
        JButton btnRelatorioAluguel = new JButton("Relatório de Livros Alugados");
        JButton btnEditarPerfil = new JButton("Editar Perfil");

        // Personalizando os botões
        btnAdicionarLivro.setBackground(new Color(70, 130, 180));
        btnAdicionarLivro.setForeground(Color.WHITE);
        btnAdicionarLivro.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnListarLivros.setBackground(new Color(70, 130, 180));
        btnListarLivros.setForeground(Color.WHITE);
        btnListarLivros.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnPerfil.setBackground(new Color(70, 130, 180));
        btnPerfil.setForeground(Color.WHITE);
        btnPerfil.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnClientes.setBackground(new Color(70, 130, 180));
        btnClientes.setForeground(Color.WHITE);
        btnClientes.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnRelatorioVendas.setBackground(new Color(70, 130, 180));
        btnRelatorioVendas.setForeground(Color.WHITE);
        btnRelatorioVendas.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnRelatorioAluguel.setBackground(new Color(70, 130, 180));
        btnRelatorioAluguel.setForeground(Color.WHITE);
        btnRelatorioAluguel.setFont(new Font("Arial", Font.BOLD, 14));
        
        btnEditarPerfil.setBackground(new Color(70, 130, 180));
        btnEditarPerfil.setForeground(Color.WHITE);
        btnEditarPerfil.setFont(new Font("Arial", Font.BOLD, 14));

        // Adiciona os botões à tela
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2, 10, 10));
        panel.add(btnAdicionarLivro);
        panel.add(btnListarLivros);
        panel.add(btnPerfil);
        panel.add(btnClientes);
        panel.add(btnRelatorioVendas);
        panel.add(btnRelatorioAluguel);
        panel.add(btnEditarPerfil);

        add(panel, BorderLayout.CENTER);

        // Ação do botão "Listar Todos os Livros"
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

        // Ação do botão "Adicionar Livro"
        btnAdicionarLivro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro(livroDAO);
                telaCadastroLivro.setVisible(true);
            }
        });

        // Ação do botão "Perfil"
        btnPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaPerfilBibliotecario telaPerfil = new TelaPerfilBibliotecario(bibliotecario);
                telaPerfil.setVisible(true);
            }
        });

        // Ação do botão "Clientes"
        btnClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TelaClientes telaClientes = new TelaClientes();
                telaClientes.setVisible(true);
            }
        });

        // Ação do botão "Relatório de Livros Vendidos"
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

        // Ação do botão "Relatório de Livros Alugados"
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

        // Ação do botão "Editar Perfil"
        btnEditarPerfil.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                EditarPerfilBibliotecario telaEditarPerfil = new EditarPerfilBibliotecario(bibliotecario);
                telaEditarPerfil.setVisible(true);
            }
        });

        setVisible(true);
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


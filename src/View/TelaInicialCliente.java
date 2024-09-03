package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import DTO.LivroDTO;
import DTO.ClienteDTO;

public class TelaInicialCliente extends JFrame {
    private JTable tabelaLivros;
    private DefaultTableModel modelLivros;
    private JButton btnVerDetalhes;
    private JMenuBar menuBar;
    private JMenu menuPerfil;
    private JMenuItem menuItemEditarPerfil;
    private JMenuItem menuItemNotificacoes;

    public TelaInicialCliente(List<LivroDTO> livrosDisponiveis, ClienteDTO clienteDTO) {
        setTitle("Tela Inicial - Cliente");
        setSize(800, 600);
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel panelTabela = new JPanel();
        panelTabela.setLayout(new BorderLayout());
        panelTabela.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        modelLivros = new DefaultTableModel();
        modelLivros.setColumnIdentifiers(new String[]{"Título", "Autor", "Editora", "Ano", "Preço", "Disponível"});

        tabelaLivros = new JTable(modelLivros);
        tabelaLivros.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tabelaLivros.setFillsViewportHeight(true);
        tabelaLivros.setRowHeight(30);
        tabelaLivros.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaLivros.setForeground(Color.BLACK);
        tabelaLivros.setBackground(Color.WHITE);
        tabelaLivros.setGridColor(new Color(220, 220, 220));
        tabelaLivros.setIntercellSpacing(new Dimension(0, 1));

        for (LivroDTO livro : livrosDisponiveis) {
            modelLivros.addRow(new Object[]{
                livro.getTitulo(),
                livro.getAutor(),
                livro.getEditora(),
                livro.getAnoPublicacao(),
                livro.getPreco(),
                livro.isDisponivel() ? "Sim" : "Não"
            });
        }

        JScrollPane scrollPane = new JScrollPane(tabelaLivros);
        panelTabela.add(scrollPane, BorderLayout.CENTER);
        add(panelTabela, BorderLayout.CENTER);

        btnVerDetalhes = new JButton("Ver Detalhes");
        btnVerDetalhes.setPreferredSize(new Dimension(150, 40));
        btnVerDetalhes.setBackground(new Color(70, 130, 180)); 
        btnVerDetalhes.setForeground(Color.WHITE);
        btnVerDetalhes.setFont(new Font("Arial", Font.BOLD, 14));
        btnVerDetalhes.setFocusPainted(false);

        JPanel panelBotao = new JPanel();
        panelBotao.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelBotao.add(btnVerDetalhes);
        add(panelBotao, BorderLayout.SOUTH);

        btnVerDetalhes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabelaLivros.getSelectedRow();
                if (selectedRow != -1) {
                    LivroDTO livroSelecionado = livrosDisponiveis.get(selectedRow);
                    new TelaDetalhesLivro(livroSelecionado, clienteDTO).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro para ver os detalhes.");
                }
            }
        });

        menuBar = new JMenuBar();
        menuPerfil = new JMenu("Perfil");
        menuPerfil.setFont(new Font("Arial", Font.BOLD, 14));

        menuItemEditarPerfil = new JMenuItem("Editar Perfil");
        menuItemEditarPerfil.setFont(new Font("Arial", Font.PLAIN, 12));
        menuItemEditarPerfil.addActionListener(e -> {
            EditarPerfilGUI editarPerfilGUI = new EditarPerfilGUI(clienteDTO);
            editarPerfilGUI.setVisible(true);
        });

        menuItemNotificacoes = new JMenuItem("Notificações");
        menuItemNotificacoes.setFont(new Font("Arial", Font.PLAIN, 12));
        menuItemNotificacoes.addActionListener(e -> {
            TelaNotificacoes telaNotificacoes = new TelaNotificacoes();
            telaNotificacoes.setVisible(true);
        });

        menuPerfil.add(menuItemEditarPerfil);
        menuPerfil.add(menuItemNotificacoes);
        menuBar.add(menuPerfil);
        setJMenuBar(menuBar);
    }
}

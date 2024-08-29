package View;

import DTO.LivroDTO;
import DAO.LivroDAO;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaListarLivros extends JFrame {
    private JTable tableLivros;
    private DefaultTableModel tableModel;
    private LivroDAO livroDAO;

    public TelaListarLivros() {
        livroDAO = new LivroDAO(); 

        setTitle("Lista de Livros");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout());

        tableModel = new DefaultTableModel();
        tableModel.addColumn("ID");
        tableModel.addColumn("Título");
        tableModel.addColumn("Autor");
        tableModel.addColumn("Editora");
        tableModel.addColumn("Ano de Publicação");
        tableModel.addColumn("ISBN");
        tableModel.addColumn("Preço");
        tableModel.addColumn("Disponível");

        tableLivros = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(tableLivros);
        add(scrollPane, BorderLayout.CENTER);

   
        JButton btnAtualizar = new JButton("Atualizar Lista");
        btnAtualizar.addActionListener(e -> carregarLivros());
        add(btnAtualizar, BorderLayout.SOUTH);

       
        carregarLivros();

        setVisible(true);
    }

    private void carregarLivros() {
        try {
            List<LivroDTO> livros = livroDAO.readAll();
            tableModel.setRowCount(0); 
            for (LivroDTO livro : livros) {
                Object[] row = new Object[]{
                        livro.getId(),
                        livro.getTitulo(),
                        livro.getAutor(),
                        livro.getEditora(),
                        livro.getAnoPublicacao(),
                        livro.getIsbn(),
                        livro.getPreco(),
                        livro.isDisponivel() ? "Sim" : "Não"
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao carregar a lista de livros: " + ex.getMessage());
        }
    }

    public static void main(String[] args) {
        new TelaListarLivros();
    }
}


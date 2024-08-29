package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import DTO.RelatorioDTO;
import DTO.VendaDTO;
import java.awt.Color;
import javax.swing.table.JTableHeader;


public class TelaRelatorioVendas extends JFrame {

    public TelaRelatorioVendas(List<RelatorioDTO> relatorioVendas) {
        setTitle("Relatório de Livros Vendidos");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Layout
        setLayout(new BorderLayout());

        // Tabela
        String[] columnNames = {"ID do Livro", "Total de Vendas"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        // Preencher tabela
        for (RelatorioDTO relatorio : relatorioVendas) {
            Object[] row = {
                    relatorio.getLivroId(),
                    relatorio.getTotalVendas()
            };
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Estilização
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);

        // Cabeçalho
        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);
    }
}

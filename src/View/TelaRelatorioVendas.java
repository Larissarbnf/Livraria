package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import java.awt.*;
import java.util.List;
import DTO.RelatorioDTO;

public class TelaRelatorioVendas extends JFrame {

    public TelaRelatorioVendas(List<RelatorioDTO> relatorioVendas) {
        setTitle("Relatório de Vendas");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        String[] columnNames = {"Nome do Livro", "Nome do Cliente", "Data da Venda"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        for (RelatorioDTO relatorio : relatorioVendas) {
            Object[] row = {
                    relatorio.getNomeLivro(),
                    relatorio.getNomeCliente(),
                    relatorio.getDataVenda()
            };
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);
    }
}

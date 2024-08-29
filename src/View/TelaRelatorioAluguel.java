package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import DTO.RelatorioDTO;
import java.awt.Color;
import javax.swing.table.JTableHeader;


public class TelaRelatorioAluguel extends JFrame {

    public TelaRelatorioAluguel(List<RelatorioDTO> relatorioAluguel) {
        setTitle("Relatório de Livros Alugados");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        
        setLayout(new BorderLayout());

        
        String[] columnNames = {"ID do Livro", "Total de Aluguéis"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

       
        for (RelatorioDTO relatorio : relatorioAluguel) {
            Object[] row = {
                    relatorio.getLivroId(),
                    relatorio.getTotalAlugueis()
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


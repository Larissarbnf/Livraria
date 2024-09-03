package View;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

import DTO.RelatorioDTO;
import Interfaces.Observer;
import Model.NotificationService;

public class TelaRelatorioAluguel extends JFrame {
    
    private List<RelatorioDTO> relatorioAluguel;

    public TelaRelatorioAluguel(List<RelatorioDTO> relatorioAluguel) {
        this.relatorioAluguel = relatorioAluguel;
        setTitle("Relatório de Livros Alugados");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLayout(new BorderLayout());

        String[] columnNames = {"Nome do Livro", "Nome do Cliente", "Data de Devolução"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);

        for (RelatorioDTO relatorio : relatorioAluguel) {
            Object[] row = {
                relatorio.getNomeLivro(),
                relatorio.getNomeCliente(),
                relatorio.getDataDevolucao()
            };
            tableModel.addRow(row);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JButton btnNotificar = new JButton("Notificar Prazo de Devolução");
        add(btnNotificar, BorderLayout.SOUTH);

        btnNotificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    RelatorioDTO selectedRelatorio = relatorioAluguel.get(selectedRow);
                    enviarNotificacao(selectedRelatorio);
                } else {
                    JOptionPane.showMessageDialog(null, "Selecione um livro para notificar.");
                }
            }
        });

        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.setSelectionBackground(Color.LIGHT_GRAY);
        table.setSelectionForeground(Color.BLACK);

        JTableHeader header = table.getTableHeader();
        header.setFont(new Font("Arial", Font.BOLD, 16));
        header.setBackground(Color.GRAY);
        header.setForeground(Color.WHITE);
    }

    private void enviarNotificacao(RelatorioDTO relatorio) {
       
        String notification = "Delvolver o Livro. Prazo quase finalizando " + relatorio.getNomeLivro();
        NotificationService.addNotification(notification);
        
        JOptionPane.showMessageDialog(this, notification);
    }

}


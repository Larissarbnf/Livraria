package View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import DAO.ClienteDAO;
import DTO.ClienteDTO;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class TelaClientes extends JFrame {

    private JTable table;
    private DefaultTableModel tableModel;
    private ClienteDAO clienteDAO;

    public TelaClientes() {
        try {
            clienteDAO = new ClienteDAO();
            initComponents();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao conectar com o banco de dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void initComponents() throws SQLException {
        setTitle("Clientes");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); 
        setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nome", "Email", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(30);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panelButtons.setBackground(new Color(245, 245, 245)); 

        JButton btnAlterarStatus = new JButton("Alterar Status");
        btnAlterarStatus.setBackground(new Color(70, 130, 180)); 
        btnAlterarStatus.setForeground(Color.WHITE);
        btnAlterarStatus.setFont(new Font("Arial", Font.BOLD, 14));
        btnAlterarStatus.setFocusPainted(false);
        btnAlterarStatus.setPreferredSize(new Dimension(150, 40));
        panelButtons.add(btnAlterarStatus);

        add(panelButtons, BorderLayout.SOUTH);

        btnAlterarStatus.addActionListener(e -> {
            try {
                alterarStatusCliente();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao alterar status: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        loadData();
    }

    private void loadData() {
        try {
            List<ClienteDTO> clientes = clienteDAO.listarClientes();

            for (ClienteDTO cliente : clientes) {
                tableModel.addRow(new Object[]{
                    cliente.getIdRegistro(),
                    cliente.getNome(),
                    cliente.getEmail(),
                    cliente.getStatus()  
                });
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar dados dos clientes: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void alterarStatusCliente() throws SQLException {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String currentStatus = (String) tableModel.getValueAt(selectedRow, 3);
            String newStatus = currentStatus.equals("VIP") ? "Regular" : "VIP";
            tableModel.setValueAt(newStatus, selectedRow, 3);
            
            int clienteId = (int) tableModel.getValueAt(selectedRow, 0);
            clienteDAO.alterarStatusCliente(clienteId, newStatus);
            
            JOptionPane.showMessageDialog(this, "Status alterado para " + newStatus);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um cliente.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaClientes().setVisible(true));
    }
}


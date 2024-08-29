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
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setLayout(new BorderLayout());

        // Configuração da tabela
        String[] columnNames = {"ID", "Nome", "Email", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Painel para os botões
        JPanel panelButtons = new JPanel();
        panelButtons.setLayout(new FlowLayout());

        JButton btnAdicionarCliente = new JButton("Adicionar Cliente");
        btnAdicionarCliente.setBackground(new Color(50, 205, 50)); // Lime Green
        btnAdicionarCliente.setForeground(Color.WHITE);
        btnAdicionarCliente.setFont(new Font("Arial", Font.BOLD, 14));
        panelButtons.add(btnAdicionarCliente);

        JButton btnEditarCliente = new JButton("Editar Cliente");
        btnEditarCliente.setBackground(new Color(255, 165, 0)); // Orange
        btnEditarCliente.setForeground(Color.WHITE);
        btnEditarCliente.setFont(new Font("Arial", Font.BOLD, 14));
        panelButtons.add(btnEditarCliente);

        JButton btnDeletarCliente = new JButton("Deletar Cliente");
        btnDeletarCliente.setBackground(new Color(255, 69, 58)); // Red
        btnDeletarCliente.setForeground(Color.WHITE);
        btnDeletarCliente.setFont(new Font("Arial", Font.BOLD, 14));
        panelButtons.add(btnDeletarCliente);

        JButton btnAlterarStatus = new JButton("Alterar Status");
        btnAlterarStatus.setBackground(new Color(70, 130, 180)); // Steel Blue
        btnAlterarStatus.setForeground(Color.WHITE);
        btnAlterarStatus.setFont(new Font("Arial", Font.BOLD, 14));
        panelButtons.add(btnAlterarStatus);

        add(panelButtons, BorderLayout.SOUTH);

        // Ações dos botões
        btnAdicionarCliente.addActionListener(e -> JOptionPane.showMessageDialog(this, "Adicionar Cliente clicado."));
        btnEditarCliente.addActionListener(e -> JOptionPane.showMessageDialog(this, "Editar Cliente clicado."));
        btnDeletarCliente.addActionListener(e -> JOptionPane.showMessageDialog(this, "Deletar Cliente clicado."));
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
}
  


package View;

import DTO.ClienteDTO;
import DTO.LivroDTO;
import DAO.VendaDAO;
import DTO.VendaDTO;
import Model.Cliente;
import Model.ClienteRegular;
import Model.ClienteVip;
import Model.FormaPagamento;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.sql.SQLException;

public class TelaCompra extends JFrame {

    private LivroDTO livroDTO;
    private ClienteDTO clienteDTO;

    private JComboBox<String> cmbFormaPagamento;
    private JLabel lblValor;
    private JLabel lblDataCompra;
    private JLabel lblStatusCliente;
    private JLabel lblValorComDesconto;
    private JButton btnConfirmar;

    public TelaCompra(LivroDTO livroDTO, ClienteDTO clienteDTO) {
        this.livroDTO = livroDTO;
        this.clienteDTO = clienteDTO;
        initComponents();
    }

    private void initComponents() {
        setTitle("Compra de Livro");
        setSize(400, 350); 
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));

        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10));
        panel.setBackground(new Color(245, 245, 245)); 
        add(panel, BorderLayout.CENTER);

        JLabel lblFormaPagamento = new JLabel("Forma de Pagamento:");
        lblFormaPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblFormaPagamento);

        cmbFormaPagamento = new JComboBox<>(new String[] {"Dinheiro", "Cartão de Crédito", "Boleto"});
        cmbFormaPagamento.setFont(new Font("Arial", Font.PLAIN, 14));
        cmbFormaPagamento.setBackground(Color.WHITE);
        panel.add(cmbFormaPagamento);

        JLabel lblValorCompra = new JLabel("Valor Original:");
        lblValorCompra.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblValorCompra);

        lblValor = new JLabel(String.format("R$ %.2f", livroDTO.getPreco()));
        lblValor.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblValor);

        JLabel lblValorDesc = new JLabel("Valor com Desconto:");
        lblValorDesc.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblValorDesc);

        double precoComDesconto = calcularPrecoComDesconto(livroDTO.getPreco());
        lblValorComDesconto = new JLabel(String.format("R$ %.2f", precoComDesconto));
        lblValorComDesconto.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblValorComDesconto);

        JLabel lblData = new JLabel("Data da Compra:");
        lblData.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblData);

        lblDataCompra = new JLabel(new Date(System.currentTimeMillis()).toString());
        lblDataCompra.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblDataCompra);

        JLabel lblStatus = new JLabel("Status do Cliente:");
        lblStatus.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblStatus);

        lblStatusCliente = new JLabel(clienteDTO.getStatus());
        lblStatusCliente.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(lblStatusCliente);

        btnConfirmar = new JButton("Confirmar Compra");
        btnConfirmar.setBackground(new Color(34, 139, 34)); 
        btnConfirmar.setForeground(Color.WHITE);
        btnConfirmar.setFont(new Font("Arial", Font.BOLD, 14));
        add(btnConfirmar, BorderLayout.SOUTH);

        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    confirmPurchase();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao confirmar a compra: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private double calcularPrecoComDesconto(double preco) {
        Cliente cliente = criarCliente();  
        return cliente.calcularDesconto(preco);
    }

    private Cliente criarCliente() {
        if ("VIP".equals(clienteDTO.getStatus())) {
            return new ClienteVip(); 
        } else {
            return new ClienteRegular(); 
        }
    }

    private void confirmPurchase() throws SQLException {
     
        if (clienteDTO == null || livroDTO == null) {
            JOptionPane.showMessageDialog(null, "Cliente ou livro não encontrado.");
            return;
        }

        String formaPagamentoSelecionada = (String) cmbFormaPagamento.getSelectedItem();
        FormaPagamento formaPagamento = FormaPagamento.fromDescricao(formaPagamentoSelecionada);

    
        VendaDTO venda = new VendaDTO();
        venda.setDataVenda(new Date(System.currentTimeMillis()));
        venda.setCliente(clienteDTO); 
        venda.setIdLivro(livroDTO.getId()); 
        double precoComDesconto = calcularPrecoComDesconto(livroDTO.getPreco());
        venda.setTotalVenda(precoComDesconto); 
        venda.setFormaPagamento(formaPagamento);

        
        VendaDAO vendaDAO = new VendaDAO();
        vendaDAO.create(venda);

        JOptionPane.showMessageDialog(null, "Compra confirmada com sucesso!");
        dispose();
    }
}

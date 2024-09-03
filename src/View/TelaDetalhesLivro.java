package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import DTO.LivroDTO;
import DTO.ClienteDTO;

public class TelaDetalhesLivro extends JFrame {

    private LivroDTO livro;
    private ClienteDTO cliente;
    
    private JLabel lblTitulo;
    private JLabel lblAutor;
    private JLabel lblEditora;
    private JLabel lblAnoPublicacao;
    private JLabel lblPreco;
    private JLabel lblDisponibilidade;
    
    private JButton btnAlugar;
    private JButton btnComprar;

    public TelaDetalhesLivro(LivroDTO livro, ClienteDTO cliente) {
        this.livro = livro;
        this.cliente = cliente;
        initComponents();
    }

    private void initComponents() {
        setTitle("Detalhes do Livro");
        setSize(400, 300);
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());

        getContentPane().setBackground(new Color(245, 245, 245));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        lblTitulo = new JLabel("Título: " + livro.getTitulo());
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitulo, gbc);

        lblAutor = new JLabel("Autor: " + livro.getAutor());
        lblAutor.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        add(lblAutor, gbc);

        lblEditora = new JLabel("Editora: " + livro.getEditora());
        lblEditora.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 2;
        add(lblEditora, gbc);

        lblAnoPublicacao = new JLabel("Ano de Publicação: " + livro.getAnoPublicacao());
        lblAnoPublicacao.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 3;
        add(lblAnoPublicacao, gbc);

        lblPreco = new JLabel("Preço: R$" + livro.getPreco());
        lblPreco.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 4;
        add(lblPreco, gbc);

        lblDisponibilidade = new JLabel("Disponibilidade: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));
        lblDisponibilidade.setFont(new Font("Arial", Font.PLAIN, 14));
        lblDisponibilidade.setForeground(livro.isDisponivel() ? new Color(34, 139, 34) : Color.RED); 
        gbc.gridy = 5;
        add(lblDisponibilidade, gbc);

        btnAlugar = new JButton("Alugar");
        btnAlugar.setBackground(new Color(70, 130, 180));
        btnAlugar.setForeground(Color.WHITE);
        btnAlugar.setFont(new Font("Arial", Font.BOLD, 14));

        btnComprar = new JButton("Comprar");
        btnComprar.setBackground(new Color(60, 179, 113));
        btnComprar.setForeground(Color.WHITE);
        btnComprar.setFont(new Font("Arial", Font.BOLD, 14));

        JPanel panelBotoes = new JPanel();
        panelBotoes.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        panelBotoes.setBackground(new Color(245, 245, 245)); 
        panelBotoes.add(btnAlugar);
        panelBotoes.add(btnComprar);

        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(panelBotoes, gbc);

        btnAlugar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (livro.isDisponivel()) {
                    new TelaAluguel(livro, cliente).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "O livro não está disponível para aluguel.");
                }
            }
        });

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (livro.isDisponivel()) {
                    new TelaCompra(livro, cliente).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "O livro não está disponível para compra.");
                }
            }
        });
    }
}


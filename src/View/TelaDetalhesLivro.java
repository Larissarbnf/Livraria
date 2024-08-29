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
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(7, 2));

        lblTitulo = new JLabel("Título: " + livro.getTitulo());
        lblAutor = new JLabel("Autor: " + livro.getAutor());
        lblEditora = new JLabel("Editora: " + livro.getEditora());
        lblAnoPublicacao = new JLabel("Ano de Publicação: " + livro.getAnoPublicacao());
        lblPreco = new JLabel("Preço: R$" + livro.getPreco());
        lblDisponibilidade = new JLabel("Disponibilidade: " + (livro.isDisponivel() ? "Disponível" : "Indisponível"));

        add(lblTitulo);
        add(new JLabel()); // Placeholder
        add(lblAutor);
        add(new JLabel()); // Placeholder
        add(lblEditora);
        add(new JLabel()); // Placeholder
        add(lblAnoPublicacao);
        add(new JLabel()); // Placeholder
        add(lblPreco);
        add(new JLabel()); // Placeholder
        add(lblDisponibilidade);
        add(new JLabel()); // Placeholder

        btnAlugar = new JButton("Alugar");
        btnComprar = new JButton("Comprar");

        add(btnAlugar);
        add(btnComprar);

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


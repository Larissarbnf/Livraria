package View;
import javax.swing.*;
import DAO.LivroDAO;
import Model.Livro;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class TelaCadastroLivro extends JFrame {
    
    private JTextField txtTitulo;
    private JTextField txtAutor;
    private JTextField txtEditora;
    private JTextField txtAnoPublicacao;
    private JTextField txtPreco;
    private JTextField txtIsbn;
    private JButton btnSalvar;
    private LivroDAO livroDAO;
    private JCheckBox chkDisponibilidade;
    
    public TelaCadastroLivro(LivroDAO livroDAO) {
        this.livroDAO = livroDAO;
        initComponents();
    }
    
    private void initComponents() {
        setTitle("Cadastro de Livro");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(9, 2)); 

        add(new JLabel("Título:"));
        txtTitulo = new JTextField();
        add(txtTitulo);

        add(new JLabel("Autor:"));
        txtAutor = new JTextField();
        add(txtAutor);

        add(new JLabel("Editora:"));
        txtEditora = new JTextField();
        add(txtEditora);

        add(new JLabel("Ano de Publicação:"));
        txtAnoPublicacao = new JTextField();
        add(txtAnoPublicacao);

        add(new JLabel("Preço:"));
        txtPreco = new JTextField();
        add(txtPreco);

        add(new JLabel("ISBN:"));
        txtIsbn = new JTextField();
        add(txtIsbn);

        add(new JLabel("Disponível:"));
        chkDisponibilidade = new JCheckBox();
        add(chkDisponibilidade);

        btnSalvar = new JButton("Salvar");
        add(btnSalvar);

        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Livro.LivroBuilder livroBuilder = new Livro.LivroBuilder()
                        .setTitulo(txtTitulo.getText())
                        .setAutor(txtAutor.getText())
                        .setEditora(txtEditora.getText())
                        .setAnoPublicacao(Integer.parseInt(txtAnoPublicacao.getText()))
                        .setPreco(Double.parseDouble(txtPreco.getText()))
                        .setIsbn(txtIsbn.getText())
                        .setDisponibilidade(chkDisponibilidade.isSelected());

                    livroDAO.create(livroBuilder);
                    JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar livro: " + ex.getMessage());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao converter valores numéricos.");
                }
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                LivroDAO livroDAO = new LivroDAO(); 
                TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro(livroDAO);
                telaCadastroLivro.setVisible(true);
            }
        });
    }
}

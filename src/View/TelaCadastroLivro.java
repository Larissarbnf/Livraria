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
        setSize(500, 400);  
        setLocationRelativeTo(null);  
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color(245, 245, 245));  

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);  
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitle = new JLabel("Cadastro de Livro");
        lblTitle.setFont(new Font("Arial", Font.BOLD, 18));
        lblTitle.setHorizontalAlignment(SwingConstants.CENTER);
        lblTitle.setForeground(new Color(34, 139, 34)); 
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(lblTitle, gbc);

        gbc.gridwidth = 1;
        gbc.gridy++;

        addLabelAndField("Título:", txtTitulo = new JTextField(150), gbc); 
        addLabelAndField("Autor:", txtAutor = new JTextField(150), gbc);
        addLabelAndField("Editora:", txtEditora = new JTextField(150), gbc);
        addLabelAndField("Ano de Publicação:", txtAnoPublicacao = new JTextField(150), gbc);
        addLabelAndField("Preço:", txtPreco = new JTextField(150), gbc);
        addLabelAndField("ISBN:", txtIsbn = new JTextField(150), gbc);

        JLabel lblDisponibilidade = new JLabel("Disponível:");
        lblDisponibilidade.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        add(lblDisponibilidade, gbc);

        chkDisponibilidade = new JCheckBox();
        chkDisponibilidade.setBackground(new Color(245, 245, 245)); 
        gbc.gridx = 1;
        add(chkDisponibilidade, gbc);

        gbc.gridy++;
        gbc.gridwidth = 2;

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBackground(new Color(34, 139, 34));  
        btnSalvar.setForeground(Color.WHITE);
        btnSalvar.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 100;
        gbc.gridy++;
        add(btnSalvar, gbc);

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

    private void addLabelAndField(String labelText, JTextField textField, GridBagConstraints gbc) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridx = 0;
        add(label, gbc);

        textField.setFont(new Font("Arial", Font.PLAIN, 14));
        textField.setPreferredSize(new Dimension(500, 150));  
        textField.setBackground(Color.WHITE);
        gbc.gridx = 1;
        add(textField, gbc);

        gbc.gridy++;
    }

    public static void main(String[] args) {
        LivroDAO livroDAO = new LivroDAO();
        TelaCadastroLivro telaCadastroLivro = new TelaCadastroLivro(livroDAO);
        telaCadastroLivro.setVisible(true);
    }
}


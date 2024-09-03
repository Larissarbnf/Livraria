package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import DAO.ClienteDAO;
import DTO.ClienteDTO;

public class EditarPerfilGUI extends JFrame {
    private JTextField txtEmail;
    private JTextField txtNome;
    private JTextField txtEndereco;
    private JTextField txtCep;
    private JTextField txtTelefone;
    private JButton btnSalvar;
    private ClienteDTO clienteDTO;
    private ClienteDAO clienteDAO;

    public EditarPerfilGUI(ClienteDTO clienteDTO) {
        this.clienteDTO = clienteDTO;
        clienteDAO = new ClienteDAO();

        setTitle("Editar Perfil");
        setSize(500, 400);  
        setLocationRelativeTo(null); 
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        Font labelFont = new Font("Arial", Font.BOLD, 14);
        Font textFont = new Font("Arial", Font.PLAIN, 14);

        gbc.gridx = 0;
        gbc.gridy = 0;
        add(createLabel("E-mail:", labelFont), gbc);

        gbc.gridx = 1;
        txtEmail = createTextField(textFont);
        add(txtEmail, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(createLabel("Nome:", labelFont), gbc);

        gbc.gridx = 1;
        txtNome = createTextField(textFont);
        add(txtNome, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(createLabel("Endereço:", labelFont), gbc);

        gbc.gridx = 1;
        txtEndereco = createTextField(textFont);
        add(txtEndereco, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(createLabel("CEP:", labelFont), gbc);

        gbc.gridx = 1;
        txtCep = createTextField(textFont);
        add(txtCep, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(createLabel("Telefone:", labelFont), gbc);

        gbc.gridx = 1;
        txtTelefone = createTextField(textFont);
        add(txtTelefone, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnSalvar = createButton("Salvar");
        btnSalvar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    clienteDTO.setEmail(txtEmail.getText());
                    clienteDTO.setNome(txtNome.getText());
                    clienteDTO.setEndereco(txtEndereco.getText());
                    clienteDTO.setCep(txtCep.getText());
                    clienteDTO.setTelefone(txtTelefone.getText());

                    clienteDAO.update(clienteDTO);

                    JOptionPane.showMessageDialog(null, "Perfil atualizado com sucesso!");
                    dispose();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao atualizar perfil: " + ex.getMessage());
                }
            }
        });
        add(btnSalvar, gbc);
    }

    private JLabel createLabel(String text, Font font) {
        JLabel label = new JLabel(text);
        label.setFont(font);
        return label;
    }

    private JTextField createTextField(Font font) {
        JTextField textField = new JTextField(30);
        textField.setPreferredSize(new Dimension(300, 30));
        textField.setFont(font);
        return textField;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setPreferredSize(new Dimension(150, 40));
        button.setBackground(new Color(70, 130, 180)); // Steel Blue
        button.setForeground(Color.WHITE);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setFocusPainted(false);
        return button;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
          
            ClienteDTO clienteDTO = new ClienteDTO();
            new EditarPerfilGUI(clienteDTO).setVisible(true);
        });
    }
}

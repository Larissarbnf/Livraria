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
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Centralize the window
        setLocationRelativeTo(null);

        // Email
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Email:"), gbc);

        gbc.gridx = 1;
        txtEmail = new JTextField(20);
        txtEmail.setPreferredSize(new Dimension(200, 25));
        add(txtEmail, gbc);

        // Nome
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Nome:"), gbc);

        gbc.gridx = 1;
        txtNome = new JTextField(20);
        txtNome.setPreferredSize(new Dimension(200, 25));
        add(txtNome, gbc);

        // Endereço
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("Endereço:"), gbc);

        gbc.gridx = 1;
        txtEndereco = new JTextField(20);
        txtEndereco.setPreferredSize(new Dimension(200, 25));
        add(txtEndereco, gbc);

        // CEP
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("CEP:"), gbc);

        gbc.gridx = 1;
        txtCep = new JTextField(20);
        txtCep.setPreferredSize(new Dimension(200, 25));
        add(txtCep, gbc);

        // Telefone
        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("Telefone:"), gbc);

        gbc.gridx = 1;
        txtTelefone = new JTextField(20);
        txtTelefone.setPreferredSize(new Dimension(200, 25));
        add(txtTelefone, gbc);

        // Save Button
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        btnSalvar = new JButton("Salvar");
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

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Dummy data for testing
                ClienteDTO clienteDTO = new ClienteDTO(); // Initialize with actual data
                new EditarPerfilGUI(clienteDTO).setVisible(true);
            }
        });
    }
}

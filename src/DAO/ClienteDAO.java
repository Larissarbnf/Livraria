package DAO;
import Model.Cliente;
import Model.Livro;
import ConnectionFactory.ConexaoPostgres;
import DTO.ClienteDTO;
import Interfaces.InterfaceCliente;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements InterfaceCliente{
    @Override

        public boolean create(ClienteDTO obj) throws SQLException {
            String sql = "INSERT INTO cliente (nome, cpf, telefone, endereco, numero_endereco, cep, email, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection con = null;
            PreparedStatement pst = null;
            ResultSet rs = null;
            int id = 0;
            try {
                con = ConexaoPostgres.getInstance().abrirConexao();
                pst = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                pst.setString(1, obj.getNome());
                pst.setString(2, obj.getCpf());
                pst.setString(3, obj.getTelefone());
                pst.setString(4, obj.getEndereco());
                pst.setInt(5, obj.getNumeroEndereco());
                pst.setString(6, obj.getCep());
                pst.setString(7, obj.getEmail());
                pst.setString(8, obj.getSenha());
                
                pst.executeUpdate();
                rs = pst.getGeneratedKeys();
                if (rs.next()) {
                    id = rs.getInt(1);
                }
            
                this.setaEndereco(obj.getEndereco(), obj.getCep(), obj.getNumeroEndereco(), id);
            } catch (SQLException e) {
                throw new SQLException("Erro ao salvar cliente. " + e.getMessage());
            } finally {
                ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
            }
            return true;
        }

        private void setaEndereco(String endereco, String cep, int numero, int idCliente) throws SQLException {
            String sql = "INSERT INTO endereco_cliente (endereco, cep, numero, id_cliente) VALUES (?, ?, ?, ?)";
            Connection con = null;
            PreparedStatement pst = null;
            try {
                con = ConexaoPostgres.getInstance().abrirConexao();
                pst = con.prepareStatement(sql);
                pst.setString(1, endereco);
                pst.setString(2, cep);
                pst.setInt(3, numero);
                pst.setInt(4, idCliente);
                pst.executeUpdate();
            } catch (SQLException e) {
                throw new SQLException("Erro ao salvar endereço do cliente. " + e.getMessage());
            } finally {
                ConexaoPostgres.getInstance().fecharConexao(con, pst);
            }
        }
 
    @Override
    public boolean delete(int id) throws SQLException {
          String sql = "DELETE FROM cliente where id = ?";
          Connection con = null;
            PreparedStatement pst = null;
            try{
                con = ConexaoPostgres.getInstance().abrirConexao();
                pst = con.prepareStatement(sql);
                pst.setInt(1, id);
                pst.execute();
                return true;
            }catch(SQLException e){
                throw new SQLException("erro ao excluir cliente. " + e.getMessage());
            }finally{
                ConexaoPostgres.getInstance().fecharConexao(con, pst);
            }
    }

    @Override
    public boolean update(ClienteDTO obj) throws SQLException {
    	    String sql = "UPDATE cliente SET nome = ?, cpf = ?, telefone = ?, email = ?, senha = ? WHERE id = ?";
    	    Connection con = null;
    	    PreparedStatement pst = null;
    	    try {
    	        con = ConexaoPostgres.getInstance().abrirConexao();
    	        pst = con.prepareStatement(sql);
    	        pst.setString(1, obj.getNome());
    	        pst.setString(2, obj.getCpf());
    	        pst.setString(3, obj.getTelefone());
    	        pst.setString(4, obj.getEmail());
    	        pst.setString(5, obj.getSenha());
    	        pst.setInt(6, obj.getIdRegistro());
    	        pst.executeUpdate();
    	        return true;
    	    } catch (SQLException e) {
    	        throw new SQLException("Erro ao atualizar dados do cliente. " + e.getMessage());
    	    } finally {
    	        ConexaoPostgres.getInstance().fecharConexao(con, pst);
    	    }
    	}


    @Override
    public ClienteDTO read() throws SQLException {
        String sql = "SELECT * FROM cliente";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ArrayList<ClienteDTO> vetorCliente = new ArrayList<ClienteDTO>();
        ClienteDTO dadosClientes = new ClienteDTO();
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                ClienteDTO c = new ClienteDTO();
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                c.setTelefone(rs.getString("telefone"));
                c.setEmail(rs.getString("email"));
                c.setSenha(rs.getString("senha"));
                c.setIdRegistro(rs.getInt("id"));
                vetorCliente.add(c);
            }
            dadosClientes.setClientesRegistrados(vetorCliente);
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar clientes. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return dadosClientes;
    }


    @Override
    public ClienteDTO search(String nome) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE nome LIKE ? LIMIT 1";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setString(1, nome + "%");
            rs = pst.executeQuery();
            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setNome(rs.getString("nome"));
                cliente.setCpf(rs.getString("cpf"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setSenha(rs.getString("senha"));
                cliente.setIdRegistro(rs.getInt("id"));
                return cliente;
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar cliente. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return null;
    }
    public ClienteDTO login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM cliente WHERE email = ? AND senha = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        ClienteDTO clienteDTO = null;

        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, senha);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                clienteDTO = new ClienteDTO();
                clienteDTO.setNome(rs.getString("nome"));
                clienteDTO.setCpf(rs.getString("cpf"));
                clienteDTO.setTelefone(rs.getString("telefone"));
                clienteDTO.setEndereco(rs.getString("endereco"));
                clienteDTO.setNumeroEndereco(rs.getInt("numero_endereco"));
                clienteDTO.setCep(rs.getString("cep"));
                clienteDTO.setEmail(rs.getString("email"));
                clienteDTO.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao fazer login: " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return clienteDTO;
    }

  
    public List<ClienteDTO> listarClientes() throws SQLException {
        List<ClienteDTO> clientes = new ArrayList<>();
        String sql = "SELECT id, nome, email, status FROM cliente";

        try (Connection conn = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdRegistro(rs.getInt("id"));
                cliente.setNome(rs.getString("nome"));
                cliente.setEmail(rs.getString("email"));
                cliente.setStatus(rs.getString("status"));  
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            throw new SQLException("Erro ao listar clientes: " + e.getMessage());
        }

        return clientes;
    }
    public void alterarStatusCliente(int clienteId, String novoStatus) throws SQLException {
        String sql = "UPDATE cliente SET status = ? WHERE id = ?";

        try (Connection conn = ConexaoPostgres.getInstance().abrirConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, novoStatus);
            stmt.setInt(2, clienteId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new SQLException("Erro ao alterar status do cliente: " + e.getMessage());
        }
    }

}

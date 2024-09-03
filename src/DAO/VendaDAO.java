package DAO;


import ConnectionFactory.ConexaoPostgres;
import DTO.VendaDTO;
import DTO.ClienteDTO;
import Model.FormaPagamento;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

	public boolean create(VendaDTO venda) throws SQLException {
	  
	    String sql = "INSERT INTO vendas (data_venda, id_cliente, id_livro, total_venda, forma_pagamento) VALUES (?, ?, ?, ?, ?)";
	    Connection con = null;
	    PreparedStatement pst = null;
	    try {
	        con = ConexaoPostgres.getInstance().abrirConexao();
	        pst = con.prepareStatement(sql);
	        pst.setDate(1, venda.getDataVenda()); 
	        pst.setInt(2, venda.getCliente().getIdRegistro()); 
	        pst.setInt(3, venda.getIdLivro()); 
	        pst.setDouble(4, venda.getTotalVenda());
	        pst.setString(5, venda.getFormaPagamento().getDescricao()); 
	        pst.executeUpdate();
	        return true;
	    } catch (SQLException e) {
	        throw new SQLException("Erro ao inserir venda. " + e.getMessage());
	    } finally {
	        ConexaoPostgres.getInstance().fecharConexao(con, pst);
	    }
	}


    public VendaDTO read(int id) throws SQLException {
        String sql = "SELECT * FROM vendas WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        VendaDTO venda = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if (rs.next()) {
                venda = new VendaDTO();
                venda.setIdVenda(rs.getInt("id"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setTotalVenda(rs.getDouble("total_venda"));
                venda.setFormaPagamento(FormaPagamento.fromDescricao(rs.getString("forma_pagamento"))); 
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar venda. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return venda;
    }

    public List<VendaDTO> readAll() throws SQLException {
        String sql = "SELECT * FROM vendas";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<VendaDTO> vendas = new ArrayList<>();
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            rs = pst.executeQuery();
            while (rs.next()) {
                VendaDTO venda = new VendaDTO();
                venda.setIdVenda(rs.getInt("id"));
                venda.setDataVenda(rs.getDate("data_venda"));
                venda.setTotalVenda(rs.getDouble("total_venda"));
                venda.setFormaPagamento(FormaPagamento.fromDescricao(rs.getString("forma_pagamento"))); 
                vendas.add(venda);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar todas as vendas. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return vendas;
    }

    public boolean update(VendaDTO venda) throws SQLException {
        String sql = "UPDATE vendas SET data_venda = ?, id_cliente = ?, total_venda = ?, forma_pagamento = ? WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setDate(1, venda.getDataVenda());
            pst.setInt(2, venda.getCliente().getIdRegistro()); 
            pst.setDouble(3, venda.getTotalVenda());
            pst.setString(4, venda.getFormaPagamento().getDescricao());
            pst.setInt(5, venda.getIdVenda());
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao atualizar venda. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM vendas WHERE id = ?";
        Connection con = null;
        PreparedStatement pst = null;
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, id);
            pst.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new SQLException("Erro ao deletar venda. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst);
        }
    }
    public static List<VendaDTO> obterLivrosVendidosPorCliente(int clienteId) throws SQLException {
        String sql = "SELECT * FROM vendas WHERE id_cliente = ?";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet rs = null;
        List<VendaDTO> vendas = new ArrayList<>();
        try {
            con = ConexaoPostgres.getInstance().abrirConexao();
            pst = con.prepareStatement(sql);
            pst.setInt(1, clienteId);
            rs = pst.executeQuery();

            while (rs.next()) {
                VendaDTO venda = new VendaDTO();
                venda.setIdVenda(rs.getInt("id"));
                venda.setDataVenda(rs.getDate("data_venda"));

                ClienteDTO cliente = new ClienteDTO();
                cliente.setIdRegistro(rs.getInt("id_cliente"));
                venda.setCliente(cliente);

                venda.setTotalVenda(rs.getDouble("total_venda"));
                venda.setFormaPagamento(FormaPagamento.fromDescricao(rs.getString("forma_pagamento")));

                vendas.add(venda);
            }
        } catch (SQLException e) {
            throw new SQLException("Erro ao buscar vendas por cliente. " + e.getMessage());
        } finally {
            ConexaoPostgres.getInstance().fecharConexao(con, pst, rs);
        }
        return vendas;
    }
}
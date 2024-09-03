package DTO;


import java.sql.Date;
import Model.FormaPagamento;

public class VendaDTO {
    private int idVenda;
    private Date dataVenda;
    private ClienteDTO cliente;
    private int idLivro; 
    private double totalVenda;
    private FormaPagamento formaPagamento;
    private LivroDTO livro;

    public LivroDTO getLivro() {
		return livro;
	}

	public void setLivro(LivroDTO livro) {
		this.livro = livro;
	}

	
    public VendaDTO() {}

    // Getters e Setters
    public int getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(int idVenda) {
        this.idVenda = idVenda;
    }

    public Date getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(Date dataVenda) {
        this.dataVenda = dataVenda;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public int getIdLivro() { 
        return idLivro;
    }

    public void setIdLivro(int idLivro) { 
        this.idLivro = idLivro;
    }

    public double getTotalVenda() {
        return totalVenda;
    }

    public void setTotalVenda(double totalVenda) {
        this.totalVenda = totalVenda;
    }

    public FormaPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(FormaPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}

package DTO;

import java.sql.Date;
import java.util.List;

public class RelatorioDTO {
    private long livroId;
    private int totalVendas;
    private int totalAlugueis;
    private List<LivroDTO> livrosAlugados; 
    private List<VendaDTO> livrosVendidos;
    private String nomeCliente; 
    private String tituloLivro; 
    private String nomeLivro;
    private Date dataVenda; 
    private Date dataDevolucao;



    // Getters e Setters

    public Date getDataVenda() {
		return dataVenda;
	}

	public void setDataVenda(Date dataVenda) {
		this.dataVenda = dataVenda;
	}

	public String getNomeLivro() {
		return nomeLivro;
	}

	public void setNomeLivro(String nomeLivro) {
		this.nomeLivro = nomeLivro;
	}

	public long getLivroId() {
        return livroId;
    }

    public void setLivroId(long livroId) {
        this.livroId = livroId;
    }

    public int getTotalVendas() {
        return totalVendas;
    }

    public void setTotalVendas(int totalVendas) {
        this.totalVendas = totalVendas;
    }

    public int getTotalAlugueis() {
        return totalAlugueis;
    }

    public void setTotalAlugueis(int totalAlugueis) {
        this.totalAlugueis = totalAlugueis;
    }

    public List<LivroDTO> getLivrosAlugados() {
        return livrosAlugados;
    }

    public void setLivrosAlugados(List<LivroDTO> livrosAlugados) {
        this.livrosAlugados = livrosAlugados;
    }

    public List<VendaDTO> getLivrosVendidos() {
        return livrosVendidos;
    }

    public void setLivrosVendidos(List<VendaDTO> livrosVendidos) {
        this.livrosVendidos = livrosVendidos;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getTituloLivro() {
        return tituloLivro;
    }

    public void setTituloLivro(String tituloLivro) {
        this.tituloLivro = tituloLivro;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}

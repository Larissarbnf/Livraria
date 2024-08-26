package DTO;

import java.util.Date;

public class AluguelDTO {
    private int idAluguel;
    private Date dataRetirada;
    private Date dataDevolucao;
    private String estado;
    private ClienteDTO cliente; 
    private LivroDTO livro; 

    // Getters e Setters
    public int getIdAluguel() {
        return idAluguel;
    }

    public void setIdAluguel(int idAluguel) {
        this.idAluguel = idAluguel;
    }

    public Date getDataRetirada() {
        return dataRetirada;
    }

    public void setDataRetirada(Date dataRetirada) {
        this.dataRetirada = dataRetirada;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public ClienteDTO getCliente() {
        return cliente;
    }

    public void setCliente(ClienteDTO cliente) {
        this.cliente = cliente;
    }

    public LivroDTO getLivro() {
        return livro;
    }

    public void setLivro(LivroDTO livro) {
        this.livro = livro;
    }
}

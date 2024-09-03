package Model;

import DAO.ClienteDAO;
import DTO.ClienteDTO;
import DTO.Notificacao;
import Interfaces.Observer;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Cliente implements Observer {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String cep;
    private int numeroEndereco;
    private String email;
    private String senha;
    private ClienteDAO daoC;
    private int id;
    private List<Notificacao> notificacoes;

    public Cliente() {
        daoC = new ClienteDAO();
    }

    // Getters e Setters
    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }

    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getEndereco() { return endereco; }
    public void setEndereco(String endereco) { this.endereco = endereco; }

    public String getCep() { return cep; }
    public void setCep(String cep) { this.cep = cep; }

    public int getNumeroEndereco() { return numeroEndereco; }
    public void setNumeroEndereco(int numeroEndereco) { this.numeroEndereco = numeroEndereco; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }

    public boolean create(Object obj) throws SQLException {
        return daoC.create((ClienteDTO) obj);
    }

    public ClienteDTO search(String nome) throws SQLException {
        return daoC.search(nome);
    }

    public boolean delete(int id) throws SQLException {
        return daoC.delete(id);
    }

    public ClienteDTO read() throws SQLException {
        return daoC.read();
    }

    public boolean update(ClienteDTO obj) throws SQLException {
        return daoC.update(obj);
    }


    public Cliente(int id) {
        this.id = id;
        this.notificacoes = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<Notificacao> getNotificacoes() {
        return notificacoes;
    }

    @Override
    public void update(String mensagem) {
       
        this.notificacoes.add(new Notificacao(mensagem, this.id));
    }

	public abstract double calcularDesconto(double preco);
}

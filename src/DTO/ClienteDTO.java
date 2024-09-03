package DTO;

import java.util.ArrayList;

public class ClienteDTO {
    private String nome;
    private String cpf;
    private String telefone;
    private String endereco;
    private String cep;
    private int numeroEndereco;
    private int idRegistro;
    private String email;
    private String senha;
    private ArrayList<ClienteDTO> clientesRegistrados; 
    private String status; 
    
    public ClienteDTO() {}

    public ClienteDTO(String nome, String cpf, String telefone, String endereco, String cep, int numeroEndereco, int idRegistro) {
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.endereco = endereco;
        this.cep = cep;
        this.numeroEndereco = numeroEndereco;
        this.idRegistro = idRegistro;
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getNumeroEndereco() {
        return numeroEndereco;
    }

    public void setNumeroEndereco(int numeroEndereco) {
        this.numeroEndereco = numeroEndereco;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public ArrayList<ClienteDTO> getClientesRegistrados() {
        return clientesRegistrados;
    }

    public void setClientesRegistrados(ArrayList<ClienteDTO> clientesRegistrados) {
        this.clientesRegistrados = clientesRegistrados;
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        this.email = email; 
    }

    public String getSenha() { 
        return senha; 
    }

    public void setSenha(String senha) { 
        this.senha = senha; 
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean isVip() {
        return "VIP".equalsIgnoreCase(status);
    }
}

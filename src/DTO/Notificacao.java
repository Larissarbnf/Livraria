package DTO;


public class Notificacao {
    private String mensagem;
    private int clienteId;

    public Notificacao(String mensagem, int clienteId) {
        this.mensagem = mensagem;
        this.clienteId = clienteId;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getClienteId() {
        return clienteId;
    }
}

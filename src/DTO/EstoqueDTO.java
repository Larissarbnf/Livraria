package DTO;

public class EstoqueDTO {
    private int idLivro;
    private int quantidade;

    public EstoqueDTO() {}

    public EstoqueDTO(int idLivro, int quantidade) {
        this.idLivro = idLivro;
        this.quantidade = quantidade;
    }

    // Getters e Setters
    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "EstoqueDTO{" +
                "idLivro=" + idLivro +
                ", quantidade=" + quantidade +
                '}';
    }
}

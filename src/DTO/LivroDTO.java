package DTO;

public class LivroDTO {
    private int id;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private String isbn;
    private double preco;
    private Boolean disponibilidade; 
    
    public double getPreco() {
		return preco;
	}

	public void setPreco(double d) {
		this.preco = d;
	}

	public Boolean getDisponilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Boolean disponivel) {
		this.disponibilidade = disponivel;
	}

	public LivroDTO() {}

    public LivroDTO(int id, String titulo, String autor, String editora, int anoPublicacao, String isbn) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPublicacao = anoPublicacao;
        this.isbn = isbn;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public boolean isDisponivel() {
        return disponibilidade;
    }

    @Override
    public String toString() {
        return "LivroDTO{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", editora='" + editora + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", isbn='" + isbn + '\'' +
                '}';
    }
}

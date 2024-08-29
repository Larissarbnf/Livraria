package Model;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPublicacao;
    private Double preco;
    private String isbn;
    private Boolean disponibilidade;
    
    private Livro(LivroBuilder builder) {
        this.idLivro = builder.idLivro;
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.editora = builder.editora;
        this.anoPublicacao = builder.anoPublicacao;
        this.preco = builder.preco;
        this.isbn = builder.isbn;
        this.disponibilidade= builder.disponibilidade;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public String getEditora() {
        return editora;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Boolean getDisponibilidade() {
		return disponibilidade;
	}

	public void setDisponibilidade(Boolean disponibilidade) {
		this.disponibilidade = disponibilidade;
	}

	public static class LivroBuilder {
        public Boolean disponibilidade;
		private int idLivro;
        private String titulo;
        private String autor;
        private String editora;
        private int anoPublicacao;
        private Double preco;
        private String isbn;

        public LivroBuilder setIdLivro(int idLivro) {
            this.idLivro = idLivro;
            return this;
        }

        public LivroBuilder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LivroBuilder setAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public LivroBuilder setEditora(String editora) {
            this.editora = editora;
            return this;
        }

        public LivroBuilder setAnoPublicacao(int anoPublicacao) {
            this.anoPublicacao = anoPublicacao;
            return this;
        }

        public LivroBuilder setPreco(Double preco) {
            this.preco = preco;
            return this;
        }

        public LivroBuilder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }
        public LivroBuilder setDisponibilidade(Boolean disponibilidade) {
            this.disponibilidade = disponibilidade;
            return this;
        }

        public Livro build() {
            return new Livro(this);
        }
        public static LivroBuilder toBuilder(Livro livro) {
            return new LivroBuilder()
                .setIdLivro(livro.getIdLivro())
                .setTitulo(livro.getTitulo())
                .setAutor(livro.getAutor())
                .setEditora(livro.getEditora())
                .setAnoPublicacao(livro.getAnoPublicacao())
                .setPreco(livro.getPreco())
                .setIsbn(livro.getIsbn())
                .setDisponibilidade(livro.getDisponibilidade());
        }
    }
	 
}


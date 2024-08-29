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

    private LivroDTO(LivroDTOBuilder builder) {
        this.id = builder.id;
        this.titulo = builder.titulo;
        this.autor = builder.autor;
        this.editora = builder.editora;
        this.anoPublicacao = builder.anoPublicacao;
        this.isbn = builder.isbn;
        this.preco = builder.preco;
        this.disponibilidade = builder.disponibilidade;
    }

    public int getId() { return id; }
    public String getTitulo1() { return titulo; }
    public String getAutor1() { return autor; }
    public String getEditora1() { return editora; }
    public int getAnoPublicacao1() { return anoPublicacao; }
    public String getIsbn1() { return isbn; }
    public double getPreco() { return preco; }
    public Boolean getDisponibilidade() { return disponibilidade; }

    public static class LivroDTOBuilder {
        private int id;
        private String titulo;
        private String autor;
        private String editora;
        private int anoPublicacao;
        private String isbn;
        private double preco;
        private Boolean disponibilidade;

        public LivroDTOBuilder() {}

        public LivroDTOBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public LivroDTOBuilder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public LivroDTOBuilder setAutor(String autor) {
            this.autor = autor;
            return this;
        }

        public LivroDTOBuilder setEditora(String editora) {
            this.editora = editora;
            return this;
        }

        public LivroDTOBuilder setAnoPublicacao(int anoPublicacao) {
            this.anoPublicacao = anoPublicacao;
            return this;
        }

        public LivroDTOBuilder setIsbn(String isbn) {
            this.isbn = isbn;
            return this;
        }

        public LivroDTOBuilder setPreco(double preco) {
            this.preco = preco;
            return this;
        }

        public LivroDTOBuilder setDisponibilidade(Boolean disponibilidade) {
            this.disponibilidade = disponibilidade;
            return this;
        }

        public LivroDTO build() {
            return new LivroDTO(this);
        }
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

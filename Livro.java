package MODEL;

import java.util.Date;

public class Livro {
    private int id_livro;
    private String titulo_livro;
    private String autor_livro;
    private String editora;
    private int ano_publicacao;
    private int isbn;                
    private String genero;
    private String idioma;
    private int quantidade_estoque;
    private Date data_cadastro;

    public int getId_livro() {
        return id_livro;
    }
    public void setId_livro(int id_livro) {
        this.id_livro = id_livro;
    }

    public String getTitulo_livro() {
        return titulo_livro;
    }
    public void setTitulo_livro(String titulo_livro) {
        this.titulo_livro = titulo_livro;
    }

    public String getAutor_livro() {
        return autor_livro;
    }
    public void setAutor_livro(String autor_livro) {
        this.autor_livro = autor_livro;
    }

    public String getEditora() {
        return editora;
    }
    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }
    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public int getIsbn() {
        return isbn;
    }
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }
    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getIdioma() {
        return idioma;
    }
    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public int getQuantidade_estoque() {
        return quantidade_estoque;
    }
    public void setQuantidade_estoque(int quantidade_estoque) {
        this.quantidade_estoque = quantidade_estoque;
    }

    public Date getData_cadastro() {
        return data_cadastro;
    }
    public void setData_cadastro(Date data_cadastro) {
        this.data_cadastro = data_cadastro;
    }
}
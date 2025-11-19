package CONTROL;

import DAO.ConexaoDAO;
import MODEL.Livro;
import java.sql.*;
import java.util.ArrayList;

public class ControleLivro {

    // ====================== CADASTRAR ======================
    public void cadastrarLivro(Livro livro) {
        String sql = "INSERT INTO livro (titulo_livro, autor_livro, editora, ano_publicacao, isbn, genero, idioma, quantidade_estoque, data_cadastro) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, NOW())";

        try (Connection conn = new ConexaoDAO().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, livro.getTitulo_livro());
            pstm.setString(2, livro.getAutor_livro());
            pstm.setString(3, livro.getEditora());
            pstm.setInt(4, livro.getAno_publicacao());
            pstm.setInt(5, livro.getIsbn());           // ← AGORA É INT!
            pstm.setString(6, livro.getGenero());
            pstm.setString(7, livro.getIdioma());
            pstm.setInt(8, livro.getQuantidade_estoque());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar livro: " + e.getMessage());
        }
    }

    // ====================== LISTAR (agora chama consultarLivro) ======================
    public ArrayList<Livro> consultarLivro() {
        ArrayList<Livro> lista = new ArrayList<>();
        String sql = "SELECT * FROM livro ORDER BY titulo_livro";

        try (Connection conn = new ConexaoDAO().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Livro l = new Livro();
                l.setId_livro(rs.getInt("id_livro"));
                l.setIsbn(rs.getInt("isbn"));               // ← INT!
                l.setTitulo_livro(rs.getString("titulo_livro"));
                l.setAutor_livro(rs.getString("autor_livro"));
                l.setEditora(rs.getString("editora"));
                l.setAno_publicacao(rs.getInt("ano_publicacao"));
                l.setGenero(rs.getString("genero"));
                l.setIdioma(rs.getString("idioma"));
                l.setQuantidade_estoque(rs.getInt("quantidade_estoque"));
                l.setData_cadastro(rs.getDate("data_cadastro"));
                lista.add(l);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao listar livros: " + e.getMessage());
        }
        return lista;
    }

    // ====================== ALTERAR ======================
    public void alterarLivro(Livro livro) {
        String sql = "UPDATE livro SET titulo_livro=?, autor_livro=?, editora=?, ano_publicacao=?, isbn=?, genero=?, idioma=?, quantidade_estoque=? WHERE id_livro=?";

        try (Connection conn = new ConexaoDAO().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setString(1, livro.getTitulo_livro());
            pstm.setString(2, livro.getAutor_livro());
            pstm.setString(3, livro.getEditora());
            pstm.setInt(4, livro.getAno_publicacao());
            pstm.setInt(5, livro.getIsbn());
            pstm.setString(6, livro.getGenero());
            pstm.setString(7, livro.getIdioma());
            pstm.setInt(8, livro.getQuantidade_estoque());
            pstm.setInt(9, livro.getId_livro());
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao alterar livro: " + e.getMessage());
        }
    }

    // ====================== EXCLUIR ======================
    public void excluirLivro(int id_livro) {
        String sql = "DELETE FROM livro WHERE id_livro = ?";

        try (Connection conn = new ConexaoDAO().conectaBD();
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, id_livro);
            pstm.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao excluir livro: " + e.getMessage());
        }
    }
}
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import DAO.ConexaoDAO;
import MODEL.Aluno;
import MODEL.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Kaliandrik
 */
public class ControleAluno {

    // Variaveis Globais
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<Aluno> lista = new ArrayList<>();

    public void cadastrarAluno(Aluno objAluno) {
    String sql = "INSERT INTO aluno(matricula_aluno, nome_aluno, CPF, data_nasc, curso_idcurso) VALUES (?,?,?,?,?)";
    conn = new ConexaoDAO().conectaBD();

    try {
        pstm = conn.prepareStatement(sql);
        pstm.setInt(1, objAluno.getMatricula_aluno());
        pstm.setString(2, objAluno.getNome_aluno());
        pstm.setString(3, objAluno.getCpf_aluno());
        //Converter java.util.Date p/ java.sql.Date
        java.sql.Date dataSQL = new java.sql.Date(objAluno.getData_nasc_aluno().getTime());
        pstm.setDate(4, dataSQL);
        pstm.setInt(5, objAluno.getCurso().getId_curso());
        pstm.execute(); //pedi para executar a variavel
        pstm.close(); //
    } catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "verifique com a TI,o problema na classe ControleAluno no método cadastrarCurso" + erro);
    }
}

    public ArrayList<Aluno> consultarAluno() {
        // a. * -> selecionar todas as colunas da tabela
        // c.nome_curso -> pega o nome do curso da tabela
        // a é um apelido (alias)
        // c é um apelido (alias) p/ o curso
        // ON a.curso_id_curso = c.id_curso
        /* Define a condição de jinção entre  as tabelas:
        campo curso_idcurso na tabela aluno
        Deve ser igual ao campo id_curso na tabela curso
        É assim
         */

        String sql = "SELECT a.*, c.nome_curso FROM aluno a INNER JOIN curso c ON a.curso_idcurso = c.id_curso";
        conn = new ConexaoDAO().conectaBD();
        lista.clear();

        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();

            while (rs.next()) {
                Aluno objAluno = new Aluno();
                objAluno.setId_aluno(rs.getInt("id_aluno"));
                objAluno.setMatricula_aluno(rs.getInt("matricula_aluno"));
                objAluno.setNome_aluno(rs.getString("nome_aluno"));
                objAluno.setCpf_aluno(rs.getString("CPF"));
                objAluno.setData_nasc_aluno(rs.getDate("data_nasc"));

                Curso objCurso = new Curso();
                objCurso.setId_curso(rs.getInt("curso_idcurso"));
                objCurso.setNome_curso(rs.getString("nome_curso"));
                objAluno.setCurso(objCurso);

                lista.add(objAluno);
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha ao Consultar o Aluno: Verifique com a TI" + erro);
        }
        return lista;
    }

    public void alterarAluno(Aluno objAluno) {
        String sql = "UPDATE aluno SET matricula_aluno = ?, nome_aluno = ?, CPF = ?, data_nasc = ?, curso_idcurso = ? WHERE id_aluno = ?";

        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objAluno.getMatricula_aluno());
            pstm.setString(2, objAluno.getNome_aluno());
            pstm.setString(3, objAluno.getCpf_aluno());

            // Converter java.util.Date p/ java.sql.Date
            java.sql.Date dataSQL = new java.sql.Date(objAluno.getData_nasc_aluno().getTime());
            pstm.setDate(4, dataSQL);

            pstm.setInt(5, objAluno.getCurso().getId_curso());
            pstm.setInt(6, objAluno.getId_aluno());

            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na classe ControleAluno ao alterar os dados do aluno" + erro);
        }
    }

    public void excluirAluno(Aluno objAluno) {
        String sql = "DELETE FROM aluno Where id_aluno = ?";
        conn = new ConexaoDAO().conectaBD();

        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objAluno.getId_aluno());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir o aluno" + erro);
        }
    }

    // Método para buscar cursos ativos para o ComboBox
    public ArrayList<Curso> consultarCursosAtivos() {
        String sql = "SELECT * FROM curso WHERE status_curso = 'ATIVO'";
        Connection conn = new ConexaoDAO().conectaBD();
        ArrayList<Curso> listaCursos = new ArrayList<>();

        try {
            PreparedStatement pstm = conn.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                Curso objCurso = new Curso();
                objCurso.setId_curso(rs.getInt("id_curso"));
                objCurso.setNome_curso(rs.getString("nome_curso"));
                objCurso.setStatus_curso(rs.getString("status_curso"));
                listaCursos.add(objCurso);
            }

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha ao carregar cursos: " + erro);
        }

        return listaCursos;
    }
}

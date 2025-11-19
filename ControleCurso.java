/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CONTROL;

import DAO.ConexaoDAO;
import MODEL.Curso;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;

/**
 *
 * @author Kaliandrik
 */
public class ControleCurso {
    Connection conn;
    PreparedStatement pstm;
    ResultSet rs;
    ArrayList<Curso> lista = new ArrayList<>();
    public void cadastrarCurso(Curso objCurso){
        String sql ="INSERT INTO curso(nome_curso,status_curso) values (?,?)";
        conn = new ConexaoDAO().conectaBD();
        //criando a exceção
        try {
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1,objCurso.getNome_curso());
            pstm.setString(2, objCurso.getStatus_curso());
            //pede pra executar a variável
            pstm.execute();
            //depois de executar fecha a conexão
            pstm.close(); 
            
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "verifique com a TI,o problema na classe ControleCurso no método cadastrarCurso" + erro);
        }
        
    }
    
    public ArrayList<Curso> consultarCurso(){
        String sql = "select * from curso";
        conn = new ConexaoDAO().conectaBD();
        try {
            pstm = conn.prepareStatement(sql);
            rs = pstm.executeQuery();
            // podemos ter varias informações no banco
        // crio um laço de repetição
        while(rs.next()) {// next proximo
           Curso objCurso = new Curso(0, sql, sql);
           objCurso.setId_curso(rs.getInt("id_curso"));
           objCurso.setNome_curso(rs.getString("nome_curso"));
           objCurso.setStatus_curso(rs.getString("status_curso"));
           
           lista.add(objCurso);
        }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Falha ao Consultar os Cursos" + erro);
        }
        return lista;
     
    }
    
    public void alterarCurso(Curso objCurso){
        String sql = "UPDATE curso SET nome_curso = ?, status_curso = ? where id_curso = ?";
        conn = new ConexaoDAO().conectaBD();
        
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, objCurso.getNome_curso());
            pstm.setString(2, objCurso.getStatus_curso());
            pstm.setInt(3, objCurso.getId_curso());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro na classe ControleCurso ao alterar os dados do curso" + erro);
        }
    }
    
    public void excluirCurso(Curso objCurso){
        String sql = "DELETE FROM curso Where id_curso = ?";
        conn = new ConexaoDAO().conectaBD();
        
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setInt(1, objCurso.getId_curso());
            pstm.executeUpdate();
            pstm.close();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir curso" + erro);
        }
    }
}

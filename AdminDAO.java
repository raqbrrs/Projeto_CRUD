/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import MODEL.Login;
// conexão sql para java
import java.sql.Connection;
// permite o acesso e a manipulação dos dados retornados por uma consulta a uma base de dados
import java.sql.ResultSet;
// Executar consultar SQL parametrizadas, previnindo ataques SQLInjection
import java.sql.PreparedStatement;

import java.sql.SQLException;

import javax.swing.JOptionPane;

/**
 *
 * @author Raquel
 */
public class AdminDAO {
    // variavel global fora do metodo
    Connection conn;
    /*Acessar os objetos, então trago todos os objetos para cá*/
    public ResultSet autenticarLogin(Login objLogin){
        // Acessando minha classe de conexão com o nosso metodo conectaBD()
    conn = new ConexaoDAO().conectaBD();
    
        try {
            String sql = "Select * from login where usuario_login = ? and senha_login = ?";
            /*preparar para o objeto pstm a conexão e chamar a variavel sql*/
            PreparedStatement pstm = conn.prepareStatement(sql);
            // usando o objeto pstm para comparar o que o usuario digitou e definir o parametro
            pstm.setString(1,objLogin.getUsuario_login());
            pstm.setString(2,objLogin.getSenha_login());
            // executar tudo que foi passado
            ResultSet rs = pstm.executeQuery();
            return rs;
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null,"Erro na classe AdmDAO" + erro.getMessage());
        }
        return null;
    }
}

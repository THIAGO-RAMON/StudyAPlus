package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import java.util.ArrayList;
import model.bean.User;

public class UserDAO{

    Connection con;

    public UserDAO(){
        con = ConnectionFactory.getConnection();
    }

    public boolean saveCadastro(User user){

        String sql = "INSERT into usuario(nome, senha) values (?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            stmt.setString(2, user.getSenha());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);  
        }
    }

    public List<User> listUsers(){
        
        String sql = "SELECT * FROM usuario";

        PreparedStatement stmt=null;
        ResultSet rs=null;

        ArrayList<User> usuarios = new ArrayList<>();
        
        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                User user = new User();
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                usuarios.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Error na hora de listar os usuarios", "ERROR", JOptionPane.WARNING_MESSAGE);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }

}

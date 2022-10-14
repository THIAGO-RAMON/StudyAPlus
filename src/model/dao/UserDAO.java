package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.bean.User;

public class UserDAO {

    Connection con;

    public UserDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean saveCadastro(User user) {

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
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public boolean updateImagemUsuario(User user, String ImagePath){
        
        String sql = "update usuario set imagem = ? where nome = ?";
        PreparedStatement stmt = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, ImagePath);
            stmt.setString(2, user.getNome());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na hora de salvar a imagem do usuario", "ERROR", 0);
            return false;
        }
    }
    
    public String getImagemUsuario(User user){
        
        String pathImagem = "";
        
        String sql="select imagem from usuario where nome = ?";
        PreparedStatement stmt=null;
        ResultSet rs = null;
        
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, user.getNome());
            rs = stmt.executeQuery();
            while(rs.next()){
                pathImagem = rs.getString("Imagem");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na hora de pegar a imagem do usuario", "ERROR", 0);
        }
        
        return pathImagem;
    }

    public List<User> listUsers() {

        String sql = "SELECT * FROM usuario";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<User> usuarios = new ArrayList<>();

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User();
                user.setNome(rs.getString("nome"));
                user.setSenha(rs.getString("senha"));
                usuarios.add(user);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            JOptionPane.showMessageDialog(null, "Error na hora de listar os usuarios", "ERROR", JOptionPane.WARNING_MESSAGE);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }

        return usuarios;
    }

    public boolean updateDesempenho(User user, double porcentagem) {

        String sql = "UPDATE usuario set desempenho = ? where nome = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setDouble(1, porcentagem);
            stmt.setString(2, user.getNome());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

    public double getPorcentagem(User user) {

        double porcentagem = 0;

        String sql = "SELECT desempenho from usuario where nome = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareCall(sql);

            stmt.setString(1, user.getNome());

            rs = stmt.executeQuery();
            while (rs.next()) {
                porcentagem = rs.getDouble("Desempenho");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na hora de pegar a porcentagem\n" + ex, "ERROR", JOptionPane.ERROR_MESSAGE);
            porcentagem = Double.NaN;
        }

        return porcentagem;
    }

    public boolean updateUser(User userOld, User userNew) {

        String sql = "UPDATE usuario set nome = ? , senha =? where nome = ?";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setString(1, userNew.getNome());
            stmt.setString(2, userNew.getSenha());
            stmt.setString(3, userOld.getNome());
            stmt.executeUpdate();

            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR BD\n Erro ao Atualizar", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}

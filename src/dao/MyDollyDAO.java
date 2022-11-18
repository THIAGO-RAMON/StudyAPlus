/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.MyDolly;
import model.Recompensa;
import view.auxiliares.Principal;

/**
 *
 * @author dudal
 */
public class MyDollyDAO {
    
    private Connection con;

    public MyDollyDAO() {
        con = ConnectionFactory.getConnection();
    }

    public Connection getCon() {
        return con;
    }
    
    public void insertRoupa(MyDolly mydolly) throws SQLException{
        
        String sql = "insert into mydolly(id, user_nome, cabeca, torso, perna) values (DEFAULT, ?, ?, ?, ?)";
        
        PreparedStatement stmt = getCon().prepareStatement(sql);
        
        stmt.setString(1, Principal.user.getNome());
        stmt.setString(2, mydolly.getCabeca());
        stmt.setString(3, mydolly.getTorso());
        stmt.setString(4, mydolly.getPerna());
        
        stmt.execute();
        
        ConnectionFactory.closeConnection(stmt);
        
    }
    
    public void updateMyDolly(String parte, Recompensa recompensa) throws SQLException{
        
        String sql = "update mydolly set torso = ? where user_nome = ?";
        
        PreparedStatement stmt = getCon().prepareStatement(sql);
        
        stmt.setString(1, recompensa.getImg());
        stmt.setString(2, Principal.user.getNome());
        
        stmt.executeUpdate();
    }
    public void updateMyDollyCabeca(String parte, Recompensa recompensa) throws SQLException{
        
        String sql = "update mydolly set cabeca = ? where user_nome = ?";
        
        PreparedStatement stmt = getCon().prepareStatement(sql);
        
        stmt.setString(1, recompensa.getImg());
        stmt.setString(2, Principal.user.getNome());
        
        stmt.executeUpdate();
    }
    public void updateMyDollyPerna(String parte, Recompensa recompensa) throws SQLException{
        
        String sql = "update mydolly set perna = ? where user_nome = ?";
        
        PreparedStatement stmt = getCon().prepareStatement(sql);
        
        stmt.setString(1, recompensa.getImg());
        stmt.setString(2, Principal.user.getNome());
        
        stmt.executeUpdate();
    }
    
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.Desafio;

/**
 *
 * @author thiag
 */
public class DesafioDAO {
    
    private Connection con;

    public DesafioDAO() {
        con = ConnectionFactory.getConnection();
    }

    public Connection getCon() {
        return con;
    }
    
    public void insertDesafio(String sql, Desafio desafio) throws SQLException{
        
        PreparedStatement stmt = null;
        
        stmt = con.prepareStatement(sql);
        stmt.setString(1, desafio.getUser().getNome());
        stmt.setString(2, desafio.getTitulo());
        stmt.executeUpdate();
        
        
    }
    
    public void updateDesafioCumprido(String sql, Desafio desafio) throws SQLException{
        
        PreparedStatement stmt = null;
        
        stmt = con.prepareStatement(sql);
        
        stmt.setString(1, desafio.getUser().getNome());
        stmt.setString(2, desafio.getTitulo());
        stmt.executeUpdate();
    }
    
}

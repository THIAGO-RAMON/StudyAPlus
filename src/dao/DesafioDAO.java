/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Desafio;
import model.User;

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

    public void insertDesafio(String sql, Desafio desafio) throws SQLException {

        PreparedStatement stmt = null;

        stmt = con.prepareStatement(sql);
        stmt.setString(1, desafio.getUser().getNome());
        stmt.setString(2, desafio.getTitulo());
        stmt.executeUpdate();

        ConnectionFactory.closeConnection(stmt);
    }

    public void updateDesafioCumprido(String sql, Desafio desafio) throws SQLException {

        PreparedStatement stmt = null;

        stmt = con.prepareStatement(sql);

        stmt.setString(1, desafio.getUser().getNome());
        stmt.setString(2, desafio.getTitulo());
        stmt.executeUpdate();

        ConnectionFactory.closeConnection(stmt);
    }

    public List<Desafio> listarDesafios(String sql, User usuario) throws SQLException {

        ArrayList<Desafio> desafios = new ArrayList<>();

        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        stmt = getCon().prepareStatement(sql);
        stmt.setString(1, usuario.getNome());
        
        rs = stmt.executeQuery();

        while (rs.next()) {
            Desafio desafio = new Desafio();
            desafio.setId(rs.getInt("id"));
            desafio.setTitulo(rs.getString("titulo"));
            desafio.setUser(usuario);

            desafios.add(desafio);
        }
        
        ConnectionFactory.closeConnection(stmt, rs);
        
        return desafios;
    }

    public int vefIsCreatedForUser(User user) throws SQLException {

        int qtd = 0;

        String sql = "select count(*) from desafio where user_nome = ?";

        PreparedStatement stmt = null;

        stmt = getCon().prepareStatement(sql);
        stmt.setString(1, user.getNome());

        ResultSet rs = stmt.executeQuery();
        rs.next();

        qtd = Integer.parseInt(rs.getString(1));

        return qtd;
    }

}

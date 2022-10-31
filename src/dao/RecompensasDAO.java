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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Desafio;
import model.Recompensa;
import model.User;

/**
 *
 * @author thiag
 */
public class RecompensasDAO {

    Connection con;

    public RecompensasDAO() {
        con = ConnectionFactory.getConnection();
    }

    public void insertRecompensa(Recompensa recompensa) {

        String sql = "insert into recompensa(id, user_nome, id_desafio, nome, descricao, imagem, habilitado) values (DEFAULT, ?, ?, ?, ?, ?)";

        PreparedStatement stmt = null;

        try {

            stmt = con.prepareStatement(sql);

            stmt.setString(1, recompensa.getUser().getNome());
            stmt.setInt(2, recompensa.getDesafio().getId());
            stmt.setString(3, recompensa.getNome());
            stmt.setString(4, recompensa.getDescricao());
            stmt.setString(5, recompensa.getImg());
            stmt.setBoolean(6, recompensa.isHabilitado());

            stmt.executeUpdate();

        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public List<Recompensa> listarRecompensas(User usuario) {

        ArrayList<Recompensa> recompensas = new ArrayList<>();

        String sql = "select * from recompensa";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Recompensa recompensa = new Recompensa();
                recompensa.setUser(usuario);
                recompensa.getDesafio().setId(rs.getInt("id_desafio"));
                recompensa.setNome(rs.getString("nome"));
                recompensa.setDescricao(rs.getString("descricao"));
                recompensa.setImg(rs.getString("imagem"));
                recompensa.setHabilitado(rs.getBoolean("habilitado"));

                recompensas.add(recompensa);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return recompensas;
    }

    public boolean setRecompensaHabilitadoTrue(User usuario, Desafio desafio, Recompensa recompensa) {

        String sql = "update recompensa set habilitado = ? where user_nome = ? and id_desafio = ? and nome = ?";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);
            stmt.setBoolean(1, true);
            stmt.setInt(2, desafio.getId());
            stmt.setString(3, usuario.getNome());
            stmt.setString(4, recompensa.getNome());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            System.err.println(ex);
            return false;
        }
    }

}

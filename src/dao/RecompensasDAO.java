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

    public Connection getCon() {
        return con;
    }

    public void insertRecompensa(Recompensa recompensa) throws SQLException {

        String sql = "insert into recompensa(id, id_desafio, user_nome, nome, descricao, imagem, habilitado) values (DEFAULT, ?, ?, ?, ?, ?,?)";

        PreparedStatement stmt = null;

        stmt = getCon().prepareStatement(sql);

        stmt.setInt(1, recompensa.getDesafio().getId());
        stmt.setString(2, recompensa.getUser().getNome());
        stmt.setString(3, recompensa.getNome());
        stmt.setString(4, recompensa.getDescricao());
        stmt.setString(5, recompensa.getImg());
        stmt.setBoolean(6, recompensa.isHabilitado());

        stmt.execute();

        ConnectionFactory.closeConnection(stmt);
    }

    public List<Recompensa> listarRecompensas(User usuario) throws SQLException {

        ArrayList<Recompensa> recompensas = new ArrayList<>();

        String sql = "select * from recompensa where user_nome = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        stmt = getCon().prepareStatement(sql);

        stmt.setString(1, usuario.getNome());

        rs = stmt.executeQuery();

        while (rs.next()) {

            Desafio desafio = new Desafio();

            desafio.setId(rs.getInt("id_desafio"));

            Recompensa recompensa = new Recompensa();
            recompensa.setUser(usuario);
            recompensa.setDesafio(desafio);
            recompensa.setNome(rs.getString("nome"));
            recompensa.setDescricao(rs.getString("descricao"));
            recompensa.setImg(rs.getString("imagem"));
            recompensa.setHabilitado(rs.getBoolean("habilitado"));

            recompensas.add(recompensa);

        }

        ConnectionFactory.closeConnection(stmt, rs);
        return recompensas;
    }

    public void setRecompensaHabilitadoTrue(Recompensa recompensa) throws SQLException {

        String sql = "update recompensa set habilitado = ? where id_desafio = ? and user_nome = ?";

        PreparedStatement stmt = null;

        stmt = getCon().prepareStatement(sql);
        stmt.setBoolean(1, true);
        stmt.setInt(2, recompensa.getDesafio().getId());
        stmt.setString(3, recompensa.getUser().getNome());
        stmt.executeUpdate();

        ConnectionFactory.closeConnection(stmt);
    }

    public int qtdRecompensas(User user) throws SQLException {

        int qtd = 0;

        String sql = "select count(*) from recompensa where user_nome = ?";

        PreparedStatement stmt = null;

        stmt = getCon().prepareStatement(sql);
        stmt.setString(1, user.getNome());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        qtd = Integer.parseInt(rs.getString(1));

        ConnectionFactory.closeConnection(stmt, rs);

        return qtd;
    }
}

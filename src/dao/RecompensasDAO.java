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

    private String fileProject = System.getProperty("user.dir");

    public RecompensasDAO() {
        con = ConnectionFactory.getConnection();
    }

    public Connection getCon() {
        return con;
    }

    public void insertRecompensa(Recompensa recompensa) throws SQLException {

        String sql = "insert into recompensa(id, id_desafio, user_nome, nome, descricao, imagem, habilitado, tipo) values (DEFAULT, ?, ?, ?, ?, ?,?,?)";

        PreparedStatement stmt = null;

        stmt = getCon().prepareStatement(sql);

        stmt.setInt(1, recompensa.getDesafio().getId());
        stmt.setString(2, recompensa.getUser().getNome());
        stmt.setString(3, recompensa.getNome());
        stmt.setString(4, recompensa.getDescricao());
        stmt.setString(5, recompensa.getImg());
        stmt.setBoolean(6, recompensa.isHabilitado());
        stmt.setString(7, recompensa.getTipo());

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
            recompensa.setTipo(rs.getString("tipo"));

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

    public int qtdRecompensasGanha(User user) throws SQLException {
        int qtd = 0;

        String sql = "select count(*) from recompensa where user_nome = ? and habilitado = true";

        PreparedStatement stmt = null;

        stmt = getCon().prepareStatement(sql);
        stmt.setString(1, user.getNome());
        ResultSet rs = stmt.executeQuery();
        rs.next();
        qtd = Integer.parseInt(rs.getString(1));

        ConnectionFactory.closeConnection(stmt, rs);

        return qtd;
    }

    public List<Recompensa> listarRecompensasHabilitadaPorTipo(User user, String tipo) throws SQLException {

        ArrayList<Recompensa> recompensas = new ArrayList<>();

        String sql = "select * from recompensa where user_nome = ? and habilitado = true and tipo = ?";

        PreparedStatement stmt = getCon().prepareStatement(sql);
        stmt.setString(1, user.getNome());
        stmt.setString(2, tipo);

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {

            Desafio desafio = new Desafio();

            desafio.setId(rs.getInt("id_desafio"));

            Recompensa recompensa = new Recompensa();
            recompensa.setUser(user);
            recompensa.setDesafio(desafio);
            recompensa.setNome(rs.getString("nome"));
            recompensa.setDescricao(rs.getString("descricao"));
            recompensa.setImg(rs.getString("imagem"));

            recompensas.add(recompensa);

        }

        if (tipo.equals("cabeca")) {
            Recompensa cabecaDefault = new Recompensa();
            cabecaDefault.setImg(fileProject + "\\etc\\MyDolly\\ImagensDefault\\CABEÇA_DEFAULT.png");
            cabecaDefault.setTipo("cabeca");
            recompensas.add(cabecaDefault);
        } else if (tipo.equals("torso")) {
            Recompensa torsoDefault = new Recompensa();
            torsoDefault.setImg(fileProject + "\\etc\\MyDolly\\ImagensDefault\\TORSO_DEFAULT.png");
            torsoDefault.setTipo("torso");
            recompensas.add(torsoDefault);
        } else if (tipo.equals("perna")) {
            Recompensa pernaDefault = new Recompensa();
            pernaDefault.setImg(fileProject + "\\etc\\MyDolly\\ImagensDefault\\PERNA_DEFAULT.png");
            pernaDefault.setTipo("perna");
            recompensas.add(pernaDefault);
        }

        ConnectionFactory.closeConnection(stmt, rs);

        return recompensas;
    }
}

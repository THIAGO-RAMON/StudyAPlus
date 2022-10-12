/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.dao;

import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.bean.Objetivo;
import model.bean.User;

/**
 *
 * @author Migas
 */
public class ObjetivoDAO {

    Connection con;

    public ObjetivoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean saveObjetivo(Objetivo obj) {

        String sql = "INSERT into objetivos(id,user_nome,descricao, dataInic) values (?,?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getUser().getNome());
            stmt.setString(3, obj.getDescricao());
            stmt.setString(4, obj.getDataInic());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

    public List<Objetivo> listObjetivo(User user) {

        String sql = "Select descricao, dataInic from Objetivos where user_nome= (?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Objetivo> objetivos = new ArrayList<>();

        try {

            stmt = con.prepareCall(sql);
            stmt.setString(1, user.getNome());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Objetivo objetivo = new Objetivo();

                objetivo.setUser(user);
                objetivo.setDescricao(rs.getString("descricao"));
                objetivo.setDataInic(rs.getString("dataInic"));
                objetivos.add(objetivo);

            }

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "ERROR_LIST", JOptionPane.WARNING_MESSAGE);
        }

        return objetivos;
    }

    public boolean updateObjetivo(Objetivo objAntigo, Objetivo objetivoNovo) {
        String sql = "update Objetivos set descricao= (?), dataInic = (?) where descricao = (?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareCall(sql);

            stmt.setString(1, objetivoNovo.getDescricao());
            stmt.setString(2, objetivoNovo.getDataInic());

            stmt.setString(3, objAntigo.getDescricao());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração de objetivos\n" + ex, "Error", 0);
            return false;
        }

    }

    public boolean deleteObjetivo(Objetivo objetivo) {

        String sql = "DELETE FROM Objetivos where User_nome = (?) and descricao = (?)";

        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(sql);

            stmt.setString(1, objetivo.getUser().getNome());
            stmt.setString(2, objetivo.getDescricao());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro deletando a tarefa"+ex, "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }
}

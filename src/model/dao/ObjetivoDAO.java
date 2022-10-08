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

        String sql = "INSERT into objetivos(id,user_nome,descricao) values (?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getDescricao());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }

}

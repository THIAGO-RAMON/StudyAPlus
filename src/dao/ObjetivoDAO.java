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
import javax.swing.JOptionPane;
import model.Objetivo;
import model.User;

/**
 *
 * @author Migas
 */
public class ObjetivoDAO {

    Connection con;

    public ObjetivoDAO() {
        con = ConnectionFactory.getConnection();
    }

    public Connection getCon() {
        return con;
    }

    public boolean saveObjetivo(Objetivo obj) {

        String sql = "INSERT into objetivos(user_nome,descricao, dataInic) values (?,?,?)";
        PreparedStatement stmt = null;
        try {
            stmt = getCon().prepareStatement(sql);
            stmt.setString(1, obj.getUser().getNome());
            stmt.setString(2, obj.getDescricao());
            stmt.setString(3, obj.getDataInic());
            stmt.execute();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
    }

    public List<Objetivo> listObjetivo(User user) {

        String sql = "Select descricao, dataInic from Objetivos where user_nome= (?)";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        ArrayList<Objetivo> objetivos = new ArrayList<>();

        try {

            stmt = getCon().prepareCall(sql);
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
        } finally {
            ConnectionFactory.closeConnection(stmt, rs);
        }

        return objetivos;
    }

    public boolean updateObjetivo(Objetivo objAntigo, Objetivo objetivoNovo) {
        String sql = "update Objetivos set descricao= (?), dataInic = (?) where descricao = (?)";

        PreparedStatement stmt = null;

        try {
            stmt = getCon().prepareCall(sql);

            stmt.setString(1, objetivoNovo.getDescricao());
            stmt.setString(2, objetivoNovo.getDataInic());

            stmt.setString(3, objAntigo.getDescricao());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração de objetivos\n" + ex, "Error", 0);
            return false;
        } finally {

            ConnectionFactory.closeConnection(stmt);

        }

    }

    public boolean deleteObjetivo(Objetivo objetivo) {

        String sql = "DELETE FROM Objetivos where User_nome = (?) and descricao = (?)";

        PreparedStatement stmt = null;

        try {
            stmt = getCon().prepareStatement(sql);

            stmt.setString(1, objetivo.getUser().getNome());
            stmt.setString(2, objetivo.getDescricao());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro deletando a tarefa" + ex, "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }

    }
    
    public int getNumObjetivosByUser(String sql,User user) throws SQLException{
        
        int qtd = 0;
        
        PreparedStatement stmt = getCon().prepareStatement(sql);
        stmt.setString(1, user.getNome());
        
        ResultSet rs = stmt.executeQuery();
        rs.next();
        qtd = Integer.parseInt(rs.getString(1));
        
        ConnectionFactory.closeConnection(stmt, rs);
        
        return qtd;
    }
}

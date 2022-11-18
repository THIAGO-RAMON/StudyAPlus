/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.ConnectionFactory;
import dao.MyDollyDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.MyDolly;
import model.Recompensa;
import view.auxiliares.Principal;

/**
 *
 * @author dudal
 */
public class MyDollyController {

    private String fileProject = System.getProperty("user.dir");

    public void loadMyDolly() throws SQLException {
        
        String[] elementos = new String[3];
        elementos[0] = fileProject+"\\etc\\MyDolly\\ImagensDefault\\CABEÇA_DEFAULT.png";
        elementos[1] = fileProject+"\\etc\\MyDolly\\ImagensDefault\\TORSO_DEFAULT.png";
        elementos[2] = fileProject+"\\etc\\MyDolly\\ImagensDefault\\PERNA_DEFAULT.png";
        
        int cont = 0;

        MyDolly mydolly = new MyDolly();
        mydolly.setUser(Principal.user);
        
            for (int i = 0; i < elementos.length; i++) {
                if(elementos[i] == elementos[0]){
                    mydolly.setCabeca(elementos[0]);
                }else if(elementos[i] == elementos[1]){
                    mydolly.setTorso(elementos[1]);
                }else if(elementos[i] == elementos[2]){
                    mydolly.setPerna(elementos[2]);
                }
            }
            
            new MyDollyDAO().insertRoupa(mydolly);
            
    }

    public MyDolly listarMyDolly() throws SQLException {

        MyDolly mydolly = new MyDolly();
        mydolly.setUser(Principal.user);

        String sql = "select * from mydolly where user_nome = ?";

        PreparedStatement stmt = new MyDollyDAO().getCon().prepareStatement(sql);

        stmt.setString(1, Principal.user.getNome());

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            mydolly.setCabeca(rs.getString("cabeca"));
            mydolly.setTorso(rs.getString("torso"));
            mydolly.setPerna(rs.getString("perna"));
        }

        ConnectionFactory.closeConnection(stmt, rs);

        return mydolly;
    }

    public int verificarSeJaFoiCarregado() {

        int qtd = 0;
        String sql = "select count(*) from mydolly where user_nome = ?";

        try {
            PreparedStatement stmt = new MyDollyDAO().getCon().prepareStatement(sql);
            stmt.setString(1, Principal.user.getNome());

            ResultSet rs = stmt.executeQuery();
            rs.next();

            qtd = Integer.parseInt(rs.getString(1));

            ConnectionFactory.closeConnection(stmt, rs);

        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return qtd;
    }

    public void updateMyDolly(String parte, Recompensa recompensa) {

        try {
            new MyDollyDAO().updateMyDolly(parte, recompensa);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }
    public void updateMyDollyCabeca(String parte, Recompensa recompensa) {

        try {
            new MyDollyDAO().updateMyDollyCabeca(parte, recompensa);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }
    public void updateMyDollyPerna(String parte, Recompensa recompensa) {

        try {
            new MyDollyDAO().updateMyDollyPerna(parte, recompensa);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }
}

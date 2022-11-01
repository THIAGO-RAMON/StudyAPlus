/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.DesafioDAO;
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
import model.Desafio;
import model.Recompensa;
import model.User;
import view.auxiliares.Principal;

/**
 *
 * @author thiag
 */
public class DesafioController {

    private String fileProject = System.getProperty("user.dir");
    private ArrayList<Desafio> desafios = null;
    private ArrayList<Recompensa> recompensas = null;

    public void insertDesafios(Desafio desafio) {
        String sql = "insert into desafio(id,user_nome,titulo) values (DEFAULT, ?, ?)";

        try {
            new DesafioDAO().insertDesafio(sql, desafio);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Desafio> listarDesafios(User usuario) {

        String sql = "select * from desafio";

        ArrayList<Desafio> desafios = new ArrayList<>();

        try {
            PreparedStatement stmt = null;
            ResultSet rs = null;
            
            stmt = new DesafioDAO().getCon().prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Desafio desafio = new Desafio();
                desafio.setId(rs.getInt("id"));
                desafio.setTitulo(rs.getString("titulo"));
                desafio.setUser(usuario);

                desafios.add(desafio);
            }
        } catch (SQLException ex) {
            System.err.println(ex);
        }

        return desafios;
    }

    public void loadDesafios(User usuario) throws FileNotFoundException, IOException {

        String pathTitulos = fileProject + "\\etc\\Desafios\\Titulos.txt";
        File fileTitulos = new File(pathTitulos);

        FileReader leitorTitulo = new FileReader(fileTitulos);

        BufferedReader lerTitulo = new BufferedReader(leitorTitulo);

        String titulo = lerTitulo.readLine();

        while (titulo != null) {

            Desafio desafio = new Desafio();
            desafio.setUser(usuario);
            desafio.setTitulo(titulo);
            insertDesafios(desafio);

            titulo = lerTitulo.readLine();
        }

        lerTitulo.close();
    }

}
    
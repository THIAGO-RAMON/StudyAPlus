/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.ConnectionFactory;
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

/**
 *
 * @author thiag
 */
public class DesafioController {

    private final String fileProject = System.getProperty("user.dir");

    public void insertDesafios(Desafio desafio) {
        String sql = "insert into desafio(id,user_nome,titulo) values (DEFAULT, ?, ?)";

        try {
            new DesafioDAO().insertDesafio(sql, desafio);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public List<Desafio> listarDesafios(User usuario) throws SQLException {
        
        String sql = "select * from desafio where user_nome = ?";
        
        return new DesafioDAO().listarDesafios(sql, usuario);

    }

    public void loadDesafios(User user) throws FileNotFoundException, IOException {

        String pathTitulos = fileProject + "\\etc\\Desafios\\Titulos.txt";
        File fileTitulos = new File(pathTitulos);

        FileReader leitorTitulo = new FileReader(fileTitulos);

        BufferedReader lerTitulo = new BufferedReader(leitorTitulo);

        String titulo = lerTitulo.readLine();

        while (titulo != null) {

            Desafio desafio = new Desafio();
            desafio.setUser(user);
            desafio.setTitulo(titulo);
            insertDesafios(desafio);

            titulo = lerTitulo.readLine();
        }
        
        lerTitulo.close();
    }

    public boolean vefRecompensaCriadaParaOUsuario(User user) throws SQLException{
        
        int qtd = new DesafioDAO().vefIsCreatedForUser(user);
        
        return qtd > 0;
        
    }
}
    
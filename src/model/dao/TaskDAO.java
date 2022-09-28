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
import model.bean.Task;
import model.bean.User;

/**
 *
 * @author Migas
 */
public class TaskDAO {

    Connection con;

    public TaskDAO() {
        con = ConnectionFactory.getConnection();
    }

    public boolean saveTarefa(Task task) {
        String sql = "insert into tarefas(id, User_nome, titulo, descricao, dataInic, dataFim) values (DEFAULT,?, ?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, task.getUser().getNome());
            stmt.setString(2, task.getTitulo());
            stmt.setString(3, task.getDescricao());
            stmt.setDate(4, task.getDataInic());
            stmt.setDate(5, task.getDataFim());
            
            stmt.execute();
            return true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }
    
    public List<Task> listarTaks(){
        String sql = "Select * from Tarefas";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            
            stmt = con.prepareCall(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Task task = new Task();
                User user = new User();
                user.setNome(rs.getString("nome"));
                
                task.setUser(user);
                task.setDescricao(rs.getString("descricao"));
                task.setDataInic(rs.getDate("dataInic"));
                task.setDataFim(rs.getDate("dataFim"));
                tasks.add(task);
                
            }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error na hora de listar as tarefas", "ERROR", JOptionPane.WARNING_MESSAGE);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return tasks;
        
    }
    
     public boolean updateTarefa(Task taskOld, Task taskNew) {
        String sql = "UPDATE tarefas set descricao = ? where descricao = ?";
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareCall(sql);
            stmt.setString(1, taskOld.getDescricao());
            stmt.setString(2, taskNew.getDescricao());
            
            stmt.executeUpdate();
            return true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "ERROR BD\n Erro ao Atualizar a tarefa", JOptionPane.WARNING_MESSAGE);
            return false;
        }finally{
            ConnectionFactory.closeConnection(con,stmt);
        }
    }

}

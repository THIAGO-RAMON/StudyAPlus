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
import model.Task;
import model.User;
import view.auxiliares.Principal;

/**
 *
 * @author Migas
 */
public class TaskDAO {

    Connection con;

    public TaskDAO() {
        con = ConnectionFactory.getConnection();
    }

    public Connection getCon() {
        return con;
    }

    public boolean saveTarefa(Task task) {
        String sql = "insert into tarefas(id, User_nome, titulo, descricao, dataInic, dataFim, importante, concluido) values (DEFAULT,?,?,?,?,?,?,?)";
        PreparedStatement stmt = null;

        try {
            stmt = getCon().prepareCall(sql);
            stmt.setString(1, task.getUser().getNome());
            stmt.setString(2, task.getTitulo());
            stmt.setString(3, task.getDescricao());
            stmt.setDate(4, task.getDataInic());
            stmt.setDate(5, task.getDataFim());
            stmt.setBoolean(6, task.isImportante());
            stmt.setBoolean(7, task.isConcluido());

            stmt.execute();
            return true;

        } catch (SQLException ex) {

            JOptionPane.showMessageDialog(null, ex, "ERROR BD", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }
    }

    public boolean updateTarefa(Task tarefaAntiga, Task tarefaNova) {
        String sql = "update tarefas set titulo = ?, descricao = ?, dataInic = ?, dataFim = ?, importante = ? where User_nome = ? and titulo = ?";

        PreparedStatement stmt = null;

        try {
            stmt = getCon().prepareCall(sql);

            stmt.setString(1, tarefaNova.getTitulo());
            stmt.setString(2, tarefaNova.getDescricao());
            stmt.setDate(3, tarefaNova.getDataInic());
            stmt.setDate(4, tarefaNova.getDataFim());
            stmt.setBoolean(5, tarefaNova.isImportante());

            stmt.setString(6, tarefaAntiga.getUser().getNome());
            stmt.setString(7, tarefaAntiga.getTitulo());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro na alteração da tarefa\n" + ex, "Error", 0);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }

    }

    public boolean updateTarefaConcluido(Task tarefa) {
        String sql = "UPDATE tarefas set concluido = 1 where titulo = ?";
        PreparedStatement stmt = null;

        try {

            stmt = getCon().prepareCall(sql);
            stmt.setString(1, tarefa.getTitulo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "ERROR BD\n Erro ao Concluir a tarefa no banco de dados", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }

    }

    public boolean updateTarefaNaoConcluido(Task tarefa) {

        String sql = "UPDATE tarefas set concluido = 0 where titulo = ?";
        PreparedStatement stmt = null;

        try {

            stmt = getCon().prepareCall(sql);
            stmt.setString(1, tarefa.getTitulo());
            stmt.executeUpdate();
            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "ERROR BD\n Erro ao Concluir a tarefa no banco de dados", JOptionPane.WARNING_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }

    }

    public boolean deleteTarefa(Task tarefa) {

        String sql = "DELETE FROM tarefas where User_nome = ? and titulo = ?";

        PreparedStatement stmt = null;

        try {
            stmt = getCon().prepareStatement(sql);

            stmt.setString(1, tarefa.getUser().getNome());
            stmt.setString(2, tarefa.getTitulo());

            stmt.executeUpdate();

            return true;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro deletando a tarefa", "ERROR", JOptionPane.ERROR_MESSAGE);
            return false;
        } finally {
            ConnectionFactory.closeConnection(stmt);
        }

    }

    public int qtdsTarefa(){

        int qtd = 0;

        String sql = "select count(*) from tarefas where user_nome = ?";

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = getCon().prepareStatement(sql);
            stmt.setString(1, Principal.user.getNome());
            rs = stmt.executeQuery();
            rs.next();
            qtd = Integer.parseInt(rs.getString(1));
        } catch (SQLException ex) {
            System.err.println(ex);
            System.out.println("Erro no loading da qtd de tarefas");
        } finally {
            ConnectionFactory.closeConnection(stmt, rs);
        }

        return qtd;

    }

    public List<Task> listTask(User usuario) {
        String sql = "Select * from Tarefas where user_nome = (?)";
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<Task> tasks = new ArrayList<>();

        try {

            stmt = getCon().prepareStatement(sql);
            stmt.setString(1, usuario.getNome());
            rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task();

                task.setUser(usuario);
                task.setTitulo(rs.getString("titulo"));
                task.setDescricao(rs.getString("descricao"));
                task.setDataInic(rs.getDate("dataInic"));
                task.setDataFim(rs.getDate("dataFim"));
                task.setImportante(rs.getBoolean("importante"));
                task.setConcluido(rs.getBoolean("concluido"));
                tasks.add(task);

            }
        } catch (SQLException ex) {
            System.err.println(ex);
            System.out.println("Erro na listagem de tarefas");
        } finally {
            ConnectionFactory.closeConnection(stmt, rs);
        }

        return tasks;

    }

}

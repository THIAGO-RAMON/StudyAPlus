/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import connection.ConnectionFactory;
import dao.TaskDAO;
import java.io.Externalizable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import model.User;
import view.auxiliares.Principal;

/**
 *
 * @author thiag
 */
public class TaskController {

    public void salvarTarefa(Task Task) {
        new TaskDAO().saveTarefa(Task);
    }

    public List<Task> listarTarefa(User usuario) {
        return new TaskDAO().listTask(usuario);
    }

    public boolean updateTarefa(Task tarefaAntiga, Task tarefaNova) {
        return new TaskDAO().updateTarefa(tarefaAntiga, tarefaNova);
    }

    public boolean updateTarefaConcluido(Task tarefa) {
        return new TaskDAO().updateTarefaConcluido(tarefa);
    }

    public boolean updateTarefaNaoConcluido(Task tarefa) {
        return new TaskDAO().updateTarefaNaoConcluido(tarefa);
    }

    public boolean deletarTarefa(Task tarefa) {
        return new TaskDAO().deleteTarefa(tarefa);
    }

    public int getQtdsTarefa(){
        return new TaskDAO().qtdsTarefa();
    }
}

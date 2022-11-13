package controller;

import dao.RecompensasDAO;
import dao.TaskDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import model.Desafio;
import model.Recompensa;
import model.Task;
import model.User;
import view.auxiliares.Principal;

/**
 *
 * @author Thiago Ramon
 */
public class RecompensaController {

    private User usuario = Principal.user;

    private DesafioController controllerDesafio = new DesafioController();
    private ArrayList<Desafio> desafios = null;

    private String fileProject = System.getProperty("user.dir");

    private TaskDAO daoTarefas = new TaskDAO();

    private ArrayList<Task> tarefas = (ArrayList<Task>) daoTarefas.listarTarefas(usuario);

    private ArrayList<Recompensa> recompensas = new ArrayList<>();

    private RecompensasDAO dao = new RecompensasDAO();

    public RecompensaController() {
        try {
            desafios = (ArrayList<Desafio>) controllerDesafio.listarDesafios(usuario);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    // Metodo para a leitura por meio dos arquivo txts encontrado em etc>Recompensas
    public void loadRecompensas(User user) {

        try {
            if (controllerDesafio.vefRecompensaCriadaParaOUsuario(user) == false) {
                controllerDesafio.loadDesafios(user);
            }

            String pathNome = fileProject + "\\etc\\Recompensas\\nomeRecompensas.txt";
            File fileNome = new File(pathNome);

            String pathDescricao = fileProject + "\\etc\\Recompensas\\descricaoRecompensas.txt";
            File fileDescricao = new File(pathDescricao);

            String pathImagem = fileProject + "\\etc\\Recompensas\\PathImagensRecompensas.txt";
            File fileImage = new File(pathImagem);

            FileReader leitorNome = new FileReader(fileNome);
            FileReader leitorDescricao = new FileReader(fileDescricao);
            FileReader leitorImage = new FileReader(fileImage);

            BufferedReader lerNome = new BufferedReader(leitorNome);
            BufferedReader lerDecricao = new BufferedReader(leitorDescricao);
            BufferedReader lerImage = new BufferedReader(leitorImage);

            String nome = lerNome.readLine();
            String descricao = lerDecricao.readLine();
            String imagem = fileProject + lerImage.readLine();

            desafios = (ArrayList<Desafio>) controllerDesafio.listarDesafios(user);

            for (Desafio desafio : desafios) {

                Recompensa recompensa = new Recompensa();
                recompensa.setUser(user);
                recompensa.setNome(nome);
                recompensa.setDesafio(desafio);
                recompensa.setDescricao(descricao);
                recompensa.setImg(imagem);
                recompensa.setHabilitado(false);

                dao.insertRecompensa(recompensa);

                nome = lerNome.readLine();
                descricao = lerDecricao.readLine();
                imagem = fileProject + lerImage.readLine();
            }

            leitorNome.close();
            leitorDescricao.close();
            leitorImage.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public List<Recompensa> listRecompensa(User usuario) throws SQLException {

        if (dao.listarRecompensas(usuario).isEmpty()) {
            return null;
        } else {
            return dao.listarRecompensas(usuario);
        }
    }

    public void execListRecompensa() {
        try {
            recompensas = (ArrayList<Recompensa>) listRecompensa(usuario);
        } catch (SQLException ex) {
            System.err.println(ex);
        }

    }

    public void atualizarRecompensa(Recompensa recompensa) throws SQLException {
        dao.setRecompensaHabilitadoTrue(recompensa);
    }

    public boolean quantidadeDeRecompensasValida(User user) throws SQLException {

        int qtd = dao.qtdRecompensas(user);

        return qtd == 0;

    }

    public void vefRecompensas() throws SQLException {

        //Verifica o Desafio criar 3 tarefas
        if (daoTarefas.qtdsTarefa() >= 3) {
            Recompensa recompensa = recompensas.get(0);
            if (recompensa.isHabilitado() == false) {
                atualizarRecompensa(recompensa);
                notificarCumprimento(recompensa);
            }
        }

        // Verifica o cumprimento de 1 Tarefa
        for (Task tarefa : tarefas) {
            if (tarefa.isConcluido()) {
                Recompensa recompensa = recompensas.get(1);
                if (recompensa.isHabilitado() == false) {
                    atualizarRecompensa(recompensa);
                    notificarCumprimento(recompensa);
                }
            }
        }

        // Verifica a criação de 10 Tarefa
        if (daoTarefas.qtdsTarefa() >= 10) {
            Recompensa recompensa = recompensas.get(2);
            if (recompensa.isHabilitado() == false) {
                atualizarRecompensa(recompensa);
                notificarCumprimento(recompensa);
            }
        }

        // Verifica o cumprimento de 5 tarefas
        int qtdTarefasCumpridas = 0;

        for (Task tarefa : tarefas) {
            if (tarefa.isConcluido()) {
                qtdTarefasCumpridas++;
            }
        }

        if (qtdTarefasCumpridas >= 5) {
            Recompensa recompensa = recompensas.get(3);
            if (recompensa.isHabilitado() == false) {
                atualizarRecompensa(recompensa);
                notificarCumprimento(recompensa);
            }
        }

        // Tenha um desempenho superior a 70%
        if (daoTarefas.qtdsTarefa() >= 5) {
            if (usuario.getDesempenho_percentual() >= 70) {
                Recompensa recompensa = recompensas.get(4);
                if (recompensa.isHabilitado() == false) {
                    atualizarRecompensa(recompensa);
                    notificarCumprimento(recompensa);
                }
            }
        }
    }

    private void notificarCumprimento(Recompensa recompensa) {
        JOptionPane.showMessageDialog(null,
                recompensa.getUser().getNome() + "\nDesafio concluido\n" + recompensa.getNome() + " desbloqueada",
                "Desafio cumprido",
                JOptionPane.INFORMATION_MESSAGE);

    }

}

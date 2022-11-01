package controller;

import dao.RecompensasDAO;
import dao.TaskDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
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

    // Metodo para a leitura por meio dos arquivo txts encontrado em etc>Recompensas
    public void loadRecompensas(User usuario) {

        try {

            controllerDesafio.loadDesafios(usuario);

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
            String imagem = fileProject+lerImage.readLine();
            
            for (Desafio listarDesafio : controllerDesafio.listarDesafios(usuario)) {
                System.out.println(listarDesafio.getId());
            }

            for (Desafio desafio : controllerDesafio.listarDesafios(usuario)) {
                Recompensa recompensa = new Recompensa();
                recompensa.setUser(usuario);
                recompensa.setNome(nome);
                recompensa.setDesafio(desafio);
                recompensa.setDescricao(descricao);
                recompensa.setImg(imagem);
                recompensa.setHabilitado(false);

                dao.insertRecompensa(desafio, recompensa);

                nome = lerNome.readLine();
                descricao = lerDecricao.readLine();
                imagem = fileProject+lerImage.readLine();
            }

            leitorNome.close();
            leitorDescricao.close();
            leitorImage.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public List<Recompensa> listRecompensa(User usuario) {

        if (dao.listarRecompensas(usuario).isEmpty()) {
            System.err.println("Não há recompensas");
            return null;
        } else {
            return dao.listarRecompensas(usuario);
        }
    }

    public void atualizarRecompensa(Recompensa recompensa, Desafio desafio) {

        if (dao.setRecompensaHabilitadoTrue(usuario, desafio, recompensa)) {
            System.out.println("Desbloqueado com sucesso");
        }
    }

    public void vefRecompensas() throws SQLException, DesafioCumprido {

        desafios = (ArrayList<Desafio>) controllerDesafio.listarDesafios(usuario);
        recompensas = (ArrayList<Recompensa>) listRecompensa(usuario);

        //Verifica o Desafio criar 3 tarefas
        if (daoTarefas.qtdsTarefa() >= 3) {
            Recompensa recompensa = recompensas.get(0);
            if (recompensa.isHabilitado()) {
                Desafio desafio = desafios.get(0);
                atualizarRecompensa(recompensa, desafio);
                throw new DesafioCumprido();
            }
        }

        // Verifica o cumprimento de 1 Tarefa
        for (Task tarefa : tarefas) {
            if (tarefa.isConcluido()) {
                Recompensa recompensa = recompensas.get(1);
                if (recompensa.isHabilitado()) {
                    Desafio desafio = desafios.get(1);
                    atualizarRecompensa(recompensa, desafio);
                    throw new DesafioCumprido();
                }
            }
        }

        // Verifica a criação de 10 Tarefa
        if (daoTarefas.qtdsTarefa() >= 10) {
            Recompensa recompensa = recompensas.get(2);
            if (recompensa.isHabilitado()) {
                Desafio desafio = desafios.get(2);
                atualizarRecompensa(recompensa, desafio);
                throw new DesafioCumprido();
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
            if (recompensa.isHabilitado()) {
                Desafio desafio = desafios.get(3);
                atualizarRecompensa(recompensa, desafio);
                throw new DesafioCumprido();
            }
        }

        // Tenha um desempenho superior a 70%	
        if (usuario.getDesempenho_percentual() >= 70) {
            Recompensa recompensa = recompensas.get(4);
            if (recompensa.isHabilitado()) {
                Desafio desafio = desafios.get(4);
                atualizarRecompensa(recompensa, desafio);
                throw new DesafioCumprido();

            }
        }

    }

    public class DesafioCumprido extends Exception {

        public DesafioCumprido() {
        }

    }
}

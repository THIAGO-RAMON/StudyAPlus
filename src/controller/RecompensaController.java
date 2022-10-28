package controller;

import dao.RecompensasDAO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import model.bean.Recompensa;
import model.bean.User;
import view.auxiliares.Principal;

/**
 *
 * @author Thiago Ramon
 */
public class RecompensaController {

    private User usuario = Principal.user;

    private String fileProject = System.getProperty("user.dir");

    private RecompensasDAO dao = new RecompensasDAO();

    // Metodo para a leitura por meio dos arquivo txts encontrado em etc>Recompensas
    public void loadRecompensas(User usuario) {

        try {
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
            String imagem = lerImage.readLine();

            while (nome != null && descricao != null && imagem != null) {

                Recompensa recompensa = new Recompensa();
                recompensa.setUser(usuario);
                recompensa.setNome(nome);
                recompensa.setDescricao(descricao);
                recompensa.setImg(imagem);
                recompensa.setHabilitado(false);

                dao.insertRecompensa(recompensa);

                nome = lerNome.readLine();
                descricao = lerDecricao.readLine();
                imagem = lerImage.readLine();
            }

            leitorNome.close();
            leitorDescricao.close();
            leitorImage.close();

        } catch (Exception e) {
            System.err.println(e);
        }
    }

    public List<Recompensa> listRecompensa() {

        if (dao.listarRecompensas(usuario).isEmpty()) {
            System.err.println("Não há recompensas");
            return null;
        } else {
            return dao.listarRecompensas(usuario);
        }
    }

    public void atualizarRecompensa(Recompensa recompensa, boolean habilitado) {

        if (habilitado) {
            if (dao.setRecompensaHabilitadoTrue(usuario, recompensa)) {
                System.out.println("Desbloqueado com sucesso");
            } else {
            }
        }

    }
}

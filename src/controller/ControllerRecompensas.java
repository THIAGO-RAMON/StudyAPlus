package controller;

import java.util.ArrayList;
import model.bean.Recompensa;

/**
 *
 * @author Thiago Ramon
 */
public class ControllerRecompensas {

    private static String fileProject = System.getProperty("user.dir");

    public static ArrayList<Recompensa> recompensas = new ArrayList<>();

    
    // Metodo para a leitura por meio dos arquivo txts encontrado em etc>Recompensas
    public static void loadRecompensas() {

        String nomeRecomp1 = "Carro voador";
        String descricaoRecomp1 = "Um carro voador que vai direto pro teu pai";
        String imagemRecomp1 = fileProject + "\\etc\\Recompensas\\images\\CarroVoador.jpeg";

        Recompensa recompensa1 = new Recompensa(nomeRecomp1, descricaoRecomp1, imagemRecomp1);

        recompensas.add(recompensa1);

        String nomeRecomp2 = "Vasco";
        String descricaoRecomp2 = "A imagem do vasco muito pica";
        String imagemRecomp2 = fileProject + "\\etc\\Recompensas\\images\\vasco.png";

        Recompensa recompensa2 = new Recompensa(nomeRecomp2, descricaoRecomp2, imagemRecomp2);

        String nomeRecomp3 = "Paaaaaaaaaaulo";
        String descRecomp3 = "AQUI É O PAAAAAAAAAAAAULO MALUCO";
        String imagemRecomp3 = fileProject + "\\etc\\Recompensas\\\\images\\paulin.jpg";

        Recompensa recompensa3 = new Recompensa(nomeRecomp3, descRecomp3, imagemRecomp3);

        recompensas.add(recompensa2);
        
        recompensas.add(recompensa3);
    }

}
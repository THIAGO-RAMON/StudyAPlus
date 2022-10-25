/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.ArrayList;
import model.bean.Recompensa;

/**
 *
 * @author Migas
 */
public class ControllerRecompensas {
    
    private static String fileProject = System.getProperty("user.dir");
    
    private static ArrayList<Recompensa> recompensas = new ArrayList<>();
    
    public static void configRecompensas(){
        
        String nomeRecomp1 = "Carro voador";
        String descricaoRecomp1 = "Um carro voador que vai direto pro teu pai";
        String imagemRecomp1 = fileProject+"\\etc\\Recompensas\\CarroVoador.jpeg";
        
        Recompensa recompensa1 = new Recompensa(nomeRecomp1, descricaoRecomp1, imagemRecomp1);
        
        recompensas.add(recompensa1);
    }
    
    
}

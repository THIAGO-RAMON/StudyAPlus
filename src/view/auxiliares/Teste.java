/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.auxiliares;

import controller.ControllerRecompensas;
import java.awt.Color;
import javax.swing.JPanel;
import model.bean.Recompensa;

/**
 *
 * @author Migas
 */
public class Teste extends TelaPadraoFullScreen {

    private JPanel painelTeste;
    private BarraLateral barraLateral;
    private Recompensa recompensa;

    public Teste() {
        configPainel();
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painelTeste.add(barraLateral);
        
        ControllerRecompensas.configRecompensas();
        
    }

    private void configPainel() {

        painelTeste = new JPanel(null);
        painelTeste.setBackground(new Color(205, 227, 225));
        painelTeste.setBounds(0, 0, this.getWidth(), this.getHeight());

        add(painelTeste);

    }

    private void getRecompensas(){
        
        
        
    }


    public static void main(String[] args) {
        new Teste().setVisible(true);
    }
}

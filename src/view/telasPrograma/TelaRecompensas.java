/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import java.awt.Color;
import javax.swing.JPanel;
import view.auxiliares.BarraLateral;
import view.auxiliares.TelaPadraoFullScreen;

/**
 *
 * @author Migas
 */
public class TelaRecompensas extends TelaPadraoFullScreen{
    
    private JPanel painelPrincipal;
    private BarraLateral barraLateral;
    
    public TelaRecompensas(){
        
        configPainel();
        
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painelPrincipal.add(barraLateral);
        
        
        
    }
     
    private void configPainel(){
        
        painelPrincipal = new JPanel();
        painelPrincipal.setLayout(null);
        painelPrincipal.setBounds(0, 0, this.getWidth(), this.getHeight());
        painelPrincipal.setBackground(new Color(207, 227, 225));
        add(painelPrincipal);
        
    }
    
    private class painelRecompensas extends JPanel{

        private recompensas[] = {};
        
        public painelRecompensas() {
            setLayout(null);
        }
        
        
        
    }
    
    public static void main(String[] args) {
        new TelaRecompensas().setVisible(true);
    }
}

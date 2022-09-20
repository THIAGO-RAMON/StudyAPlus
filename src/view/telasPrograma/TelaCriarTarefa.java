/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import java.awt.Color;
import java.awt.EventQueue;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import view.Main.BarraLateral;
import view.Main.TelaPadraoFullScreen;

/**
 *
 * @author Migas
 */
public class TelaCriarTarefa extends TelaPadraoFullScreen{
    
    private JPanel painel;
    private JButton btnFoto;
    BarraLateral barraLateral;

    public TelaCriarTarefa() {
        configPanel();
        add(painel);
        
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 100, barraLateral.getWidth(), barraLateral.getHeight());
        painel.add(barraLateral);
        
        btnFoto = new JButton();
        btnFoto.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnFoto.setBackground(new Color(147, 230, 232));
        btnFoto.setBorder(null);
        btnFoto.setBounds(10, 10, 100, 75);
        painel.add(btnFoto);
    }
    
    private void configPanel(){
        
        painel = new JPanel();
        painel.setSize(getSize());
        painel.setLayout(null);
        painel.setBackground(new Color(147, 230, 232));
        
    }
    
    public void runTela(){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCriarTarefa().setVisible(true);
            }
        });
    }
    
    public static void main(String[] args) {
        new TelaCriarTarefa().runTela();
    }
    
}

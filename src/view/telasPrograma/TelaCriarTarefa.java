/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import view.Main.BarraLateral;
import view.Main.TelaPadraoFullScreen;

/**
 *
 * @author Migas
 */
public class TelaCriarTarefa extends TelaPadraoFullScreen {

    private JPanel painel;
    private JButton btnFoto;
    private JLabel titulo;
    private PainelCriar painelCriar;
    private BarraLateral barraLateral;

    public TelaCriarTarefa() {
        configPanel();
        add(painel);

        titulo = new JLabel("CRIE SUA TAREFA");
        titulo.setBounds(540, 20, 400, 30);
        titulo.setFont(new Font("Arial",1,30));
        titulo.setForeground(Color.black.darker());
        painel.add(titulo);
        
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 100, barraLateral.getWidth(), barraLateral.getHeight());
        painel.add(barraLateral);

        btnFoto = new JButton();
        btnFoto.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnFoto.setBackground(new Color(147, 230, 232));
        btnFoto.setBorder(null);
        btnFoto.setBounds(10, 10, 100, 75);
        painel.add(btnFoto);

        painelCriar = new PainelCriar();
        painelCriar.setBounds(320, 100, 930, 600);
        painelCriar.setBackground(painel.getBackground());
        painelCriar.setLayout(null);
        painel.add(painelCriar);
        
        
    }

    private void configPanel() {

        painel = new JPanel();
        painel.setSize(getSize());
        painel.setLayout(null);
        painel.setBackground(new Color(147, 230, 232));

    }

    public void runTela() {
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

    private class PainelCriar extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            g.setColor(new Color(168, 168, 168));
            g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 35, 35);

        }

        @Override
        protected void paintBorder(Graphics g) {
            // TODO Auto-generated method stub
            super.paintBorder(g);
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 35, 35);
        }

    }

}

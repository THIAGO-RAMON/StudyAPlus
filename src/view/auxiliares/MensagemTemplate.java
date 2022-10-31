/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.auxiliares;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 *
 * @author thiag
 */
public class MensagemTemplate extends JPanel{

    private int width;
    private int height;
    private Color background;

    public MensagemTemplate(int width, int height, Color background) {
        this.width = width;
        this.height = height;
        this.background = background;
        
        this.setSize(width, height);
        this.setLayout(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody
        g.setColor(background);
        g.fillRoundRect(0, 0, width, height, 30, 30);
    }
    
}

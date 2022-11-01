/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.auxiliares;

import controller.RecompensaController;
import dao.RecompensasDAO;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 *
 * @author thiag
 */
public class MensagemDesafio extends MensagemTemplate {

    private JLabel lblDesafioCumprido;
    private RecompensaController recompensaController;

    public MensagemDesafio(Color colorBackgroundTela) {
        super(150, 50, new Color(142, 226, 227));

        setBackground(colorBackgroundTela);

        lblDesafioCumprido = new JLabel("Desafio Cumprido");
        lblDesafioCumprido.setFont(new Font("Arial", 1, 18));
        lblDesafioCumprido.setBounds(0, 0, 150, 50);
        lblDesafioCumprido.setHorizontalAlignment(SwingConstants.CENTER);
        lblDesafioCumprido.setHorizontalTextPosition(SwingConstants.CENTER);
        lblDesafioCumprido.setVerticalAlignment(SwingConstants.CENTER);
        lblDesafioCumprido.setVerticalTextPosition(SwingConstants.CENTER);

        add(lblDesafioCumprido);
    }

    public void desafioCumprido() {
        recompensaController = new RecompensaController();
        
        try {
            recompensaController.vefRecompensas();
        } catch (SQLException ex) {
        } catch (RecompensaController.DesafioCumprido ex) {
            
        }
        
        
    }

    public void loadMensagem(int x, int y, JPanel painelBack, JPanel thisPainel) throws InterruptedException{

        painelBack.setBounds(x, y, thisPainel.getWidth(), thisPainel.getHeight());
        painelBack.add(thisPainel);

        Thread thread = new Thread(new AparecerMensagem(x, y, painelBack, thisPainel));
        thread.run();
        thread.join();

    }

    private class AparecerMensagem implements Runnable {

        int x = 0;
        int y = 0;
        int xInicial = x + 150;
        JPanel painelBack, thisPainel;

        public AparecerMensagem(int x, int y, JPanel painelBack, JPanel thisPainel) {
            this.x = x;
            this.y = x;
            this.painelBack = painelBack;
            this.thisPainel = thisPainel;
        }

        @Override
        public void run() {
            try {
                while (xInicial >= x) {
                    thisPainel.setBounds(x, y, thisPainel.getWidth(), thisPainel.getHeight());
                    painelBack.add(thisPainel);
                    xInicial--;
                    Thread.sleep(2);
                }
            } catch (InterruptedException ex) {
                System.err.println(ex);
            }
        }
    }

    private class MensagemApareceu extends Exception {

        public MensagemApareceu() {
        }

    }

}

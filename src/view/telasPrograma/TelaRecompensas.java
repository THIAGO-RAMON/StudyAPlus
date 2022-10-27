/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import controller.ControllerRecompensas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.bean.Recompensa;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.BarraLateral;
import view.auxiliares.CardRecompensas;
import view.auxiliares.TelaPadraoFullScreen;

/**
 *
 * @author Migas
 */
public class TelaRecompensas extends TelaPadraoFullScreen {

    private JPanel painelPrincipal, painelBloqueado, painelDesbloqueado;
    private JLabel lblBloqueado, lblDesbloqueado;
    private JScrollPane painelBloqueados, painelDesbloqueados;
    private BarraLateral barraLateral;
    private Recompensa recompensa;

    public TelaRecompensas() {
        configPainel();
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painelPrincipal.add(barraLateral);

        ControllerRecompensas.loadRecompensas();

        painelBloqueado = new JPanel(new MigLayout());
        painelBloqueado.setPreferredSize(new Dimension(960, 120));
        painelBloqueado.setBackground(new Color(168, 168, 168));

        painelDesbloqueado = new JPanel(new MigLayout());
        painelDesbloqueado.setPreferredSize(new Dimension(960, 120));
        painelDesbloqueado.setBackground(new Color(168, 168, 168));

        getRecompensas();

        lblBloqueado = new JLabel("Bloqueados");
        lblBloqueado.setFont(new Font("Arial", 1, 32));
        lblBloqueado.setBounds(315, 100, 300, 30);
        painelPrincipal.add(lblBloqueado);

        painelBloqueados = new JScrollPane(painelBloqueado);
        painelBloqueados.setBounds(315, 130, 960, 200);
        painelBloqueados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelBloqueados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        painelPrincipal.add(painelBloqueados);

        lblDesbloqueado = new JLabel("Desbloqueados");
        lblDesbloqueado.setFont(new Font("Arial", 1, 32));
        lblDesbloqueado.setBounds(315, 360, 300, 30);
        painelPrincipal.add(lblDesbloqueado);

        painelDesbloqueados = new JScrollPane(painelDesbloqueado);
        painelDesbloqueados.setBounds(315, 390, 960, 200);
        painelDesbloqueados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelDesbloqueados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        painelPrincipal.add(painelDesbloqueados);

        System.out.println(System.getProperty("user.dir"));
    }

    private void configPainel() {

        painelPrincipal = new JPanel(null);
        painelPrincipal.setBackground(new Color(205, 227, 225));
        painelPrincipal.setBounds(0, 0, this.getWidth(), this.getHeight());

        add(painelPrincipal);

    }

    private void getRecompensas() {

        for (Recompensa recompensa1 : ControllerRecompensas.recompensas) {

            if (recompensa1.isHabilitado()) {
                CardRecompensas cardRecompensa = new CardRecompensas(recompensa1, true);
                painelDesbloqueado.add(cardRecompensa, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
            }else{
                CardRecompensas cardRecompensas = new CardRecompensas(recompensa1, false);
                painelBloqueado.add(cardRecompensas, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
            }
            
        }

    }

    public static void main(String[] args) {
        new TelaRecompensas().setVisible(true);
    }
}

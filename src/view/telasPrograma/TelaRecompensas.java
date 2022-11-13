/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import controller.RecompensaController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import model.Recompensa;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.BarraLateral;
import view.auxiliares.CardRecompensas;
import view.auxiliares.Principal;
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
    private RecompensaController recompensaController;
    private ArrayList<Recompensa> recompensas;
    private JButton leave, btnDesafios;
    
    private User user = Principal.user;
    
    private static TelaRecompensas telaRecompensas = new TelaRecompensas();

    public TelaRecompensas() {
        configPainel();
        
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painelPrincipal.add(barraLateral);

        painelBloqueado = new JPanel(new MigLayout());
        painelBloqueado.setPreferredSize(new Dimension(960, 120));
        painelBloqueado.setBackground(new Color(168, 168, 168));

        painelDesbloqueado = new JPanel(new MigLayout());
        painelDesbloqueado.setPreferredSize(new Dimension(960, 120));
        painelDesbloqueado.setBackground(new Color(168, 168, 168));     

        lblBloqueado = new JLabel("Bloqueados");
        lblBloqueado.setFont(new Font("Arial", 1, 32));
        lblBloqueado.setBounds(315, 100, 300, 30);
        painelPrincipal.add(lblBloqueado);
        
        btnDesafios = new JButton(new ImageIcon(getClass().getResource("/images/desafioLogo.png")));
        btnDesafios.setBounds(painelPrincipal.getWidth()-75, 70, 50, 50);
        btnDesafios.setBackground(null);
        btnDesafios.setBorder(null);
        btnDesafios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                TelaDesafios telaDesafios = new TelaDesafios();
                telaDesafios.setVisible(true);
                telaDesafios.setAlwaysOnTop(true);
            }
        });
        
        painelPrincipal.add(btnDesafios);

        painelBloqueados = new JScrollPane(painelBloqueado);
        painelBloqueados.setBounds(315, 130, 960, 240);
        painelBloqueados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelBloqueados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        painelPrincipal.add(painelBloqueados);

        lblDesbloqueado = new JLabel("Desbloqueados");
        lblDesbloqueado.setFont(new Font("Arial", 1, 32));
        lblDesbloqueado.setBounds(315, 400, 300, 30);
        painelPrincipal.add(lblDesbloqueado);

        painelDesbloqueados = new JScrollPane(painelDesbloqueado);
        painelDesbloqueados.setBounds(315, 430, 960, 240);
        painelDesbloqueados.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelDesbloqueados.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        painelPrincipal.add(painelDesbloqueados);
        
        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        leave.setBounds(painelPrincipal.getWidth() - 60, 0, 60, 30);

        painelPrincipal.add(leave);

        getRecompensas();
        
    }

    private void configPainel() {

        painelPrincipal = new JPanel(null);
        painelPrincipal.setBackground(new Color(205, 227, 225));
        painelPrincipal.setBounds(0, 0, this.getWidth(), this.getHeight());

        add(painelPrincipal);

    }
    
    private void getRecompensas() {

        try {
            recompensaController = new RecompensaController();
            
            recompensas = (ArrayList<Recompensa>) recompensaController.listRecompensa(user);
            
            for (Recompensa recompensa1 : recompensas) {
                
                if (recompensa1.isHabilitado()) {
                    CardRecompensas cardRecompensa = new CardRecompensas(recompensa1, true);
                    painelDesbloqueado.add(cardRecompensa, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
                }else{
                    CardRecompensas cardRecompensas = new CardRecompensas(recompensa1, false);
                    painelBloqueado.add(cardRecompensas, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
                }
                
            }
        } catch (SQLException ex) {
            System.err.println("Erro na listagem da tela de recompensas");
            System.err.println(ex);
        }

    }
    
    public static void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                telaRecompensas = new TelaRecompensas();
                if (telaRecompensas.isActive()) {
                    telaRecompensas.dispose();
                }

                TelaRecompensas telaRecompensaNova = new TelaRecompensas();
                telaRecompensas = telaRecompensaNova;
                telaRecompensas.setVisible(true);
            }
        });
    }

    public JScrollPane getPainelDes() {
        return painelDesbloqueados;
    }
    
    public void setPainelDes(JScrollPane painel){
        this.painelDesbloqueados = painel;
    }
    
    public static void main(String[] args) {
        new TelaRecompensas().setVisible(true);
    }

}

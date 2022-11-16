package view.telasPrograma;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */


import controller.DesafioController;
import controller.RecompensaController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.Desafio;
import model.Recompensa;
import model.User;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.Principal;

/**
 *
 * @author thiag
 */
public class TelaDesafios extends JFrame {

    private JPanel painelPrincipal;
    private JScrollPane painelScroll;
    private JLabel titulo;
    private JButton leave;

    public TelaDesafios() {
        try {
            configFrame();
            configPaineis();
            configElementos();
            configDesafios();
        } catch (SQLException ex) {
            System.out.println("Erro no carregamento das recompensas nos desafios");
            System.err.println(ex);
        }
    }

    private void configFrame() {
        setSize(600, 450);
        setUndecorated(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);
    }

    private void configPaineis() {
        painelPrincipal = new JPanel(new MigLayout());
        painelPrincipal.setPreferredSize(new Dimension(600, 1500));
        painelPrincipal.setBackground(new Color(216, 221, 222));

        painelScroll = new JScrollPane(painelPrincipal);
        painelScroll.setBounds(0, 0, 600, 450);
        painelScroll.setBorder(null);
        painelScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        add(painelScroll);
    }

    private void configElementos() {
        titulo = new JLabel("Desafios");
        titulo.setFont(new Font("Arial", 1, 32));
        titulo.setHorizontalTextPosition(SwingConstants.CENTER);
        titulo.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(titulo, "w 600, h 40, y 0");

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        leave.setFocusPainted(false);
        painelPrincipal.add(leave, "w 30, h 30, x (600-30)-30, y 0");
    }
    
    private void configDesafios() throws SQLException{
        
        int cont = 0;
        
        ArrayList<Recompensa> recompensas = (ArrayList<Recompensa>) new RecompensaController().listRecompensa(Principal.user);
        ArrayList<Desafio> desafios = (ArrayList<Desafio>) new DesafioController().listarDesafios(Principal.user);
        
        for (Recompensa recompensa : recompensas) {
            JLabel lblFotoD = new JLabel(resizeImage(recompensa.getImg(), 75, 75));
            lblFotoD.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            painelPrincipal.add(lblFotoD, "w 75, h 75, x 20, gaptop 40");
            
            JLabel lblDesafio = new JLabel();
            lblDesafio.setText(desafios.get(cont).getTitulo());
            lblDesafio.setFont(new Font("Arial", 1, 18));
            lblDesafio.setVerticalAlignment(SwingConstants.CENTER);
            lblDesafio.setVerticalTextPosition(SwingConstants.CENTER);
            painelPrincipal.add(lblDesafio, "x (75+20)+40, gaptop 40, w 350, h 75, wrap");
            
            cont++;
        }
        
    }
    
    private ImageIcon resizeImage(String imagemPath, int width, int height) {
        ImageIcon minhaImagem = new ImageIcon(imagemPath);
        Image img = minhaImagem.getImage();
        Image imgNew = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imgNew);
        return imagem;
    }

    public static void main(String[] args) {
        new TelaDesafios().setVisible(true);
    }

}

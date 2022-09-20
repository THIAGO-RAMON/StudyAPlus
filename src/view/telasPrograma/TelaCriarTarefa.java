/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Date;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import view.Main.BarraLateral;
import view.Main.TelaPadraoFullScreen;

/**
 *
 * @author Migas
 */
public class TelaCriarTarefa extends TelaPadraoFullScreen {

    private JPanel painel;
    private JButton btnFoto;
    private JLabel cabecalho, lblInforme, lblDescricao, lblTitulo, lblDataInicio, lblDataFim;
    private JTextField txtTitulo, txtDataInicio, txtDataFim;
    private JTextArea txtDescricao;
    private PainelCriar painelCriar;
    private BarraLateral barraLateral;
    private Font sansSerif = new Font("SansSerif", 0, 20);

    public TelaCriarTarefa() {
        configPanel();
        add(painel);

        cabecalho = new JLabel("CRIE SUA TAREFA");
        cabecalho.setBounds(540, 20, 400, 30);
        cabecalho.setFont(new Font("Arial",1,30));
        cabecalho.setForeground(Color.black.darker());
        painel.add(cabecalho);
        
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
        
        lblInforme = new JLabel("Informe a sua tarefa nos campos a seguir");
        lblInforme.setFont(new Font("Arial", 1, 30));
        lblInforme.setForeground(Color.BLACK.darker());
        lblInforme.setBounds(175, 10, 600, 40);
        painelCriar.add(lblInforme);
        
        lblTitulo = new JLabel("Escreva o titulo de sua tarefa");
        lblTitulo.setFont(sansSerif);
        lblTitulo.setForeground(Color.BLACK.darker());
        lblTitulo.setBounds(40, 90, 300, 30);
        painelCriar.add(lblTitulo);
        
        txtTitulo = new JTextField();
        txtTitulo.setFont(sansSerif);
        txtTitulo.setBackground(new Color(247, 247, 247));
        txtTitulo.setBounds(40, 130, 600, 30);
        txtTitulo.setBorder(new BordaTextField());
        painelCriar.add(txtTitulo);
        
        lblDescricao = new JLabel("Escreva a descrição sua tarefa");
        lblDescricao.setFont(sansSerif);
        lblDescricao.setForeground(Color.BLACK.darker());
        lblDescricao.setBounds(40, 170, 300, 30);
        painelCriar.add(lblDescricao);
        
        txtDescricao = new JTextArea(3, 10);
        txtDescricao.setFont(sansSerif);
        txtDescricao.setBackground(new Color(247, 247, 247));
        txtDescricao.setBounds(40, 210, 600, 120);
        txtDescricao.setBorder(new BordaTextField());
        painelCriar.add(txtDescricao);
        
        lblDataInicio = new JLabel("Começa em: ");
        lblDataInicio.setFont(sansSerif);
        lblDataInicio.setForeground(Color.black.darker());
        lblDataInicio.setBounds(40, 350, 300, 30);
        painelCriar.add(lblDataInicio);
        
        txtDataInicio = new JTextField();
        txtDataInicio.setFont(sansSerif);
        txtDataInicio.setBackground(new Color(247, 247, 247));
        txtDataInicio.setBounds(165, 350, 150,30);
        txtDataInicio.setBorder(new BordaTextField());
        txtDataInicio.addActionListener(new EventoTxtChangeColor());
        painelCriar.add(txtDataInicio);
        
        lblDataFim = new JLabel("Termina em: ");
        lblDataFim.setFont(sansSerif);
        lblDataFim.setForeground(Color.black.darker());
        lblDataFim.setBounds(350, 350, 300, 30);
        painelCriar.add(lblDataFim);
        
        txtDataFim = new JTextField();
        txtDataFim.setFont(sansSerif);
        txtDataFim.setBackground(new Color(247, 247, 247));
        txtDataFim.setBounds(475, 350, 150,30);
        txtDataFim.setBorder(new BordaTextField());
        txtDataFim.addActionListener(new EventoTxtChangeColor());
        painelCriar.add(txtDataFim);
        
    }
    
    private class EventoTxtChangeColor implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
        }
        
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
    
    private class BordaTextField extends AbstractBorder{
        
        private final BasicStroke contorno = new BasicStroke(2);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height); //To change body of generated methods, choose Tools | Templates.
            
            Graphics2D g2d = (Graphics2D) g;
            
            g2d.setStroke(contorno);
            g2d.drawRoundRect(x, y, width-1, height-1, 10, 10);
            g.clearRect(x, y, 1, 1);
            
        }
        
        
    }
    
}

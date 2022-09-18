package view.perfil;

import static view.Main.Principal.tl;
import static view.Main.Principal.userDao;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;

import view.Main.BarraLateral;
import view.Main.TelaPadraoFullScreen;

public class TelaPerfil extends TelaPadraoFullScreen {

    JButton btnFoto, btnLogo, leave, btnAlterar, btnAlterar2;
    JPanel painel1, painelB;
    JLabel lblNome, lblSenha;
    JTextField txtNome, txtSenha;
    
    public static TelaPerfil telaPerfil = new TelaPerfil();

    public TelaPerfil() {
        // Configurações de tela
        painel1();
        BarraLateral barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 100, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        btnLogo = new JButton();
        btnLogo.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnLogo.setBackground(new Color(218, 217, 215));
        btnLogo.setBorder(null);
        btnLogo.setBounds(10, 10, 100, 75);
        painel1.add(btnLogo);

        // Labels e TextFields
        lblNome = new JLabel("NOME:");
        lblNome.setFont(new Font("Arial", 1, 20));
        lblNome.setBounds(358, 250, 90, 30);
        painel1.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(358, 300, 300, 25);
        txtNome.setBackground(new Color(218, 217, 215));
        txtNome.setText(" " + userDao.getNome());
        txtNome.setBorder(new BordaCantoArrendondado());
        txtNome.setEditable(false);
        painel1.add(txtNome);

        lblSenha = new JLabel("SENHA:");
        lblSenha.setFont(new Font("Arial", 1, 20));
        lblSenha.setBounds(358, 350, 90, 30);
        painel1.add(lblSenha);

        txtSenha = new JTextField();
        txtSenha.setBounds(358, 400, 300, 25);
        txtSenha.setBackground(new Color(218, 217, 215));   
        txtSenha.setText(" " + userDao.getSenha());
        txtSenha.setBorder(new BordaCantoArrendondado());
        txtSenha.setEditable(false);
        painel1.add(txtSenha);

        // Botões
        btnFoto = new JButton("Foto do usuário");
        btnFoto.setBackground(new Color(218, 217, 215));
        btnFoto.setBounds(358, barraLateral.getY(), 150, 150);
        painel1.add(btnFoto);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        leave.setBounds(painel1.getWidth() - 62, 0, 60, 30);
        painel1.add(leave);

        EventoBotao eventoBotao = new EventoBotao();

        btnAlterar = new JButton();
        btnAlterar.addActionListener(eventoBotao);
        btnAlterar.setText("Deseja alterar suas informações? Clique aqui!");
        btnAlterar.setBorder(null);
        btnAlterar.setBackground(new Color(218, 217, 215));
        btnAlterar.setBounds(358, 450, 300, 40);
        painel1.add(btnAlterar);
    }

    void configTela() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(Color.black);
    }

    void painel1() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(218, 217, 215));
        add(painel1);
    }

    private class EventoBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (e.getSource() == btnAlterar){
                new TelaAlterar().setVisible(true);
            }
        }

    }

    private class BordaCantoArrendondado extends AbstractBorder {

        private final BasicStroke CONTORNO = new BasicStroke(2);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            super.paintBorder(c, g, x, y, width, height);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(CONTORNO);

            g2d.setColor(Color.black);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 20, 20);

            g2d.dispose();
        }
    }

    public static void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
             telaPerfil.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaPerfil.runTela();
    }

}
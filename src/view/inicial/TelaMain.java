package view.inicial;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import view.auxiliares.TelaPadraoFullScreen;

public class TelaMain extends TelaPadraoFullScreen {

    JLabel lbl1, lbl2, lblLogo;
    JButton cadastrar, logar, leave;
    PainelPadrao painel1;
    String nome, senha;
    
    public TelaMain(String nome, String senha) {
        configTela();
        TelaPadraoFullScreen.InserirIcone ic = new TelaPadraoFullScreen.InserirIcone();
        ic.InserirIcone(this);
        setNome(nome);
        setSenha(senha);

        painel1 = new PainelPadrao();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(218, 217, 215));
        add(painel1);

        lbl1 = new JLabel("OLÁ, SEJA BEM VINDO(A)!");
        lbl1.setFont(new Font("Arial", 1, 30));
        lbl1.setBounds(280, 50, 400, 30);
        painel1.add(lbl1);

        lbl2 = new JLabel("SUAS OPÇÕES:");
        lbl2.setFont(new Font("Arial", 1, 30));
        lbl2.setBounds(75, 300, 400, 30);
        painel1.add(lbl2);

        lblLogo = new JLabel("Imagem");
        lblLogo.setBounds(600, 300, 150, 30);
        painel1.add(lblLogo);

        cadastrar = new JButton("Cadastrar");

        cadastrar.addActionListener(new EventonBotao());

        cadastrar.setFont(new Font("Arial", 1, 25));
        cadastrar.setBackground(new Color(247, 235, 123));
        cadastrar.setBounds(125, 280, 250, 50);
        painel1.add(cadastrar);

        logar = new JButton("Login");

        logar.addActionListener(new EventonBotao());

        logar.setFont(new Font("Arial", 1, 25));
        logar.setBounds(125, 350, 250, 50);
        logar.setBackground(new Color(247, 235, 123));
        painel1.add(logar);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));

        leave.addActionListener(new EventonBotao());

        leave.setBounds(getWidth() - 60, 0, 60, 30);
        painel1.add(leave);
    }

    public TelaMain() {

        configTela();
        TelaPadraoFullScreen.InserirIcone ic = new TelaPadraoFullScreen.InserirIcone();
        ic.InserirIcone(this);
        painel1 = new PainelPadrao();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(218, 217, 215));
        add(painel1);

        lbl1 = new JLabel("STUDY A+");
        lbl1.setFont(new Font("Arial", 1, 50));
        lbl1.setBounds(170, 75, 400, 50);

        painel1.add(lbl1);

        lbl2 = new JLabel("SUAS OPÇÕES:");
        lbl2.setFont(new Font("Arial", 1, 30));
        lbl2.setBounds(100, 250, 400, 30);

        painel1.add(lbl2);

        lblLogo = new JLabel();
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/images/BackGroundStudyAPlus705x720.png")));
        lblLogo.setBounds(576, 0, 705, 720);

        painel1.add(lblLogo);

        cadastrar = new JButton("Cadastrar");
        cadastrar.addActionListener(new EventonBotao());

        cadastrar.setFont(new Font("Arial", 1, 25));
        cadastrar.setBackground(new Color(237, 236, 235));
        cadastrar.setBounds(125, 360, 350, 75);
        cadastrar.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        painel1.add(cadastrar);

        logar = new JButton("Login");

        logar.addActionListener(new EventonBotao());

        logar.setFont(new Font("Arial", 1, 25));
        logar.setBounds(125, 510, 350, 75);
        logar.setBackground(new Color(237, 236, 235));
        logar.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        painel1.add(logar);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new EventonBotao());

        leave.setBounds(getWidth() - 60, 0, 60, 30);
        painel1.add(leave);

    }

    void configTela() {
        setBackground(new Color(242, 208, 37));
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaMain().setVisible(true);
            }
        });

    }

    public static void main(String[] args) {
        new TelaMain().runTela();
    }

    private class PainelPadrao extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            // Zona do Nome do Software
            g.setColor(new Color(189, 196, 243));
            g.fillRoundRect(0, 0, 575, 200, 20, 20);

            // Divisão de tela
            g.setColor(Color.black);
            g.fillRect(575, 0, 3, 1200);

            // Zona de Cadastro e Login
            g.setColor(new Color(168, 168, 168));
            g.fillRoundRect(75, 300, 450, 350, 30, 30);

        }

    }

    private class EventonBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == cadastrar) {
                new TelaCadastro().setVisible(true);
                dispose();

            } else if (e.getSource() == logar) {
                new TelaLogin().runTela();
                dispose();

            } else if (e.getSource() == leave) {
                System.exit(0);

            }

        }

    }
}

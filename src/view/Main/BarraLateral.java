package view.Main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import view.perfil.TelaCriarObjetivos;
import view.perfil.TelaObjetivos;

import view.perfil.TelaPerfil;
import view.telasPrograma.TelaDesempenho;
import view.telasPrograma.TelaTarefas;

import static view.telasPrograma.TelaTarefas.telaDasTarefas;
import static view.telasPrograma.TelaDesempenho.telaDesempenho;
import static view.perfil.TelaPerfil.telaPerfil;
import static view.telasPrograma.TelaCriarTarefa.telaCriarTarefa;
import view.telasPrograma.TelaCriarTarefa;
import static view.perfil.TelaCriarObjetivos.telaCriarObjetivos;
import view.perfil.TelaCriarObjetivos;
import view.perfil.VisualizarObjetivos;

public class BarraLateral extends JPanel {

    private JLabel iconeTarefas, iconeCriarTarefa, iconePerfil, iconeProgesso;
    private JButton painelTarefas, painelProgresso, painelPerfil, painelCriarTarefa, btnLogo, painelCriarObj, painelObjetivos;
    private Color colorButton = Color.WHITE;

    public BarraLateral() {
        setSize(300, 685);
        setLayout(null);
        setBackground(new Color(207, 227, 225));

        btnLogo = new JButton();
        btnLogo.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnLogo.setBackground(this.getBackground());
        btnLogo.setBorder(null);
        btnLogo.setBounds(0, 0, 100, 75);
        add(btnLogo);

        iconeTarefas = new JLabel();
        iconeTarefas.setBounds(0, 115, 50, 50);
        iconeTarefas.setIcon(new ImageIcon(getClass().getResource("/images/iconeTarefas50x50.png")));
        add(iconeTarefas);

        EventoAbrirTarefas eventoTarefas = new EventoAbrirTarefas();

        painelTarefas = new JButton("Tarefas");
        painelTarefas.setFont(new Font("Arial", 1, 20));
        painelTarefas.setBackground(colorButton);
        painelTarefas.setBorder(new BordaPersonalizada());
        painelTarefas.setBounds(60, 115, 230, 50);
        painelTarefas.addActionListener(eventoTarefas);
        add(painelTarefas);

        EventoAbrirCriarTarefa evtCriar = new EventoAbrirCriarTarefa();

        iconeCriarTarefa = new JLabel();
        iconeCriarTarefa.setBounds(0, 175, 50, 50);
        iconeCriarTarefa.setIcon(new ImageIcon(getClass().getResource("/images/iconeAddTarefa.png")));
        add(iconeCriarTarefa);

        painelCriarTarefa = new JButton("Criar Tarefa");
        painelCriarTarefa.setFont(new Font("Arial", 1, 20));
        painelCriarTarefa.setBackground(colorButton);
        painelCriarTarefa.setBorder(new BordaPersonalizada());
        painelCriarTarefa.setBounds(60, 175, 230, 50);
        painelCriarTarefa.addActionListener(evtCriar);
        add(painelCriarTarefa);

        iconeProgesso = new JLabel();
        iconeProgesso.setBounds(0, 235, 50, 50);
        iconeProgesso.setIcon(new ImageIcon(getClass().getResource("/images/iconeProgesso50x50.png")));
        add(iconeProgesso);

        EventoAbrirProgresso eventoAbrirProgresso = new EventoAbrirProgresso();

        painelProgresso = new JButton("Desempenho");
        painelProgresso.setFont(new Font("Arial", 1, 20));
        painelProgresso.setBackground(colorButton);
        painelProgresso.setBorder(new BordaPersonalizada());
        painelProgresso.setBounds(60, 235, 230, 50);
        painelProgresso.addActionListener(eventoAbrirProgresso);
        add(painelProgresso);

        EventoAbrirObjetivos eventoObjetivo = new EventoAbrirObjetivos();

        painelCriarObj = new JButton("Criar Objetivos");
        painelCriarObj.setFont(new Font("Arial", 1, 20));
        painelCriarObj.setBackground(colorButton);
        painelCriarObj.setBorder(new BordaPersonalizada());
        painelCriarObj.setBounds(60, 295, 230, 50);
        painelCriarObj.addActionListener(eventoObjetivo);
        add(painelCriarObj);

        EventoAbrirPerfil eventoAbrirPerfil = new EventoAbrirPerfil();

        iconePerfil = new JLabel();
        iconePerfil.setBounds(0, 625, 50, 50);
        iconePerfil.setIcon(new ImageIcon(getClass().getResource("/images/iconePerfil50x50.png")));
        add(iconePerfil);

        EventoObjetivos eventoObjetivos = new EventoObjetivos();

        painelObjetivos = new JButton("Objetivos");
        painelObjetivos.setFont(new Font("Arial", 1, 20));
        painelObjetivos.setBackground(colorButton);
        painelObjetivos.setBorder(new BordaPersonalizada());
        painelObjetivos.setBounds(60, 350, 230, 50);
        painelObjetivos.addActionListener(eventoObjetivos);
        add(painelObjetivos);

        painelPerfil = new JButton("Perfil");
        painelPerfil.setFont(new Font("Arial", 1, 20));
        painelPerfil.setBackground(colorButton);
        painelPerfil.setBorder(new BordaPersonalizada());
        painelPerfil.setBounds(60, 625, 230, 50);
        painelPerfil.addActionListener(eventoAbrirPerfil);
        add(painelPerfil);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        // Barra Lateral
        g.setColor(new Color(168, 168, 168));
        g.fillRoundRect(0, 90, this.getWidth(), this.getHeight() - 90, 30, 30);

    }

    @Override
    protected void paintBorder(Graphics g) {
        // TODO Auto-generated method stub
        super.paintBorder(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.black);

        g2d.drawRoundRect(0, 90, this.getWidth() - 1, this.getHeight() - 91, 30, 30);

    }

    private class BordaPersonalizada extends AbstractBorder {

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            super.paintBorder(c, g, x, y, width, height);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(x, y, width, height, 10, 10);

        }

    }

    private class EventoObjetivos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (telaDasTarefas.isVisible()) {
                telaDasTarefas.dispose();
            }
            if (telaCriarObjetivos.isVisible()) {
                telaCriarObjetivos.dispose();
            }
            if(telaPerfil.isVisible()){
                telaPerfil.dispose();
            }
            if(telaCriarTarefa.isVisible()){
                telaCriarTarefa.isVisible();
            }
            new VisualizarObjetivos().runTela();
        }

    }

    private class EventoAbrirCriarTarefa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (telaPerfil.isVisible()) {
                telaPerfil.dispose();
            }
            if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
            if (telaDasTarefas.isVisible()) {
                telaDasTarefas.dispose();
            }
            if (telaCriarObjetivos.isVisible()) {
                telaCriarObjetivos.dispose();
            }
            new TelaCriarTarefa().runTela();
        }

    }

    private class EventoAbrirObjetivos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (telaPerfil.isVisible()) {
                telaPerfil.dispose();
            }
            if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
            if (telaDasTarefas.isVisible()) {
                telaDasTarefas.dispose();

            } else if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
            new TelaCriarObjetivos().runTela();

        }

    }

    private class EventoAbrirTarefas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (telaPerfil.isVisible()) {
                telaPerfil.dispose();
            }
            if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
            if (telaCriarTarefa.isVisible()) {
                telaCriarTarefa.dispose();
            }
            if (telaCriarObjetivos.isVisible()) {
                telaCriarObjetivos.dispose();
            }
            TelaTarefas.runTela();
        }
    }

    private class EventoAbrirProgresso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (telaDasTarefas.isVisible()) {
                telaDasTarefas.dispose();
            }
            if (telaCriarTarefa.isVisible()) {
                telaCriarTarefa.dispose();
            }
            if (telaPerfil.isVisible()) {
                telaPerfil.dispose();
            }
            if (telaCriarObjetivos.isVisible()) {
                telaCriarObjetivos.dispose();
            }
            new TelaDesempenho().runTela();

        }
    }

    private class EventoAbrirPerfil implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (telaDasTarefas.isVisible()) {
                telaDasTarefas.dispose();
            }
            if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
            if (telaCriarTarefa.isVisible()) {
                telaCriarTarefa.dispose();
            }
            if (telaCriarObjetivos.isVisible()) {
                telaCriarObjetivos.dispose();
            }
            new TelaPerfil().runTela();
        }
    }
}

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

import view.perfil.TelaPerfil;
import view.telasPrograma.TelaDesempenho;
import view.telasPrograma.TelaTarefas;

import static view.telasPrograma.TelaTarefas.telaDasTarefas;
import static view.telasPrograma.TelaDesempenho.telaDesempenho;
import static view.perfil.TelaPerfil.telaPerfil;
import view.telasPrograma.TelaCriarTarefa;

public class BarraLateral extends JPanel {

    private JLabel iconeTarefas, iconePerfil, iconeProgesso;
    private JButton painelTarefas, painelProgresso, painelPerfil, painelCriarTarefa;
    private Color colorButton = Color.WHITE;
    
    public BarraLateral() {
        setSize(300, 600);
        setLayout(null);
        setBackground(new Color(147, 230, 232));

        iconeTarefas = new JLabel();
        iconeTarefas.setBounds(0, 30, 50, 50);

        iconeTarefas.setIcon(new ImageIcon(getClass().getResource("/images/iconeTarefas50x50.png")));
        add(iconeTarefas);

        EventoAbrirTarefas eventoTarefas = new EventoAbrirTarefas();

        painelTarefas = new JButton("Tarefas");
        painelTarefas.setFont(new Font("Arial", 1, 20));
        painelTarefas.setBackground(colorButton);
        painelTarefas.setBorder(new BordaPersonalizada());
        painelTarefas.setBounds(60, 30, 230, 50);
        painelTarefas.addActionListener(eventoTarefas);
        add(painelTarefas);

        EventoAbrirCriarTarefa evtCriar = new EventoAbrirCriarTarefa();

        painelCriarTarefa = new JButton("Criar Tarefa");
        painelCriarTarefa.setFont(new Font("Arial", 1, 20));
        painelCriarTarefa.setBackground(colorButton);
        painelCriarTarefa.setBorder(new BordaPersonalizada());
        painelCriarTarefa.setBounds(60, 90, 230, 50);
        painelCriarTarefa.addActionListener(evtCriar);
        add(painelCriarTarefa);

        iconeProgesso = new JLabel();
        iconeProgesso.setBounds(0, 150, 50, 50);
        iconeProgesso.setIcon(new ImageIcon(getClass().getResource("/images/iconeProgesso50x50.png")));
        add(iconeProgesso);

        EventoAbrirProgresso eventoAbrirProgresso = new EventoAbrirProgresso();

        painelProgresso = new JButton("Desempenho");
        painelProgresso.setFont(new Font("Arial", 1, 20));
        painelProgresso.setBackground(colorButton);
        painelProgresso.setBorder(new BordaPersonalizada());
        painelProgresso.setBounds(60, 150, 230, 50);
        painelProgresso.addActionListener(eventoAbrirProgresso);
        add(painelProgresso);

        EventoAbrirPerfil eventoAbrirPerfil = new EventoAbrirPerfil();

        iconePerfil = new JLabel();
        iconePerfil.setBounds(0, 540, 50, 50);
        iconePerfil.setIcon(new ImageIcon(getClass().getResource("/images/iconePerfil50x50.png")));
        add(iconePerfil);
        painelPerfil = new JButton("Perfil");
        painelPerfil.setFont(new Font("Arial", 1, 20));
        painelPerfil.setBackground(colorButton);
        painelPerfil.setBorder(new BordaPersonalizada());
        painelPerfil.setBounds(60, 540, 230, 50);
        painelPerfil.addActionListener(eventoAbrirPerfil);
        add(painelPerfil);

    }

    @Override
    protected void paintComponent(Graphics g) {
        // TODO Auto-generated method stub
        super.paintComponent(g);

        // Barra Lateral
        g.setColor(new Color(168, 168, 168));
        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 30, 30);

    }

    @Override
    protected void paintBorder(Graphics g) {
        // TODO Auto-generated method stub
        super.paintBorder(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(Color.black);

        g2d.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 30, 30);

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

    private class EventoAbrirCriarTarefa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaCriarTarefa().runTela();
        }

    }

    private class EventoAbrirTarefas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            TelaTarefas.runTela();
            if (telaPerfil.isVisible()) {
                telaPerfil.dispose();
            }
            if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
        }
    }

    private class EventoAbrirProgresso implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            new TelaDesempenho().runTela();

        }
    }

    private class EventoAbrirPerfil implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            TelaPerfil.runTela();
            if (telaDasTarefas.isVisible()) {
                telaDasTarefas.dispose();
            }
            if (telaDesempenho.isVisible()) {
                telaDesempenho.dispose();
            }
        }
    }
}

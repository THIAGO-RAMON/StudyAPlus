package view.perfil;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import view.Main.TelaPadraoFullScreen;
import view.telasPrograma.TelaTarefas;

public class TelaObjetivos extends TelaPadraoFullScreen {

    private JPanel painel1, painel2;
    private JLabel lblMensagem, lblMensagem2, lblMensagem3, lblMensagem4, lbl;
    private JButton btnC, leave;
    public static TelaObjetivos telaObjetivos = new TelaObjetivos();
    
    TelaObjetivos() {
        painel();

        lblMensagem = new JLabel("Olá! Bem vindo(a) ao StudyAPlus!");
        lblMensagem2 = new JLabel("Todo o usuário tem objetivos para serem alcançados,");
        lblMensagem3 = new JLabel("esses objetivos são importantes para o total");
        lblMensagem4 = new JLabel("desempenho e disciplina, por isso é necessário especificá-los.");

        lblMensagem.setFont(new Font("Arial", 1, 20));
        lblMensagem2.setFont(new Font("Arial", 1, 20));
        lblMensagem3.setFont(new Font("Arial", 1, 20));
        lblMensagem4.setFont(new Font("Arial", 1, 20));

        lblMensagem.setBounds(10, 10, 550, 30);
        lblMensagem2.setBounds(10, 40, 550, 30);
        lblMensagem3.setBounds(10, 70, 550, 30);
        lblMensagem4.setBounds(10, 100, 600, 30);

        painel2.add(lblMensagem);
        painel2.add(lblMensagem2);
        painel2.add(lblMensagem3);
        painel2.add(lblMensagem4);

        btnC = new JButton("Continuar");
        btnC.setFont(new Font("Arial", 1, 20));
        btnC.setBorder(new BordaCantoArrendondado());
        btnC.setBounds(260, 500, 180, 40);
        painel1.add(btnC);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        leave.setBounds(painel1.getWidth() - 60, 0, 60, 30);
        painel1.add(leave);

        lbl = new JLabel("Icone do software");
        lbl.setBounds(900, 100, 150, 30);
        painel1.add(lbl);

        EventoBotao evt = new EventoBotao();
        btnC.addActionListener(evt);

    }

    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0, 0, 1280, 720);
        add(painel1);

        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBorder(new BordaCantoArrendondado());
        painel2.setBackground(new Color(207, 227, 225));
        painel2.setBounds(50, 50, 650, 300);
        painel1.add(painel2);
    }

    private class EventoBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaTarefas().runTela();
            dispose();
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

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                telaObjetivos.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaObjetivos.runTela();
    }
}

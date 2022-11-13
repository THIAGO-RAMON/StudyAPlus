package view.telasPrograma;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import model.Recompensa;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.BarraLateral;
import view.auxiliares.TelaPadraoFullScreen;
import view.telasPrograma.TelaRecompensas;

public class TelaMyDolly extends TelaPadraoFullScreen {

    private TelaMyDolly mydolly;
    private BarraLateral barraLateral;
    private JPanel painel1;
    private JButton leave;
    private JButton cabeca, torso, bDireito, bEsquerdo, pDireita, pEsquerda;
    private ImageIcon iconeCabeca, iconeTorco, iconeBEsquerdo, iconeBDireito, iconePEsquerda, iconePDireita;
    private SelecionarRec painelSelecionarRecompensas;

    public TelaMyDolly() {
        painel();
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

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

        cabeca = new JButton("cabeça");
        cabeca.setBackground(null);
        cabeca.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        cabeca.addMouseListener(new verRecompensas());
        cabeca.setBounds(500, 200, 60, 60);
        painel1.add(cabeca);

        torso = new JButton("torso");
        torso.setBackground(null);
        torso.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        torso.setBounds(cabeca.getX() - 10, cabeca.getY() + 65, 80, 130);
        painel1.add(torso);

        bDireito = new JButton("braço");
        bDireito.setBackground(null);
        bDireito.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        bDireito.setBounds(torso.getX() - 63, cabeca.getY() + 65, 55, 130);
        painel1.add(bDireito);

        bEsquerdo = new JButton("braço2");
        bEsquerdo.setBackground(null);
        bEsquerdo.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        bEsquerdo.setBounds(torso.getX() + 88, cabeca.getY() + 65, 55, 130);
        painel1.add(bEsquerdo);

        pDireita = new JButton(iconePDireita);
        pDireita.setBackground(null);
        pDireita.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        pDireita.setBounds(torso.getX(), torso.getHeight() + 275, torso.getWidth() / 2, 130);
        painel1.add(pDireita);

        pEsquerda = new JButton("perna");
        pEsquerda.setBackground(null);
        pEsquerda.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        pEsquerda.setBounds(torso.getX() + pDireita.getWidth(), pDireita.getY(), torso.getWidth() / 2, 130);
        painel1.add(pEsquerda);

        painelSelecionarRecompensas = new SelecionarRec();
        painelSelecionarRecompensas.setVisible(true);

        generateSelection();
    }

    private void generateSelection() {
        painelSelecionarRecompensas = new SelecionarRec();
        painelSelecionarRecompensas.setBounds(painel1.getX() + 800, painel1.getY() + 20, 400, 650);
        painelSelecionarRecompensas.setVisible(false);
        painel1.add(painelSelecionarRecompensas);

    }

    private class SelecionarRec extends JPanel {

        private TelaRecompensas tr = new TelaRecompensas();

        public SelecionarRec() {
            config();
            configRec();
        }

        private void config() {
            setBackground(new Color(200, 200, 200));
            setLayout(null);
        }

        private void configRec() {
            JScrollPane jsp = new JScrollPane(tr.getPainelDes());
            jsp.setBounds(0, 0, 400, 650);
            add(jsp);
        }

    }

    private class verRecompensas implements MouseListener {

        public int cont = 0;

        @Override
        public void mouseClicked(MouseEvent e) {
            if (cont % 2 != 0) {
                painelSelecionarRecompensas.setVisible(true);
            } else {
                painelSelecionarRecompensas.setVisible(false);
            }
            cont++;
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
        }

        @Override
        public void mouseExited(MouseEvent e) {
        }

    }

    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0, 0, getWidth(), getHeight());
        add(painel1);
    }

    public void runTela() {
        mydolly = new TelaMyDolly();
        if (mydolly.isActive()) {
            mydolly.dispose();
        }

        TelaMyDolly My = new TelaMyDolly();
        mydolly = My;
        mydolly.setVisible(true);
    }

}

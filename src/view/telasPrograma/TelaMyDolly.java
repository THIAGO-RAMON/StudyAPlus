package view.telasPrograma;

import java.awt.Color;
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

public class TelaMyDolly extends TelaPadraoFullScreen{
    
    private TelaMyDolly mydolly;
    private BarraLateral barraLateral;
    private JPanel painel1;
    private JButton leave;
    private JButton cabeca,torco, bDireito, bEsquerdo, pDireita, pEsquerda;
    private ImageIcon iconeCabeca,iconeTorco,iconeBEsquerdo,iconeBDireito,iconePEsquerda,iconePDireita;
    
    public TelaMyDolly(){
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
        cabeca.setBounds(500, 30, 70, 70);
        painel1.add(cabeca);

        torco = new JButton("torço");
        torco.setBackground(null);
        torco.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        torco.setBounds(cabeca.getX() - 43, 130, 150, 220);
        painel1.add(torco);

        bDireito = new JButton("braço");
        bDireito.setBackground(null);
        bDireito.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        bDireito.setBounds(torco.getX() - 80, 130, 60, 220);
        painel1.add(bDireito);

        bEsquerdo = new JButton("braço");
        bEsquerdo.setBackground(null);
        bEsquerdo.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        bEsquerdo.setBounds(torco.getX() + 170, 130, 60, 220);
        painel1.add(bEsquerdo);

        pDireita = new JButton(iconePDireita);
        pDireita.setBackground(null);
        pDireita.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        pDireita.setBounds(torco.getX(), torco.getHeight() + 150, torco.getWidth() / 2, 220);
        painel1.add(pDireita);

        pEsquerda = new JButton("perna");
        pEsquerda.setBackground(null);
        pEsquerda.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        pEsquerda.setBounds(torco.getX() + pDireita.getWidth(), pDireita.getY(), torco.getWidth() / 2, 220);
        painel1.add(pEsquerda);
        
    }
    
    private class SelecionarRec extends JFrame{
        
        private JPanel painelPrincipal;
        private TelaRecompensas tr = new TelaRecompensas();
        public SelecionarRec(){
            config();
            configPainel();
            JScrollPane teupai = new JScrollPane(tr.getPainelDes());
            teupai.setBounds(0,0,painelPrincipal.getWidth(),painelPrincipal.getHeight());
            painelPrincipal.add(teupai);
        }
        
        private void config(){
            setUndecorated(true);
            setLocationRelativeTo(null);
            setLayout(null);
            setBounds(painel1.getX()+800,painel1.getY()+20,400,650);
        }
        private void configPainel() {
            painelPrincipal = new JPanel(null);
            painelPrincipal.setBounds(0, 0, this.getWidth(), this.getHeight());
            painelPrincipal.setBackground(new Color(200, 200, 200));
            add(painelPrincipal);
        }
        
    }
    
    private class verRecompensas implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            new SelecionarRec().setVisible(true);
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
    
    private void painel(){
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0,0,getWidth(),getHeight());
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

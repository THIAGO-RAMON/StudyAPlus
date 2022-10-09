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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import model.bean.Objetivo;
import view.Main.BarraLateral;
import view.Main.TelaPadraoFullScreen;
import model.dao.ObjetivoDAO;
import view.Main.Principal;
import static view.perfil.TelaPerfil.telaPerfil;

public class TelaCriarObjetivos extends TelaPadraoFullScreen {

    private JPanel painel1;
    private JLabel lbl, lbl2;
    private JTextArea txt;
    private JScrollPane pane;
    private JButton btn, leave;
    private BarraLateral barraLateral;

    private ObjetivoDAO dao = new ObjetivoDAO();
    private Objetivo obj;

    public static TelaCriarObjetivos telaCriarObjetivos = new TelaCriarObjetivos();

    public TelaCriarObjetivos() {
        painel();

        txt = new JTextArea();
        txt.setFont(new Font("Arial", 1, 20));
        pane = new JScrollPane(txt, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        pane.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        pane.setBounds(425, 200, 600, 400);
        painel1.add(pane);

        lbl = new JLabel("O que deseja alcançar? Tente Explorar seus objetivos com base em seu calendário.");
        lbl.setFont(new Font("Arial", 1, 27));
        lbl.setBounds(150, 40, 1150, 30);
        painel1.add(lbl);

        lbl2 = new JLabel("Coloque seus objetivos aqui!");
        lbl2.setFont(new Font("Arial", 1, 18));
        lbl2.setBounds(425, 160, 300, 30);
        painel1.add(lbl2);

        EventoBotao evt = new EventoBotao();
        btn = new JButton("Continuar");
        btn.setBorder(new BordaCantoArrendondado());
        btn.setFont(new Font("Arial", 1, 20));
        btn.setBounds(getWidth() - 160, 680, 150, 30);
        btn.addActionListener(evt);
        painel1.add(btn);

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

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

    }

    private class EventoBotao implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            saveObjetivo();
        }
        
    }
    
    private void saveObjetivo(){
        if(txt.equals("")){
            JOptionPane.showMessageDialog(null, "Por favor, insira algum texto!","Objetivos",0);
        }
        
        obj = new Objetivo();
        obj.setUser(Principal.user);
        obj.setDescricao(txt.getText());
        
        if(dao.saveObjetivo(obj)){
            JOptionPane.showMessageDialog(null, "Objetivo salvo com sucesso!","Objetivos", JOptionPane.INFORMATION_MESSAGE);
        }
        
        txt.setText("");
        
    }
    
    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0, 0, 1280, 720);
        add(painel1);
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
                telaCriarObjetivos.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaCriarObjetivos.runTela();
    }

}

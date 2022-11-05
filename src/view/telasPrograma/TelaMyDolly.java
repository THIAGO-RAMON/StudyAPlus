package view.telasPrograma;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.BarraLateral;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaMyDolly extends TelaPadraoFullScreen{
    
    private TelaMyDolly mydolly;
    private BarraLateral barraLateral;
    private JPanel painel1;
    private JButton leave;
    
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

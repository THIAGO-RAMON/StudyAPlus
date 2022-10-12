package view.perfil;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class DicasEstudo extends JFrame{
    
    JPanel painel1,painel2;
    JLabel msg,dica;
    JButton btnGerar;
    
    DicasEstudo(){
        config();
        painel();
        
        msg = new JLabel("Seu desempenho foi bom, gerar uma dica!");
        msg.setFont(new Font("Arial",1,20));
        msg.setBounds(50,120,400,30);
        painel1.add(msg);
        
        btnGerar = new JButton("Gerar dica");
        btnGerar.setFont(new Font("Arial",1,25));
        btnGerar.setBounds(180,280,160,40);
        btnGerar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dica.setText("Se organize.");

            Thread.yield();

            Runnable run = new Runnable() {

                @Override
                public void run() {
                    try {
                        int contx = dica.getX();

                        while (dica.getX() != painel2.getWidth() - 400) {
                            dica.setBounds(contx, 0, dica.getWidth(), dica.getHeight());
                            contx++;
                            Thread.sleep(1);
                        }
                    } catch (InterruptedException ex) {
                        System.err.println(ex);
                    }
                }
            };

            Thread mover = new Thread(run);
            mover.start();
            }
        });
        painel1.add(btnGerar);
        
        dica = new JLabel();
        dica.setFont(new Font("Arial",1,18));
        dica.setBounds(0,0,400,30);
        painel2.add(dica);
    }
    
    
    private void painel(){
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0,0,1280,720);
        painel1.setBackground(new Color(200,200,200));
        painel1.setBorder(new LineBorder(Color.BLACK.darker(),1,true));
        add(painel1);
        
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(600,100,500,500);
        painel2.setBackground(new Color(200,200,200));
        painel2.setBorder(new LineBorder(Color.BLACK.darker(),1,true));
        painel1.add(painel2);
    }
    
    private void config(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1280,720);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }
    
    public static void main(String[] args) {
        new DicasEstudo().setVisible(true);
    }
    
}

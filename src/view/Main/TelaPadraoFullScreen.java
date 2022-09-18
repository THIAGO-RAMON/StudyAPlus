package view.Main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.AbstractBorder;

import java.awt.*;
import java.net.URL;

public class TelaPadraoFullScreen extends JFrame{
    
    private Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    public TelaPadraoFullScreen(){
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    public class BordaCantoArrendondado extends AbstractBorder {

        private final BasicStroke CONTORNO = new BasicStroke(2);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            super.paintBorder(c, g, x, y, width, height);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(CONTORNO);

            g2d.setColor(Color.black);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 15, 15);

        }
    }

        public class InserirIcone{
        public void InserirIcone (JFrame jfr){
            URL url = this.getClass().getResource("/images/Logo100x75.png");
            try{
                jfr.setIconImage(Toolkit.getDefaultToolkit().getImage(url));
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, ex.toString(), "StudyA+", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    }



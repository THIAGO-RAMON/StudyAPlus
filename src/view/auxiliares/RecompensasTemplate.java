package view.auxiliares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import model.Recompensa;

/**
 *
 * @author Thiago Ramon
 */
public class RecompensasTemplate extends JPanel{

    protected Recompensa recompensa;
    protected Color corBackground;
    private boolean isHabilitado;

    public RecompensasTemplate(Recompensa recompensa, boolean isHabilitado) {
        configPainelTemplate();
        
        this.recompensa = recompensa;
        
        if(isHabilitado){
            corBackground = new Color(167, 196, 193);
        }else{
            corBackground = new Color(142, 155, 153);
        }
    }
    
    private void configPainelTemplate(){
        setPreferredSize(new Dimension(125, 125));
        setLayout(null);
        setBorder(null);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
        
        g.setColor(corBackground);
        g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 30, 30);
    }
    
    
}
package view.auxiliares;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.bean.Recompensa;

/**
 *
 * @author Thiago Ramon
 */
public class CardRecompensas extends RecompensasTemplate{
   
    private JLabel imagemRecompensa;
    private JLabel nomeRecompensa;
    private JButton verMais;
    private FileNameExtensionFilter filtro;
    
    public CardRecompensas(Recompensa recompensa, boolean isHabilitado) {
        super(recompensa, isHabilitado);
        setBackground(new Color(168, 168, 168));
        configFilterFileChosser();
        configPainel();
    }

    private void configPainel(){
        imagemRecompensa = new JLabel();
        imagemRecompensa.setBounds(20, 10, 85, 85);
        imagemRecompensa.setIcon(resizeImage(recompensa.getImg()));
        add(imagemRecompensa);
        
        nomeRecompensa = new JLabel(recompensa.getNome());
        nomeRecompensa.setFont(new Font("Arial", 0, 14));
        nomeRecompensa.setBounds(10, 95, 105, 40);
        nomeRecompensa.setHorizontalAlignment(SwingConstants.CENTER);
        nomeRecompensa.setHorizontalTextPosition(SwingConstants.CENTER);
        add(nomeRecompensa);
        
    }
    
    private void configFilterFileChosser() {
        filtro = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
    }

    private ImageIcon resizeImage(String imagemPath) {
        ImageIcon minhaImagem = new ImageIcon(imagemPath);
        Image img = minhaImagem.getImage();
        Image imgNew = img.getScaledInstance(imagemRecompensa.getWidth(), imagemRecompensa.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imgNew);
        return imagem;
    }
    
    
}
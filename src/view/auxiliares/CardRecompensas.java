/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.auxiliares;

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
 * @author Migas
 */
public class CardRecompensas extends RecompensasTemplate{
   
    private JLabel imagemRecompensa;
    private JLabel nomeRecompensa;
    private JButton verMais;
    FileNameExtensionFilter filtro;
    
    public CardRecompensas(Recompensa recompensa, boolean isHabilitado) {
        super(recompensa, isHabilitado);
        configFilterFileChosser();
        configPainel();
    }

    private void configPainel(){
        imagemRecompensa = new JLabel(resizeImage(recompensa.getImg()));
        imagemRecompensa.setBounds((this.getWidth()/2)-75, 10, 75, 75);
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
        Image imgNew = img.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imgNew);
        return imagem;
    }
    
    
}

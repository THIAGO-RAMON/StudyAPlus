package view.auxiliares;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JWindow;
import javax.swing.SwingConstants;

public class Splash {

    private final int LARGURA_IMG = 550;
    private final int ALTURA_IMG = 400;
    private final String CAMINHO_GIF = "/images/carregamento.gif";

    public Splash() {
        JWindow janela = new JWindow();
        janela.getContentPane().setBackground(new Color(207, 227, 225));
        janela.getContentPane().add(
                new JLabel("", new ImageIcon(getClass().getResource(CAMINHO_GIF)), SwingConstants.CENTER)
        );

        Toolkit tool = Toolkit.getDefaultToolkit();
        Dimension dimension = tool.getScreenSize();

        janela.setBounds((dimension.width - LARGURA_IMG) / 2, (dimension.height - ALTURA_IMG) / 2, LARGURA_IMG, ALTURA_IMG);
        janela.setVisible(true);

        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }

        janela.dispose();
    }
}

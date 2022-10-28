package view.auxiliares;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.event.MouseInputListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.bean.Recompensa;

/**
 *
 * @author Thiago Ramon
 */
public class CardRecompensas extends RecompensasTemplate {

    private JLabel imagemRecompensa;
    private JLabel nomeRecompensa;
    private JButton verMais;
    private FileNameExtensionFilter filtro;

    public CardRecompensas(Recompensa recompensa, boolean isHabilitado) {
        super(recompensa, isHabilitado);
        setBackground(new Color(168, 168, 168));
        addMouseListener(new CardSelecionado());
        configFilterFileChosser();
        configPainel();
    }

    private void configPainel() {
        imagemRecompensa = new JLabel();
        imagemRecompensa.setBounds(20, 10, 85, 85);
        imagemRecompensa.setIcon(resizeImage(recompensa.getImg(), 85, 85));
        add(imagemRecompensa);

        nomeRecompensa = new JLabel(recompensa.getNome());
        nomeRecompensa.setFont(new Font("Arial", 0, 14));
        nomeRecompensa.setBounds(10, 95, 105, 30);
        nomeRecompensa.setHorizontalAlignment(SwingConstants.CENTER);
        nomeRecompensa.setHorizontalTextPosition(SwingConstants.CENTER);
        add(nomeRecompensa);
        
    }

    private void configFilterFileChosser() {
        filtro = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
    }

    private ImageIcon resizeImage(String imagemPath, int width, int height) {
        ImageIcon minhaImagem = new ImageIcon(imagemPath);
        Image img = minhaImagem.getImage();
        Image imgNew = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imgNew);
        return imagem;
    }

    
    // Classes Internas
    
    private class CardSelecionado implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent me) {
            new MostrarMaisRecompensas(recompensa).setVisible(true);
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            setBackground(corBackground.darker());
        }

        @Override
        public void mouseExited(MouseEvent me) {
            setBackground(corBackground);
        }
        
    }
    
    private class MostrarMaisRecompensas extends JFrame {

        private Recompensa recompensa;

        private JPanel painelPrincipal;
        private JLabel imagem, nome;
        private JTextArea descricao;
        private JButton btnSair;

        public MostrarMaisRecompensas(Recompensa recompensa) {
            this.recompensa = recompensa;

            setSize(300, 300);
            setUndecorated(true);
            setLocationRelativeTo(null);
            setLayout(null);
            setAlwaysOnTop(true);

            configPainel();

            nome = new JLabel(recompensa.getNome());
            nome.setFont(new Font("Arial", 0, 18));
            nome.setHorizontalAlignment(SwingConstants.CENTER);
            nome.setHorizontalTextPosition(SwingConstants.CENTER);
            nome.setBounds(0, 20, 300, 30);
            painelPrincipal.add(nome);
            
            imagem = new JLabel(resizeImage(recompensa.getImg(), 150, 150));
            imagem.setHorizontalAlignment(SwingConstants.CENTER);
            imagem.setHorizontalTextPosition(SwingConstants.CENTER);
            imagem.setBounds(0, 50, 300, 150);
            painelPrincipal.add(imagem);

            descricao = new JTextArea(recompensa.getDescricao());
            descricao.setLineWrap(true);
            descricao.setBackground(painelPrincipal.getBackground());
            descricao.setEditable(false);
            descricao.setBorder(null);
            descricao.setFont(new Font("Arial", 0, 14));
            descricao.setBounds(20, 225, painelPrincipal.getWidth() - 30, 65);
            painelPrincipal.add(descricao);

            btnSair = new JButton("-");
            btnSair.setBackground(new Color(223, 63, 16));
            btnSair.setFont(new Font("Arial", 0, 8));
            btnSair.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });
            btnSair.setFocusable(false);
            btnSair.setBounds(painelPrincipal.getWidth() - 30, 0, 30, 30);
            painelPrincipal.add(btnSair);

        }

        private void configPainel() {
            painelPrincipal = new JPanel(null);
            painelPrincipal.setBounds(0, 0, this.getWidth(), this.getHeight());
            painelPrincipal.setBackground(new Color(207, 227, 225));
            add(painelPrincipal);
        }

    }
}

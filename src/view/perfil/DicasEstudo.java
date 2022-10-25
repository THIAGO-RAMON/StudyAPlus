package view.perfil;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.font.TextAttribute;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import model.bean.User;
import model.dao.UserDAO;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;

public class DicasEstudo extends JFrame {

    private JPanel painel1, painel2;
    private JLabel msg;
    private JButton btnGerar,leave;
    private static DicasEstudo dicasEstudo;
    private BarraLateral barraLateral;
    private UserDAO userDao = new UserDAO();
    private User usuario = Principal.user;
    private static int posicao = 0;
    private String dicasDeEstudo[] = {"Crie um plano de estudos.", "Tenha um horário fixo.", "Escreva os conteúdos.", "Faça exercícios.", "https://www.napratica.org.br/dicas-para-estudar-melhor-ciencia/"};
    private double porcentagem = 0;
    private static int v = 0;

    public DicasEstudo() {
        
        v=0;

        porcentagem = userDao.getPorcentagem(usuario);

        config();
        painel();
        
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

        msg = new JLabel("Seu desempenho foi bom, gerar uma dica!");
        msg.setFont(new Font("Arial", 1, 20));
        msg.setBounds(550, 70, 400, 30);
        painel1.add(msg);

        btnGerar = new JButton("Gerar dica");
        btnGerar.setFont(new Font("Arial", 1, 22));
        btnGerar.setBounds(675, 150, 160, 40);
        btnGerar.setEnabled(false);
        //btnGerar.addMouseListener(new verMouse(btnGerar));
        btnGerar.setBorder(new BordaPersonalizada());
        btnGerar.setBackground(new Color(207, 227, 225));
        btnGerar.addActionListener(new EventoBotao());
        painel1.add(btnGerar);

        verifica();
    }

    private class EventoBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                JLabel dica = new JLabel(dicasDeEstudo[v++]);
                dica.setFont(new Font("Arial", 1, 18));
                dica.setBounds(0, posicao, 400, 30);
                painel2.add(dica);

                Thread.yield();

                Runnable run = new Runnable() {

                    @Override
                    public void run() {
                        try {
                            int contx = dica.getX();

                            while (dica.getX() != painel2.getWidth() - 450) {
                                dica.setBounds(contx, posicao, dica.getWidth(), dica.getHeight());
                                contx++;
                                Thread.sleep(4);
                            }
                        } catch (InterruptedException ex) {
                            System.err.println(ex);
                        }
                    }
                };

                Thread mover = new Thread(run);
                mover.start();
                posicao += 30;

                if (dica.getText().equals("https://www.napratica.org.br/dicas-para-estudar-melhor-ciencia/")) {
                    
                    Map<TextAttribute, Object> atributos = new HashMap<TextAttribute, Object>();
                    atributos.put(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);

                    dica.setFont(new Font("Arial", 2, 13).deriveFont(atributos));
                    dica.setForeground(new Color(39, 146, 231));
                    dica.addMouseListener(new EventoMouse());
                }

            } catch (ArrayIndexOutOfBoundsException ex) {
                System.err.println(ex);
            }

        }
    }

    private class EventoMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            try {
                URI link = new URI("https://www.napratica.org.br/dicas-para-estudar-melhor-ciencia/");
                Desktop.getDesktop().browse(link);
            } catch (Exception ex) {

            }

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

    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, 1280, 720);
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        add(painel1);

        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(510, 250, 500, 400);
        painel2.setBackground(new Color(200, 200, 200));
        painel2.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painel1.add(painel2);
    }

    private void config() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        setSize(1280, 720);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    private class BordaPersonalizada extends AbstractBorder {

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            // TODO Auto-generated method stub
            super.paintBorder(c, g, x, y, width, height);

            Graphics2D g2d = (Graphics2D) g;

            g2d.setStroke(new BasicStroke(2));
            g2d.setColor(Color.BLACK);
            g2d.drawRoundRect(x, y, width, height, 10, 10);

        }

    }

    private class verMouse implements MouseListener {

        JButton btn;

        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
        }

        @Override
        public void mouseReleased(MouseEvent e) {
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            btn.setBackground(new Color(161, 207, 203));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            btn.setBackground(new Color(207, 227, 225));
        }

        public verMouse(JButton e) {
            this.btn = e;
        }

    }

    private void verifica() {

        if (porcentagem > 60.00) {
            btnGerar.setEnabled(true);
            btnGerar.addMouseListener(new verMouse(btnGerar));
        } else {
            JOptionPane.showMessageDialog(null, "Seu desempenho não está bom o suficiente.", "Dicas de Estudo", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void runTela() {
        dicasEstudo = new DicasEstudo();
        if (dicasEstudo.isActive()) {
            dicasEstudo.dispose();
        }

        DicasEstudo telaTarefasNova = new DicasEstudo();
        dicasEstudo = telaTarefasNova;
        dicasEstudo.setVisible(true);
    }

    public static void main(String[] args) {
        new DicasEstudo().runTela();
    }

}

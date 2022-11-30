package view.telasPrograma;

import controller.MyDollyController;
import controller.RecompensaController;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import model.MyDolly;
import model.Recompensa;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.RecompensasTemplate;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaMyDolly extends TelaPadraoFullScreen {

    private TelaMyDolly mydollyTela;
    private BarraLateral barraLateral;
    private JPanel painel1, painelRecompensas;
    private JButton leave;
    private JButton cabeca, torso, perna;
    private MyDolly mydolly;
    private MyDollyController controllerMD = new MyDollyController();
    private ImageIcon iconeCabeca, iconeTorco, iconeBEsquerdo, iconeBDireito, iconePEsquerda, iconePDireita;
    private SelecionarRec painelSelecionarRecompensas;
    private JLabel lblTitulo, lblDescricao;
    private boolean isCabecaDefaultLoaded = false, isTorsoDefaultLoaded = false, isPernaDefaultLoaded = false;
    private String fileProject = System.getProperty("user.dir");
    private JPanel painelBackgroundMyDolly;

    public TelaMyDolly() {
        painel();

        painelBackground();
        
        if (controllerMD.verificarSeJaFoiCarregado() == 0) {
            carregarDollyProUser();
        } else {
            carregarMeuDolly();
        }

        lblTitulo = new JLabel("MyDolly");
        lblTitulo.setFont(new Font("Arial", 1, 32));
        lblTitulo.setBounds((int) (painel1.getWidth() / 2) + 50, 30, 300, 30);
        painel1.add(lblTitulo);

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

        lblDescricao = new JLabel("Descricao");
        lblDescricao.setBounds(painel1.getX() + 800, painel1.getY() + 110, 350, 30);
        lblDescricao.setBorder(BorderFactory.createLineBorder(Color.black, 2, true));
        lblDescricao.setHorizontalTextPosition(SwingConstants.CENTER);
        lblDescricao.setHorizontalAlignment(SwingConstants.CENTER);
        lblDescricao.setFont(new Font("Arial", 1, 24));
        lblDescricao.setVisible(false);
        painel1.add(lblDescricao);

        painelRecompensas = new JPanel(new MigLayout());
        painelRecompensas.setPreferredSize(new Dimension(350, 600));
        painelRecompensas.setBackground(new Color(168, 168, 168));

        cabeca = new JButton();
        cabeca.setBackground(null);
        cabeca.setText("cabeca");
        cabeca.setBorder(null);
        cabeca.setIcon(resizeImage(mydolly.getCabeca(), 150, 150));
        cabeca.addActionListener(new verRecompensas(cabeca));
        cabeca.setBounds((int)(painelBackgroundMyDolly.getWidth()/2)-35, 60, 100, 100);
        painelBackgroundMyDolly.add(cabeca);

        torso = new JButton(resizeImage(mydolly.getTorso(), 200, 200));
        torso.setBackground(null);
        torso.setText("torso");
        torso.setBorder(null);
        torso.addActionListener(new verRecompensas(torso));
        torso.setBounds(cabeca.getX() - 35, cabeca.getY() + 105, 150, 120);
        painelBackgroundMyDolly.add(torso);

        perna = new JButton(resizeImage(mydolly.getPerna(), 200, 300));
        perna.setBackground(null);
        perna.setText("perna");
        perna.setBorder(null);
        perna.addActionListener(new verRecompensas(perna));
        perna.setBounds(torso.getX(), torso.getY() + 125, 150, 100);
        painelBackgroundMyDolly.add(perna);

        painelSelecionarRecompensas = new SelecionarRec();
        painelSelecionarRecompensas.setVisible(true);

        generateSelection();
    }

    private void generateSelection() {
        painelSelecionarRecompensas = new SelecionarRec();
        painelSelecionarRecompensas.setBounds(painel1.getX() + 800, painel1.getY() + 150, 350, 500);
        painelSelecionarRecompensas.setVisible(false);
        painel1.add(painelSelecionarRecompensas);

    }

    private class SelecionarRec extends JPanel {

        public SelecionarRec() {
            config();
            configRec();
        }

        private void config() {
            setBackground(new Color(200, 200, 200));
            setLayout(null);
        }

        private void configRec() {
            JScrollPane jsp = new JScrollPane(painelRecompensas);
            jsp.setBounds(0, 0, 350, 500);
            jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            add(jsp);
        }

    }

    private class verRecompensas implements ActionListener {

        private int cont = 1;
        private JButton btn;

        public verRecompensas(JButton btn) {
            this.btn = btn;
        }

        private String configDesc(String descricao) {
            String aux = "";
            aux = descricao.toUpperCase().substring(0, 1);
            aux += descricao.toLowerCase().substring(1, descricao.length());
            return aux;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            try {
                if (painelSelecionarRecompensas.isVisible() == false) {
                    lblDescricao.setText(configDesc(btn.getText()));
                    lblDescricao.setVisible(true);
                    carregarRecompensaGanhaPorTela(btn.getText(), btn);
                    painelSelecionarRecompensas.setVisible(true);
                } else {
                    lblDescricao.setText("");
                    lblDescricao.setVisible(false);
                    painelRecompensas.removeAll();
                    painelSelecionarRecompensas.setVisible(false);
                }
                cont++;
            } catch (SQLException ex) {
                System.out.println("Erro no loading das recompensas ganha");
                System.err.println(ex);
            }
        }

    }

    private void carregarRecompensaGanhaPorTela(String tipo, JButton btn) throws SQLException {

        int qtdRecompensas = 0;

        ArrayList<Recompensa> recompensasHabilitada = (ArrayList<Recompensa>) new RecompensaController().recompensasHabilitadasPorTipo(Principal.user, tipo);

        for (Recompensa recompensa : recompensasHabilitada) {
            if (qtdRecompensas >= 3) {
                BotaoRecompensa recompensaBtn = new BotaoRecompensa(recompensa, true, btn);
                painelRecompensas.add(recompensaBtn, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
                qtdRecompensas = 0;
            } else {
                BotaoRecompensa recompensaBtn = new BotaoRecompensa(recompensa, true, btn);
                painelRecompensas.add(recompensaBtn, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30, wrap");
                qtdRecompensas++;
            }
        }

    }
    public void carregarMeuDolly() {
        try {
            mydolly = controllerMD.listarMyDolly();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public void carregarDollyProUser() {
        try {
            controllerMD.loadMyDolly();
            mydolly = controllerMD.listarMyDolly();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private ImageIcon resizeImage(String imagemPath, int width, int height) {
        ImageIcon minhaImagem = new ImageIcon(imagemPath);
        Image img = minhaImagem.getImage();
        Image imgNew = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imgNew);
        return imagem;
    }

    //Classe interna
    public class BotaoRecompensa extends RecompensasTemplate {

        private JLabel imagemRecompensa;
        private JButton btn;

        public BotaoRecompensa(Recompensa recompensa, boolean isHabilitada, JButton btn) {
            super(recompensa, isHabilitada);
            this.btn = btn;
            setBackground(new Color(168, 168, 168));
            addMouseListener(new BotaoSelecionado());
            configPainel();
        }

        private void configPainel() {
            imagemRecompensa = new JLabel();
            imagemRecompensa.setBounds(0, 0, 125, 125);
            imagemRecompensa.setIcon(resizeImage(recompensa.getImg(), 100, 100));
            imagemRecompensa.setHorizontalAlignment(SwingConstants.CENTER);
            imagemRecompensa.setHorizontalTextPosition(SwingConstants.CENTER);
            imagemRecompensa.setVerticalAlignment(SwingConstants.CENTER);
            imagemRecompensa.setVerticalTextPosition(SwingConstants.CENTER);
            imagemRecompensa.setBorder(null);
            imagemRecompensa.setBackground(null);
            add(imagemRecompensa);

        }

        private ImageIcon resizeImage(String imagemPath, int width, int height) {
            ImageIcon minhaImagem = new ImageIcon(imagemPath);
            Image img = minhaImagem.getImage();
            Image imgNew = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            ImageIcon imagem = new ImageIcon(imgNew);
            return imagem;
        }

        private class BotaoSelecionado extends MouseAdapter {

            @Override
            public void mouseClicked(MouseEvent me) {
            }

            @Override
            public void mousePressed(MouseEvent me) {
                alterarParteDoCorpo(btn, recompensa);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
                setBackground(getBackground().darker());
            }

            @Override
            public void mouseExited(MouseEvent me) {
                setBackground(getBackground().brighter());
            }

        }

        private void alterarParteDoCorpo(JButton btn, Recompensa recompensa) {
            btn.setIcon(null);
            String parte = btn.getText();
            if (parte.equalsIgnoreCase("cabeca")) {
                new MyDollyController().updateMyDollyCabeca(btn.getText(), recompensa);
            } else if (parte.equalsIgnoreCase("perna")) {
                new MyDollyController().updateMyDollyPerna(btn.getText(), recompensa);
            } else if (parte.equalsIgnoreCase("torso")) {
                new MyDollyController().updateMyDolly(btn.getText(), recompensa);

            }

            dispose();
            runTela();
        }

        private void alterarParteDoCorpoCabeca(JButton btn, Recompensa recompensa) {
            btn.setIcon(null);
            new MyDollyController().updateMyDollyCabeca(btn.getText(), recompensa);
            dispose();
            runTela();
        }

        private void alterarParteDoCorpoPerna(JButton btn, Recompensa recompensa) {
            btn.setIcon(null);
            new MyDollyController().updateMyDollyPerna(btn.getText(), recompensa);
            dispose();
            runTela();
        }

    }

    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0, 0, getWidth(), getHeight());
        add(painel1);
    }
    
    private void painelBackground(){
        painelBackgroundMyDolly = new JPanel();
        painelBackgroundMyDolly.setBounds(400, 145, 300, 500);
        painelBackgroundMyDolly.setBackground(new Color(218, 217, 215));
        painelBackgroundMyDolly.setBorder(new BordaCantoArrendondado());
        painelBackgroundMyDolly.setLayout(null);
        painel1.add(painelBackgroundMyDolly);
    }

    public void runTela() {
        mydollyTela = new TelaMyDolly();
        if (mydollyTela.isActive()) {
            mydollyTela.dispose();
        }

        TelaMyDolly My = new TelaMyDolly();
        mydollyTela = My;
        mydollyTela.setVisible(true);
    }

}

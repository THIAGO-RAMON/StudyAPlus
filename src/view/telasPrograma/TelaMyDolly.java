package view.telasPrograma;

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
import model.Recompensa;
import net.miginfocom.swing.MigLayout;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.RecompensasTemplate;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaMyDolly extends TelaPadraoFullScreen {

    private TelaMyDolly mydolly;
    private BarraLateral barraLateral;
    private JPanel painel1, painelRecompensas;
    private JButton leave;
    private JButton cabeca, torso, bDireito, bEsquerdo, pDireita, pEsquerda;
    private ImageIcon iconeCabeca, iconeTorco, iconeBEsquerdo, iconeBDireito, iconePEsquerda, iconePDireita;
    private SelecionarRec painelSelecionarRecompensas;
    private JLabel lblTitulo, lblDescricao;

    public TelaMyDolly() {
        painel();

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

        cabeca = new JButton("cabeça");
        cabeca.setBackground(null);
        cabeca.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        cabeca.addActionListener(new verRecompensas(cabeca));
        cabeca.setBounds(500, 200, 60, 60);
        painel1.add(cabeca);

        torso = new JButton("torso");
        torso.setBackground(null);
        torso.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        torso.addActionListener(new verRecompensas(torso));
        torso.setBounds(cabeca.getX() - 10, cabeca.getY() + 65, 80, 130);
        painel1.add(torso);

        bDireito = new JButton("");
        bDireito.setBackground(null);
        bDireito.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        bDireito.addActionListener(new verRecompensas(torso));
        bDireito.setBounds(torso.getX() - 63, cabeca.getY() + 65, 55, 130);
        painel1.add(bDireito);

        bEsquerdo = new JButton("");
        bEsquerdo.setBackground(null);
        bEsquerdo.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        bEsquerdo.addActionListener(new verRecompensas(torso));
        bEsquerdo.setBounds(torso.getX() + 88, cabeca.getY() + 65, 55, 130);
        painel1.add(bEsquerdo);

        pDireita = new JButton("perna");
        pDireita.setBackground(null);
        pDireita.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        pDireita.addActionListener(new verRecompensas(pDireita));
        pDireita.setBounds(torso.getX(), torso.getHeight() + 275, torso.getWidth() / 2, 130);
        painel1.add(pDireita);

        pEsquerda = new JButton("perna");
        pEsquerda.setBackground(null);
        pEsquerda.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        pEsquerda.addActionListener(new verRecompensas(pDireita));
        pEsquerda.setBounds(torso.getX() + pDireita.getWidth(), pDireita.getY(), torso.getWidth() / 2, 130);
        painel1.add(pEsquerda);

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
                if (cont % 2 != 0) {
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

        ArrayList<Recompensa> recompensasHabilitada
                = (ArrayList<Recompensa>) new RecompensaController().recompensasHabilitadasPorTipo(Principal.user, tipo);

        for (Recompensa recompensa : recompensasHabilitada) {
            if (qtdRecompensas >= 3) {
                BotaoRecompensa recompensaBtn = new BotaoRecompensa(recompensa,true,btn);
                painelRecompensas.add(recompensaBtn, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
                qtdRecompensas = 0;
            } else {
                BotaoRecompensa recompensaBtn = new BotaoRecompensa(recompensa,true,btn);
                painelRecompensas.add(recompensaBtn, "w 125, h 125, gaptop 10,  gapleft 10, gapright 30");
                qtdRecompensas++;
            }
        }

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
    
    private class BotaoSelecionado extends MouseAdapter{

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
    
    private void alterarParteDoCorpo(JButton btn, Recompensa recompensa){
        btn.setText("");
        btn.setIcon(null);
        configLocalizaçãoByRecompensa(btn, recompensa);
        painel1.repaint();
        painel1.revalidate();
    }
    
    private void configLocalizaçãoByRecompensa(JButton btn, Recompensa recompensa){
        
        switch(recompensa.getNome()){
            case "Oculos":
                btn.setIcon(resizeImage(recompensa.getImg(), btn.getWidth(), btn.getHeight()));
                btn.setHorizontalAlignment(SwingConstants.CENTER);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                btn.setVerticalAlignment(SwingConstants.CENTER);
                btn.setVerticalTextPosition(SwingConstants.CENTER);
                break;
            case "Boné":
                btn.setIcon(resizeImage(recompensa.getImg(), btn.getWidth(), btn.getHeight()));
                btn.setHorizontalAlignment(SwingConstants.CENTER);
                btn.setHorizontalTextPosition(SwingConstants.CENTER);
                break;
        }
        
    }
    
}

    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0, 0, getWidth(), getHeight());
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

package view.telasPrograma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

import view.Main.BarraLateral;
import view.Main.TelaPadraoFullScreen;
import view.Main.TelaPadraoFullScreen;

import view.perfil.TelaPerfil;

public class TelaTarefas extends TelaPadraoFullScreen {

    private PainelTarefas painelTarefas;
    private JPanel painelPrincipal;
    private BarraLateral barraLateral;
    private JLabel lblAfazeres, lblCreateTarefa, lblTarefa;
    private JButton btn, btnAdd, btnFoto, btnConfirmar, btnAlter, btnPerfil, btnDesempenho, btnX, leave;
    private JTextField txtCreateTarefa, txtAlter;
    private ArrayList<JCheckBox> cBoxs = new ArrayList<>();
    private ArrayList<JLabel> tarefas = new ArrayList<>();
    private static double tarefa = 0, concluido = 0;

    public static TelaTarefas telaDasTarefas = new TelaTarefas();

    public static int XTAREFALBL = 90, XCBTAREFA = 60, XBTNRMOVE = 700;
    public static int YTAREFALBL = 60, YCBTAREFA = 60, YBTNREMOVE = 60;

    public TelaTarefas() {
        EventoCriarTarefa evtCriar = new EventoCriarTarefa();
        EventoPerfil eventoPerfil = new EventoPerfil();

        InserirIcone ic = new InserirIcone();
        ic.InserirIcone(this);

        painelPrincipal = new JPanel(null);
        painelPrincipal.setBackground(new Color(218, 217, 215));
        painelPrincipal.setBounds(0, 0, getWidth(), getHeight());
        add(painelPrincipal);

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 100, barraLateral.getWidth(), barraLateral.getHeight());
        painelPrincipal.add(barraLateral);

        painelTarefas = new PainelTarefas();
        painelTarefas.setBounds(320, 100, 930, 600);
        painelTarefas.setBackground(new Color(218, 217, 215));
        painelTarefas.setLayout(null);
        painelPrincipal.add(painelTarefas);

        lblAfazeres = new JLabel("SEUS AFAZERES");
        lblAfazeres.setFont(new Font("Arial", 1, 25));
        lblAfazeres.setBounds(580, 20, 250, 30);
        lblAfazeres.setForeground(Color.BLACK.darker());
        painelPrincipal.add(lblAfazeres);

        lblCreateTarefa = new JLabel("CRIAR TAREFA");
        lblCreateTarefa.setFont(new Font("Arial", 1, 26));
        lblCreateTarefa.setBackground(new Color(255, 249, 232));
        lblCreateTarefa.setForeground(Color.BLACK.darker());
        lblCreateTarefa.setBounds(350, 60, 200, 35);
        painelTarefas.add(lblCreateTarefa);

        btnAdd = new JButton("+");
        btnAdd.setBackground(new Color(237, 236, 235));
        btnAdd.setFont(new Font("Arial", 0, 20));
        btnAdd.setForeground(Color.BLACK.darker());
        btnAdd.addActionListener(evtCriar);
        btnAdd.setBounds(750, 60, 50, 35);
        painelTarefas.add(btnAdd);

        btnFoto = new JButton();
        btnFoto.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnFoto.setBackground(new Color(218, 217, 215));
        btnFoto.setBorder(null);
        btnFoto.setBounds(10, 10, 100, 75);
        painelPrincipal.add(btnFoto);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        leave.setBounds(painelPrincipal.getWidth() - 60, 0, 60, 30);
        painelPrincipal.add(leave);

    }

    // EVENTOS DE BOTÃOS
    private class EventoConfirmarTarefa implements ActionListener, KeyListener {

        @Override
        public void actionPerformed(ActionEvent ae) {

            lblCreateTarefa.setVisible(true);
            txtCreateTarefa.setVisible(false);
            btnAdd.setVisible(true);
            btnConfirmar.setVisible(false);

            String txtTarefa = txtCreateTarefa.getText();

            if (txtTarefa.equals("")) {

            } else {
                criarTarefa();
            }

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int cod = e.getKeyCode();
            int tecla = KeyEvent.VK_ENTER;

            if (cod == tecla) {

                lblCreateTarefa.setVisible(true);
                txtCreateTarefa.setVisible(false);
                btnAdd.setVisible(true);
                btnConfirmar.setVisible(false);

                String txtTarefa = txtCreateTarefa.getText();

                if (txtTarefa.equals("")) {
                } else {
                    criarTarefa();
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }

    }

    private class EventoPerfil implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaPerfil().setVisible(true);

        }

    }

    private class EventoCheck implements ItemListener {

        @Override
        public void itemStateChanged(ItemEvent e) {

            int cont = 0;

            for (JCheckBox cb : cBoxs) {
                if (cb.isSelected()) {
                    tarefas.get(cont).setForeground(new Color(58, 133, 10));

                } else {
                    tarefas.get(cont).setForeground(Color.BLACK);
                }
                cont++;
            }
            concluido++;
        }

    }

    private class EventoCriarTarefa implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            EventoConfirmarTarefa evt = new EventoConfirmarTarefa();

            lblCreateTarefa.setVisible(false);
            btnAdd.setVisible(false);

            btnConfirmar = new JButton("Confirmar");
            btnConfirmar.setBackground(new Color(237, 236, 235));
            btnConfirmar.setFont(new Font("Arial", 0, 20));

            txtCreateTarefa = new JTextField();
            txtCreateTarefa.setFont(new Font("Arial", 0, 22));
            txtCreateTarefa.addKeyListener(evt);
            txtCreateTarefa.setBackground(new Color(243, 238, 233));
            txtCreateTarefa.setForeground(Color.BLACK.darker());
            txtCreateTarefa.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.black, Color.black.darker()));

            painelTarefas.add(txtCreateTarefa);
            txtCreateTarefa.setBounds(60, 60, 600, 35);
            txtCreateTarefa.requestFocus();

            painelTarefas.add(btnConfirmar);
            btnConfirmar.setBounds(700, 60, 150, 35);

            btnConfirmar.addActionListener(evt);
        }

    }

    private class PainelTarefas extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            g.setColor(new Color(168, 168, 168));
            g.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 35, 35);

        }

        @Override
        protected void paintBorder(Graphics g) {
            // TODO Auto-generated method stub
            super.paintBorder(g);
            g.setColor(Color.BLACK);
            g.drawRoundRect(0, 0, this.getWidth() - 1, this.getHeight() - 1, 35, 35);
        }
    }

    private class EventoBotaoAlterar implements ActionListener, KeyListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            alterarTarefa();
            
            txtAlter.setVisible(true);
            txtAlter.requestFocus();
            txtAlter.setText(lblTarefa.getText());
            txtAlter.setEditable(true);
            lblTarefa.setVisible(false);

            btnAlter.setVisible(false);
            btnX.setVisible(true);
            
            btnX.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    txtAlter.setVisible(false);
                    lblTarefa.setVisible(true);
                    
                    btnX.setVisible(false);
                    btnAlter.setVisible(true);
                }
            });

            txtAlter.addKeyListener(this);

        }

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int cod = e.getKeyCode();
            int tecla = KeyEvent.VK_ENTER;

            if (cod == tecla) {
                lblTarefa.setText(txtAlter.getText());
                txtAlter.setVisible(false);
                lblTarefa.setVisible(true);
                
                btnX.setVisible(false);
                btnAlter.setVisible(true);
            }

        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    // MÉTODOS AUXILIARES
    public void criarTarefa() {

        JCheckBox cb = new JCheckBox();

        EventoCheck evtCheck = new EventoCheck();

        YTAREFALBL += 45;
        YCBTAREFA += 45;
        YBTNREMOVE += 45;

        lblTarefa = new JLabel(txtCreateTarefa.getText());
        lblTarefa.setFont(new Font("Arial", 0, 20));
        lblTarefa.setForeground(Color.BLACK);
        tarefas.add(lblTarefa);

        btnAlter = new JButton();

        painelTarefas.add(lblTarefa);
        painelTarefas.add(btnAlter);

        lblTarefa.setBounds(XTAREFALBL, YTAREFALBL, 450, 30);

        cb.setBounds(XCBTAREFA, YCBTAREFA, 30, 30);
        cb.setBackground(new Color(168, 168, 168));

        btnAlter.setBounds(750, YBTNREMOVE, 35, 35);
        btnAlter.setFont(new Font("Arial", 0, 18));
        btnAlter.setIcon(new ImageIcon(getClass().getResource("/images/Alterar.png")));
        btnAlter.setBackground(new Color(168, 168, 168));
        btnAlter.setBorder(null);
        btnAlter.addActionListener(new EventoBotaoAlterar());

        XTAREFALBL = lblTarefa.getX();
        YTAREFALBL = lblTarefa.getY();

        XBTNRMOVE = btnAlter.getX();
        YBTNREMOVE = btnAlter.getY();

        XCBTAREFA = cb.getX();
        YCBTAREFA = cb.getY();
        cb.addItemListener(evtCheck);

        cBoxs.add(cb);
        cb.setBackground(new Color(168, 168, 168));

        alterarTarefa();

        painelTarefas.add(cb);
        tarefa++;
    }

    public void alterarTarefa() {
        txtAlter = new JTextField();
        txtAlter.setFont(new Font("Arial", 0, 22));
        txtAlter.setBackground(new Color(243, 238, 233));
        txtAlter.setForeground(Color.BLACK.darker());
        txtAlter.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.black, Color.black.darker()));

        txtAlter.setBounds(lblTarefa.getX(), lblTarefa.getY(), 600, lblTarefa.getHeight());
        txtAlter.requestFocus();
        txtAlter.setVisible(false);

        btnX = new JButton("X");
        btnX.setBounds(btnAlter.getX(), btnAlter.getY(), 30, 30);
        btnX.setFont(new Font("Arial", 0, 20));
        btnX.setBackground(new Color(168, 168, 168));
        btnX.setBorder(new BevelBorder(BevelBorder.LOWERED, Color.black, Color.black.darker()));
        btnX.setForeground(Color.black);
        btnX.setVisible(false);
        
        painelTarefas.add(btnX);
        painelTarefas.add(txtAlter);

    }

    public double porcentagem() {
        return (concluido / tarefa) * 100;
    }

    public static void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                telaDasTarefas.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaDasTarefas.runTela();
    }

}



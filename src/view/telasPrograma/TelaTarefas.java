package view.telasPrograma;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import model.bean.Task;
import model.bean.User;
import model.dao.TaskDAO;
import model.dao.UserDAO;
import net.miginfocom.swing.MigLayout;

import view.Main.BarraLateral;
import view.Main.Principal;
import view.Main.TelaPadraoFullScreen;

import view.perfil.TelaPerfil;

public class TelaTarefas extends TelaPadraoFullScreen {

    private User user = Principal.user;
    private PainelTarefas painelTarefas;
    private JPanel painelPrincipal, painelInternoPendente, painelExternoPendetes, painelExternoConc, painelInternoConc;
    private BarraLateral barraLateral;
    private JScrollPane painelPendente, painelConcluido, painelBackgroundTarefas;

    private HashMap<JButton, Task> pendentes = new HashMap<>();
    private HashMap<JButton, Task> botaosTarefas = new HashMap<>();

    private final TaskDAO daoTarefas = new TaskDAO();
    private UserDAO daoUser = new UserDAO();

    private JLabel lblAfazeres, lblCreateTarefa, lblTarefa;
    private JButton btnConcluido, btnPendente, btnAdd, btnFoto, btnConfirmar, btnAlter, btnPerfil, btnDesempenho, btnX, leave;
    private JTextField txtCreateTarefa, txtAlter, txtConcluido, txtPendente;
    private ArrayList<JCheckBox> cBoxs = new ArrayList<>();
    private ArrayList<Task> tarefas = new ArrayList<>();
    private ImageIcon iconeMarcado = new ImageIcon(getClass().getResource("/images/QuadradoMarcado.png"));
    private ImageIcon iconeNaoMarcado = new ImageIcon(getClass().getResource("/images/QuadradoNaoMarcado.png"));
    private static double tarefa = 0, concluido = 0;
    private static int numClicksConcluido = 1, numClicksPainelPendente = 1, numClicksPainelConcluido = 1;

    private boolean isCreatedTarefaPendente = false, hasComponentPendente = false, isOpenPendente = false;
    private boolean isCreatedTarefaConcluido = false, hasComponentConcluido = false, isOpenConcluido = false;
    private boolean runs = true;

    public static TelaTarefas telaDasTarefas;

    public static int XTAREFALBL = 90, XCBTAREFA = 60, XBTNRMOVE = 700;
    public static int YTAREFALBL = 60, YCBTAREFA = 60, YBTNREMOVE = 60;

    public TelaTarefas() {

        runTask();

        InserirIcone ic = new InserirIcone();
        ic.InserirIcone(this);

        painelPrincipal = new JPanel(null);
        painelPrincipal.setBackground(new Color(207, 227, 225));
        painelPrincipal.setBounds(0, 0, getWidth(), getHeight());
        add(painelPrincipal);

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painelPrincipal.add(barraLateral);

        generatePainelTarefas();

        lblAfazeres = new JLabel("SEUS AFAZERES");
        lblAfazeres.setFont(new Font("Arial", 1, 30));
        lblAfazeres.setBounds(550, 20, 400, 30);
        lblAfazeres.setForeground(Color.BLACK.darker());
        painelPrincipal.add(lblAfazeres);

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

        generateConcluido();

        generatePendente();
    }

    // EVENTOS DE BOTÃOS
    private class EventoConcluirTarefas implements ActionListener {

        JButton btnCheck, btnTarefa;

        public EventoConcluirTarefas(JButton btnCheck, JButton btnTarefa) {
            this.btnCheck = btnCheck;
            this.btnTarefa = btnTarefa;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (numClicksConcluido % 2 != 0) {
                btnCheck.setIcon(iconeMarcado);
                vefMarcado();
            } else {
                btnCheck.setIcon(iconeNaoMarcado);
                vefMarcado();
            }

            numClicksConcluido++;
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

    private void telaPendenteVisivel() {
        painelExternoPendetes = new JPanel(new MigLayout());
        painelExternoPendetes.add(painelPendente, "w 840, h 400");
        painelExternoPendetes.setBackground(new Color(168, 168, 168));
        painelExternoPendetes.setVisible(false);
        painelTarefas.add(painelExternoPendetes, "gapleft 20, gaptop 20, w 840, h 400, wrap");
    }

    private void metodoPainelPendente() {
        telaPendenteVisivel();
        btnPendente.setIcon(new ImageIcon(getClass().getResource("/images/setaBaixo.png")));
        painelExternoPendetes.setVisible(true);
    }

    private class EventoPainelPendente implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (numClicksPainelPendente % 2 != 0) {
                if (hasComponentPendente) {
                    metodoPainelPendente();
                    isOpenPendente = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Não possui tarefas", "ERROR", 0);
                }
            } else {
                try {
                    btnPendente.setIcon(new ImageIcon(getClass().getResource("/images/setaEsquerda.png")));
                    if (painelExternoPendetes != null) {
                        painelExternoPendetes.setVisible(false);
                        painelTarefas.remove(painelExternoPendetes);
                    }
                    isOpenPendente = false;
                } catch (NullPointerException ex) {
                    System.err.println("Não possui tarefas");
                }
            }
            numClicksPainelPendente++;
        }

    }

    private void telaConcVisivel() {
        painelExternoConc = new JPanel(new MigLayout());
        painelExternoConc.add(painelConcluido, "w 840, h 400");
        painelExternoConc.setBackground(new Color(168, 168, 168));
        painelExternoConc.setVisible(false);
        painelTarefas.add(painelExternoConc, "gapleft 20, gaptop 20, w 840, h 400, wrap");
        atualizaPendente();
    }

    private class EventoConcluido implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (numClicksPainelConcluido % 2 != 0) { // Se a quantidade click no botão for par = true
                if (hasComponentConcluido) {
                    btnConcluido.setIcon(new ImageIcon(getClass().getResource("/images/setaBaixo.png")));
                    telaConcVisivel();
                    isOpenConcluido = true;
                    painelExternoConc.setVisible(true);
                    if (isOpenPendente) {
                        metodoPainelPendente();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não possui tarefas concluidas", "ERROR", 0);
                }
            } else { // Se a quantidade de click no botão for impar = false

                btnConcluido.setIcon(new ImageIcon(getClass().getResource("/images/setaEsquerda.png")));
                if (painelExternoConc != null) {
                    painelExternoConc.setVisible(false);
                    painelTarefas.remove(painelExternoConc);
                }
            }

            numClicksPainelConcluido++;
        }

    }

    // MÉTODOS AUXILIARES
    private void generateTarefaConcluido() {

        if (!(isCreatedTarefaConcluido)) {
            runTask();
            for (Task tarefa1 : tarefas) {
                if (tarefa1.isConcluido()) {

                    JButton btnConcluidoTarefas = new JButton(iconeMarcado);
                    btnConcluidoTarefas.setBorder(null);
                    btnConcluidoTarefas.setBackground(painelInternoConc.getBackground());
                    painelInternoConc.add(btnConcluidoTarefas, "gapright 30");

                    JButton tarefasBtn = new JButton(tarefa1.getTitulo());
                    tarefasBtn.setFont(new Font("Arial", 0, 24));
                    tarefasBtn.setBorder(null);
                    tarefasBtn.setBackground(new Color(132, 132, 132));
                    tarefasBtn.setHorizontalAlignment(SwingConstants.LEFT);
                    tarefasBtn.setForeground(Color.white.brighter());

                    btnConcluidoTarefas.addActionListener(new EventoConcluirTarefas(btnConcluidoTarefas, tarefasBtn));

                    painelInternoConc.add(tarefasBtn, "gaptop 10, w 700, h 30, gapbottom 10, wrap ");

                }
            }
        }

        isCreatedTarefaConcluido = true;

        if (painelInternoConc.getComponentCount() != 0) {
            hasComponentConcluido = true;
        }

    }

    private void generateTarefaPendentes() {

        if (!(isCreatedTarefaPendente)) {
            runTask();
            for (Task tarefa : tarefas) {
                if (!(tarefa.isConcluido())) {
                    JButton btnConcluidoTarefas = new JButton(iconeNaoMarcado);
                    btnConcluidoTarefas.setBorder(null);
                    btnConcluidoTarefas.setBackground(painelInternoPendente.getBackground());
                    painelInternoPendente.add(btnConcluidoTarefas, "gapright 30");

                    JButton tarefasBtn = new JButton(tarefa.getTitulo());
                    tarefasBtn.setFont(new Font("Arial", 0, 24));
                    tarefasBtn.setBorder(null);
                    tarefasBtn.setBackground(new Color(132, 132, 132));
                    tarefasBtn.setHorizontalAlignment(SwingConstants.LEFT);
                    tarefasBtn.setForeground(Color.white.brighter());
                    painelInternoPendente.add(tarefasBtn, "gaptop 10, w 700, h 30, gapbottom 10, wrap ");

                    btnConcluidoTarefas.addActionListener(new EventoConcluirTarefas(btnConcluidoTarefas, tarefasBtn));

                    pendentes.put(btnConcluidoTarefas, tarefa);
                    botaosTarefas.put(tarefasBtn, tarefa);
                }
            }

        }
        isCreatedTarefaPendente = true;
        if (painelInternoPendente.getComponentCount() != 0) {
            hasComponentPendente = true;
        }

    }

    private void runTask() {
        if (tarefas.isEmpty()) {
            for (Task tarefa : daoTarefas.listarTaksTarefas(user)) {
                tarefas.add(tarefa);
            }
        } else {
            tarefas.clear();
            runTask();
        }
    }

    private void generatePendente() {

        txtPendente = new JTextField(" Pendente");
        txtPendente.setBackground(new Color(218, 217, 215));
        txtPendente.setFont(new Font("Arial", 1, 30));
        txtPendente.setBorder(new BarraLateral().getBorder());
        txtPendente.setEditable(false);
        painelTarefas.add(txtPendente, "gapleft 20, gaptop 20, w 830, h 30");

        EventoPainelPendente evtPendente = new EventoPainelPendente();
        btnPendente = new JButton();
        btnPendente.setBorder(null);
        btnPendente.setBackground(new Color(168, 168, 168));
        btnPendente.setIcon(new ImageIcon(getClass().getResource("/images/setaEsquerda.png")));
        btnPendente.setBounds(860, 90, 35, 35);
        btnPendente.addActionListener(evtPendente);
        painelTarefas.add(btnPendente, "gapleft 20, gaptop 20, wrap");

        painelInternoPendente = new JPanel(new MigLayout());
        painelInternoPendente.setBackground(new Color(168, 168, 168));
        generateTarefaPendentes();

        painelPendente = new JScrollPane(painelInternoPendente, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelPendente.setBorder(null);
        painelPendente.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.WHITE.brighter();
                this.trackColor = new Color(168, 168, 168).brighter();

            }
        });
        painelPendente.setBackground(new Color(168, 168, 168));
    }

    private void removePendente() {
        painelTarefas.remove(txtPendente);
        painelTarefas.remove(painelInternoPendente);
        painelTarefas.remove(painelPendente);
        painelTarefas.remove(btnPendente);
        if (painelExternoPendetes != null) {
            painelTarefas.remove(painelExternoPendetes);
        }
    }

    private void atualizaPendente() {
        removePendente();
        isCreatedTarefaPendente = false;
        generatePendente();
    }

    private void generateConcluido() {
        txtConcluido = new JTextField(" Concluído");
        txtConcluido.setFont(new Font("Arial", 1, 30));
        txtConcluido.setBackground(new Color(218, 217, 215));
        txtConcluido.setBorder(new BarraLateral().getBorder());
        txtConcluido.setEditable(false);
        painelTarefas.add(txtConcluido, " gaptop 20, gapleft 20, w 830, h 30");

        EventoConcluido evtConcluido = new EventoConcluido();
        btnConcluido = new JButton();
        btnConcluido.setBorder(null);
        btnConcluido.setBackground(new Color(168, 168, 168));
        btnConcluido.setIcon(new ImageIcon(getClass().getResource("/images/setaEsquerda.png")));
        btnConcluido.addActionListener(evtConcluido);
        btnConcluido.setSize(35, 35);
        painelTarefas.add(btnConcluido, "gapleft 20, gaptop 20, wrap");

        painelInternoConc = new JPanel(new MigLayout());
        painelInternoConc.setBackground(new Color(168, 168, 168));
        generateTarefaConcluido();

        painelConcluido = new JScrollPane(painelInternoConc, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        painelConcluido.setBorder(null);
        painelConcluido.getVerticalScrollBar().setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = Color.WHITE.brighter();
                this.trackColor = new Color(168, 168, 168).brighter();
            }

        });
        painelConcluido.setBackground(new Color(168, 168, 168));
    }

    private void vefMarcado() {

        for (JButton btn : pendentes.keySet()) {
            if (btn.getIcon() == iconeMarcado) {
                if (daoTarefas.updateTarefaConcluido(pendentes.get(btn))) {
                    System.out.println("Marcado concluido no BD");
                }
            } else if (btn.getIcon() == iconeNaoMarcado) {
                if (daoTarefas.updateTarefaNaoConcluido(pendentes.get(btn))) {
                    System.out.println("Desmarcado concluido no BD");
                }
            }
        }

    }

    private void generatePainelTarefas() {
        painelTarefas = new PainelTarefas();
        painelTarefas.setLayout(new MigLayout());
        painelTarefas.setBackground(new Color(207, 227, 225));
        painelTarefas.setSize(930, 600);

        painelBackgroundTarefas = new JScrollPane(painelTarefas);
        painelBackgroundTarefas.setBounds(315, 100, 960, 600);
        painelBackgroundTarefas.setBackground(new Color(207, 227, 225));
        painelBackgroundTarefas.setBorder(null);

        painelPrincipal.add(painelBackgroundTarefas);
    }

    // Runnables
    public static void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                telaDasTarefas = new TelaTarefas();
                if (telaDasTarefas.isActive()) {
                    telaDasTarefas.dispose();
                }

                telaDasTarefas.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaDasTarefas.runTela();
    }

}

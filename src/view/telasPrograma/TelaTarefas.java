package view.telasPrograma;

import java.awt.Color;
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
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
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
    
    private HashMap<JButton, JButton> pendentes = new HashMap<>();
    private HashMap<JButton, JButton> concluidos = new HashMap<>();
    
    private TaskDAO daoTarefas = new TaskDAO();
    private UserDAO daoUser = new UserDAO();
    
    private JLabel lblAfazeres, lblCreateTarefa, lblTarefa;
    private JButton btnConcluido, btnPendente, btnAdd, btnFoto, btnConfirmar, btnAlter, btnPerfil, btnDesempenho, btnX, leave;
    private JTextField txtCreateTarefa, txtAlter, txtConcluido, txtPendente;
    private ArrayList<JCheckBox> cBoxs = new ArrayList<>();
    private ArrayList<Task> tarefas = new ArrayList<>();
    private static double tarefa = 0, concluido = 0;
    
    private boolean isCreatedTarefaPendente = false, hasComponentPendente = false;
    private boolean isCreatedTarefaConcluido = false, hasComponentConcluido = false;
    
    public static TelaTarefas telaDasTarefas;
    
    public static int XTAREFALBL = 90, XCBTAREFA = 60, XBTNRMOVE = 700;
    public static int YTAREFALBL = 60, YCBTAREFA = 60, YBTNREMOVE = 60;
    
    public TelaTarefas() {
        
        for (Task tarefa : daoTarefas.listarTaksTarefas(user)) {
            tarefas.add(tarefa);
        }
        
        InserirIcone ic = new InserirIcone();
        ic.InserirIcone(this);
        
        painelPrincipal = new JPanel(null);
        painelPrincipal.setBackground(new Color(207, 227, 225));
        painelPrincipal.setBounds(0, 0, getWidth(), getHeight());
        add(painelPrincipal);
        
        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painelPrincipal.add(barraLateral);
        
        painelTarefas = new PainelTarefas();
        painelTarefas.setLayout(new MigLayout());
        painelTarefas.setBackground(new Color(207, 227, 225));
        painelTarefas.setSize(930, 600);
        painelBackgroundTarefas = new JScrollPane(painelTarefas);
        painelBackgroundTarefas.setBounds(315, 100, 960, 600);
        painelBackgroundTarefas.setBackground(new Color(207, 227, 225));
        painelBackgroundTarefas.setBorder(null);
        painelPrincipal.add(painelBackgroundTarefas);
        
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
        btnConcluido.addMouseListener(evtConcluido);
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
        btnPendente.addMouseListener(evtPendente);
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
        
        btnFoto = new JButton();
        btnFoto.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnFoto.setBackground(new Color(218, 217, 215));
        btnFoto.setBorder(null);
        btnFoto.setBounds(10, 10, 100, 75);
        painelPrincipal.add(btnFoto);
        
    }

    // EVENTOS DE BOTÃOS
    private class EventoConcluirTarefas implements MouseListener {
        
        int cont = 1;
        
        JButton btn;
        
        public EventoConcluirTarefas(JButton btn) {
            this.btn = btn;
        }
        
        @Override
        public void mouseClicked(MouseEvent e) {
            
            if (cont % 2 != 0) {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/QuadradoMarcado.png")));
                
                
            } else {
                btn.setIcon(new ImageIcon(getClass().getResource("/images/QuadradoNaoMarcado.png")));
            }
            
            cont++;
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
    
    private class EventoPerfil implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaPerfil().setVisible(true);
            
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
    
    private void telaPendenteVisivel() {
        painelExternoPendetes = new JPanel(new MigLayout());
        painelExternoPendetes.add(painelPendente, "w 840, h 400");
        painelExternoPendetes.setBackground(new Color(168, 168, 168));
        painelExternoPendetes.setVisible(false);
        painelTarefas.add(painelExternoPendetes, "gapleft 20, gaptop 20, w 840, h 400");
    }
    
    private class EventoPainelPendente implements MouseListener {
        
        int cont = 1;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (cont % 2 != 0) {
                if (hasComponentPendente) {
                    telaPendenteVisivel();
                    btnPendente.setIcon(new ImageIcon(getClass().getResource("/images/setaBaixo.png")));
                    painelExternoPendetes.setVisible(true);
                }else{
                    JOptionPane.showMessageDialog(null, "Não possui tarefas", "ERROR", 0);
                }
            } else {
                try{
                btnPendente.setIcon(new ImageIcon(getClass().getResource("/images/setaEsquerda.png")));
                painelExternoPendetes.setVisible(false);
                painelTarefas.remove(painelExternoPendetes);
                }catch(NullPointerException ex){
                    System.err.println("Não possui tarefas");
                }
            }
            
            cont++;
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
    
    private void telaConcVisivel() {
        painelExternoConc = new JPanel();
        painelExternoConc.add(painelConcluido, "w 840, h 400");
        painelExternoConc.setBackground(new Color(168, 168, 168));
        painelExternoConc.setVisible(false);
        painelTarefas.add(painelExternoConc, "gapleft 20, gaptop 20, w 840, h 400, wrap");
    }
    
    private class EventoConcluido implements MouseListener {
        
        int cont = 1;
        
        @Override
        public void mouseClicked(MouseEvent e) {
            if (cont % 2 != 0) {
                if (hasComponentConcluido) {
                    btnConcluido.setIcon(new ImageIcon(getClass().getResource("/images/setaBaixo.png")));
                    telaConcVisivel();
                    painelExternoConc.setVisible(true);
                    generateTarefaConcluido();
                }else{
                    JOptionPane.showMessageDialog(null, "Não possui tarefas concluidas", "ERROR", 0);
                }
            } else {
                try{
                btnConcluido.setIcon(new ImageIcon(getClass().getResource("/images/setaEsquerda.png")));
                painelTarefas.remove(painelExternoConc);
                }catch(NullPointerException ex){
                    System.err.println("Não possui tarefas concluidas");
                }
            }
            
            cont++;
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

// MÉTODOS AUXILIARES
    public void generateTarefaConcluido() {
        
        if (!(isCreatedTarefaConcluido)) {
            for (Task tarefa1 : tarefas) {
                if (tarefa1.isConcluido()) {
                    JButton btnConcluidoTarefas = new JButton(new ImageIcon(getClass().getResource("/images/QuadradoNaoMarcado.png")));
                    btnConcluidoTarefas.setBorder(null);
                    btnConcluidoTarefas.setBackground(painelInternoConc.getBackground());
                    btnConcluidoTarefas.addMouseListener(new EventoConcluirTarefas(btnConcluidoTarefas));
                    painelInternoConc.add(btnConcluidoTarefas, "gapright 30");
                    
                    JButton tarefasBtn = new JButton(tarefa1.getTitulo());
                    tarefasBtn.setFont(new Font("Arial", 0, 24));
                    tarefasBtn.setBorder(null);
                    tarefasBtn.setBackground(painelInternoConc.getBackground());
                    tarefasBtn.setForeground(Color.white.brighter());
                    btnConcluidoTarefas.addActionListener(new EventoBTNConc());
                    
                    painelInternoConc.add(tarefasBtn, "gaptop 10, pushx, gapbottom 10, wrap ");
                    
                    
                }
            }
        }
        
        isCreatedTarefaConcluido = true;
        
        if (painelInternoConc.getComponentCount() != 0) {
            hasComponentPendente = true;
        }
        
    }
    
    private class EventoBTNConc implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            for (JButton cBox : pendentes.keySet()) {
                
            }
        }
        
    }
    
    public void generateTarefaPendentes() {
        if (!(isCreatedTarefaPendente)) {
            for (Task tarefa : tarefas) {
                
                JButton btnConcluidoTarefas = new JButton(new ImageIcon(getClass().getResource("/images/QuadradoNaoMarcado.png")));
                btnConcluidoTarefas.setBorder(null);
                btnConcluidoTarefas.setBackground(painelInternoPendente.getBackground());
                btnConcluidoTarefas.addMouseListener(new EventoConcluirTarefas(btnConcluidoTarefas));
                painelInternoPendente.add(btnConcluidoTarefas, "gapright 30");
                
                JButton tarefasBtn = new JButton(tarefa.getTitulo());
                tarefasBtn.setFont(new Font("Arial", 0, 24));
                tarefasBtn.setBorder(null);
                tarefasBtn.setBackground(painelInternoPendente.getBackground());
                tarefasBtn.setForeground(Color.white.brighter());
                painelInternoPendente.add(tarefasBtn, "gaptop 10, pushx, gapbottom 10, wrap ");
                pendentes.put(btnConcluidoTarefas, tarefasBtn);
                
                
            }
            
        }
        isCreatedTarefaPendente = true;
        if (painelInternoPendente.getComponentCount() != 0) {
            hasComponentPendente = true;
        }
        
    }
    
    private void alterarTarefa() {
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

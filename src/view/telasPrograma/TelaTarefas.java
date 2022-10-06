package view.telasPrograma;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicScrollBarUI;
import model.bean.Task;
import model.bean.User;
import model.dao.TaskDAO;
import model.dao.UserDAO;
import net.miginfocom.swing.MigLayout;

import view.Main.BarraLateral;
import view.Main.Principal;
import view.Main.TelaPadraoFullScreen;

public class TelaTarefas extends TelaPadraoFullScreen {

    private User user = Principal.user;
    private PainelTarefas painelTarefas;
    private JPanel painelPrincipal, painelInternoPendente, painelExternoPendetes, painelExternoConc, painelInternoConc;
    private BarraLateral barraLateral;
    private JScrollPane painelPendente, painelConcluido, painelBackgroundTarefas;
    private PainelInfoTarefas painelInformaçõesTarefa;

    private HashMap<JButton, Task> pendentes = new HashMap<>();
    private HashMap<JButton, Task> botaosTarefas = new HashMap<>();
    private Point coordMouse = new Point();

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

    private class EventoMostrarInfoTarefas implements ActionListener {

        Task tarefa;

        public EventoMostrarInfoTarefas(Task tarefa) {
            this.tarefa = tarefa;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {

            painelInformaçõesTarefa = new PainelInfoTarefas(tarefa);
            painelInformaçõesTarefa.setBounds(600, 200, painelInformaçõesTarefa.getWidth(), painelInformaçõesTarefa.getHeight());
            painelBackgroundTarefas.setVisible(false);
            painelPrincipal.add(painelInformaçõesTarefa);
            painelPrincipal.revalidate();
            painelPrincipal.repaint();
        }

    }
    
    private class EventoCancelarInfoTarefas implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent ae) {
            painelPrincipal.remove(painelInformaçõesTarefa);
            painelBackgroundTarefas.setVisible(true);
            
            painelPrincipal.revalidate();
            painelPrincipal.repaint();
        }
     
    }

    private class EventoAlterarTarefa implements ActionListener {

        private Task tarefaAntiga;
        private TelaTarefas.PainelInfoTarefas painel = new TelaTarefas.PainelInfoTarefas();
        

        public EventoAlterarTarefa(Task tarefaAntiga) {
            this.tarefaAntiga = tarefaAntiga;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            int ok = JOptionPane.showConfirmDialog(null, "Deseja alterar a sua mensagem?", "Alterar Tarefa", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (ok == 0) {
                String titulo = painel.tituloTarefa.getText();
                String descricao = painel.descricaoTarefa.getText();
                String dataInicio = painel.dataInicioTarefa.getText();
                String dataFim = painel.dataFimTarefa.getText();
                String importante = painel.importanteTarefa.getText();
                boolean concluido = tarefaAntiga.isConcluido();
                
                boolean importanteBol = false;
                
                if(importante != "Sim" || importante != "Não"){
                    JOptionPane.showMessageDialog(null, "Preencha o campo importante com:\n \"Sim\" ou \"Não\"", "ERROR", JOptionPane.ERROR_MESSAGE);
                }
                
                if(importante.equalsIgnoreCase("Sim")){
                    importanteBol = true;
                }else if(importante.equalsIgnoreCase("Não")){
                    importanteBol = false;
                }
                
                Task tarefaNova = new Task(user, titulo, descricao, dataInicio, dataFim, importanteBol, concluido);
                if (daoTarefas.updateTarefa(tarefaAntiga, tarefaNova)) {
                    JOptionPane.showMessageDialog(null, "Tarefa alterada com sucesso", "Alterar Tarefa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        }

    }
    
    private class EventoExcluirTarefa implements ActionListener{

        private Task tarefa;

        public EventoExcluirTarefa(Task tarefa) {
            this.tarefa = tarefa;
        }
        
        @Override
        public void actionPerformed(ActionEvent ae) {
            int ok = JOptionPane.showConfirmDialog(null, "Deseja Excluir?", "Excluir Tarefa", JOptionPane.YES_NO_OPTION,
                                                   JOptionPane.PLAIN_MESSAGE);
            
            if(ok == 0){
                
                if(daoTarefas.deleteTarefa(tarefa)){
                    JOptionPane.showMessageDialog(null, "Tarefa deletada com sucesso", "Excluir Tarefa", JOptionPane.INFORMATION_MESSAGE);
                    
                    painelPrincipal.remove(painelInformaçõesTarefa);
                    painelBackgroundTarefas.setVisible(true);
                    
                    painelPrincipal.revalidate();
                    painelPrincipal.repaint();
                    
                }
            }
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
                    tarefasBtn.addActionListener(new EventoMostrarInfoTarefas(tarefa1));

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
                    tarefasBtn.addActionListener(new EventoMostrarInfoTarefas(tarefa));
                    painelInternoPendente.add(tarefasBtn, "gaptop 10, w 700, h 30, gapbottom 10, wrap ");

                    btnConcluidoTarefas.addActionListener(new EventoConcluirTarefas(btnConcluidoTarefas, tarefasBtn));

                    pendentes.put(btnConcluidoTarefas, tarefa);
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

    //Classes Internas Anonimas
    private class PainelInfoTarefas extends JPanel {

        Font arialRotulos = new Font("Arial", 1, 18);
        Font arialElementos = new Font("Arial", 0, 16);

        Task tarefa;

        JLabel titulo, descricao, dataInicio, dataFim, importante;
        JTextField tituloTarefa, dataInicioTarefa, dataFimTarefa, importanteTarefa;
        JTextArea descricaoTarefa;
        JButton btnAlterar, btnCancelar, btnExcluir;

        public PainelInfoTarefas(){
        }
        
        public PainelInfoTarefas(Task tarefa) {
            this.tarefa = tarefa;
            configPainel();
            configElementosPainel();
        }

        private void configPainel() {
            setSize(500, 410);
            setLayout(null);
            setBackground(new Color(187, 228, 224));
        }

        private void configElementosPainel() {
            titulo = new JLabel("Titulo");
            descricao = new JLabel("Descrição");
            dataInicio = new JLabel("Data de Inicio");
            dataFim = new JLabel("Data de Termino:");
            importante = new JLabel("É importante?");

            titulo.setFont(arialRotulos);
            descricao.setFont(arialRotulos);
            dataInicio.setFont(arialRotulos);
            dataFim.setFont(arialRotulos);
            importante.setFont(arialRotulos);

            titulo.setBounds(10, 10, 200, 30);
            descricao.setBounds(10, 80, 200, 30);
            dataInicio.setBounds(10, 180, 200, 30);
            dataFim.setBounds(10, 260, 200, 30);
            importante.setBounds(10, 340, 200, 30);

            add(titulo);
            add(descricao);
            add(dataInicio);
            add(dataFim);
            add(importante);

            tituloTarefa = new JTextField(tarefa.getTitulo());
            descricaoTarefa = new JTextArea(tarefa.getDescricao());
            dataInicioTarefa = new JTextField(tarefa.getDataInic());
            dataFimTarefa = new JTextField(tarefa.getDataFim());
            if (tarefa.isImportante()) {
                importanteTarefa = new JTextField("Sim");
            } else {
                importanteTarefa = new JTextField("Não");
            }

            tituloTarefa.setBorder(BorderFactory.createEmptyBorder());
            tituloTarefa.setBackground(this.getBackground().darker());
            descricaoTarefa.setBorder(BorderFactory.createEmptyBorder());
            descricaoTarefa.setBackground(this.getBackground().darker());
            dataInicioTarefa.setBorder(BorderFactory.createEmptyBorder());
            dataInicioTarefa.setBackground(this.getBackground().darker());
            dataFimTarefa.setBorder(BorderFactory.createEmptyBorder());
            dataFimTarefa.setBackground(this.getBackground().darker());
            importanteTarefa.setBorder(BorderFactory.createEmptyBorder());
            importanteTarefa.setBackground(this.getBackground().darker());

            tituloTarefa.setFont(arialElementos);
            descricaoTarefa.setFont(arialElementos);
            dataInicioTarefa.setFont(arialElementos);
            dataFimTarefa.setFont(arialElementos);
            importanteTarefa.setFont(arialElementos);

            tituloTarefa.setBounds(180, 10, 200, 30);
            descricaoTarefa.setBounds(180, 80, 200, 80);
            dataInicioTarefa.setBounds(180, 180, 200, 30);
            dataFimTarefa.setBounds(180, 260, 200, 30);
            importanteTarefa.setBounds(180, 340, 200, 30);

            add(tituloTarefa);
            add(descricaoTarefa);
            add(dataInicioTarefa);
            add(dataFimTarefa);
            add(importanteTarefa);

            btnAlterar = new JButton("Alterar");
            btnAlterar.setBackground(this.getBackground());
            btnAlterar.setBounds(10, 380, 225, 30);
            btnAlterar.addActionListener(new EventoAlterarTarefa(tarefa));
            add(btnAlterar);

            btnCancelar = new JButton("Cancelar");
            btnCancelar.setBackground(this.getBackground());
            btnCancelar.setBounds(260, 380, 225, 30);
            btnCancelar.addActionListener(new EventoCancelarInfoTarefas());
            add(btnCancelar);

            btnExcluir = new JButton(new ImageIcon(getClass().getResource("/images/ExcluirTarefa.png")));
            btnExcluir.setBackground(this.getBackground());
            btnExcluir.setBorder(BorderFactory.createEmptyBorder());
            btnExcluir.addActionListener(new EventoExcluirTarefa(tarefa));
            btnExcluir.setBounds(450, 0, 50, 50);
            add(btnExcluir);

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

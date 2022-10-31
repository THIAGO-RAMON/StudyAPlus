package view.telasPrograma;

import controller.UserController;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
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
import model.Task;
import model.User;
import dao.TaskDAO;
import dao.UserDAO;
import net.miginfocom.swing.MigLayout;

import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaTarefas extends TelaPadraoFullScreen {

    private User user = Principal.user;
    private PainelTarefas painelTarefas;
    private JPanel painelPrincipal, painelInternoPendente, painelExternoPendetes, painelExternoConc, painelInternoConc;
    private BarraLateral barraLateral;
    private JScrollPane painelPendente, painelConcluido, painelBackgroundTarefas;
    private PainelInfoTarefas painelInformaçõesTarefa;

    private HashMap<JButton, Task> pendentes = new HashMap<>();
    private HashMap<JButton, Task> concluidos = new HashMap<>();

    private final TaskDAO daoTarefas = new TaskDAO();
    private UserDAO daoUser = new UserDAO();

    private JLabel lblAfazeres;
    private JButton btnConcluido, btnPendente, leave;
    private JTextField txtConcluido, txtPendente;
    private ArrayList<JCheckBox> cBoxs = new ArrayList<>();
    private ArrayList<Task> tarefas = new ArrayList<>();
    private ImageIcon iconeMarcado = new ImageIcon(getClass().getResource("/images/QuadradoMarcado.png"));
    private ImageIcon iconeNaoMarcado = new ImageIcon(getClass().getResource("/images/QuadradoNaoMarcado.png"));
    private ImageIcon iconeSetaEsquerda = new ImageIcon(getClass().getResource("/images/setaEsquerda.png"));
    private ImageIcon iconeSetaBaixo = new ImageIcon(getClass().getResource("/images/setaBaixo.png"));

    private boolean isCreatedTarefaPendente = false, hasComponentPendente = false, isOpenPendente = false;
    private boolean isCreatedTarefaConcluido = false, hasComponentConcluido = false;

    public static TelaTarefas telaDasTarefas;
    
    private JButton teste;

    private UserController cc;

    public static int XTAREFALBL = 90, XCBTAREFA = 60, XBTNRMOVE = 700;
    public static int YTAREFALBL = 60, YCBTAREFA = 60, YBTNREMOVE = 60;

    public TelaTarefas() {

        runTask();

        TelaPadraoFullScreen.InserirIcone ic = new TelaPadraoFullScreen.InserirIcone();
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

        teste = new JButton("TESTE");
        teste.setBounds(painelPrincipal.getWidth()-150, 40, 150, 50);
        painelPrincipal.add(teste);
        
        generateConcluido();

        generatePendente();
    }

    // EVENTOS DE BOTÃOS
    private class EventoConcluirTarefas implements ActionListener {

        JButton btnCheck;

        public EventoConcluirTarefas(JButton btnCheck) {
            this.btnCheck = btnCheck;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (btnCheck.getIcon() == iconeNaoMarcado) {
                btnCheck.setIcon(iconeMarcado);
                vefMarcado();
                dispose();
                new TelaTarefas().runTela();
            } else if (btnCheck.getIcon() == iconeMarcado) {
                btnCheck.setIcon(iconeNaoMarcado);
                vefMarcado();
                dispose();
                new TelaTarefas().runTela();
            }

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
        painelExternoPendetes.setVisible(true);
    }

    private class EventoPainelPendente implements ActionListener {

        JButton btnSeta;

        public EventoPainelPendente(JButton btnSeta) {
            this.btnSeta = btnSeta;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (btnSeta.getIcon() == iconeSetaEsquerda) {
                if (hasComponentPendente) {
                    btnSeta.setIcon(iconeSetaBaixo);
                    metodoPainelPendente();
                    isOpenPendente = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Não possui tarefas", "ERROR", 0);
                }
            } else if (btnSeta.getIcon() == iconeSetaBaixo) {
                try {
                    btnSeta.setIcon(iconeSetaEsquerda);
                    if (painelExternoPendetes != null) {
                        painelExternoPendetes.setVisible(false);
                        painelTarefas.remove(painelExternoPendetes);
                    }
                    isOpenPendente = false;
                } catch (NullPointerException ex) {
                    System.err.println("Não possui tarefas");
                }
            }
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

        JButton btnSeta;

        public EventoConcluido(JButton btnSeta) {
            this.btnSeta = btnSeta;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (btnSeta.getIcon() == iconeSetaEsquerda) {
                if (hasComponentConcluido) {
                    btnSeta.setIcon(iconeSetaBaixo);
                    telaConcVisivel();
                    painelExternoConc.setVisible(true);
                    if (isOpenPendente) {
                        metodoPainelPendente();
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Não possui tarefas concluidas", "ERROR", 0);
                }
            } else if (btnSeta.getIcon() == iconeSetaBaixo) {

                btnSeta.setIcon(iconeSetaEsquerda);
                if (painelExternoConc != null) {
                    painelExternoConc.setVisible(false);
                    painelTarefas.remove(painelExternoConc);
                }
            }

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
            painelInformaçõesTarefa.setBounds(460, 120, painelInformaçõesTarefa.getWidth(), painelInformaçõesTarefa.getHeight());
            painelBackgroundTarefas.setVisible(false);
            painelPrincipal.add(painelInformaçõesTarefa);
            painelPrincipal.revalidate();
            painelPrincipal.repaint();
        }

    }

    private class EventoCancelarInfoTarefas implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            painelPrincipal.remove(painelInformaçõesTarefa);
            painelBackgroundTarefas.setVisible(true);

            runTask();

            painelPrincipal.revalidate();
            painelPrincipal.repaint();
        }

    }

    private class EventoAlterarTarefa implements ActionListener {

        private Task tarefaAntiga;
        private TelaTarefas.PainelInfoTarefas painel;
        private boolean isAlteravel = false;

        public EventoAlterarTarefa(Task tarefaAntiga, PainelInfoTarefas painel) {
            this.tarefaAntiga = tarefaAntiga;
            this.painel = painel;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            int ok = JOptionPane.showConfirmDialog(null, "Deseja alterar a sua tarefa?", "Alterar Tarefa", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);
            if (ok == 0) {
                String titulo = painel.tituloTarefa.getText();
                String descricao = painel.descricaoTarefa.getText();
                String dataInicio = painel.dataInicioTarefa.getText();
                String dataFim = painel.dataFimTarefa.getText();
                String importante = painel.importanteTarefa.getText();
                boolean concluido = tarefaAntiga.isConcluido();

                boolean importanteBol = false;

                importante = importante.trim();

                if (importante.equalsIgnoreCase("Sim")) {
                    importanteBol = true;
                    isAlteravel = true;
                } else if (importante.equalsIgnoreCase("Não")) {
                    importanteBol = false;
                    isAlteravel = true;
                } else {
                    JOptionPane.showMessageDialog(null, "Preencha o campo importante com:\n \"Sim\" ou \"Não\"", "ERROR", JOptionPane.ERROR_MESSAGE);
                    isAlteravel = false;
                }

                if (isAlteravel) {
                    Task tarefaNova = new Task(user, titulo, descricao, dataInicio, dataFim, importanteBol, concluido);

                    if (daoTarefas.updateTarefa(tarefaAntiga, tarefaNova)) {
                        JOptionPane.showMessageDialog(null, "Tarefa alterada com sucesso", "Alterar Tarefa", JOptionPane.INFORMATION_MESSAGE);
                        dispose();

                        runTask();

                        new TelaTarefas().runTela();

                        painelPrincipal.revalidate();
                        painelPrincipal.repaint();
                    }
                }
            }
        }

    }

    private class EventoExcluirTarefa implements ActionListener {

        private Task tarefa;

        public EventoExcluirTarefa(Task tarefa) {
            this.tarefa = tarefa;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            int ok = JOptionPane.showConfirmDialog(null, "Deseja Excluir?", "Excluir Tarefa", JOptionPane.YES_NO_OPTION,
                    JOptionPane.PLAIN_MESSAGE);

            if (ok == 0) {

                if (daoTarefas.deleteTarefa(tarefa)) {
                    JOptionPane.showMessageDialog(null, "Tarefa deletada com sucesso", "Excluir Tarefa", JOptionPane.INFORMATION_MESSAGE);

                    dispose();

                    runTask();

                    new TelaTarefas().runTela();

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

                    btnConcluidoTarefas.addActionListener(new EventoConcluirTarefas(btnConcluidoTarefas));

                    painelInternoConc.add(tarefasBtn, "gaptop 10, w 700, h 30, gapbottom 10, wrap ");

                    concluidos.put(btnConcluidoTarefas, tarefa1);

                }
            }
        }

        isCreatedTarefaConcluido = true;

        if (painelInternoConc.getComponentCount() != 0) {
            hasComponentConcluido = true;
        }

        atualizarPercentual();
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

                    btnConcluidoTarefas.addActionListener(new EventoConcluirTarefas(btnConcluidoTarefas));

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
            for (Task tarefa : daoTarefas.listarTarefas(user)) {
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

        btnPendente = new JButton();
        btnPendente.setBorder(null);
        btnPendente.setBackground(new Color(168, 168, 168));
        btnPendente.setIcon(iconeSetaEsquerda);
        btnPendente.setBounds(860, 90, 35, 35);
        EventoPainelPendente evtPendente = new EventoPainelPendente(btnPendente);
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

        btnConcluido = new JButton();
        btnConcluido.setBorder(null);
        btnConcluido.setBackground(new Color(168, 168, 168));
        btnConcluido.setIcon(iconeSetaEsquerda);
        EventoConcluido evtConcluido = new EventoConcluido(btnConcluido);
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
        try {
            Thread thread1, thread2;

            Runnable vefPendentes = new Runnable() {
                @Override
                public void run() {
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
            };

            Runnable vefConcluidos = new Runnable() {
                @Override
                public void run() {
                    for (JButton btn : concluidos.keySet()) {
                        if (btn.getIcon() == iconeMarcado) {
                            if (daoTarefas.updateTarefaConcluido(concluidos.get(btn))) {
                                System.out.println("Marcado concluido no BD");
                            }
                        } else if (btn.getIcon() == iconeNaoMarcado) {
                            if (daoTarefas.updateTarefaNaoConcluido(concluidos.get(btn))) {
                                System.out.println("Desmarcado concluido no BD");
                            }
                        }
                    }
                }
            };

            thread1 = new Thread(vefPendentes);
            thread2 = new Thread(vefConcluidos);
            if (!(pendentes.isEmpty())) {
                thread1.start();
                thread1.join();
            }

            if (!(concluidos.isEmpty())) {
                thread2.start();
                thread2.join();
            }
        } catch (InterruptedException ex) {
            JOptionPane.showMessageDialog(null, "Erro de Thread\n" + ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }

    }

    private void atualizarPercentual() {

        double porcentagem;
        cc = new UserController();

        if (!(concluidos.isEmpty())) {
            double qtdTarefasConcluida = concluidos.size();
            double qtdTarefas = tarefas.size();

            porcentagem = (qtdTarefasConcluida / qtdTarefas) * 100;

            if (cc.updateDesempenho(user, porcentagem)) {
            }

        } else {
            porcentagem = 0;
            if (cc.updateDesempenho(user, porcentagem));
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

        private Font arialRotulos = new Font("Arial", 1, 20);
        private Font arialElementos = new Font("Arial", 0, 18);
        private Color palavrasBrancas = Color.white.brighter();

        private Task tarefa;

        private JLabel titulo, descricao, dataInicio, dataFim, importante;
        private JTextField tituloTarefa, dataInicioTarefa, dataFimTarefa, importanteTarefa;
        private JTextArea descricaoTarefa;
        private JButton btnAlterar, btnCancelar, btnExcluir;

        private JScrollPane painelTextArea;

        private boolean isImportante = false;

        public PainelInfoTarefas() {
        }

        public PainelInfoTarefas(Task tarefa) {
            this.tarefa = tarefa;
            configPainel();
            configElementosPainel();
        }

        private void configPainel() {
            setSize(700, 550);
            setLayout(null);
            setBackground(new Color(168, 168, 168));
            setBorder(BorderFactory.createMatteBorder(3, 3, 3, 3, Color.BLACK));
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

            titulo.setForeground(palavrasBrancas);
            descricao.setForeground(palavrasBrancas);
            dataInicio.setForeground(palavrasBrancas);
            dataFim.setForeground(palavrasBrancas);
            importante.setForeground(palavrasBrancas);

            titulo.setBounds(10, 10, 300, 30);
            descricao.setBounds(10, 100, 300, 30);
            dataInicio.setBounds(10, 210, 300, 30);
            dataFim.setBounds(10, 300, 300, 30);
            importante.setBounds(10, 390, 300, 30);

            add(titulo);
            add(descricao);
            add(dataInicio);
            add(dataFim);
            add(importante);

            tituloTarefa = new JTextField(tarefa.getTitulo());
            descricaoTarefa = new JTextArea(tarefa.getDescricao().trim());
            dataInicioTarefa = new JTextField(tarefa.getDataInic());
            dataFimTarefa = new JTextField(tarefa.getDataFim());
            importanteTarefa = new JTextField();

            if (tarefa.isImportante()) {
                importanteTarefa.setText("Sim");
            } else if (!(tarefa.isImportante())) {
                importanteTarefa.setText("Não");
            }

            tituloTarefa.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.BLACK));
            tituloTarefa.setBackground(this.getBackground().darker());

            descricaoTarefa.setBorder(BorderFactory.createEmptyBorder());
            descricaoTarefa.setBackground(this.getBackground().darker());

            dataInicioTarefa.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.BLACK));
            dataInicioTarefa.setBackground(this.getBackground().darker());

            dataFimTarefa.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.BLACK));
            dataFimTarefa.setBackground(this.getBackground().darker());

            importanteTarefa.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.BLACK));
            importanteTarefa.setBackground(this.getBackground().darker());

            tituloTarefa.setFont(arialElementos);
            descricaoTarefa.setFont(arialElementos);
            dataInicioTarefa.setFont(arialElementos);
            dataFimTarefa.setFont(arialElementos);
            importanteTarefa.setFont(arialElementos);

            tituloTarefa.setForeground(palavrasBrancas);
            descricaoTarefa.setForeground(palavrasBrancas);
            dataInicioTarefa.setForeground(palavrasBrancas);
            dataFimTarefa.setForeground(palavrasBrancas);
            importanteTarefa.setForeground(palavrasBrancas);

            tituloTarefa.setBounds(250, 10, 300, 30);
            descricaoTarefa.setLineWrap(true);
            dataInicioTarefa.setBounds(250, 210, 300, 30);
            dataFimTarefa.setBounds(250, 300, 300, 30);
            importanteTarefa.setBounds(250, 390, 300, 30);

            painelTextArea = new JScrollPane(descricaoTarefa);
            painelTextArea.setBounds(250, 100, 330, 90);
            painelTextArea.setBackground(this.getBackground());
            painelTextArea.setBorder(BorderFactory.createMatteBorder(2, 3, 2, 3, Color.BLACK));
            painelTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

            add(tituloTarefa);
            add(painelTextArea);
            add(dataInicioTarefa);
            add(dataFimTarefa);
            add(importanteTarefa);

            btnAlterar = new JButton("Alterar");
            btnAlterar.setBackground(this.getBackground().darker());
            btnAlterar.setFont(new Font("Arial", 1, 24));
            btnAlterar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            btnAlterar.setBounds(30, 490, 300, 30);
            btnAlterar.setForeground(palavrasBrancas);
            btnAlterar.addActionListener(new EventoAlterarTarefa(tarefa, this));
            add(btnAlterar);

            btnCancelar = new JButton("Cancelar");
            btnCancelar.setBackground(this.getBackground().darker());
            btnCancelar.setBounds(355, 490, 300, 30);
            btnCancelar.setFont(new Font("Arial", 1, 24));
            btnCancelar.setForeground(palavrasBrancas);
            btnCancelar.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
            btnCancelar.addActionListener(new EventoCancelarInfoTarefas());
            add(btnCancelar);

            btnExcluir = new JButton(new ImageIcon(getClass().getResource("/images/ExcluirTarefa.png")));
            btnExcluir.setBackground(this.getBackground());
            btnExcluir.setBorder(BorderFactory.createEmptyBorder());
            btnExcluir.addActionListener(new EventoExcluirTarefa(tarefa));
            btnExcluir.setBounds(640, 10, 50, 50);
            btnExcluir.setToolTipText("Excluir Tarefa");
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

                TelaTarefas telaTarefasNova = new TelaTarefas();
                telaDasTarefas = telaTarefasNova;
                telaDasTarefas.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaDasTarefas.runTela();
    }

}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view.telasPrograma;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.AbstractBorder;
import model.Task;
import model.User;
import dao.TaskDAO;
import javax.swing.border.LineBorder;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;

/**
 *
 * @author Migas
 */
public class TelaCriarTarefa extends TelaPadraoFullScreen {

    public static TelaCriarTarefa telaCriarTarefa = new TelaCriarTarefa();

    private SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");

    private TaskDAO dao = new TaskDAO();
    private Task tarefa;
    public static Principal principal;

    private JPanel painel;
    private JButton btnImportante, btnConfirmar, btnLimpar, leave;
    private JLabel cabecalho, lblInforme, lblDescricao, lblTitulo, lblDataInicio, lblDataFim, lblImportante;
    private JTextField txtTitulo, txtDataInicio, txtDataFim;
    private JTextArea txtDescricao;
    private PainelCriar painelCriar;
    private BarraLateral barraLateral;
    private Color colorTxtField = new Color(255, 255, 255);
    private Font sansSerif = new Font("SansSerif", 0, 20);
    private EventoTxtChangeColor eventoTxtDestaque = new EventoTxtChangeColor();
    private boolean isImportante = false;

    public TelaCriarTarefa() {
        configPanel();
        add(painel);

        cabecalho = new JLabel("CRIE SUA TAREFA");
        cabecalho.setBounds(540, 20, 400, 30);
        cabecalho.setFont(new Font("Arial", 1, 30));
        cabecalho.setForeground(Color.black.darker());
        painel.add(cabecalho);

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel.add(barraLateral);

        painelCriar = new PainelCriar();
        painelCriar.setBounds(320, 100, 930, 600);
        painelCriar.setBackground(painel.getBackground());
        painelCriar.setLayout(null);
        painel.add(painelCriar);

        lblInforme = new JLabel("Informe a sua tarefa nos campos a seguir");
        lblInforme.setFont(new Font("Arial", 1, 30));
        lblInforme.setForeground(Color.BLACK.darker());
        lblInforme.setBounds(150, 10, 700, 40);
        painelCriar.add(lblInforme);

        lblTitulo = new JLabel("Escreva o titulo de sua tarefa *");
        lblTitulo.setFont(sansSerif);
        lblTitulo.setForeground(Color.BLACK.darker());
        lblTitulo.setBounds(40, 90, 300, 30);
        painelCriar.add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setFont(sansSerif);
        txtTitulo.setBackground(colorTxtField);
        txtTitulo.setBounds(40, 130, 600, 30);
        txtTitulo.addMouseListener(eventoTxtDestaque);
        txtTitulo.setBorder(new BordaTextField());
        painelCriar.add(txtTitulo);

        lblDescricao = new JLabel("Escreva a descrição sua tarefa");
        lblDescricao.setFont(sansSerif);
        lblDescricao.setForeground(Color.BLACK.darker());
        lblDescricao.setBounds(40, 170, 300, 30);
        painelCriar.add(lblDescricao);

        txtDescricao = new JTextArea(3, 10);
        txtDescricao.setLineWrap(true);
        txtDescricao.setFont(sansSerif);
        txtDescricao.setBackground(colorTxtField);
        txtDescricao.setBounds(40, 210, 600, 120);
        txtDescricao.addMouseListener(eventoTxtDestaque);
        txtDescricao.setBorder(new BordaTextField());
        painelCriar.add(txtDescricao);

        lblDataInicio = new JLabel("Começa em: ");
        lblDataInicio.setFont(sansSerif);
        lblDataInicio.setForeground(Color.black.darker());
        lblDataInicio.setBounds(40, 350, 300, 30);
        painelCriar.add(lblDataInicio);

        txtDataInicio = new JTextField();
        txtDataInicio.setFont(sansSerif);
        txtDataInicio.setBackground(colorTxtField);
        txtDataInicio.setBounds(165, 350, 150, 30);
        txtDataInicio.setBorder(new BordaTextField());
        txtDataInicio.addMouseListener(new EventoTxtChangeColor());
        painelCriar.add(txtDataInicio);

        lblDataFim = new JLabel("Termina em: ");
        lblDataFim.setFont(sansSerif);
        lblDataFim.setForeground(Color.black.darker());
        lblDataFim.setBounds(350, 350, 300, 30);
        painelCriar.add(lblDataFim);

        txtDataFim = new JTextField();
        txtDataFim.setFont(sansSerif);
        txtDataFim.setBackground(colorTxtField);
        txtDataFim.setBounds(475, 350, 150, 30);
        txtDataFim.setBorder(new BordaTextField());
        txtDataFim.addMouseListener(new EventoTxtChangeColor());
        painelCriar.add(txtDataFim);

        lblImportante = new JLabel("É importante? ");
        lblImportante.setFont(sansSerif);
        lblImportante.setForeground(Color.black.darker());
        lblImportante.setBounds(40, 430, 300, 30);
        painelCriar.add(lblImportante);

        btnImportante = new JButton(new ImageIcon(getClass().getResource("/images/CirculoNaoMarcado.png")));
        btnImportante.setFont(sansSerif);
        btnImportante.setBorder(null);
        btnImportante.addMouseListener(new EventoImportante());
        btnImportante.setBackground(new Color(168, 168, 168));
        btnImportante.setBounds(190, 420, 50, 50);
        painelCriar.add(btnImportante);

        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.setFont(sansSerif);
        btnConfirmar.setBackground(colorTxtField);
        btnConfirmar.setBounds(40, 530, 200, 40);
        btnConfirmar.addMouseListener(new EventoTxtChangeColor());
        btnConfirmar.addActionListener(new EventoConfirmar());
        btnConfirmar.setBorder(new BordaTextField());
        painelCriar.add(btnConfirmar);

        btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(sansSerif);
        btnLimpar.setBackground(colorTxtField);
        btnLimpar.addMouseListener(new EventoTxtChangeColor());
        btnLimpar.addActionListener(new EventoLimpar());
        btnLimpar.setBorder(new BordaTextField());
        btnLimpar.setBounds(340, 530, 200, 40);
        painelCriar.add(btnLimpar);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        leave.setBounds(painel.getWidth() - 60, 0, 60, 30);
        painel.add(leave);
    }

    private void confirmar() {

        User user = principal.user;

        String titulo = "";
        String descricao = "";
        String dataInicio;
        String dataFim;

        boolean importante = isImportante;

        if (txtTitulo.getText() == "" || txtDataInicio.getText() == "" || txtDataFim.getText() == "") {
            JOptionPane.showMessageDialog(null, "Preencha o Campos *", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else if (vefData(txtDataFim) == false || vefData(txtDataInicio) == false) {
            JOptionPane.showMessageDialog(null, "Datas Incompatives", "ERROR", JOptionPane.ERROR_MESSAGE);
        } else {

            titulo = txtTitulo.getText();
            descricao = txtDescricao.getText();
            dataInicio = txtDataInicio.getText();
            dataFim = txtDataFim.getText();

            Task tarefa = new Task(user, titulo, descricao, dataInicio, dataFim, importante, false);

            if (dao.saveTarefa(tarefa)) {
                JOptionPane.showMessageDialog(null, "Tarefa adicionada com sucesso", "Criar Tarefa", JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new TelaTarefas().runTela();
            }
        }

    }

    private boolean vefData(JTextField txtData) {

        String data = txtData.getText();
        String auxiliar = "";
        boolean isRight = false;

        if (data.length() == 10) {
            isRight = true;
        }else{
            isRight = false;
        }

        if (isRight) {
            if (data.charAt(2) == '/' && data.charAt(5) == '/') {
                isRight = true;
            }else{
                isRight = false;
            }
        }

        return isRight;
    }

    private class EventoLimpar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtTitulo.setText("");
            txtDescricao.setText("");
            txtDataInicio.setText("");
            txtDataFim.setText("");
        }
    }

    private class EventoConfirmar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            confirmar();
        }

    }

    private class EventoImportante implements MouseListener {

        int cont = 1;

        @Override
        public void mouseClicked(MouseEvent me) {
            if (cont % 2 != 0) {
                isImportante = true;
                btnImportante.setIcon(new ImageIcon(getClass().getResource("/images/CirculoMarcado.png")));
            } else {
                isImportante = false;
                btnImportante.setIcon(new ImageIcon(getClass().getResource("/images/CirculoNaoMarcado.png")));
            }

            cont++;
        }

        @Override
        public void mousePressed(MouseEvent me) {
        }

        @Override
        public void mouseReleased(MouseEvent me) {
        }

        @Override
        public void mouseEntered(MouseEvent me) {
            me.getComponent().setBackground(me.getComponent().getBackground().darker());
        }

        @Override
        public void mouseExited(MouseEvent me) {
            me.getComponent().setBackground(new Color(168, 168, 168));
        }

    }

    private class EventoTxtChangeColor implements MouseListener {

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
            e.getComponent().setBackground(e.getComponent().getBackground().darker());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            e.getComponent().setBackground(e.getComponent().getBackground().brighter());
        }

    }

    private void configPanel() {

        painel = new JPanel();
        painel.setSize(getSize());
        painel.setBorder(new LineBorder(Color.BLACK.darker(),1,true));
        painel.setLayout(null);
        painel.setBackground(new Color(207, 227, 225));

    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (telaCriarTarefa.isVisible()) {
                    telaCriarTarefa.dispose();
                }
                
                TelaCriarTarefa telaCriarNova = new TelaCriarTarefa();
                telaCriarTarefa = telaCriarNova;
                telaCriarTarefa.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new TelaCriarTarefa().runTela();
    }

    private class PainelCriar extends JPanel {

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

    private class BordaTextField extends AbstractBorder {

        private final BasicStroke contorno = new BasicStroke(4);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height); //To change body of generated methods, choose Tools | Templates.

            Graphics2D g2d = (Graphics2D) g;

            g2d.setStroke(contorno);
            g2d.drawRoundRect(x, y, width + 1, height + 1, 10, 10);
            g.clearRect(x, y, 1, 1);

        }

    }

}

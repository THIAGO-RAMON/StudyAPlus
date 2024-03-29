package view.objetivo;

import controller.ObjetivoController;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.AbstractBorder;
import dao.ObjetivoDAO;
import model.Objetivo;
import model.User;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;

public class VisualizarObjetivos extends JFrame {

    private TelaVisualizar telaVisualizar;
    private JPanel painel1;
    private String colunasLivros[] = {"Objetivos", "Data"};
    private String dados[][] = {};
    private JTable tabela;
    private JButton btnPes, leave;
    private JTextField txtPes;
    private JLabel lblObj, lblTitulo;
    public static VisualizarObjetivos visualizarObjetivos;
    private DefaultTableModel modeloTabelaObjetivos;
    private JScrollPane js;
    private ObjetivoDAO dao = new ObjetivoDAO();
    private Objetivo objetivo = new Objetivo();
    private User user = Principal.user;
    private int cont = 0;
    private BarraLateral barraLateral;
    private PainelCriar painelCriar;

    private ObjetivoController cc;

    public VisualizarObjetivos() {
        config();
        painel();

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        lblTitulo = new JLabel("Objetivos");
        lblTitulo.setFont(new Font("Arial", 1, 32));
        lblTitulo.setBounds((int) (painel1.getWidth() / 2) + 50, 10, 300, 30);
        painel1.add(lblTitulo);

        telaVisualizar = new TelaVisualizar();

        modeloTabelaObjetivos = new DefaultTableModel(dados, colunasLivros) {
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        tabela = new JTable(modeloTabelaObjetivos);
        tabela.addMouseListener(new EventoMouse());
        tabela.setFont(new Font("Arial", 0, 14));

        js = new JScrollPane(tabela);
        painel1.add(js);

        JScrollPane painelScrollado = new JScrollPane(tabela);
        painelScrollado.setBounds(350, 350, 900, 350);
        painelScrollado.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painel1.add(painelScrollado);
        objetivosTabela();

        lblObj = new JLabel("Seus objetivos");
        lblObj.setFont(new Font("Arial", 1, 20));
        lblObj.setBounds(350, 315, 200, 30);
        painel1.add(lblObj);

        txtPes = new JTextField();
        txtPes.setFont(new Font("Arial", 1, 15));
        txtPes.setBounds(475, 320, 400, 23);
        txtPes.setVisible(false);
        txtPes.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        txtPes.addKeyListener(new EventoPesquisar2());
        painel1.add(txtPes);

        EventoPesquisar evtPes = new EventoPesquisar();
        btnPes = new JButton("Pesquisar");
        btnPes.setFont(new Font("Arial", 1, 15));
        btnPes.setBounds(960, 320, 130, 23);
        btnPes.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        btnPes.setBackground(painel1.getBackground());
        btnPes.addActionListener(evtPes);
        painel1.add(btnPes);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.setBounds(painel1.getWidth() - 60, 0, 60, 30);
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        painel1.add(leave);
        generatePanel();

    }

    private void objetivosTabela() {
        cc = new ObjetivoController();

        for (Objetivo o : cc.listObj(user)) {
            modeloTabelaObjetivos.addRow(new Object[]{o.getDescricao(), o.getDataInic()});
        }
    }

    private class EventoPesquisar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Thread.yield();
            txtPes.setVisible(true);
            Runnable run = new Runnable() {

                @Override
                public void run() {
                    try {
                        int contx = txtPes.getX();

                        while (txtPes.getX() != painel1.getWidth() - 750) {
                            txtPes.setBounds(contx, txtPes.getY(), txtPes.getWidth(), txtPes.getHeight());
                            contx++;
                            Thread.sleep(7);
                        }
                    } catch (InterruptedException ex) {
                        System.err.println(ex);
                    }
                }
            };

            Thread mover = new Thread(run);
            mover.start();
        }

    }

    private class EventoPesquisar2 implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int cod = e.getKeyCode();
            int tecla = KeyEvent.VK_ENTER;

            if (cod == tecla) {
                String nome = txtPes.getText().trim();

                if (nome.length() > 0) {

                    for (int i = 0; i < modeloTabelaObjetivos.getRowCount(); i++) {
                        if (nome.equals(modeloTabelaObjetivos.getValueAt(i, 0))) {
                            telaVisualizar.txtNome.setText(Principal.user.getNome());
                            telaVisualizar.txtObj.setText(modeloTabelaObjetivos.getValueAt(i, 0).toString());
                            telaVisualizar.txtData.setText(modeloTabelaObjetivos.getValueAt(i, 1).toString());

                            objetivo.setUser(Principal.user);
                            objetivo.setDescricao(modeloTabelaObjetivos.getValueAt(i, 0).toString());
                            objetivo.setDataInic(modeloTabelaObjetivos.getValueAt(i, 1).toString());
                            telaVisualizar.setVisible(true);
                            break;
                        }
                    }

                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }
    }

    private class EventoMouse implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {

            if (e.getClickCount() == 2 && !e.isConsumed()) {
                e.consume();
                int linha = tabela.getSelectedRow();

                objetivo.setUser(Principal.user);
                objetivo.setDescricao(modeloTabelaObjetivos.getValueAt(linha, 0).toString());
                objetivo.setDataInic(modeloTabelaObjetivos.getValueAt(linha, 1).toString());

                telaVisualizar.txtNome.setText(Principal.user.getNome());
                telaVisualizar.txtObj.setText(modeloTabelaObjetivos.getValueAt(linha, 0).toString());
                telaVisualizar.txtData.setText(modeloTabelaObjetivos.getValueAt(linha, 1).toString());
                telaVisualizar.setVisible(true);
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
            tabela.setSelectionBackground(Color.gray.brighter());
        }

        @Override
        public void mouseExited(MouseEvent e) {
            tabela.setSelectionBackground(Color.white);

        }

    }

    private class TelaVisualizar extends JFrame {

        private JPanel painel;
        private JButton leave, btnVoltar, btnAlterar;
        private JTextField txtNome, txtData, txtObj;
        private JLabel lblNome, lblData, lblObj, lblTitulo;

        public TelaVisualizar() {
            configFrame();

            leave = new JButton("X");
            leave.setBackground(new Color(223, 63, 16));
            leave.setBounds(painel.getWidth() - 55, 0, 55, 20);
            leave.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    dispose();
                }
            });

            painel.add(leave);

            txtNome = new JTextField(Principal.user.getNome());
            txtNome.setBounds(82, 85, 300, 30);
            txtNome.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            txtNome.setEditable(false);
            painel.add(txtNome);

            txtObj = new JTextField();
            txtObj.setBounds(82, 155, 300, 30);
            txtObj.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            painel.add(txtObj);

            txtData = new JTextField();
            txtData.setBounds(82, 225, 300, 30);
            txtData.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            painel.add(txtData);

            txtNome.setFont(new Font("Arial", 0, 15));
            txtObj.setFont(new Font("Arial", 0, 15));
            txtData.setFont(new Font("Arial", 0, 15));

            lblTitulo = new JLabel("Detalhes do objetivo");
            lblTitulo.setFont(new Font("Arial", 1, 20));
            lblTitulo.setBounds(138, 5, 200, 30);
            painel.add(lblTitulo);

            lblNome = new JLabel("Nome");
            lblNome.setFont(new Font("Arial", 1, 15));
            lblNome.setBounds(82, 60, 120, 30);
            painel.add(lblNome);

            lblObj = new JLabel("Objetivo");
            lblObj.setFont(new Font("Arial", 1, 15));
            lblObj.setBounds(82, 130, 120, 30);
            painel.add(lblObj);

            lblData = new JLabel("Data estabelecida");
            lblData.setFont(new Font("Arial", 1, 15));
            lblData.setBounds(82, 200, 150, 30);
            painel.add(lblData);

            btnVoltar = new JButton("Deletar");
            btnVoltar.setBackground(painel.getBackground());
            btnVoltar.setBounds(255, 300, 125, 25);
            btnVoltar.addActionListener(new EventoVoltar(objetivo));
            painel.add(btnVoltar);

            btnAlterar = new JButton("Alterar");
            btnAlterar.setBackground(painel.getBackground());
            btnAlterar.setBounds(82, 300, 125, 25);
            btnAlterar.addActionListener(new EventoAlterar(objetivo));
            painel.add(btnAlterar);

            painel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        }

        private class EventoVoltar implements ActionListener {

            Objetivo obj;

            public EventoVoltar(Objetivo obj) {
                this.obj = obj;
            }

            @Override
            public void actionPerformed(ActionEvent e) {

                int i = JOptionPane.showConfirmDialog(null, "Deseja realmente excluir?", "Objetivo", JOptionPane.WARNING_MESSAGE);
                cc = new ObjetivoController();
                if (i == 0) {
                    if (cc.deleteObjetivo(objetivo)) {

                        JOptionPane.showMessageDialog(null, "Objetivo deletado com sucesso", "Objetivos", JOptionPane.INFORMATION_MESSAGE);
                        painel1.repaint();
                        painel1.revalidate();
                        visualizarObjetivos.dispose();
                        new VisualizarObjetivos().runTela();
                        dispose();
                    }
                }
            }

        }

        private class EventoAlterar implements ActionListener {

            Objetivo antigoObj;

            public EventoAlterar(Objetivo antigObjetivo) {
                this.antigoObj = antigObjetivo;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                cc = new ObjetivoController();
                Objetivo novoObj = new Objetivo(user, txtObj.getText(), txtData.getText());

                if (cc.updateObj(antigoObj, novoObj)) {
                    JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso\n", "Objetivos", JOptionPane.INFORMATION_MESSAGE);
                }

                painel1.repaint();
                painel1.revalidate();
                visualizarObjetivos.dispose();
                new VisualizarObjetivos().runTela();
                dispose();

            }

        }

        private void configFrame() {
            setSize(500, 350);
            setLocationRelativeTo(null);
            setUndecorated(true);
            setLayout(null);

            painel = new JPanel(null);
            painel.setBackground(new Color(207, 227, 225));
            painel.setSize(this.getSize());
            add(painel);
        }

    }

    private class PainelCriar extends JPanel {

        private JTextArea txtObj;
        private JTextField txtData;
        private JLabel lblTitulo, lblData, lblObj, lblFormato;
        private JScrollPane jsp;
        private JButton btnLimpar, btnCriar;

        public PainelCriar() {
            setSize(450, 600);
            setLayout(null);
            configEl();

        }

        private void configEl() {
            txtObj = new JTextArea();
            txtObj.setFont(new Font("Arial", 0, 15));
            jsp = new JScrollPane(txtObj, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            jsp.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            jsp.setBounds(65, 80, 350, 150);
            add(jsp);

            lblTitulo = new JLabel("O que deseja alcançar? Tente Explorar seus objetivos com base em seu calendário.");
            lblTitulo.setFont(new Font("Arial", 1, 18));
            lblTitulo.setBounds(65, 5, 1150, 30);
            add(lblTitulo);

            lblObj = new JLabel("Coloque seus objetivos aqui!");
            lblObj.setFont(new Font("Arial", 1, 15));
            lblObj.setBounds(66, 50, 300, 30);
            add(lblObj);

            lblData = new JLabel("Data do objetivo");
            lblData.setFont(new Font("Arial", 1, 15));
            lblData.setBounds(600, 50, 300, 30);
            add(lblData);

            lblFormato = new JLabel("Formato(DD/MM/AAAA)");
            lblFormato.setFont(new Font("Arial", 1, 10));
            lblFormato.setBounds(605, 100, 300, 30);
            add(lblFormato);

            txtData = new JTextField();
            txtData.setFont(new Font("Arial", 0, 15));
            txtData.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            txtData.setBounds(600, 80, 123, 23);
            add(txtData);

            btnCriar = new JButton("Criar");
            btnCriar.setFont(new Font("Arial", 0, 12));
            btnCriar.setBackground(new Color(168, 168, 168));
            btnCriar.setForeground(Color.BLACK);
            btnCriar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            btnCriar.addActionListener(new EventoCriar());
            btnCriar.setBounds(525, 200, 100, 25);
            add(btnCriar);

            btnLimpar = new JButton("Limpar");
            btnLimpar.setFont(new Font("Arial", 0, 12));
            btnLimpar.setBounds(715, 200, 100, 25);
            btnLimpar.setForeground(Color.BLACK);
            btnLimpar.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
            btnLimpar.addActionListener(new EventoLimpar());
            btnLimpar.setBackground(new Color(168, 168, 168));
            add(btnLimpar);

        }

        private class EventoLimpar implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                txtObj.setText("");
                txtData.setText("");
            }
        }

        private class EventoCriar implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                saveObjetivo();
            }

        }

        private void saveObjetivo() {
            cc = new ObjetivoController();
            if (vefData(txtData)) {
                if (txtObj.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira algum texto!", "Objetivos", 0);
                } else if (txtData.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, insira alguma data!", "Objetivos", 0);

                } else if (Principal.user.getNome() != null) {

                    objetivo = new Objetivo();
                    objetivo.setUser(Principal.user);
                    objetivo.setDescricao(txtObj.getText());
                    objetivo.setDataInic(txtData.getText());

                    cc.saveObj(Principal.user, txtObj.getText(), txtData.getText());
                    JOptionPane.showMessageDialog(null, "Objetivo salvo com sucesso!", "Objetivos", JOptionPane.INFORMATION_MESSAGE);

                    txtObj.setText("");
                    txtData.setText("");

                    painel1.revalidate();
                    painel1.repaint();
                    visualizarObjetivos.dispose();
                    new VisualizarObjetivos().runTela();
                } else {
                    JOptionPane.showMessageDialog(null, "Nenhum usuário cadastrado", "Criar Objetivo", 0);
                }

            } else {
                JOptionPane.showMessageDialog(null, "Dados da Data Incompatíveis", "Criar Objetivos", 0);
            }

        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); //To change body of generated methods, choose Tools | Templates.
            g.setColor(new Color(168, 168, 168));
            g.fillRoundRect(0, 0, 900, 250, 30, 30);
        }

    }

    private class BordaPainelCriar extends AbstractBorder {

        private BasicStroke contorno = new BasicStroke(1);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            super.paintBorder(c, g, x, y, width, height); //To change body of generated methods, choose Tools | Templates.

            Graphics2D g2d = (Graphics2D) g;

            g2d.setColor(Color.black);
            g2d.setStroke(contorno);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 30, 30);

        }

    }

    private void config() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1280, 720);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    private void painel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
        painel1.setBackground(new Color(207, 227, 225));
        add(painel1);
    }

    private void generatePanel() {
        painelCriar = new PainelCriar();
        painelCriar.setBounds(350, 50, 900, 250);
        painelCriar.setBorder(new BordaPainelCriar());
        painelCriar.setBackground(painel1.getBackground());
        painel1.add(painelCriar);

    }

    private boolean vefData(JTextField txtData) {

        String data = txtData.getText();
        String auxiliar = "";
        boolean isRight = false;

        if (data.length() == 10) {
            isRight = true;
        } else {
            isRight = false;
        }

        if (isRight) {
            if (data.charAt(2) == '/' && data.charAt(5) == '/') {
                isRight = true;
            } else {
                isRight = false;
            }
        }

        return isRight;
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                visualizarObjetivos = new VisualizarObjetivos();
                visualizarObjetivos.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new VisualizarObjetivos().runTela();
    }

}

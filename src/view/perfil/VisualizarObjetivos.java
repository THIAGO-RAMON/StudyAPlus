package view.perfil;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import model.dao.ObjetivoDAO;
import model.bean.Objetivo;
import model.bean.User;
import view.Main.BarraLateral;
import view.Main.Principal;

public class VisualizarObjetivos extends JFrame {

    private TelaVisualizar telaVisualizar;
    private JPanel painel1;
    private String colunasLivros[] = {"Objetivos", "Data de Início"};
    private String dados[][] = {};
    private JTable tabela;
    private JButton btnPes, leave;
    private JTextField txtPes;
    private JLabel lblObj;
    private static VisualizarObjetivos visualizarObjetivos;
    private DefaultTableModel modeloTabelaObjetivos;
    private ObjetivoDAO dao;
    private Objetivo objetivo = new Objetivo();
    private User user = Principal.user;
    private int cont = 0;
    private BarraLateral barraLateral;

    public VisualizarObjetivos() {
        config();
        painel();

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        telaVisualizar = new TelaVisualizar();

        modeloTabelaObjetivos = new DefaultTableModel(dados, colunasLivros);
        tabela = new JTable(modeloTabelaObjetivos);

        JScrollPane painelScrollado = new JScrollPane(tabela);
        painelScrollado.setBounds(350, 200, 900, 400);
        painel1.add(painelScrollado);
        objetivosTabela();

        lblObj = new JLabel("Seus objetivos");
        lblObj.setFont(new Font("Arial", 1, 20));
        lblObj.setBounds(350, 170, 200, 30);
        painel1.add(lblObj);

        txtPes = new JTextField();
        txtPes.setFont(new Font("Arial", 1, 15));
        txtPes.setBounds(550, 175, 400, 23);
        painel1.add(txtPes);

        btnPes = new JButton("Pesquisar");
        btnPes.setFont(new Font("Arial", 1, 15));
        btnPes.setBounds(960, 175, 130, 23);
        btnPes.addActionListener(new EventoPesquisar());
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
    }

    private void objetivosTabela() {
        dao = new ObjetivoDAO();

        for (Objetivo o : dao.listObjetivo(user)) {
            modeloTabelaObjetivos.addRow(new Object[]{o.getDescricao(), o.getDataInic()});

        }

    }

    private class EventoPesquisar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String nome = txtPes.getText();
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
            txtNome.setEditable(false);
            painel.add(txtNome);

            txtObj = new JTextField();
            txtObj.setBounds(82, 155, 300, 30);
            painel.add(txtObj);

            txtData = new JTextField();
            txtData.setBounds(82, 225, 300, 30);
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

            lblData = new JLabel("Data de Inicio");
            lblData.setFont(new Font("Arial", 1, 15));
            lblData.setBounds(82, 200, 120, 30);
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

        }

        private class EventoVoltar implements ActionListener {

            Objetivo obj;

            public EventoVoltar(Objetivo obj) {
                this.obj = obj;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if (dao.deleteTarefa(objetivo)) {

                    JOptionPane.showMessageDialog(null, "Objetivo deletado com sucesso", "Objetivos", JOptionPane.INFORMATION_MESSAGE);
                    painel1.repaint();
                    painel1.revalidate();
                    dispose();
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

                Objetivo novoObj = new Objetivo(user, txtObj.getText(), txtData.getText());

                if (dao.updateObjetivo(antigoObj, novoObj)) {
                    JOptionPane.showMessageDialog(null, "Alteração Realizada com sucesso", "Objetivos", JOptionPane.INFORMATION_MESSAGE);
                }

                painel1.repaint();
                painel1.revalidate();
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
        new VisualizarObjetivos().setVisible(true);
    }

}

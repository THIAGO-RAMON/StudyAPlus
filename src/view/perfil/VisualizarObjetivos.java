package view.perfil;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import model.dao.ObjetivoDAO;
import model.bean.Objetivo;
import model.bean.User;
import view.Main.Principal;

public class VisualizarObjetivos extends JFrame {

    private JPanel painel1;
    private String colunasLivros[] = {"Objetivos", "Data de Início"};
    private String dados[][] = {};
    private JTable tabelaLivros;
    private JButton btnPes, leave;
    private JTextField txtPes;
    private JLabel lblObj;
    private static VisualizarObjetivos visualizarObjetivos;
    private DefaultTableModel modelaTabelaLivros;
    private ObjetivoDAO dao;
    private User user = Principal.user;
    private int cont =0;
    
    public VisualizarObjetivos() {
        config();
        painel();

        modelaTabelaLivros = new DefaultTableModel(dados, colunasLivros);
        tabelaLivros = new JTable(modelaTabelaLivros);
        
        
        JScrollPane painelScrollado = new JScrollPane(tabelaLivros);
        painelScrollado.setBounds(160, 200, 960, 400);
        painel1.add(painelScrollado);
        objetivosTabela();

        lblObj = new JLabel("Seus objetivos");
        lblObj.setFont(new Font("Arial", 1, 20));
        lblObj.setBounds(160, 170, 200, 30);
        painel1.add(lblObj);

        txtPes = new JTextField();
        txtPes.setFont(new Font("Arial", 1, 15));
        txtPes.setBounds(360, 175, 400, 23);
        painel1.add(txtPes);

        btnPes = new JButton("Pesquisar");
        btnPes.setFont(new Font("Arial", 1, 15));
        btnPes.setBounds(770, 175, 130, 23);
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
            modelaTabelaLivros.addRow(new Object[]{o.getDescricao(), null });
            
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
        painel1.setBackground(new Color(111, 144, 190));
        add(painel1);
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

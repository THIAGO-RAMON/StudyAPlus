package view.telasPrograma;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.EventQueue;
import java.text.DecimalFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.bean.User;
import model.dao.UserDAO;

import view.auxiliares.BarraLateral;
import view.auxiliares.TelaPadraoFullScreen;
import static view.perfil.TelaPerfil.principal;

public class TelaDesempenho extends TelaPadraoFullScreen {

    JPanel painel1, painel2;
    JLabel lblCabe, lblDes1, lblDes2, lblMensagem, lblMsgDes, lblError;
    JButton btnBack, btnFoto, leave;

    private User usuario = principal.user;
    private static UserDAO daoUser = new UserDAO();

    private double porcentagem;
    
    private static final DecimalFormat df = new DecimalFormat("0.00");
    private static TelaTarefas telaTarefas;
    private BarraLateral barraLateral;

    public static TelaDesempenho telaDesempenho = new TelaDesempenho();

    public TelaDesempenho() {
        
        runDesempenho();
        
        painel2();
        painel1();

        telaTarefas = new TelaTarefas();
        TelaPadraoFullScreen.InserirIcone ic = new TelaPadraoFullScreen.InserirIcone();
        ic.InserirIcone(this);

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        lblCabe = new JLabel("DESEMPENHO");
        lblCabe.setBounds(600, 20, 300, 50);
        lblCabe.setFont(new Font("Arial", 1, 30));
        lblCabe.setForeground(Color.BLACK.darker());
        painel1.add(lblCabe);

        lblDes1 = new JLabel("O seu desempenho é:");
        lblDes1.setBounds(20, 30, 420, 30);
        lblDes1.setFont(new Font("Arial", 1, 32));
        painel2.add(lblDes1);

        lblMsgDes = new JLabel();
        lblMsgDes.setBounds(40, 180, 420, 40);
        lblMsgDes.setFont(new Font("Arial", 1, 32));
        painel2.add(lblMsgDes);

        lblDes2 = new JLabel(df.format(porcentagem)+"%");
        lblDes2.setFont(new Font("Arial", 1, 20));
        lblDes2.setBounds(390, 25, 95, 50);

        painel2.add(lblDes2);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                System.exit(0);
            }
        });
        leave.setBounds(painel1.getWidth() - 60, 0, 60, 30);

        painel1.add(leave);

        btnFoto = new JButton();
        btnFoto.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnFoto.setBackground(new Color(218, 217, 215));
        btnFoto.setBorder(null);
        btnFoto.setBounds(10, 10, 100, 75);
        painel1.add(btnFoto);

        if (porcentagem >= 0.0 && porcentagem <= 19.9) {
            lblMsgDes.setText("Desempenho muito baixo!");
        } else if (porcentagem >= 20.0 && porcentagem <= 39.9) {
            lblMsgDes.setText("Desempenho baixo!");
        } else if (porcentagem >= 40.0 && porcentagem <= 59.9) {
            lblMsgDes.setText("Desempenho bom!");
        } else if (porcentagem >= 60.0 && porcentagem <= 79.9) {
            lblMsgDes.setText("Desempenho muito bom!");
        } else if (porcentagem >= 80.0 && porcentagem <= 100.0) {
            lblMsgDes.setText("Desempenho ótimo!");
        }
        
        if (Double.isNaN(porcentagem)) {
            porcentagem = 0;

            lblError = new JLabel("Nenhuma tarefa criada");
            lblError.setBounds(40, 180, 300, 30);
            lblError.setFont(new Font("Arial", 1, 23));;
            lblError.setForeground(Color.red);
        }
    }

    private void painel1() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, this.getWidth(), this.getHeight());
        painel1.setBackground(new Color(207, 227, 225));
        add(painel1);
    }

    private void painel2() {
        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBounds(480, 200, 600, 400);
        painel2.setBackground(new Color(218, 217, 215));
        painel2.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        add(painel2);
    }
    
    private void runDesempenho() {
        porcentagem = daoUser.getPorcentagem(usuario);
    }
    
    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                if(telaDesempenho.isActive()){
                    telaDesempenho.dispose();
                }
                TelaDesempenho telaDesempenhoNova = new TelaDesempenho();
                telaDesempenho = telaDesempenhoNova;
                telaDesempenho.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaDesempenho.runTela();
    }

    
}

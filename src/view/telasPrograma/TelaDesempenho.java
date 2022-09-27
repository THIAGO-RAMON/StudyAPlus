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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.bean.User;
import model.dao.UserDAO;

import view.Main.BarraLateral;
import view.Main.Principal;
import view.Main.TelaPadraoFullScreen;
import view.perfil.TelaPerfil;
import static view.perfil.TelaPerfil.principal;

public class TelaDesempenho extends TelaPadraoFullScreen {

    JPanel painel1, painel2;
    JLabel lblCabe, lblDes1, lblDes2, lblMensagem, lblMsgDes, lblError;
    JButton btnBack, btnFoto, leave;

    private User usuario = principal.user;
    private Principal princial;
    private UserDAO dao = new UserDAO();

    private static final DecimalFormat df = new DecimalFormat("0.00");
    static TelaTarefas telaTarefas;
    BarraLateral barraLateral;
    private static double x ;

    public static TelaDesempenho telaDesempenho = new TelaDesempenho();

    public TelaDesempenho() {
        painel2();
        painel1();

        telaTarefas = new TelaTarefas();
        InserirIcone ic = new InserirIcone();
        ic.InserirIcone(this);

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        x = 4;

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

        usuario = dao.listDesempenho(Principal.user.getNome());

        lblDes2 = new JLabel(String.valueOf(usuario.getDesempenho_percentual()) + "%");
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

        if (x >= 0.0 && x <= 19.9) {
            lblMsgDes.setText("Desempenho muito baixo!");
        } else if (x >= 20.0 && x <= 39.9) {
            lblMsgDes.setText("Desempenho baixo!");
        } else if (x >= 40.0 && x <= 59.9) {
            lblMsgDes.setText("Desempenho bom!");
        } else if (x >= 60.0 && x <= 79.9) {
            lblMsgDes.setText("Desempenho muito bom!");
        } else if (x >= 80.0 && x <= 100.0) {
            lblMsgDes.setText("Desempenho ótimo!");
        }
        
        if (Double.isNaN(x)) {
            x = 0;

            lblError = new JLabel("Nenhuma tarefa criada");
            lblError.setBounds(40, 180, 300, 30);
            lblError.setFont(new Font("Arial", 1, 23));;
            lblError.setForeground(Color.red);

            usuario.setDesempenho_percentual(x);

            User desNew = new User();
            desNew.setDesempenho_percentual(x);

            if (dao.updateUser(usuario, desNew)) {
                Principal.user.setDesempenho_percentual(x);
            }

        } else {

            if (x != usuario.getDesempenho_percentual()) {

                User desNew = new User();
                desNew.setDesempenho_percentual(x);

                if (dao.updateDesempenho(usuario, desNew)) {
                    JOptionPane.showMessageDialog(null, "Desempenho atualizado com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                    principal.user = desNew;
                    lblDes2.setText(String.valueOf(Principal.user.getDesempenho_percentual()) + " %");
                }

            }
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
        painel2.setBorder(new BordaCantoArrendondado());
        add(painel2);
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                telaDesempenho.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaDesempenho.runTela();
    }
}

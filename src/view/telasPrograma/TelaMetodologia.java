package view.telasPrograma;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.bean.User;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaMetodologia extends TelaPadraoFullScreen {

    JPanel painel1, painel2;
    BarraLateral barraLateral;
    JButton leave;
    TelaMetodologia metodologia;
    JLabel lblmsg, lblmsg2, lblExtra;
    User user = Principal.user;

    public TelaMetodologia() {
        configPainel();

        barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        leave.setBounds(painel1.getWidth() - 60, 0, 60, 30);
        painel1.add(leave);

        lblmsg = new JLabel();
        lblmsg.setText("Sua idade é de um estudante do");
        lblmsg.setFont(new Font("Arial", 1, 20));
        lblmsg.setBounds(20, 20, 310, 30);
        painel2.add(lblmsg);

        lblExtra = new JLabel();
        lblExtra.setFont(new Font("Arial", 1, 20));
        lblExtra.setBounds(335, 20, 200, 30);
        painel2.add(lblExtra);

        lblmsg2 = new JLabel();
        lblmsg2.setText("é recomendado que utilize esta metodologia.");
        lblmsg2.setFont(new Font("Arial", 1, 20));
        lblmsg2.setBounds(20, 50, 600, 30);
        painel2.add(lblmsg2);

        AlgoritmoEstudo();
    }

    private void configPainel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBackground(new Color(207, 227, 225));
        painel1.setBounds(0, 0, getWidth(), getHeight());
        add(painel1);

        painel2 = new JPanel();
        painel2.setLayout(null);
        painel2.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painel2.setBackground(new Color(200, 200, 200));
        painel2.setBounds(400, 100, 800, 600);
        painel1.add(painel2);
    }

    public void runTela() {
        metodologia = new TelaMetodologia();
        if (metodologia.isActive()) {
            metodologia.dispose();
        }

        TelaMetodologia telaTarefasNova = new TelaMetodologia();
        metodologia = telaTarefasNova;
        metodologia.setVisible(true);
    }

    private void AlgoritmoEstudo() {

        if (user.getIdade() < 15) {
            lblExtra.setText("ensino fundamental,");
        } else if (user.getIdade() >= 15 && user.getIdade() <= 17) {
            lblExtra.setText("ensino médio,");
        } else if (user.getIdade() > 17) {
            lblExtra.setText("ensino superior");
        }
    }
    

    public static void main(String[] args) {
        new TelaMetodologia().setVisible(true);
    }
}

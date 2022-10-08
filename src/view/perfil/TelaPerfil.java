package view.perfil;

import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.bean.User;

import view.Main.BarraLateral;
import view.Main.Principal;
import view.Main.TelaPadraoFullScreen;

public class TelaPerfil extends TelaPadraoFullScreen {

    private JButton btnFoto, btnLogo, leave, btnAlterar, btnAlterar2;
    private JPanel painel1, painelB;
    private JLabel lblNome, lblSenha;
    private JTextField txtNome, txtSenha;
    public static Principal principal;
    private User user = principal.user;

    public static TelaPerfil telaPerfil = new TelaPerfil();

    public TelaPerfil() {
        // Configurações de tela
        painel1();
        BarraLateral barraLateral = new BarraLateral();
        barraLateral.setBounds(10, 10, barraLateral.getWidth(), barraLateral.getHeight());
        painel1.add(barraLateral);

        btnLogo = new JButton();
        btnLogo.setIcon(new ImageIcon(getClass().getResource("/images/Logo100x75.png")));
        btnLogo.setBackground(new Color(218, 217, 215));
        btnLogo.setBorder(null);
        btnLogo.setBounds(10, 10, 100, 75);
        painel1.add(btnLogo);

        // Labels e TextFields
        lblNome = new JLabel("NOME:");
        lblNome.setFont(new Font("Arial", 1, 28));
        lblNome.setBounds(358, 275, 130, 30);
        painel1.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(358, 325, 300, 30);
        txtNome.setBackground(new Color(218, 217, 215));
        txtNome.setFont(new Font("Arial", 0, 24));
        txtNome.setText(" " + user.getNome());
        txtNome.setBorder(new BordaCantoArrendondado());
        txtNome.setEditable(false);
        painel1.add(txtNome);

        lblSenha = new JLabel("SENHA:");
        lblSenha.setFont(new Font("Arial", 1, 28));
        lblSenha.setBounds(358, 375, 130, 40);
        painel1.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(358, 425, 300, 30);
        txtSenha.setFont(new Font("Arial", 0, 24));
        txtSenha.setBackground(new Color(218, 217, 215));
        txtSenha.setText(" " + user.getSenha());
        txtSenha.setBorder(new BordaCantoArrendondado());
        txtSenha.setEditable(false);
        painel1.add(txtSenha);

        // Botões
        btnFoto = new JButton("Foto do usuário");
        btnFoto.setBackground(new Color(218, 217, 215));
        btnFoto.setBounds(358, barraLateral.getY()+90, 150, 150);
        painel1.add(btnFoto);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        leave.setBounds(painel1.getWidth() - 62, 0, 60, 30);
        painel1.add(leave);

        EventoBotao eventoBotao = new EventoBotao();

        btnAlterar = new JButton();
        btnAlterar.addActionListener(eventoBotao);
        btnAlterar.setText("Deseja alterar suas informações? Clique aqui!");
        btnAlterar.setBorder(null);
        btnAlterar.setBackground(painel1.getBackground());
        btnAlterar.setBounds(358, 643, 300, 40);
        painel1.add(btnAlterar);
    }

    void configTela() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 650);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setBackground(Color.black);
    }

    void painel1() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(207, 227, 225));
        add(painel1);
    }

    private class EventoBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (e.getSource() == btnAlterar) {
                new TelaAlterar().setVisible(true);
            }
        }

    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                
                if(telaPerfil.isVisible()){
                    telaPerfil.dispose();
                }
                telaPerfil.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        telaPerfil.runTela();
    }

}

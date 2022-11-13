package view.inicial;

import controller.RecompensaController;
import controller.UserController;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import model.User;
import dao.UserDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import model.Recompensa;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;
import view.telasPrograma.TelaTarefas;

public class TelaLogin extends TelaPadraoFullScreen {

    private JLabel lblT, lblNU, lblSenha, lblBackground;
    private JTextField tfNome;
    private JPasswordField pfSenha;
    private JButton btnOk, btnCancel, btnVerSenha1, btnOcultar1;
    private final PainelGraphics painel1;
    private final User usuario = new User();
    private final UserDAO dao = new UserDAO();
    private RecompensaController recompensaController = new RecompensaController();
    private ArrayList<Recompensa> recompensas;
    private UserController cc;

    public static Principal principal;

    TelaLogin() {

        loadRecompensas();

        TelaPadraoFullScreen.InserirIcone ic = new TelaPadraoFullScreen.InserirIcone();
        ic.InserirIcone(this);

        painel1 = new PainelGraphics();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(218, 217, 215));
        add(painel1);

        lblT = new JLabel("TELA DE LOGIN");
        lblT.setFont(new Font("Arial", 1, 40));
        lblT.setBounds((getWidth() / 2) - 150, 10, 700, 50);
        painel1.add(lblT);

        lblNU = new JLabel("Nome de Usuário:");
        lblNU.setFont(new Font("Arial", 0, 30));
        lblNU.setBounds(70, 280, 250, 30);
        painel1.add(lblNU);

        tfNome = new JTextField();
        tfNome.setBackground(new Color(218, 217, 215));
        tfNome.setBounds(70, 320, 425, 30);
        tfNome.setFont(new Font("Arial", 0, 20));
        tfNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        tfNome.requestFocus();

        painel1.add(tfNome);

        lblSenha = new JLabel("Senha:");

        lblSenha.setFont(new Font("Arial", 0, 30));
        lblSenha.setBounds(70, 360, 150, 30);
        painel1.add(lblSenha);

        pfSenha = new JPasswordField();
        pfSenha.setBackground(new Color(218, 217, 215));
        pfSenha.setBounds(70, 400, 425, 30);
        pfSenha.setFont(new Font("Arial", 0, 20));
        pfSenha.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        pfSenha.addKeyListener(new EventoTecla());
        painel1.add(pfSenha);

        btnOk = new JButton("OK");
        btnOk.setBackground(new Color(168, 168, 168));
        btnOk.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        btnOk.setFont(new Font("Arial", 1, 20));
        btnOk.setBounds(70, 475, 170, 50);
        EventoConfirmar evt = new EventoConfirmar();
        btnOk.addActionListener(evt);
        painel1.add(btnOk);

        btnCancel = new JButton("VOLTAR");
        btnCancel.addActionListener(new EventoCancelar());

        btnCancel.setFont(new Font("Arial", 1, 20));
        btnCancel.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        btnCancel.setBackground(new Color(168, 168, 168));
        btnCancel.setBounds(310, 475, 170, 50);
        painel1.add(btnCancel);

        lblBackground = new JLabel("Imagem do software");
        lblBackground.setBounds(641, 69, 640, 659);
        lblBackground.setIcon(new ImageIcon(getClass().getResource("/images/BackGroundLogin.png")));
        painel1.add(lblBackground);

        // BOTÕES DE SENHA
        btnVerSenha1 = new JButton();
        btnVerSenha1.setBorder(null);
        btnVerSenha1.setIcon(new ImageIcon(getClass().getResource("/images/senhaVisible (1).png")));
        btnVerSenha1.setBackground(new Color(168, 168, 168));
        btnVerSenha1.setBounds(505, 401, 33, 30);
        btnVerSenha1.addActionListener(new EventoSenha());
        painel1.add(btnVerSenha1);

        btnOcultar1 = new JButton();
        btnOcultar1.setBorder(null);
        btnOcultar1.setIcon(new ImageIcon(getClass().getResource("/images/visible (1).png")));
        btnOcultar1.setBackground(new Color(168, 168, 168));
        btnOcultar1.setBounds(505, 401, 33, 30);
        btnOcultar1.addActionListener(new EventoSenha());
        btnOcultar1.setVisible(false);
        painel1.add(btnOcultar1);

    }

    private void loadRecompensas() {
        try {
            recompensas = (ArrayList<Recompensa>) recompensaController.listRecompensa(usuario);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    private class EventoTecla implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            try {
                int code = e.getKeyCode();
                int tecla = KeyEvent.VK_ENTER;
                cc = new UserController();

                if (code == tecla) {
                    String nome = tfNome.getText().trim();
                    boolean b = false;

                    for (User user : cc.listUser()) {

                        if (nome.equals(user.getNome()) && pfSenha.getText().equals(user.getSenha())) { // True

                            Principal.user = user;

                            if (recompensaController.quantidadeDeRecompensasValida(user)) { // Verifica se as recompensas foram já carregadas para o perfil
                                recompensaController.loadRecompensas(user);
                            }

                            b = true;

                            break;
                        }

                    }

                    if (b) {
                        JOptionPane.showMessageDialog(null, "Login realizado!", "Login!", JOptionPane.INFORMATION_MESSAGE);
                        new TelaTarefas().runTela();
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Login Incorreto!", "Login!", 0);
                        new TelaMain().runTela();
                        dispose();
                    }

                }
            } catch (SQLException ex) {
                System.err.println("Erro no loading das recompensas");
                System.err.println(ex);
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
        }

    }

    private class EventoConfirmar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            try{
            String nome = tfNome.getText().trim();
            boolean b = false;
            cc = new UserController();

            for (User user : cc.listUser()) {

                if (e.getSource() == btnOk) {

                    if (nome.equals(user.getNome()) && pfSenha.getText().equals(user.getSenha())) { // True

                        Principal.user = user;

                        if (recompensaController.quantidadeDeRecompensasValida(user)) { // Verifica se as recompensas foram já carregadas para o perfil
                            recompensaController.loadRecompensas(user);
                        }

                        b = true;

                        break;
                    }

                }
            }

            if (b) {
                JOptionPane.showMessageDialog(null, "Login realizado!", "Login!", JOptionPane.INFORMATION_MESSAGE);
                new TelaTarefas().runTela();
                dispose();
            } else {
                JOptionPane.showMessageDialog(null, "Login Incorreto!", "Login!", 0);
                new TelaMain().runTela();
                dispose();
            }
            }catch(SQLException ex){
                System.err.println("Erro no loading das recompensas");
                System.err.println(ex);
            }
        }
    }

    private class EventoCancelar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            dispose();
            new TelaMain().runTela();
        }

    }

    private class EventoSenha implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (e.getSource() == btnVerSenha1) {
                pfSenha.setEchoChar((char) (0));

                btnVerSenha1.setVisible(false);
                btnOcultar1.setVisible(true);
                pfSenha.requestFocus();

            } else if (e.getSource() == btnOcultar1) {
                btnOcultar1.setVisible(false);

                pfSenha.setEchoChar('•');
                pfSenha.requestFocus();

                btnVerSenha1.setVisible(true);

            }

        }
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaLogin().setVisible(true);
            }
        });
    }

    private class PainelGraphics extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            g.setColor(new Color(189, 196, 243));
            g.fillRoundRect(0, 0, 1280, 69, 10, 10);

            g.setColor(new Color(168, 168, 168));
            g.fillRoundRect(50, 240, 508, 350, 30, 40);

            g.setColor(Color.BLACK);
            g.fillRect(0, 70, 1280, 1);
            g.fillRect(640, 70, 2, 660);
        }

    }

    public static void main(String[] args) {
        new TelaLogin().setVisible(true);
    }

}

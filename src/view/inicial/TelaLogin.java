package view.inicial;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Iterator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import model.bean.User;
import model.dao.UserDAO;

import view.Main.TelaPadraoFullScreen;
import view.telasPrograma.TelaTarefas;

public class TelaLogin extends TelaPadraoFullScreen {

    JLabel lblT, lblNU, lblSenha, lblBackground;
    JTextField tfNome;
    JPasswordField pfSenha;
    JButton btnOk, btnCancel, btnVerSenha1, btnOcultar1;
    PainelGraphics painel1;
    User usuario = new User();
    UserDAO dao = new UserDAO();

    TelaLogin() {

        InserirIcone ic = new InserirIcone();
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
        tfNome.setBorder(new BordaCantoArrendondado());
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
        pfSenha.setBorder(new BordaCantoArrendondado());
        painel1.add(pfSenha);

        btnOk = new JButton("OK");
        btnOk.setBackground(new Color(168, 168, 168));
        btnOk.setBorder(new BordaCantoArrendondado());
        btnOk.setFont(new Font("Arial", 1, 20));
        btnOk.setBounds(70, 475, 170, 50);
        EventoConfirmar evt = new EventoConfirmar();
        btnOk.addActionListener(evt);
        painel1.add(btnOk);

        btnCancel = new JButton("VOLTAR");
        btnCancel.addActionListener(new EventoConfirmar());

        btnCancel.setFont(new Font("Arial", 1, 20));
        btnCancel.setBorder(new BordaCantoArrendondado());
        btnCancel.setBackground(new Color(168, 168, 168));
        btnCancel.setBounds(310, 475, 170, 50);
        painel1.add(btnCancel);

        lblBackground = new JLabel("Imagem do software");
        lblBackground.setBounds(641, 69, 640, 659);
        lblBackground.setIcon(new ImageIcon(getClass().getResource("/images/BackGroundLogin.png")));
        painel1.add(lblBackground);

        // BOTÕES DE SENHA
        btnVerSenha1 = new JButton();
        btnVerSenha1.setBorder(new BordaCantoArrendondado());
        btnVerSenha1.setIcon(new ImageIcon(getClass().getResource("/images/senhaVisible (1).png")));
        btnVerSenha1.setBackground(new Color(218, 217, 215));
        btnVerSenha1.setBounds(505, 401, 33, 30);
        btnVerSenha1.addActionListener(new EventoSenha());
        painel1.add(btnVerSenha1);

        btnOcultar1 = new JButton();
        btnOcultar1.setBorder(new BordaCantoArrendondado());
        btnOcultar1.setIcon(new ImageIcon(getClass().getResource("/images/visible (1).png")));
        btnOcultar1.setBackground(new Color(218, 217, 215));
        btnOcultar1.setBounds(505, 401, 33, 30);
        btnOcultar1.addActionListener(new EventoSenha());
        btnOcultar1.setVisible(false);
        painel1.add(btnOcultar1);

    }

    private class EventoConfirmar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            for (User user : dao.listUsers()) {
                if (e.getSource() == btnOk) {

                    if (tfNome.getText().equals(user.getNome()) && pfSenha.getText().equals(user.getSenha())) {
                        JOptionPane.showMessageDialog(null, "Login realizado!", "Login!", JOptionPane.INFORMATION_MESSAGE);
                        new TelaTarefas().runTela();
                        dispose();

                    } else if (!tfNome.getText().equals(user.getNome())) {
                        JOptionPane.showMessageDialog(null, "Nome incorreto!", "Login!", JOptionPane.WARNING_MESSAGE);

                    } else if (!pfSenha.getText().equals(user.getSenha())) {

                        JOptionPane.showMessageDialog(null, "senha incorreta!", "Login!", JOptionPane.WARNING_MESSAGE);

                    } else if (tfNome.getText().isEmpty() && pfSenha.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha os campos!", "Login!",
                                JOptionPane.WARNING_MESSAGE);

                    }

                } else if (e.getSource() == btnCancel) {
                    dispose();
                    new TelaMain().runTela();
                }
            }

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

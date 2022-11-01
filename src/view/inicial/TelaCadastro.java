package view.inicial;

import controller.DesafioController;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import model.User;
import dao.UserDAO;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import view.auxiliares.Principal;
import static view.auxiliares.Principal.tl;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaCadastro extends TelaPadraoFullScreen {

    private JLabel lblLogo, titulo, lblNU, lblSenha, lblConfirmar,lblIdade;
    private JTextField jfNome,jfIdade;
    private JPasswordField pfSenha, pfConfirmar;
    private JButton btnOk, btnCancel, leave, btnVerSenha1, btnVerSenha2, btnOcultar1, btnOcultar2;
    private PainelPadrao painel1;
    private User usuario;
    private UserDAO dao = new UserDAO();
    private UserController userController;
    private RecompensaController recompensaController;
    
    public static Principal principal;

    TelaCadastro() {

        TelaPadraoFullScreen.InserirIcone ic = new TelaPadraoFullScreen.InserirIcone();
        ic.InserirIcone(this);

        painel1 = new PainelPadrao();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(218, 217, 215));
        add(painel1);

        titulo = new JLabel("TELA DE CADASTRO");
        titulo.setFont(new Font("Arial", 1, 50));
        titulo.setBounds((getWidth() / 2) - 230, 10, 700, 50);
        painel1.add(titulo);

        lblNU = new JLabel("Nome de usuário:");
        lblNU.setFont(new Font("Arial", 0, 30));
        lblNU.setBounds(50, 230, 300, 30);
        painel1.add(lblNU);

        jfNome = new JTextField();
        jfNome.setBackground(new Color(218, 217, 215));
        jfNome.setBounds(50, 270, 500, 35);
        jfNome.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        jfNome.setFont(new Font("Arial", 0, 20));
        jfNome.requestFocus();

        painel1.add(jfNome);

        lblSenha = new JLabel("Senha:");
        lblSenha.setFont(new Font("Arial", 0, 30));
        lblSenha.setBounds(50, 310, 150, 30);
        painel1.add(lblSenha);

        pfSenha = new JPasswordField();
        pfSenha.setBackground(new Color(218, 217, 215));
        pfSenha.setBounds(50, 350, 500, 35);
        pfSenha.setFont(new Font("Arial", 0, 20));
        pfSenha.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        painel1.add(pfSenha);

        lblConfirmar = new JLabel("Confirmar senha:");
        lblConfirmar.setFont(new Font("Arial", 0, 30));
        lblConfirmar.setBounds(50, 390, 250, 30);
        painel1.add(lblConfirmar);

        pfConfirmar = new JPasswordField();
        pfConfirmar.setBackground(new Color(218, 217, 215));
        pfConfirmar.setBounds(50, 430, 500, 35);
        pfConfirmar.setFont(new Font("Arial", 0, 20));
        pfConfirmar.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        pfConfirmar.addKeyListener(new EventoTecla());
        painel1.add(pfConfirmar);
        
        lblIdade = new JLabel("Idade:");
        lblIdade.setFont(new Font("Arial",0,30));
        lblIdade.setBounds(50,480,150,30);
        painel1.add(lblIdade);
        
        jfIdade =new JTextField();
        jfIdade.setBackground(new Color(218, 217, 215));
        jfIdade.setBounds(50, 520, 90, 35);
        jfIdade.setFont(new Font("Arial", 0, 20));
        jfIdade.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
        jfIdade.addKeyListener(new EventoTecla());
        painel1.add(jfIdade);

        btnOk = new JButton("OK");
        btnOk.setBackground(new Color(168, 168, 168));
        btnOk.setFont(new Font("Arial", 1, 20));
        btnOk.setBounds(50, 590, 200, 50);
        btnOk.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        EventoConfimar evt = new EventoConfimar();
        btnOk.addActionListener(evt);
        painel1.add(btnOk);

        btnCancel = new JButton("VOLTAR");
        btnCancel.setFont(new Font("Arial", 1, 20));
        btnCancel.setBackground(new Color(168, 168, 168));
        btnCancel.addActionListener(evt);
        btnCancel.setBounds(350, 590, 200, 50);
        btnCancel.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        painel1.add(btnCancel);

        lblLogo = new JLabel("");
        lblLogo.setBounds(641, 71, 640, 680);
        lblLogo.setIcon(new ImageIcon(getClass().getResource("/images/BackGroundCadastro.png")));
        painel1.add(lblLogo);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.addActionListener(evt);
        leave.setBounds(getWidth() - 60, 0, 60, 30);
        painel1.add(leave);

        btnVerSenha1 = new JButton();
        btnVerSenha1.setBorder(null);
        btnVerSenha1.setIcon(new ImageIcon(getClass().getResource("/images/senhaVisible (1).png")));
        btnVerSenha1.setBackground(new Color(168, 168, 168));
        btnVerSenha1.setBounds(565, 353, 35, 30);
        btnVerSenha1.addActionListener(new EventoSenha());
        painel1.add(btnVerSenha1);

        btnVerSenha2 = new JButton();
        btnVerSenha2.setBorder(null);
        btnVerSenha2.setIcon(new ImageIcon(getClass().getResource("/images/senhaVisible (1).png")));
        btnVerSenha2.setBackground(new Color(168, 168, 168));
        btnVerSenha2.setBounds(565, 433, 35, 30);
        btnVerSenha2.addActionListener(new EventoSenha());
        painel1.add(btnVerSenha2);

        btnOcultar1 = new JButton();

        btnOcultar1.setBorder(null);
        btnOcultar1.setIcon(new ImageIcon(getClass().getResource("/images/visible (1).png")));
        btnOcultar1.setBackground(new Color(168, 168, 168));
        btnOcultar1.setBounds(565, 353, 35, 30);
        btnOcultar1.addActionListener(new EventoSenha());
        btnOcultar1.setVisible(false);
        painel1.add(btnOcultar1);

        btnOcultar2 = new JButton();

        btnOcultar2.setBorder(null);
        btnOcultar2.setIcon(new ImageIcon(getClass().getResource("/images/visible (1).png")));
        btnOcultar2.setBackground(new Color(168, 168, 168));
        btnOcultar2.setBounds(565, 433, 35, 30);
        btnOcultar2.addActionListener(new EventoSenha());
        btnOcultar2.setVisible(false);
        painel1.add(btnOcultar2);

    }

    private class EventoTecla implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int code = e.getKeyCode();
            int tecla = KeyEvent.VK_ENTER;
            userController = new UserController();
            recompensaController = new RecompensaController();

            if (code == tecla) {

                if (jfNome.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Cadastro", JOptionPane.WARNING_MESSAGE);
                } else if (jfNome.getText().equals("") && pfSenha.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha o Nome e senha!", "Cadastro",
                            JOptionPane.WARNING_MESSAGE);
                } else if (pfSenha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Preencha a senha!", "Cadastro", JOptionPane.WARNING_MESSAGE);
                } else if (!pfConfirmar.getText().equals(pfSenha.getText())) {
                    JOptionPane.showMessageDialog(null, "Verifique a senha novamente!", "Cadastro",
                            JOptionPane.WARNING_MESSAGE);
                } else if (pfConfirmar.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, confirme a senha!", "Cadastro",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    usuario = new User();
                    usuario.setNome(jfNome.getText().trim());
                    usuario.setSenha(pfSenha.getText());
                    usuario.setSobreMim("");
                    usuario.setIdade(Integer.parseInt(jfIdade.getText()));
                    usuario.setDesempenho_percentual(0);

                    if (userController.saveUser(usuario)) {
                        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso\nVolte e faça o login", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        tl.runTela();
                        dispose();
                    } else {
                        JOptionPane.showConfirmDialog(null, "Erro no cadastro:\nErro com no arquivamento com o Banco de Dados", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }

                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
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

            } else if (e.getSource() == btnVerSenha2) {
                pfConfirmar.setEchoChar((char) (0));

                btnVerSenha2.setVisible(false);
                btnOcultar2.setVisible(true);
                pfConfirmar.requestFocus();

            } else if (e.getSource() == btnOcultar1) {
                btnOcultar1.setVisible(false);

                pfSenha.setEchoChar('•');
                pfSenha.requestFocus();

                btnVerSenha1.setVisible(true);

            } else if (e.getSource() == btnOcultar2) {
                btnOcultar2.setVisible(false);

                pfConfirmar.setEchoChar('•');
                pfConfirmar.requestFocus();

                btnVerSenha2.setVisible(true);
            }

        }

    }

    private class EventoConfimar implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

            if (e.getSource() == btnOk) {
                userController= new UserController();
                recompensaController = new RecompensaController();
                
                if (jfNome.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha o Nome!", "Cadastro", JOptionPane.WARNING_MESSAGE);
                } else if (jfNome.getText().equals("") && pfSenha.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha o Nome e senha!", "Cadastro",
                            JOptionPane.WARNING_MESSAGE);
                } else if (pfSenha.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Preencha a senha!", "Cadastro", JOptionPane.WARNING_MESSAGE);
                } else if (!pfConfirmar.getText().equals(pfSenha.getText())) {
                    JOptionPane.showMessageDialog(null, "Verifique a senha novamente!", "Cadastro",
                            JOptionPane.WARNING_MESSAGE);
                } else if (pfConfirmar.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "Por favor, confirme a senha!", "Cadastro",
                            JOptionPane.WARNING_MESSAGE);
                } else {
                    usuario = new User();
                    usuario.setNome(jfNome.getText().trim());
                    usuario.setSenha(pfSenha.getText().trim());
                    usuario.setSobreMim("");
                    usuario.setIdade(Integer.parseInt(jfIdade.getText()));
                    usuario.setDesempenho_percentual(0);
                    
                    
                    if (userController.saveUser(usuario)) {
                        JOptionPane.showMessageDialog(null, "Cadastrado com Sucesso\nVolte e faça o login", "Cadastro", JOptionPane.INFORMATION_MESSAGE);
                        tl.runTela();
                        dispose();
                    } else {
                        JOptionPane.showConfirmDialog(null, "Erro no cadastro:\nErro com no arquivamento com o Banco de Dados", "ERROR", JOptionPane.WARNING_MESSAGE);
                    }

                }

            } else if (e.getSource() == btnCancel) {
                setVisible(false);
                new TelaMain().runTela();

            } else if (e.getSource() == leave) {
                System.exit(0);

            }

        }
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaCadastro().setVisible(true);
            }
        });
    }

    private class PainelPadrao extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            // TODO Auto-generated method stub
            super.paintComponent(g);

            g.setColor(new Color(189, 196, 243));
            g.fillRoundRect(0, 0, 1280, 69, 10, 10);

            g.setColor(new Color(168, 168, 168));
            g.fillRoundRect(20, 150, 600, 500, 35, 35);

            g.setColor(Color.black);
            g.fillRect(640, 70, 1, 650);
            g.fillRect(0, 70, 1280, 1);

        }
    }

    public static void main(String[] args) {
        new TelaCadastro().setVisible(true);
    }
}

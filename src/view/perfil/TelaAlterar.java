package view.perfil;

import controller.UserController;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.EventQueue;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BasicStroke;
import java.awt.Frame;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.border.AbstractBorder;
import model.User;
import dao.UserDAO;
import view.auxiliares.Principal;
import static view.perfil.TelaPerfil.telaPerfil;

public class TelaAlterar extends JFrame {

    private JButton btnConfirmar, btnVerSenha1, btnVerSenha2, btnOcultar1, btnOcultar2, leave;
    private JLabel lbl, lblNovoNome,lblNovaIdade, lblNovaSenha, lblConfirmarSenha;
    private JTextField tfNovoNome,txtNovaIdade;
    private JPasswordField tfNovaSenha, tfConfirmarSenha;
    private JPanel painel1;
    private Principal principal;
    private User userPrincipal = principal.user;
    private UserController cc;
    
    private UserDAO dao = new UserDAO();
    
    public TelaAlterar() {
        configPainel();
        configTela();

        // Labels and TextFields
        lbl = new JLabel("Insira suas novas informações:");
        lbl.setFont(new Font("Arial", 1, 20));
        lbl.setBounds(10, 0, 400, 30);
        painel1.add(lbl);

        lblNovoNome = new JLabel("NOME:");
        lblNovoNome.setFont(new Font("Arial", 1, 20));
        lblNovoNome.setBounds(10, 50, 150, 30);
        painel1.add(lblNovoNome);

        tfNovoNome = new JTextField();
        tfNovoNome.setBorder(new BordaCantoArrendondado());
        tfNovoNome.setBackground(new Color(218, 217, 215));
        tfNovoNome.setFont(new Font("Arial", 0, 18));
        tfNovoNome.setBounds(10, 90, 400, 30);
        painel1.add(tfNovoNome);

        // Label Senha
        lblNovaSenha = new JLabel("SENHA:");
        lblNovaSenha.setFont(new Font("Arial", 1, 20));
        lblNovaSenha.setBounds(10, 150, 150, 30);
        painel1.add(lblNovaSenha);

        // TextField Senha
        tfNovaSenha = new JPasswordField();
        tfNovaSenha.setBorder(new BordaCantoArrendondado());
        tfNovaSenha.setBackground(new Color(218, 217, 215));
        tfNovaSenha.setFont(new Font("Arial", 0, 18));
        tfNovaSenha.setBounds(10, 190, 400, 30);
        painel1.add(tfNovaSenha);

        // Label Confimar Senha
        lblConfirmarSenha = new JLabel("CONFIRMAR SENHA:");
        lblConfirmarSenha.setFont(new Font("Arial", 1, 20));
        lblConfirmarSenha.setBounds(10, 250, 400, 30);
        painel1.add(lblConfirmarSenha);

        // TextField Confimar Senha
        tfConfirmarSenha = new JPasswordField();
        tfConfirmarSenha.setBorder(new BordaCantoArrendondado());
        tfConfirmarSenha.setBackground(new Color(218, 217, 215));
        tfConfirmarSenha.setFont(new Font("Arial", 0, 18));
        tfConfirmarSenha.setBounds(10, 290, 400, 30);
        painel1.add(tfConfirmarSenha);
        
        //label confirmar nova idade
        lblNovaIdade = new JLabel("CONFIRMAR IDADE:");
        lblNovaIdade.setFont(new Font("Arial", 1, 20));
        lblNovaIdade.setBounds(10, 340, 400, 30);
        painel1.add(lblNovaIdade);
        
        // txt confirmar nova idade
         txtNovaIdade = new JTextField();
        txtNovaIdade.setBorder(new BordaCantoArrendondado());
        txtNovaIdade.setBackground(new Color(218, 217, 215));
        txtNovaIdade.setFont(new Font("Arial", 0, 18));
        txtNovaIdade.setBounds(10, 380, 60, 30);
        painel1.add(txtNovaIdade);

        // buttons
        EventoBotao evt = new EventoBotao();
        btnConfirmar = new JButton("Confirmar");
        btnConfirmar.addActionListener(evt);
        btnConfirmar.setBackground(new Color(218, 217, 215));
        btnConfirmar.setBorder(new BordaCantoArrendondado());
        btnConfirmar.setBounds(200, 450, 100, 30);
        painel1.add(btnConfirmar);

        btnVerSenha1 = new JButton();
        btnVerSenha1.setBorder(new BordaCantoArrendondado());
        btnVerSenha1.setIcon(new ImageIcon(getClass().getResource("/images/senhaVisible (1).png")));
        btnVerSenha1.setBackground(new Color(218, 217, 215));
        btnVerSenha1.setBounds(415, 190, 35, 30);
        btnVerSenha1.addActionListener(evt);
        painel1.add(btnVerSenha1);

        btnVerSenha2 = new JButton();
        btnVerSenha2.setBorder(new BordaCantoArrendondado());
        btnVerSenha2.setIcon(new ImageIcon(getClass().getResource("/images/senhaVisible (1).png")));
        btnVerSenha2.setBackground(new Color(218, 217, 215));
        btnVerSenha2.setBounds(415, 290, 35, 30);
        btnVerSenha2.addActionListener(evt);
        painel1.add(btnVerSenha2);

        btnOcultar1 = new JButton();

        btnOcultar1.setBorder(new BordaCantoArrendondado());
        btnOcultar1.setIcon(new ImageIcon(getClass().getResource("/images/visible (1).png")));
        btnOcultar1.setBackground(new Color(218, 217, 215));
        btnOcultar1.setBounds(415, 190, 35, 30);
        btnOcultar1.addActionListener(new EventoBotao());
        btnOcultar1.setVisible(false);
        painel1.add(btnOcultar1);

        btnOcultar2 = new JButton();

        btnOcultar2.setBorder(new BordaCantoArrendondado());
        btnOcultar2.setIcon(new ImageIcon(getClass().getResource("/images/visible (1).png")));
        btnOcultar2.setBackground(new Color(218, 217, 215));
        btnOcultar2.setBounds(415, 290, 35, 30);
        btnOcultar2.addActionListener(new EventoBotao());
        btnOcultar2.setVisible(false);
        painel1.add(btnOcultar2);

        leave = new JButton("X");
        leave.setBackground(new Color(223, 63, 16));
        leave.setBounds(painel1.getWidth() - 60, 0, 60, 30);
        leave.addActionListener(new EventoBotao());
        painel1.add(leave);

    }

    // MÉTODOS AUXILIARES
    void configTela() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);
    }

    void configPainel() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, 500, 500);
        painel1.setBackground(new Color(218, 217, 215));
        painel1.setBorder(new BordaCantoArrendondado());
        add(painel1);
    }

    // EVENTOS
    private class EventoBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (e.getSource() == btnConfirmar) {
                cc =new UserController();
                
                if (tfNovoNome.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Erro! Preencha o nome.", "Atenção",
                            JOptionPane.WARNING_MESSAGE);

                } else if (!(tfConfirmarSenha.getText().equals(tfNovaSenha.getText()))) {

                    JOptionPane.showMessageDialog(null, "Erro! As senhas não estão iguais.", "Atenção", JOptionPane.WARNING_MESSAGE);

                } else if (tfConfirmarSenha.getText().equals(tfNovaSenha.getText())) {

                    User userNew = new User();
                    userNew.setNome(tfNovoNome.getText());
                    userNew.setSenha(tfNovaSenha.getText());
                    userNew.setIdade(Integer.parseInt(txtNovaIdade.getText()));

                    if (cc.updateUser(userPrincipal, userNew)) {
                        JOptionPane.showMessageDialog(null, "Informações atualizadas com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
                        principal.user = userNew;
                        Principal.user.setNome(tfNovoNome.getText());
                        Principal.user.setSenha(tfNovaSenha.getText());
                    }
                    
                    
                    Frame frame[] = Frame.getFrames();
                            
                    for (int i = 0; i < frame.length; i++) {
                        frame[i].dispose();
                    }
                    
                    new TelaPerfil().runTela();
                   
                } else if (tfNovaSenha.getText().isEmpty()) {

                    JOptionPane.showMessageDialog(null, "Erro! Preencha a senha.", "Atenção",
                            JOptionPane.WARNING_MESSAGE);

                } else if (tfConfirmarSenha.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Erro! Confirme a sua senha.", "Atenção",
                            JOptionPane.WARNING_MESSAGE);
                }

            } else if (e.getSource() == btnVerSenha1) {
                tfNovaSenha.setEchoChar((char) (0));

                btnVerSenha1.setVisible(false);
                btnOcultar1.setVisible(true);
                tfNovaSenha.requestFocus();

            } else if (e.getSource() == btnVerSenha2) {
                tfConfirmarSenha.setEchoChar((char) (0));

                btnVerSenha2.setVisible(false);
                btnOcultar2.setVisible(true);
                tfConfirmarSenha.requestFocus();

            } else if (e.getSource() == btnOcultar1) {
                btnOcultar1.setVisible(false);

                tfNovaSenha.setEchoChar('•');
                tfNovaSenha.requestFocus();

                btnVerSenha1.setVisible(true);

            } else if (e.getSource() == btnOcultar2) {
                btnOcultar2.setVisible(false);

                tfConfirmarSenha.setEchoChar('•');
                tfConfirmarSenha.requestFocus();

                btnVerSenha2.setVisible(true);

            } else if (e.getSource() == leave) {
                dispose();

            }

        }

    }

    private class BordaCantoArrendondado extends AbstractBorder {

        private final BasicStroke CONTORNO = new BasicStroke(2);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            super.paintBorder(c, g, x, y, width, height);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(CONTORNO);

            g2d.setColor(Color.black);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 20, 20);

            g2d.dispose();
        }
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaAlterar().setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new TelaAlterar().runTela();
    }

}

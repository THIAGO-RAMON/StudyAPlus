package view.perfil;

import controller.UserController;
import java.awt.Color;
import java.awt.Font;
import java.awt.EventQueue;
import java.awt.Image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ContainerListener;
import java.io.File;
import javax.swing.BorderFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import model.bean.User;
import dao.UserDAO;

import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;
import view.inicial.TelaMain;

public class TelaPerfil extends TelaPadraoFullScreen {

    private JButton btnFoto, btnLogo, leave, btnAlterarUsuario, btnLogout, btnAlterar2, btnEdit, btnEditSobreMim,
            btnCancelarSobreMim;
    private JTextArea txtSobreMim;
    protected JPanel painel1, painelB;

    private JPanel painelConfigs;
    private JButton btnAlterarFotoPerfil;
    private static int NUM_CLICKS_PAINELCONFIGS = 0;
    private boolean alteradoSobreMim = false;
    private static int alterarSobreMim = 0;

    private JFileChooser fileChosser;
    FileNameExtensionFilter filtro;

    private BarraLateral barraLateral;
    private JLabel lblNome, lblSenha, lblSobreMim, lblIdade;
    private JTextField txtNome, txtSenha, txtIdade;
    public static Principal principal;
    private User user = principal.user;
    private UserController cc;
    private UserDAO daoUser = new UserDAO();

    private Thread thread1;
    private Thread thread2; // Para a verificação do SobreMim

    public static TelaPerfil telaPerfil;

    public TelaPerfil() {

        // Configurações de tela
        painel1();
        barraLateral = new BarraLateral();
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
        lblNome.setBounds(358, 325, 130, 30);
        painel1.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(358, 375, 300, 30);
        txtNome.setBackground(new Color(218, 217, 215));
        txtNome.setFont(new Font("Arial", 0, 24));
        txtNome.setText("" + Principal.user.getNome());
        txtNome.setBorder(new BordaCantoArrendondado());
        txtNome.setEditable(false);
        painel1.add(txtNome);

        lblSenha = new JLabel("SENHA:");
        lblSenha.setFont(new Font("Arial", 1, 28));
        lblSenha.setBounds(358, 425, 130, 40);
        painel1.add(lblSenha);

        txtSenha = new JPasswordField();
        txtSenha.setBounds(358, 475, 300, 30);
        txtSenha.setFont(new Font("Arial", 0, 24));
        txtSenha.setBackground(new Color(218, 217, 215));
        txtSenha.setText("" + Principal.user.getSenha());
        txtSenha.setBorder(new BordaCantoArrendondado());
        txtSenha.setEditable(false);
        painel1.add(txtSenha);

        lblIdade = new JLabel("IDADE:");
        lblIdade.setFont(new Font("Arial", 1, 28));
        lblIdade.setBounds(358, 525, 130, 40);
        painel1.add(lblIdade);

        txtIdade = new JTextField();
        txtIdade.setBounds(358, 575, 300, 30);
        txtIdade.setFont(new Font("Arial", 0, 24));
        txtIdade.setBackground(new Color(218, 217, 215));
        txtIdade.setText("" + Principal.user.getIdade());
        txtIdade.setBorder(new BordaCantoArrendondado());
        txtIdade.setEditable(false);
        painel1.add(txtIdade);

        lblSobreMim = new JLabel("SOBRE MIM:");
        lblSobreMim.setFont(new Font("Arial", 1, 28));
        lblSobreMim.setBounds(800, 325, 200, 40);
        painel1.add(lblSobreMim);

        txtSobreMim = new JTextArea(Principal.user.getSobreMim());
        txtSobreMim.setLineWrap(true);
        txtSobreMim.setBackground(new Color(218, 217, 215));
        txtSobreMim.setFont(new Font("Arial", 0, 20));
        txtSobreMim.setBorder(new BordaCantoArrendondado());
        txtSobreMim.getDocument().addDocumentListener(new ShowAlterarSobreMim());

        JScrollPane painelSobreMim = new JScrollPane(txtSobreMim);
        painelSobreMim.setBounds(800, 375, 400, 140);
        painelSobreMim.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painel1.add(painelSobreMim);

        btnEditSobreMim = new JButton("Alterar");
        btnEditSobreMim.setBounds(810, 540, 165, 40);
        btnEditSobreMim.setBorder(new BordaCantoArrendondado());
        btnEditSobreMim.setBackground(new Color(218, 217, 215));
        btnEditSobreMim.addActionListener(new AlterarSobreMim());
        btnEditSobreMim.setFont(new Font("Arial", 1, 20));
        btnEditSobreMim.setVisible(false);

        painel1.add(btnEditSobreMim);

        btnCancelarSobreMim = new JButton("Cancelar");
        btnCancelarSobreMim.setBounds(1025, 540, 165, 40);
        btnCancelarSobreMim.setBorder(new BordaCantoArrendondado());
        btnCancelarSobreMim.addActionListener(new CancelarSobreMim());
        btnCancelarSobreMim.setBackground(new Color(218, 217, 215));
        btnCancelarSobreMim.setFont(new Font("Arial", 1, 20));
        btnCancelarSobreMim.setVisible(false);

        painel1.add(btnCancelarSobreMim);

        // Botões
        btnFoto = new JButton();
        btnFoto.setBackground(new Color(168, 168, 168));
        btnFoto.setBounds(358, barraLateral.getY() + 90, 200, 200);
        vefImagemPerfil(btnFoto);
        painel1.add(btnFoto);

        btnEdit = new JButton(new ImageIcon(getClass().getResource("/images/EngrenagemIcone.png")));
        btnEdit.setBackground(painel1.getBackground());
        btnEdit.addActionListener(new MostrarConfigs());
        btnEdit.setBounds(580, barraLateral.getY() + 90, 50, 50);
        btnEdit.setBorder(null);
        painel1.add(btnEdit);

        configPainelConfigs();

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

    }

    // Metodos 
    private void painel1() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(207, 227, 225));
        add(painel1);
    }

    private void configPainelConfigs() {
        painelConfigs = new JPanel();
        painelConfigs.setLayout(null);
        painelConfigs.setSize(125, 120);
        painelConfigs.setBackground(new Color(168, 168, 168));

        EventoBotao eventoBotao = new EventoBotao();

        btnAlterarUsuario = new JButton();
        btnAlterarUsuario.addActionListener(eventoBotao);
        btnAlterarUsuario.setText("Alterar Usuario");
        btnAlterarUsuario.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnAlterarUsuario.setFont(new Font("Arial", 0, 14));
        btnAlterarUsuario.setForeground(Color.white);
        btnAlterarUsuario.setBackground(painel1.getBackground().darker());
        btnAlterarUsuario.setBounds(5, 5, 115, 30);
        painelConfigs.add(btnAlterarUsuario);

        btnAlterarFotoPerfil = new JButton();
        btnAlterarFotoPerfil.addActionListener(new EventoTrocarFotoPerfil());
        btnAlterarFotoPerfil.setText("Alterar Foto");
        btnAlterarFotoPerfil.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnAlterarFotoPerfil.setFont(new Font("Arial", 0, 14));
        btnAlterarFotoPerfil.setBackground(painel1.getBackground().darker());
        btnAlterarFotoPerfil.setForeground(Color.white);
        btnAlterarFotoPerfil.setBounds(5, 45, 115, 30);
        painelConfigs.add(btnAlterarFotoPerfil);

        btnLogout = new JButton("Logout");
        btnLogout.addActionListener(new EventoLogout());
        btnLogout.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnLogout.setFont(new Font("Arial", 0, 14));
        btnLogout.setBackground(painel1.getBackground().darker());
        btnLogout.setForeground(Color.white);
        btnLogout.setBounds(5, 85, 115, 30);
        painelConfigs.add(btnLogout);

    }

    private void vefImagemPerfil(JButton btn) {

        String imgPath = daoUser.getImagemUsuario(user);

        if (imgPath != "") {
            btn.setIcon(resizeImage(imgPath));
        } else {
            btn.setText("Foto de Perfil");
        }
    }

    private void configFilterFileChosser() {
        filtro = new FileNameExtensionFilter("*.Images", "jpg", "gif", "png", "jpeg");
    }

    private ImageIcon resizeImage(String ImagePath) {
        ImageIcon minhaImagem = new ImageIcon(ImagePath);
        Image img = minhaImagem.getImage();
        Image imgNew = img.getScaledInstance(btnFoto.getWidth(), btnFoto.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon imagem = new ImageIcon(imgNew);
        return imagem;
    }

    // Classes Internas
    private class MostrarConfigs implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (NUM_CLICKS_PAINELCONFIGS % 2 == 0) {
                thread1 = new Thread(new loadTela(painelConfigs));
                thread1.start();
            } else {
                painel1.remove(painelConfigs);
                resetPanel();
            }

            NUM_CLICKS_PAINELCONFIGS++;
        }

        private class loadTela implements Runnable {

            private int MAXHEIGHTP = 0;
            private int height = 0;

            public loadTela(JPanel painelConfigs) {
                MAXHEIGHTP = painelConfigs.getHeight();
            }

            @Override
            public void run() {
                try {
                    while (MAXHEIGHTP != height) {
                        painelConfigs.setBounds(640, barraLateral.getY() + 90, 125, height);
                        height += 3;
                        painel1.add(painelConfigs);
                        painel1.revalidate();
                        painel1.repaint();
                        Thread.sleep(2);
                    }
                } catch (InterruptedException ex) {
                    System.err.println(ex);
                }
            }

        }

    }

    private class EventoLogout implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new TelaMain().runTela();
            dispose();
        }

    }

    private class ShowAlterarSobreMim implements DocumentListener {

        private void vefAlterado() {
            String textoOriginal = user.getSobreMim();

            if (textoOriginal.equals(txtSobreMim.getText())) {
                btnEditSobreMim.setVisible(false);
                btnCancelarSobreMim.setVisible(false);
            } else {
                btnEditSobreMim.setVisible(true);
                btnCancelarSobreMim.setVisible(true);
            }
        }

        @Override
        public void insertUpdate(DocumentEvent de) {
            vefAlterado();
        }

        @Override
        public void removeUpdate(DocumentEvent de) {
            vefAlterado();
        }

        @Override
        public void changedUpdate(DocumentEvent de) {
            vefAlterado();
        }

    }

    private class AlterarSobreMim implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            int i = JOptionPane.showConfirmDialog(null, "Deseja Alterar o Sobre Mim?", "Alterando Sobre Mim", JOptionPane.YES_NO_OPTION, JOptionPane.PLAIN_MESSAGE);
            cc = new UserController();
            if (i == 0) {

                User usuario = user;
                usuario.setSobreMim(txtSobreMim.getText());

                if (cc.updateSobreMim(usuario)) {
                    dispose();
                    new TelaPerfil().runTela();
                }
            }
        }
    }

    private class CancelarSobreMim implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            txtSobreMim.setText(user.getSobreMim());
            btnEditSobreMim.setVisible(false);
            btnCancelarSobreMim.setVisible(false);
        }

    }

    private class EventoTrocarFotoPerfil implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            fileChosser = new JFileChooser(new File(System.getProperty("user.home"), "Documents"));
            configFilterFileChosser();
            fileChosser.addChoosableFileFilter(filtro);

            int resultado = fileChosser.showSaveDialog(null);
            cc = new UserController();
            if (resultado == JFileChooser.APPROVE_OPTION) {
                File arquivoSelecionado = fileChosser.getSelectedFile();
                String ImagePath = arquivoSelecionado.getAbsolutePath();
                if (cc.updateImage(user, ImagePath)) {
                    dispose();
                    new TelaPerfil().runTela();
                }
            }
        }
    }

    private class EventoBotao implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // TODO Auto-generated method stub

            if (e.getSource() == btnAlterarUsuario) {
                new TelaAlterar().setVisible(true);
            }
        }

    }

    public void resetPanel() {
        painel1.repaint();
        painel1.revalidate();
    }

    public void runTela() {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                telaPerfil = new TelaPerfil();

                if (telaPerfil.isVisible()) {
                    telaPerfil.dispose();
                }
                telaPerfil.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        new TelaPerfil().setVisible(true);
    }

}

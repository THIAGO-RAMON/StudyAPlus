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
import model.User;
import dao.UserDAO;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.io.FileReader;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;
import view.inicial.TelaMain;

public class TelaPerfil extends TelaPadraoFullScreen {

    private JButton btnFoto, btnLogo, leave, btnAlterarUsuario, btnLogout, btnAlterar2, btnEdit, btnEditSobreMim,
            btnCancelarSobreMim, btnSobreNos;
    private JTextArea txtSobreMim;
    protected JPanel painel1, painelB;

    private JPanel painelConfigs;
    private JButton btnAlterarFotoPerfil;
    private static int NUM_CLICKS_PAINELCONFIGS = 0;
    private boolean alteradoSobreMim = false;
    private static int alterarSobreMim = 0;
    private static int NUM_CLICKS_SOBRENOS = 0;
    private SobreNos sobreNos;
    JScrollPane painelSobreMim, painelSobreNos;

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
        txtNome.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
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
        txtSenha.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
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
        txtIdade.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
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
        txtSobreMim.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        txtSobreMim.getDocument().addDocumentListener(new ShowAlterarSobreMim());

        painelSobreMim = new JScrollPane(txtSobreMim);
        painelSobreMim.setBounds(800, 375, 400, 140);
        painelSobreMim.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelSobreMim.setBorder(null);
        painel1.add(painelSobreMim);
        
        sobreNos = new SobreNos();
        sobreNos.setPreferredSize(new Dimension(500, 1350));
        sobreNos.setBorder(null);
        
        painelSobreNos = new JScrollPane(sobreNos);
        painelSobreNos.setBounds(770, barraLateral.getY() + 160, 500, 500);
        painelSobreNos.setVisible(false);
        painelSobreNos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        painelSobreNos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        painel1.add(painelSobreNos);

        btnEditSobreMim = new JButton("Alterar");
        btnEditSobreMim.setBounds(810, 540, 165, 40);
        btnEditSobreMim.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
        btnEditSobreMim.setBackground(new Color(218, 217, 215));
        btnEditSobreMim.addActionListener(new AlterarSobreMim());
        btnEditSobreMim.setFont(new Font("Arial", 1, 20));
        btnEditSobreMim.setVisible(false);

        painel1.add(btnEditSobreMim);

        btnCancelarSobreMim = new JButton("Cancelar");
        btnCancelarSobreMim.setBounds(1025, 540, 165, 40);
        btnCancelarSobreMim.setBorder(new TelaPadraoFullScreen.BordaCantoArrendondado());
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
        leave.setBounds(painel1.getWidth() - 60, 0, 60, 30);
        painel1.add(leave);

    }

    // Metodos 
    private void painel1() {
        painel1 = new JPanel();
        painel1.setLayout(null);
        painel1.setBorder(new LineBorder(Color.BLACK.darker(),1,true));
        painel1.setBounds(0, 0, getWidth(), getHeight());
        painel1.setBackground(new Color(207, 227, 225));
        add(painel1);
    }

    private void configPainelConfigs() {
        painelConfigs = new JPanel();
        painelConfigs.setLayout(null);
        painelConfigs.setSize(125, 171);
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
        
        btnSobreNos = new JButton("Sobre nos");
        btnSobreNos.addActionListener(new MostrarSobreNos());
        btnSobreNos.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        btnSobreNos.setFont(new Font("Arial", 0, 14));
        btnSobreNos.setBackground(painel1.getBackground().darker());
        btnSobreNos.setForeground(Color.white);
        btnSobreNos.setBounds(5, 125, 115, 30);
        painelConfigs.add(btnSobreNos);

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
            Principal.user = new User("", "");
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
    
    private class MostrarSobreNos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (NUM_CLICKS_SOBRENOS % 2 == 0) {
                painelSobreNos.setVisible(true);
                txtSobreMim.setVisible(false);
                lblSobreMim.setVisible(false);
                painelSobreMim.setVisible(false);
                resetPanel();
            } else {
                painelSobreNos.setVisible(false);
                txtSobreMim.setVisible(true);
                lblSobreMim.setVisible(true);
                painelSobreMim.setVisible(true);
                resetPanel();
            }
            NUM_CLICKS_SOBRENOS++;
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
    
    private class CloseSobreNos implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent ae) {
            if (sobreNos != null) {
                painelSobreNos.setVisible(false);
                txtSobreMim.setVisible(true);
                lblSobreMim.setVisible(true);
                painelSobreMim.setVisible(true);
                resetPanel();
            }
        }

    }

    private class SobreNos extends JPanel {

        private JPanel painelSobreNos;
        private Card cardPedro, cardFelipe, cardMiguel, cardRamon;
        private Card cardInformações;
        private Informações textoInformações;
        private JLabel lblTitulo;
        private JLabel fotoMiguel, fotoFelipe, fotoRamon, fotoPedro;
        private JLabel lblNome1, lblNome2, lblNome3, lblNome4;
        private JLabel email1, email2, email3, email4;

        private JScrollPane painelScroll, painelScrollInformacoes;
        private JPanel painelExternoScroll;
        private JButton close;

        public SobreNos() {

            painelSobreNos = new JPanel(null);
            painelSobreNos.setPreferredSize(new Dimension(500, 1350));

            painelScroll = new JScrollPane(painelSobreNos, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            painelScroll.setSize(500, 500);
            painelScroll.setBorder(null);
            configPainel();

            painelExternoScroll = new JPanel(new GridLayout(1, 1));
            painelExternoScroll.setBounds(0, 0, 500, 500);
            painelExternoScroll.setBorder(null);
            painelExternoScroll.add(painelScroll);

            add(painelExternoScroll);

        }

        private void configPainel() {
            lblTitulo = new JLabel("INTEGRANTES");
            lblTitulo.setFont(new Font("Arial", 1, 32));
            lblTitulo.setBorder(BorderFactory.createLineBorder(Color.black, 2));
            lblTitulo.setBounds((painelScroll.getWidth() / 2) - 125, 30, 250, 30);
            lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
            lblTitulo.setHorizontalTextPosition(SwingConstants.CENTER);

            painelSobreNos.add(lblTitulo);

            close = new JButton("X");
            close.setBackground(new Color(223, 63, 16));
            close.addActionListener(new CloseSobreNos());
            close.setBounds(painelSobreNos.getWidth() - 30, 0, 30, 30);

            painelSobreNos.add(close);

            cardPedro = new Card(200, 300);
            cardPedro.setBounds(20, 70, cardPedro.getWidth(), cardPedro.getHeight());
            
            fotoPedro = new JLabel();
            
            lblNome1 = new JLabel("Pedro Uhlmann");
            lblNome1.setBounds(50, 200, 100, 30);
            lblNome1.setFont(new Font("Arial", 0, 14));
            cardPedro.add(lblNome1);

            email1 = new JLabel("2021321129@ifam.edu.br");
            email1.setBounds(30, 230, 200, 30);
            email1.setFont(new Font("Arial", 0, 12));
            cardPedro.add(email1);

            painelSobreNos.add(cardPedro);

            cardFelipe = new Card(200, 300);
            cardFelipe.setBounds(270, 70, cardPedro.getWidth(), cardPedro.getHeight());

            fotoFelipe = new JLabel(new ImageIcon(getClass().getResource("/images/FelipeGabril.png")));
            fotoFelipe.setBounds(25, 25, 150, 150);
            fotoFelipe.setBorder(BorderFactory.createLineBorder(Color.black));
            cardFelipe.add(fotoFelipe);
            
            lblNome2 = new JLabel("Felipe Gabriel");
            lblNome2.setBounds(50, 200, 100, 30);
            lblNome2.setFont(new Font("Arial", 0, 14));
            cardFelipe.add(lblNome2);

            email2 = new JLabel("2021334440@ifam.edu.br");
            email2.setBounds(30, 230, 200, 30);
            email2.setFont(new Font("Arial", 0, 12));
            cardFelipe.add(email2);

            painelSobreNos.add(cardFelipe);

            cardMiguel = new Card(200, 300);
            cardMiguel.setBounds(20, 400, cardPedro.getWidth(), cardPedro.getHeight());

            fotoMiguel = new JLabel(new ImageIcon(getClass().getResource("/images/MigasArcanjo.png")));
            fotoMiguel.setBounds(25, 25, 150, 150);
            fotoMiguel.setBorder(BorderFactory.createLineBorder(Color.black));
            cardMiguel.add(fotoMiguel);
            
            lblNome3 = new JLabel("Miguel Arcanjo");
            lblNome3.setBounds(50, 200, 100, 30);
            lblNome3.setFont(new Font("Arial", 0, 14));
            cardMiguel.add(lblNome3);

            email3 = new JLabel("2021333513@ifam.edu.br");
            email3.setBounds(30, 230, 200, 30);
            email3.setFont(new Font("Arial", 0, 12));
            cardMiguel.add(email3);

            painelSobreNos.add(cardMiguel);

            cardRamon = new Card(200, 300);
            cardRamon.setBounds(270, 400, cardPedro.getWidth(), cardPedro.getHeight());

            lblNome4 = new JLabel("Thiago Ramon");
            lblNome4.setBounds(50, 200, 100, 30);
            lblNome4.setFont(new Font("Arial", 0, 14));
            cardRamon.add(lblNome4);

            email4 = new JLabel("2021321684@ifam.edu.br");
            email4.setBounds(30, 230, 200, 30);
            email4.setFont(new Font("Arial", 0, 12));
            cardRamon.add(email4);

            painelSobreNos.add(cardRamon);

            cardInformações = new Card(460, 1150);
            cardInformações.setPreferredSize(new Dimension(cardInformações.getWidth(), cardInformações.getHeight()));

            textoInformações = new Informações(460, 590, new Color(207, 227, 225));
            textoInformações.setBounds(10, 10, cardInformações.getWidth() - 25, cardInformações.getHeight() - 20);
            loadHtml(textoInformações);

            textoInformações.setEditable(false);
            textoInformações.setBorder(null);

            cardInformações.add(textoInformações);

            painelScrollInformacoes = new JScrollPane(cardInformações);
            painelScrollInformacoes.setBounds(10, 730, cardInformações.getWidth() + 10, 610);
            painelScrollInformacoes.setBackground(painelSobreNos.getBackground());
            painelScrollInformacoes.setBorder(null);
            painelScrollInformacoes.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            painelSobreNos.add(painelScrollInformacoes);
        }

        private class Card extends JPanel {

            public Card(int widht, int height) {
                setSize(widht, height);
                setLayout(null);
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/OverriddenMethodBody

                Graphics2D g2d = (Graphics2D) g;

                g2d.setColor(Color.black);
                g2d.fillRoundRect(0, 0, this.getWidth(), this.getHeight(), 30, 30);

                g2d.setColor(new Color(207, 227, 225));
                g2d.fillRoundRect(0, 0, this.getWidth() - 4, this.getHeight() - 4, 20, 20);

            }

        }

        private class Informações extends JTextPane {

            public Informações(int width, int height, Color background) {
                this.setSize(width, height);
                this.setBackground(background);
                this.setContentType("text/html");
            }

        }

        private void loadHtml(JTextPane textPane) {

            FileReader leitor = null;
            try {
                String projectPath = System.getProperty("user.dir");
                String path = projectPath + "\\etc\\HTMLInformacoes.txt";
                File file = new File(path);
                leitor = new FileReader(file);

                while (leitor.read() != -1) {
                    textPane.read(leitor, null);
                }

                leitor.close();
            } catch (Exception ex) {
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

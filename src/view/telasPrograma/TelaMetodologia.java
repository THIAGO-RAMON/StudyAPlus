package view.telasPrograma;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.AbstractBorder;
import javax.swing.border.LineBorder;
import model.User;
import view.auxiliares.BarraLateral;
import view.auxiliares.Principal;
import view.auxiliares.TelaPadraoFullScreen;

public class TelaMetodologia extends TelaPadraoFullScreen {

    private JPanel painel1, painel2, painelFundamental, painelMedio, painelSuperior;
    private JTextArea txtFund, txtMedio, txtSuperior;
    private BarraLateral barraLateral;
    private JButton leave, alterarFund, alterarMedio, alterarSup, nextMedio, backFund,nextSup,backMedio;
    private TelaMetodologia metodologia;
    private JLabel lblmsg, lblmsg2, lblExtra;
    private User user = Principal.user;

    public TelaMetodologia() {
        configPainel();
        paineisMetodos();

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
        lblmsg.setBounds(20, 10, 310, 30);
        painel2.add(lblmsg);

        lblExtra = new JLabel();
        lblExtra.setFont(new Font("Arial", 1, 20));
        lblExtra.setBounds(330, 10, 200, 30);
        painel2.add(lblExtra);

        lblmsg2 = new JLabel();
        lblmsg2.setText("é recomendado que utilize esta metodologia.");
        lblmsg2.setFont(new Font("Arial", 1, 20));
        lblmsg2.setBounds(20, 40, 600, 30);
        painel2.add(lblmsg2);

        //painel Fundamental
        txtFund = new JTextArea();
        txtFund.setText("\n     Use estas metodologias: Teste prático, Autoexpliacação e Autointerrogação.\n\n\n\n\n       Consiste em 3 passos, o primeiro é o do teste prático, que simplismente é realizar práticas sobre o que\n       está querendo ser aprendido. Isso pode ser feito por meio de exercícios, repetições ou vídeos. \n\n       O segundo passo é a autoexplicação, onde basta você se imaginar como professor e começar a explicar \n       para si mesmo em voz alta, pois quando se fala em voz alta é mais fácil de compreender o raciocínio.\n\n       O terceiro passo é a autointerrogação, este passo é similar ao segungo passo, também consiste em \n       usar a voz alta, a diferença é que não irá ser explicado nada, apenas será levantado perguntas sobre \n       o tema.");
        txtFund.setFont(new Font("Arial", 0, 15));
        txtFund.setEditable(false);
        txtFund.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        txtFund.setBackground(new Color(200, 200, 200));
        JScrollPane jscFund = new JScrollPane(txtFund);
        jscFund.setBackground(new Color(200, 200, 200));
        jscFund.setBounds(0, 0, 760, 400);
        painelFundamental.add(jscFund);

        alterarFund = new JButton("Alterar");
        alterarFund.setVisible(false);
        alterarFund.setEnabled(false);
        alterarFund.addActionListener(new AlterarFundamental());
        alterarFund.setBackground(new Color(200, 200, 200));
        alterarFund.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        alterarFund.setBounds((painel2.getWidth() / 2) - 75, 530, 150, 30);
        painel2.add(alterarFund);

        //painel Medio
        txtMedio = new JTextArea();
        txtMedio.setText("\n     Use o método Robinson (EPL2R).\n\n       Para entender esta metodologia é preciso saber o que significa EPL2R:"
                + "\n\n\n      * E: Explorar. \n      *P: Perguntar. \n      *L: Ler. \n      *2R: Rememorar e Repassar."
                + "\n\n\n     Cada um desses tópicos equivale à uma etapa, a primeira é explorar tudo sobre o conteúdo desejado, reunindo"
                + "\n     todas as informaçõoes possíveis. Isso é necessário para a segunda etapa que é investigar para gerar as pergun-"
                + "\n     tas e levantar dúvidas para serem respondidas. A terceira etapa consiste em uma leitura mais aprofundada do"
                + "\n     conteúdo, percorrendo com mais cuidado e atenção as linhas de leitura. O ultimo passo é o R de rememorar, "
                + "\n     onde o intuito de fixar o que foi aprendido e depois repassar para ter ainda mais certeza."
        );

        txtMedio.setFont(new Font("Arial", 0, 15));
        txtMedio.setEditable(false);
        txtMedio.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        txtMedio.setBackground(new Color(200, 200, 200));
        JScrollPane jscMedio = new JScrollPane(txtMedio);
        jscMedio.setBackground(new Color(200, 200, 200));
        jscMedio.setBounds(0, 0, 760, 400);
        painelMedio.add(jscMedio);

        alterarMedio = new JButton("Alterar");
        alterarMedio.setVisible(false);
        alterarMedio.setEnabled(false);
        alterarMedio.addActionListener(new AlterarMedio());
        alterarMedio.setBackground(new Color(200, 200, 200));
        alterarMedio.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        alterarMedio.setBounds((painel2.getWidth() / 2) - 75, 530, 150, 30);
        painel2.add(alterarMedio);

        // painel ensino superior
        txtSuperior = new JTextArea();
        txtSuperior.setText("\n     Use o método Pomodoro.\n\n\n      A técnica pomodoro é, na verdade, um método de gestão em tempo, e uma das mais conhecidas. Pode ser "
                + "\n      aplicada para diversas finalidades inclusive nos estudos, onde mostra grande resultados. Ela consiste em es-"
                + "\n      tabelecer 25 minutos para exercícios da atividade e 15 minutos de descanso. Ou seja, estudo na maior parte"
                + "\n      do tempo, e usa outro período para recuperar o fôlego e esse ciclo é repetido."
                + "\n\n      Para colocar em prática esta técnica, é recomendado utilizar um conometro e fica aberto a alterações no tem-"
                + "\n      po. O ideal é que intercale sempre entre os períodos de dedicação com os de descanso, mas os minutos de-"
                + "\n      pende de quem aplica."
        );

        txtSuperior.setFont(new Font("Arial", 0, 15));
        txtSuperior.setEditable(false);
        txtSuperior.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        txtSuperior.setBackground(new Color(200, 200, 200));
        JScrollPane jscSup = new JScrollPane(txtSuperior);
        jscSup.setBackground(new Color(200, 200, 200));
        jscSup.setBounds(0, 0, 760, 400);
        painelSuperior.add(jscSup);

        alterarSup = new JButton("Alterar");
        alterarSup.setVisible(false);
        alterarSup.setEnabled(false);
        alterarSup.addActionListener(new AlterarSuperior());
        alterarSup.setBackground(new Color(200, 200, 200));
        alterarSup.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        alterarSup.setBounds((painel2.getWidth() / 2) - 75, 530, 150, 30);
        painel2.add(alterarSup);
        
        /*
        nextMedio = new JButton("M");backFund = new JButton("F");
        nextMedio.setBackground(new Color(200, 200, 200));
        nextMedio.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        nextMedio.setBounds(800-130, 530, 110, 30);
        painel2.add(nextMedio);
        
        
        //painel2
        backFund = new JButton("F");
        backFund.setBackground(new Color(200, 200, 200));
        backFund.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        backFund.setBounds(50, 530, 110, 30);
        painel2.add(backFund);
        
        nextSup = new JButton("S");
        nextSup.setBackground(new Color(200, 200, 200));
        nextSup.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        nextSup.setBounds(800-130, 530, 110, 30);
        painel2.add(nextSup);
        
        //painel3
        backMedio = new JButton("M");
        backMedio.setBackground(new Color(200, 200, 200));
        backMedio.setBorder(new TelaMetodologia.BordaCantoArrendondado());
        backMedio.setBounds(50, 530, 110, 30);
        painel2.add(backMedio);
        **/
        
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

    private void paineisMetodos() {
        painelFundamental = new JPanel();
        painelFundamental.setVisible(false);
        painelFundamental.setLayout(null);
        painelFundamental.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painelFundamental.setBounds(20, 80, 760, 400);
        painelFundamental.setBackground(new Color(200, 200, 200));
        painel2.add(painelFundamental);

        painelMedio = new JPanel();
        painelMedio.setVisible(false);
        painelMedio.setLayout(null);
        painelMedio.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painelMedio.setBounds(20, 80, 760, 400);
        painelMedio.setBackground(new Color(200, 200, 200));
        painel2.add(painelMedio);

        painelSuperior = new JPanel();
        painelSuperior.setVisible(false);
        painelSuperior.setLayout(null);
        painelSuperior.setBorder(new LineBorder(Color.BLACK.darker(), 1, true));
        painelSuperior.setBounds(20, 80, 760, 400);
        painelSuperior.setBackground(new Color(200, 200, 200));
        painel2.add(painelSuperior);
    }

    private void verOutras() {
        if (painelFundamental.isVisible()&& user.getIdade() <=100) {
            nextMedio.setVisible(true);
            liberaM(nextMedio);
        } else if (painelMedio.isVisible()&&user.getIdade() <=100) {
            backFund.setVisible(true);
            nextSup.setVisible(true);
            liberaF(backFund);
            liberaS(nextSup);
        }else if(painelSuperior.isVisible()&&user.getIdade() <=100){
            backMedio.setVisible(true);
            liberaM(backMedio);
        }
    }
    
    private boolean liberaF(JButton b){
        b.addActionListener(new LiberaFund());
        return b.isVisible();
    }
    
    private boolean liberaM(JButton b){
        b.addActionListener(new LiberaMedio());
        return b.isVisible();
    }
    
    private boolean liberaS(JButton b){
        b.addActionListener(new LiberaSup());
        return b.isVisible();
    }
    

    private class LiberaFund implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            painelFundamental.setVisible(true);
            painelMedio.setVisible(false);
            painelSuperior.setVisible(false);
            
            alterarFund.setVisible(true);
            alterarFund.setEnabled(true);
            
            backFund.setVisible(false);
            nextSup.setVisible(false);
            nextMedio.setVisible(true);
        }

    }

    private class LiberaMedio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            painelFundamental.setVisible(false);
            painelMedio.setVisible(true);
            painelSuperior.setVisible(false);

            alterarMedio.setVisible(true);
            alterarMedio.setEnabled(true);
            
            nextMedio.setVisible(false);
            backFund.setVisible(true);
            nextSup.setVisible(true);
        }
    }

    private class LiberaSup implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            painelFundamental.setVisible(false);
            painelMedio.setVisible(false);
            painelSuperior.setVisible(true);

            alterarSup.setVisible(true);
            alterarSup.setEnabled(true);
            
            backFund.setVisible(false);
            nextSup.setVisible(false);
            backMedio.setVisible(true);
        }
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
            painelFundamental.setVisible(true);
            painelMedio.setVisible(false);
            painelSuperior.setVisible(false);

            alterarFund.setVisible(true);
            alterarFund.setEnabled(true);
            
        } else if (user.getIdade() >= 15 && user.getIdade() <= 17) {
            lblExtra.setText("ensino médio,");
            painelFundamental.setVisible(false);
            painelMedio.setVisible(true);
            painelSuperior.setVisible(false);

            alterarMedio.setVisible(true);
            alterarMedio.setEnabled(true);
            
        } else if (user.getIdade() > 17) {
            lblExtra.setText("ensino superior");
            painelFundamental.setVisible(false);
            painelMedio.setVisible(false);
            painelSuperior.setVisible(true);

            alterarSup.setVisible(true);
            alterarSup.setEnabled(true);
            
        }
        
    }

    private class AlterarFundamental implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtFund.setEditable(true);
        }
    }

    private class AlterarMedio implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtMedio.setEditable(true);
        }
    }

    private class AlterarSuperior implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            txtSuperior.setEditable(true);
        }
    }

    public class BordaCantoArrendondado extends AbstractBorder {

        private final BasicStroke CONTORNO = new BasicStroke(2);

        @Override
        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {

            super.paintBorder(c, g, x, y, width, height);

            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setStroke(CONTORNO);

            g2d.setColor(Color.black);
            g2d.drawRoundRect(x, y, width - 1, height - 1, 15, 15);

        }
    }

    public static void main(String[] args) {
        new TelaMetodologia().setVisible(true);
    }
}

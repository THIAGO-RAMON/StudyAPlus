package view.Main;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.swing.ImageIcon;

import model.dao.UserDAO;
import view.inicial.TelaMain;
import view.telasPrograma.TelaTarefas;

public class Principal {
    
    public static TelaMain tl = new TelaMain();
    public static TelaTarefas tela = new TelaTarefas();

    public static void main(String[] args) {
        
        tl.runTela();
    }
    
}

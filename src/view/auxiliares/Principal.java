package view.auxiliares;

import model.User;

import view.inicial.TelaMain;

public class Principal {    
    
    public static TelaMain tl = new TelaMain();
//    public static TelaTarefas tela = new TelaTarefas();
    public static User user = new User();
    
    public static void main(String[] args) {
        new Splash();
        tl.runTela();
    }
    
}

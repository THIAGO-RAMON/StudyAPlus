/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.UserDAO;
import java.util.List;
import model.bean.User;

/**
 *
 * @author Migas
 */
public class UserController {
    
    public boolean saveUser(User user){
        return new UserDAO().saveCadastro(user);
    }
    
    public boolean updateUser(User userOld, User userNew){
        return new UserDAO().updateUser(userOld, userNew);
    }
    
    public boolean updateDesempenho(User user, double  porcentagem){
        return new UserDAO().updateDesempenho(user, porcentagem);
    }
    
    public double getDesempenho(User user){
        return new UserDAO().getPorcentagem(user);
    }
    
    public boolean updateImage(User user, String ImagePath){
        return new UserDAO().updateImagemUsuario(user, ImagePath);
    }
    
    public String getImageUser(User user){
        return new UserDAO().getImagemUsuario(user);
    }
    
    public List<User> listUser(){
        return new UserDAO().listUsers();
    }
    
    public boolean updateSobreMim(User user){
        return new UserDAO().updateSobreMim(user);
    }
    
    
}

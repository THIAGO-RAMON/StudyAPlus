/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import java.util.List;
import model.Objetivo;
import model.User;
import dao.ObjetivoDAO;

/**
 *
 * @author Migas
 */
public class ObjetivoController {

    public void saveObj(User user, String descricao, String dataInic) {

        Objetivo obj = new Objetivo();
        obj.setUser(user);
        obj.setDescricao(descricao);
        obj.setDataInic(dataInic);

        new ObjetivoDAO().saveObjetivo(obj);
    }

    public boolean updateObj(Objetivo objAntigo, Objetivo objNovo) {
        return new ObjetivoDAO().updateObjetivo(objAntigo, objNovo);
    }

    public boolean deleteObjetivo(Objetivo objetivo) {
        return new ObjetivoDAO().deleteObjetivo(objetivo);
    }

    public List<Objetivo> listObj(User user) {
        ObjetivoDAO dao = new ObjetivoDAO();

        return dao.listObjetivo(user);
    }

}

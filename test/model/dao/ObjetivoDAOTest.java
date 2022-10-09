/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.dao;

import javax.swing.JOptionPane;
import model.bean.Objetivo;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Migas
 */
public class ObjetivoDAOTest {
    
    public ObjetivoDAOTest() {
        ObjetivoDAO dao = new ObjetivoDAO();
        Objetivo obj = new Objetivo("Mas q", "00/00/00");
        for (Objetivo o : dao.listObjetivo(obj)) {
            JOptionPane.showMessageDialog(null, o.getId());
        }
    }

    @Test
    public void testSomeMethod() {
    }
    
}

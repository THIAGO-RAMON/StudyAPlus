/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package model.dao;

import javax.swing.JOptionPane;
import model.bean.Task;
import model.bean.User;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Migas
 */
public class TaskDAOTest {
    
    public TaskDAOTest() {
        TaskDAO dao = new TaskDAO();
        User user = new User("Migas", "123");
        Task task = new Task(user, "123", "123", "23/12/2005", "23/12/2005", false, false);
        
    }

    @Test
    public void testSomeMethod() {
    }
    
}

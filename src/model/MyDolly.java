/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author dudal
 */
public class MyDolly {
    
    private int id;
    private User user;
    private String cabeca;
    private String torso;
    private String perna;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getCabeca() {
        return cabeca;
    }

    public void setCabeca(String cabeca) {
        this.cabeca = cabeca;
    }

    public String getTorso() {
        return torso;
    }

    public void setTorso(String torso) {
        this.torso = torso;
    }

    public String getPerna() {
        return perna;
    }

    public void setPerna(String perna) {
        this.perna = perna;
    }

    public MyDolly(User user, String cabeca, String torso, String perna) {
        this.user = user;
        this.cabeca = cabeca;
        this.torso = torso;
        this.perna = perna;
    }

    public MyDolly() {
    }
    
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Migas
 */
public class Objetivo {

    private User user;
    private int id;
    private String descricao;
    private String dataInic;

    public String getDataInic() {
        return dataInic;
    }

    public void setDataInic(String dataInic) {
        this.dataInic = dataInic;
    }

    public Objetivo() {
    }

    public Objetivo(User user, String descricao, String dataInic) {
        this.user = user;
        this.descricao = descricao;
        this.dataInic = dataInic;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}

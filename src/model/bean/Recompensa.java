/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author Migas
 */
public class Recompensa {

    private String nome;
    private String descricao;
    private String img;
    private boolean habilitado;
    private User user;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public boolean isHabilitado() {
        return habilitado;
    }

    public void setHabilitado(boolean habilitado) {
        this.habilitado = habilitado;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Recompensa() {
    }

    public Recompensa(User user, String nome, String descricao, String imgPath) {
        this.user = user;
        this.nome = nome;
        this.descricao = descricao;
        this.img = imgPath;
        this.habilitado = false;
    }
    
}

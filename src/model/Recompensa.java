/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Migas
 */
public class Recompensa {

    private int id;
    private String nome;
    private String descricao;
    private String img;
    private boolean habilitado;
    private String tipo;
    private User user;
    private Desafio desafio;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Desafio getDesafio() {
        return desafio;
    }

    public void setDesafio(Desafio desafio) {
        this.desafio = desafio;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public Recompensa() {
    }

    public Recompensa(User user, Desafio desafio, String nome, String descricao, String img, boolean habilitado) {
        this.nome = nome;
        this.descricao = descricao;
        this.img = img;
        this.habilitado = false;
        this.user = user;
        this.desafio = desafio;
    }
    
    
    
}

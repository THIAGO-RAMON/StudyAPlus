/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import javax.swing.ImageIcon;

/**
 *
 * @author Migas
 */
public class Recompensa {

    private String nome;
    private String descricao;
    private String img;

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

    public Recompensa() {
    }

    public Recompensa(String nome, String descricao, String imgPath) {
        this.nome = nome;
        this.descricao = descricao;
        this.img = img;
    }
    
}

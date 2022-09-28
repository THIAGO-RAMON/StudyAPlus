package model.bean;

import java.sql.Date;

public class Task {
    
    private int id;
    private User user;
    private String titulo;
    private String descricao;
    private Date dataInic;
    private Date dataFim;
    private int qtd;
    private boolean importancia; 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return User return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(User user) {
        this.user = user;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    
    /**
     * @return String return the descricao
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * @param descricao the descricao to set
     */
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    /**
     * @return Date return the dataInic
     */
    public Date getDataInic() {
        return dataInic;
    }

    /**
     * @param dataInic the dataInic to set
     */
    public void setDataInic(Date dataInic) {
        this.dataInic = dataInic;
    }

    /**
     * @return Date return the dataFim
     */
    public Date getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(Date dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return boolean return the importancia
     */
    public boolean isImportancia() {
        return importancia;
    }

    /**
     * @param importancia the importancia to set
     */
    public void setImportancia(boolean importancia) {
        this.importancia = importancia;
    }

    public int getQtd() {
        return qtd;
    }

    public void setQtd(int qtd) {
        this.qtd = qtd;
    }

    public Task(){

    }

    public Task(User user, String titulo, String descricao, Date dataInic, Date dataFim){
        this.user = user;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInic = dataInic;
        this.dataFim = dataFim;
    }

}

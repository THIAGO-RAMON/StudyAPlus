package model.bean;

import java.sql.Date;

public class Task {
    
    private User user;
    private String descricao;
    private Date dataInic;
    private Date dataFim;
    private boolean importancia; 

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

    public Task(){

    }

    public Task(User user, String descricao, Date dataInic, Date dataFim){
        this.user = user;
        this.descricao = descricao;
        this.dataInic = dataInic;
        this.dataFim = dataFim;
    }

}

package model;

public class Task {
    
    private int id;
    private User user;
    private String titulo;
    private String descricao;
    private String dataInic;
    private String dataFim;
    private int qtd;
    private boolean importante; 
    private boolean concluido;

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
    public String getDataInic() {
        return dataInic;
    }

    /**
     * @param dataInic the dataInic to set
     */
    public void setDataInic(String dataInic) {
        this.dataInic = dataInic;
    }

    /**
     * @return Date return the dataFim
     */
    public String getDataFim() {
        return dataFim;
    }

    /**
     * @param dataFim the dataFim to set
     */
    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    /**
     * @return boolean return the importante
     */
    public boolean isImportante() {
        return importante;
    }

    /**
     * @param importancia the importante to set
     */
    public void setImportante(boolean importancia) {
        this.importante = importancia;
    }

    public boolean isConcluido() {
        return concluido;
    }

    public void setConcluido(boolean concluido) {
        this.concluido = concluido;
    }
    
    

    public Task(){

    }

    public Task(User user, String titulo, String descricao, String dataInic, String dataFim, boolean importante, boolean concluido){
        this.user = user;
        this.titulo = titulo;
        this.descricao = descricao;
        this.dataInic = dataInic;
        this.dataFim = dataFim;
        this.importante = importante;
        this.concluido = concluido;
    }

}

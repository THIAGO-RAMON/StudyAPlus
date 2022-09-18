package model.bean;

public class User {
    
    private String nome;
    private String senha;
    private String sobreMim;
    private String escolaridade;
    private String objetivo;
    
    /**
     * @return String return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return String return the senha
     */
    public String getSenha() {
        return senha;
    }

    /**
     * @param senha the senha to set
     */
    public void setSenha(String senha) {
        this.senha = senha;
    }

    /**
     * @return String return the sobreMim
     */
    public String getSobreMim() {
        return sobreMim;
    }

    /**
     * @param sobreMim the sobreMim to set
     */
    public void setSobreMim(String sobreMim) {
        this.sobreMim = sobreMim;
    }

    /**
     * @return String return the escolaridade
     */
    public String getEscolaridade() {
        return escolaridade;
    }

    /**
     * @param escolaridade the escolaridade to set
     */
    public void setEscolaridade(String escolaridade) {
        this.escolaridade = escolaridade;
    }

    /**
     * @return String return the objetivo
     */
    public String getObjetivo() {
        return objetivo;
    }

    /**
     * @param objetivo the objetivo to set
     */
    public void setObjetivo(String objetivo) {
        this.objetivo = objetivo;
    }

    public User(){

    }

    public User(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

}

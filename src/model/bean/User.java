package model.bean;

public class User {
    
    private String nome;
    private String senha;
    private int idade;
    private String sobreMim;
    private String escolaridade;
    private double desempenho_percentual;
    private String img;


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
     * @return Integer return the idade
     */
    public int getIdade() {
        return idade;
    }

    /**
     * @param idade the senha to set
     */
    public void setIdade(int idade) {
        this.idade= idade;
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
  

    public double getDesempenho_percentual() {
        return desempenho_percentual;
    }

    public void setDesempenho_percentual(double desempenho_percentual) {
        this.desempenho_percentual = desempenho_percentual;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public User(){

    }

    public User(String nome, String senha){
        this.nome = nome;
        this.senha = senha;
    }

}

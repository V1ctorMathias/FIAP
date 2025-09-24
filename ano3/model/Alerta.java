package model;

public class Alerta {
    private int id;
    private String tipo;
    private String mensagem;

    public Alerta() {}

    public Alerta(int id, String tipo, String mensagem) {
        this.id = id;
        this.tipo = tipo;
        this.mensagem = mensagem;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getMensagem() { return mensagem; }
    public void setMensagem(String mensagem) { this.mensagem = mensagem; }
}

public class Relatorio {

    private String tipo = "tabela";
    private String conteudo = "";

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public void relatorio(double valor, int contador) {

        if (tipo.equals("tabela")) {
            this.conteudo = "==================\nMês " + (contador + 1) + ": R$ " + valor + "\n==================";
            System.out.println(conteudo);
        }
    }
}

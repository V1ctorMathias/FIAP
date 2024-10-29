public class Relatorio {

    private String tipo = "tabela";
    private String conteudo = "";

    public void relatorio(double valor, int contador) {

        if (tipo == "tabela") {
            this.conteudo = "==================\nMês "+(contador + 1)+": R$ " + valor + "\n==================";
            System.out.println(conteudo);
        }

    }

}

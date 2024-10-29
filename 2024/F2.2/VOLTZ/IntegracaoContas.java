public class IntegracaoContas {
    
    Empresa empresa = new Empresa();

    private int contador = 0;

    public void adicionarConta(String empresa) {
        this.empresa.nomeEmpresa = empresa;
        this.empresa.identificador = contador;
        this.contador ++;
        System.out.println("Empresa: "+this.empresa.nomeEmpresa+" adicionada com sucesso!");
    }
    
    public void removerConta() {
        System.out.println("Empresa: "+this.empresa.nomeEmpresa+" removida com sucesso!");
        this.empresa.nomeEmpresa = "";
    }

}

public class IntegracaoContas {
    
    Empresa empresa = new Empresa();

    private int contador = 0;

    public void adicionarConta(String empresa) {
        this.empresa.setNomeEmpresa(empresa);
        this.empresa.setIdentificador(contador);
        this.contador ++;
        System.out.println("Empresa: "+this.empresa.getNomeEmpresa()+" adicionada com sucesso!");
    }
    
    public void removerConta() {
        System.out.println("Empresa: "+this.empresa.getNomeEmpresa()+" removida com sucesso!");
        this.empresa.setNomeEmpresa("");
    }

}

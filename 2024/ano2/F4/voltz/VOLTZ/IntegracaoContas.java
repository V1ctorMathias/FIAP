public class IntegracaoContas {
    
    Empresa empresa = new Empresa();

    public void adicionarConta(String empresa) {
        this.empresa.setNomeEmpresa(empresa);
        System.out.println("Empresa: "+empresa+" adicionada com sucesso!");
    }
    
    public void removerConta(int id) {
        System.out.println("Empresa: "+this.empresa.getNomeEmpresa(id)+" removida com sucesso!");
        this.empresa.removeEmpresa(id);
    }

}

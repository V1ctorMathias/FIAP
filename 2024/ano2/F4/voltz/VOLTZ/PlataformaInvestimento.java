import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PlataformaInvestimento extends Usuario {

    static PlataformaInvestimento plat = new PlataformaInvestimento();
    InterfaceMonitoramento monitoramento = new InterfaceMonitoramento();
    Usuario usuarioClasse = new Usuario();
    IntegracaoContas integracaoContas = new IntegracaoContas();
    Empresa empresa = new Empresa();

    // Override do logout vindo da classe Usuario
    @Override
    public void logout() {
        try {
            this.usuarioClasse.setEstaLogado(false);
            System.out.println("Você foi deslogado!");
        } catch (Exception e) {
            System.out.println("Erro no método de logout: " + e);
        }
    }

    Scanner sc = new Scanner(System.in);

    private double[] valores = new double[12];
    private double somaValores = 0;
    private double inputInvestInicial = 0;
    private int contador = 0;

    public double[] getValores() {
        return valores;
    }

    public void setValores(double[] valores) {
        this.valores = valores;
    }

    public double getSomaValores() {
        return somaValores;
    }

    public void setSomaValores(double somaValores) {
        this.somaValores = somaValores;
    }

    public double getInputInvestInicial() {
        return inputInvestInicial;
    }

    public void setInputInvestInicial(double inputInvestInicial) {
        this.inputInvestInicial = inputInvestInicial;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void loginCadastro() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            if (!this.usuarioClasse.isEstaLogado()) {
                System.out.println("Gostaria de realizar cadastro [0] ou login [1]: ");
                int cadastroLogin = sc.nextInt();

                if (cadastroLogin == 0) {

                    System.out.println("Deseja vincular um email a conta? [0] não ou [1] sim: ");
                    double vincularEmail = sc.nextInt();

                    if (vincularEmail == 1) {
                        System.out.println("Adicione o email: ");
                        String email = sc.next();
                        System.out.println("Agora um nome de usuário: ");
                        String usuario = sc.next();
                        System.out.println("Agora uma senha: ");
                        String senha = sc.next();
                        // Overload da classe Usuario adicionado email
                        this.usuarioClasse.cadastrarUsuario(email, usuario, senha);
                    } else {
                        System.out.println("Agora um nome de usuário: ");
                        String usuario = sc.next();
                        System.out.println("Agora uma senha: ");
                        String senha = sc.next();
                        // Overload da classe Usuario retirado email
                        this.usuarioClasse.cadastrarUsuario(usuario, senha);
                    }

                    loginCadastro();

                } else if (cadastroLogin == 1) {
                    System.out.println("Digite seu usuário: ");
                    String usuario = sc.next();
                    System.out.println("Agora sua senha: ");
                    String senha = sc.next();

                    this.usuarioClasse.login(usuario, senha);

                    if (!this.usuarioClasse.isEstaLogado()) {
                        loginCadastro();
                    } else {
                        System.out.println("Deseja adicionar uma conta empresa a este usuário? Não [0] Sim [1]");
                        int adicionaContaEmpresa = sc.nextInt();

                        if (adicionaContaEmpresa == 1) {
                            System.out.println("Digite o nome da empresa: ");
                            String nomeEmpresa = sc.next();
                            this.integracaoContas.adicionarConta(nomeEmpresa);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no método de LoginCadastro: " + e);
        }

    }

    public void geraRelatorio() {
        try {
            if (this.contador == 0) {
                System.out.println("Adicione o valor investido inicialmente: ");
                this.inputInvestInicial = sc.nextDouble();
            }

            System.out.println("Adicione a cotação da cripto em um mês: ");
            double inputValor = sc.nextDouble();

            this.valores[this.contador] = inputValor;

            System.out.println("Gostaria de adicionar outro valor?\nNão [0]\nSim [1] ");
            int continuaLoop = sc.nextInt();

            if (continuaLoop == 1) {
                this.contador++;
                this.geraRelatorio();
            } else {
                System.out.println("Visualizar margem [0]\nVisualizar diferença [1]");
                int tipoAlerta = sc.nextInt();

                for (int cont = 0; cont <= this.contador; cont++) {
                    this.somaValores += this.valores[cont];
                }

                monitoramento.exibirDados(this.contador, this.valores, inputInvestInicial, tipoAlerta);

                System.out.println("Escolha uma empresa para vincular os dados: ");
                for (int id = 1; id < this.integracaoContas.empresa.getLengthLstEmpresa(); id++) {
                    System.out.println(this.integracaoContas.empresa.getNomeEmpresa(id)+"["+id+"]");
                }
                int empresaEscolhida = sc.nextInt();

                if (this.integracaoContas.empresa.getNomeEmpresa(empresaEscolhida) != null) {
                    System.out.println(
                            this.usuarioClasse.getUsuario()
                                    + ", todos esses dados foram adicionados a sua conta empresa");
                    System.out.println("Deseja manter o vínculo com a empresa? Não [0] Sim [1]");
                    int manterEmpresa = sc.nextInt();

                    if (manterEmpresa == 0) {
                        // this.integracaoContas.removerConta();
                    }
                }
                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Erro no método de geraRelatorio: " + e);
        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            plat.loginCadastro();
            plat.geraRelatorio();
            plat.logout();
        } catch (Exception e) {
            System.out.println("Opa, ocorreu um erro!");
        }

    }
}

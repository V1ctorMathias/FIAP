import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class PlataformaInvestimento {

    static PlataformaInvestimento plat = new PlataformaInvestimento();
    InterfaceMonitoramento monitoramento = new InterfaceMonitoramento();
    Usuario usuarioClasse = new Usuario();
    IntegracaoContas integracaoContas = new IntegracaoContas();
    Empresa empresa = new Empresa();

    Scanner sc = new Scanner(System.in);

    public double[] valores = new double[12];
    public double somaValores = 0;
    public double inputInvestInicial = 0;

    public int contador = 0;

    public void loginCadastro() throws NoSuchAlgorithmException, UnsupportedEncodingException {

        if (this.usuarioClasse.estaLogado == false) {

            System.out.println("gostaria de realizar cadastro [0] ou login [1]: ");
            int cadastroLogin = sc.nextInt();

            if (cadastroLogin == 0) {

                System.out.println("Adicione um email: ");
                String email = sc.next();

                System.out.println("Agora um nome de usuario: ");
                String usuario = sc.next();

                System.out.println("Agora uma senha: ");
                String senha = sc.next();

                this.usuarioClasse.cadastrarUsuario(email, usuario, senha);
                loginCadastro();

            } else if (cadastroLogin == 1) {

                System.out.println("Digite seu usuario: ");
                String usuario = sc.next();

                System.out.println("Agora sua senha: ");
                String senha = sc.next();

                this.usuarioClasse.login(usuario, senha);

                if (this.usuarioClasse.estaLogado == false) {
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

    }

    public void logout() {
        if (this.usuarioClasse.estaLogado == true) {
            this.usuarioClasse.logout();
        }

    }

    public void geraRelatorio() {

        int continuaLoop = 0;

        if (this.contador == 0) {
            System.out.println("Adicione o valor investido inicialmente: ");
            this.inputInvestInicial = sc.nextDouble();
        }

        System.out.println("Adicione a cotação da cripto em um mês: ");
        double inputValor = sc.nextDouble();

        // Recebe os valores vindos do input
        this.valores[this.contador] = inputValor;

        System.out.println("Gostaria de adicionar outro valor?\nNão [0]\nSim [1] ");
        continuaLoop = sc.nextInt();

        if (continuaLoop == 1) {
            this.contador = this.contador + 1;
            this.geraRelatorio();
        } else {

            System.out.println("Visualizar margem [0]\nVisualizar diferença [1]");
            int tipoAlerta = sc.nextInt();

            for (int cont = 0; cont <= this.contador; cont++) {
                this.somaValores += this.valores[cont];
            }

            monitoramento.exibirDados(this.contador, this.valores, inputInvestInicial, tipoAlerta);

            if (this.integracaoContas.empresa.nomeEmpresa != "") {
                System.out.println(this.usuarioClasse.usuario
                        + ", todos esses dados foram adicionados a sua conta empresa");
                System.out.println("Deseja manter o vinculo com a empresa? Não [0] Sim [1]");

                int manterEmpresa = sc.nextInt();

                if (manterEmpresa == 0) {
                    this.integracaoContas.removerConta();
                }
            }

            sc.close();
        }

    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        plat.loginCadastro();
        plat.geraRelatorio();
        plat.logout();
    }

}

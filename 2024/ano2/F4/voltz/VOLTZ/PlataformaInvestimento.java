import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import java.util.Scanner;

public class PlataformaInvestimento extends Usuario {
    static PlataformaInvestimento plat = new PlataformaInvestimento();

    InterfaceMonitoramento monitoramento = new InterfaceMonitoramento();
    Usuario usuarioClasse = new Usuario();
    IntegracaoContas integracaoContas = new IntegracaoContas();
    Empresa empresa = new Empresa();
    Conexao conexaoClasse = new Conexao();

    Scanner sc = new Scanner(System.in);

    private double[] valores = new double[12];
    private double somaValores = 0;
    private double inputInvestInicial = 0;
    private int contador = 0;

    // GETTERS E SETTERS

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

    // Override do logout vindo da classe Usuario
    @Override
    public void logout() {
        try {
            this.usuarioClasse.setEstaLogado(false);
            Conexao.logado = false;
            System.out.println("Você foi deslogado!");
        } catch (Exception e) {
            System.out.println("Erro no método de logout: " + e);
        }
    }

    // Método para login e cadastro
    public void loginCadastro() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            if (!this.usuarioClasse.isEstaLogado()) {
                System.out.println("Gostaria de realizar cadastro [0] ou login [1]: ");
                int cadastroLogin = sc.nextInt();

                if (cadastroLogin == 0) {

                    System.out.println("Deseja vincular um email a conta? [0] não ou [1] sim: ");
                    int vincularEmail = sc.nextInt();

                    if (vincularEmail == 1) {
                        sc.nextLine(); // consumir \n pendente
                        System.out.println("Adicione o email: ");
                        String email = sc.nextLine();
                        System.out.println("Agora um nome de usuário: ");
                        String usuario = sc.nextLine();
                        System.out.println("Agora uma senha: ");
                        String senha = sc.nextLine();
                        // Overload da classe Usuario adicionado email
                        this.usuarioClasse.cadastrarUsuario(email, usuario, senha);
                    } else {
                        sc.nextLine(); // consumir \n pendente
                        System.out.println("Agora um nome de usuário: ");
                        String usuario = sc.nextLine();
                        System.out.println("Agora uma senha: ");
                        String senha = sc.nextLine();
                        // Overload da classe Usuario retirado email
                        this.usuarioClasse.cadastrarUsuario(usuario, senha);
                    }

                    loginCadastro();

                } else if (cadastroLogin == 1) {
                    sc.nextLine(); // consumir \n pendente
                    System.out.println("Digite seu usuário: ");
                    String usuario = sc.nextLine();
                    System.out.println("Agora sua senha: ");
                    String senha = sc.nextLine();

                    this.usuarioClasse.login(usuario, senha);

                    if (!this.usuarioClasse.isEstaLogado()) {
                        loginCadastro();
                    } else {
                        System.out.println("Deseja adicionar uma conta empresa a este usuário? Não [0] Sim [1]");
                        int adicionaContaEmpresa = sc.nextInt();

                        if (adicionaContaEmpresa == 1) {
                            sc.nextLine(); // consumir \n pendente
                            System.out.println("Digite o nome da empresa: ");
                            String nomeEmpresa = sc.nextLine();
                            this.integracaoContas.adicionarConta(nomeEmpresa);
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Erro no método de LoginCadastro: " + e);
        }
    }

    // Método para gerar relatório
    public void geraRelatorio() {
        try {
            if (this.contador == 0) {
                System.out.println("Adicione o valor investido inicialmente: ");
                this.inputInvestInicial = sc.nextDouble();
            }

            System.out.println("Adicione a cotação da cripto: ");
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

                sc.close();
            }
        } catch (Exception e) {
            System.out.println("Erro no método de geraRelatorio: " + e);
        }
    }

    // MENU PRINCIPAL SIMPLIFICADO
    public void menuPrincipal() {
        int opcao;

        do {
            System.out.println("\n=== MENU PRINCIPAL ===");
            System.out.println("1. Usuários");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> menuUsuarios();
                case 0 -> System.out.println("Saindo...");
                default -> System.out.println("Opção inválida!");
            }
        } while (opcao != 0);
    }

    // SUBMENU USUÁRIOS
    private void menuUsuarios() {
        int opcao;
        do {
            System.out.println("\n--- Submenu Usuários ---");
            System.out.println("1. Incluir Usuário");
            System.out.println("2. Alterar Usuário");
            System.out.println("3. Excluir Usuário");
            System.out.println("4. Exibir Todos os Usuários");
            System.out.println("5. Exibir um Usuário");
            System.out.println("0. Voltar");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch (opcao) {
                    case 1 -> incluirUsuario();
                    case 2 -> alterarUsuario();
                    case 3 -> excluirUsuario();
                    case 4 -> exibirTodosUsuarios();
                    case 5 -> exibirUsuario();
                    case 0 -> System.out.println("Voltando ao menu principal...");
                    default -> System.out.println("Opção inválida!");
                }
            } catch (Exception e) {
                System.out.println("Erro: " + e.getMessage());
            }
        } while (opcao != 0);
    }

    // MÉTODOS DO SUBMENU USUÁRIOS
    private void incluirUsuario() throws NoSuchAlgorithmException, UnsupportedEncodingException {
        System.out.print("Email: ");
        String email = sc.nextLine();
        System.out.print("Usuário: ");
        String nome = sc.nextLine();
        System.out.print("Senha: ");
        String senha = sc.nextLine();

        usuarioClasse.cadastrarUsuario(email, nome, senha);
    }

    private void alterarUsuario() throws SQLException {
        System.out.print("Usuário a ser alterado: ");
        String nome = sc.nextLine();
        if (!usuarioClasse.isUsuarioCadastrado(nome)) {
            System.out.println("Usuário não encontrado.");
            return;
        }
        System.out.print("Nova senha: ");
        String novaSenha = sc.nextLine();
        System.out.print("Novo email: ");
        String novoEmail = sc.nextLine();
        usuarioClasse.setUsuarioSenha(nome, novaSenha, novoEmail);
        System.out.println("Usuário atualizado com sucesso.");
    }

    private void excluirUsuario() {
        System.out.print("Usuário a ser excluído: ");
        String nome = sc.nextLine();
        if (usuarioClasse.lstUsuario.remove(nome) != null) {
            System.out.println("Usuário removido com sucesso.");
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    private void exibirTodosUsuarios() {
        if (usuarioClasse.lstUsuario.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado.");
        } else {
            System.out.println("Lista de usuários:");
            usuarioClasse.lstUsuario.forEach((k, v) -> System.out.println("Usuário: " + k + ", Senha: " + v));
        }
    }

    private void exibirUsuario() {
        System.out.print("Digite o nome do usuário: ");
        String nome = sc.nextLine();
        if (usuarioClasse.isUsuarioCadastrado(nome)) {
            System.out.println("Usuário: " + nome + ", Senha: " + usuarioClasse.lstUsuario.get(nome));
        } else {
            System.out.println("Usuário não encontrado.");
        }
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        try {
            plat.loginCadastro();
            plat.menuPrincipal();
            plat.geraRelatorio();
            plat.logout();
        } catch (Exception e) {
            System.out.println("Opa, ocorreu um erro: " + e.getMessage());
        }
    }
}
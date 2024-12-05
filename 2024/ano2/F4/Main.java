import com.google.gson.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

// Superclasse Tarifa
abstract class Tarifa {
    protected String nome;
    protected float precoKWh;

    public Tarifa(String nome, float precoKWh) {
        this.nome = nome;
        this.precoKWh = precoKWh;
    }

    public String getNome() {
        return nome;
    }

    public float getPrecoKWh() {
        return precoKWh;
    }

    public abstract float obterCusto(float consumo);
}

class TarifaFixa extends Tarifa {
    public TarifaFixa(String nome, float precoKWh) {
        super(nome, precoKWh);
    }

    @Override
    public float obterCusto(float consumo) {
        return consumo * precoKWh;
    }
}

class TarifaVariavel extends Tarifa {
    private float precoForaPico;

    public TarifaVariavel(String nome, float precoPico, float precoForaPico) {
        super(nome, precoPico);
        this.precoForaPico = precoForaPico;
    }

    public float getPrecoForaPico() {
        return precoForaPico;
    }

    @Override
    public float obterCusto(float consumo) {
        // Simulação de cálculo com base em 70% fora de pico e 30% no pico
        return consumo * (0.3f * precoKWh + 0.7f * precoForaPico);
    }
}

// Classe Usuario
class Usuario {
    private String nome;
    private String endereco;
    private List<ConsumoEnergia> consumoHistorico;

    public Usuario(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
        this.consumoHistorico = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarConsumo(ConsumoEnergia consumo) {
        consumoHistorico.add(consumo);
    }

    public float obterConsumoMedio() {
        return (float) consumoHistorico.stream()
                .mapToDouble(ConsumoEnergia::getConsumoKWh)
                .average()
                .orElse(0);
    }

    public List<ConsumoEnergia> getConsumoHistorico() {
        return consumoHistorico;
    }
}

// Classe ConsumoEnergia
class ConsumoEnergia {
    private Date dataRegistro;
    private float consumoKWh;

    public ConsumoEnergia(Date dataRegistro, float consumoKWh) {
        this.dataRegistro = dataRegistro;
        this.consumoKWh = consumoKWh;
    }

    public Date getDataRegistro() {
        return dataRegistro;
    }

    public float getConsumoKWh() {
        return consumoKWh;
    }
}

// Classe FornecedorEnergia
class FornecedorEnergia {
    private String nome;
    private List<Tarifa> tarifas;

    public FornecedorEnergia(String nome, List<Tarifa> tarifas) {
        this.nome = nome;
        this.tarifas = tarifas;
    }

    public String getNome() {
        return nome;
    }

    public List<Tarifa> getTarifas() {
        return tarifas;
    }
}

// Classe Main
public class Main {
    private static final List<Usuario> usuarios = new ArrayList<>();
    private static final List<FornecedorEnergia> fornecedores = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        carregarFornecedoresDaAPI();

        int opcao;
        do {
            exibirMenu();
            opcao = obterEntradaInt(scanner, "Escolha uma opção: ");
            switch (opcao) {
                case 1 -> cadastrarUsuario(scanner);
                case 2 -> registrarConsumo(scanner);
                case 3 -> sugerirPlano(scanner);
                case 4 -> System.out.println("Saindo do programa. Até logo!");
                default -> System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 4);
    }

    private static void exibirMenu() {
        System.out.println("\n=== Menu de Opções ===");
        System.out.println("1. Cadastrar Usuário");
        System.out.println("2. Registrar Consumo");
        System.out.println("3. Sugerir Plano");
        System.out.println("4. Sair");
    }

    private static void cadastrarUsuario(Scanner scanner) {
        System.out.println("\n--- Cadastrar Usuário ---");
        System.out.print("Digite o nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite o endereço: ");
        String endereco = scanner.nextLine();
        usuarios.add(new Usuario(nome, endereco));
        System.out.println("Usuário cadastrado com sucesso!");
    }

    private static void registrarConsumo(Scanner scanner) {
        System.out.println("\n--- Registrar Consumo ---");
        if (usuarios.isEmpty()) {
            System.out.println("Nenhum usuário cadastrado. Cadastre um usuário primeiro.");
            return;
        }
        Usuario usuario = selecionarUsuario(scanner);
        float consumo = obterEntradaFloat(scanner, "Digite o consumo em kWh: ");
        usuario.adicionarConsumo(new ConsumoEnergia(new Date(), consumo));
        System.out.println("Consumo registrado com sucesso!");
    }

    private static void sugerirPlano(Scanner scanner) {
        System.out.println("\n--- Sugerir Plano ---");
        if (usuarios.isEmpty() || fornecedores.isEmpty()) {
            System.out.println("Nenhum usuário ou fornecedor cadastrado.");
            return;
        }
        Usuario usuario = selecionarUsuario(scanner);
        float consumoMedio = usuario.obterConsumoMedio();

        Tarifa melhorPlano = null;
        float menorCusto = Float.MAX_VALUE;
        for (FornecedorEnergia fornecedor : fornecedores) {
            for (Tarifa tarifa : fornecedor.getTarifas()) {
                float custo = tarifa.obterCusto(consumoMedio);
                if (custo < menorCusto) {
                    menorCusto = custo;
                    melhorPlano = tarifa;
                }
            }
        }

        if (melhorPlano != null) {
            System.out.printf("Melhor plano sugerido: %s com custo de R$%.2f para um consumo médio de %.2f kWh.%n",
                    melhorPlano.getNome(), menorCusto, consumoMedio);
        } else {
            System.out.println("Não foi possível sugerir um plano.");
        }
    }

    private static Usuario selecionarUsuario(Scanner scanner) {
        System.out.println("\nUsuários cadastrados:");
        for (int i = 0; i < usuarios.size(); i++) {
            System.out.printf("%d. %s%n", i + 1, usuarios.get(i).getNome());
        }
        int indice = obterEntradaInt(scanner, "Selecione um usuário: ") - 1;
        return usuarios.get(Math.max(0, Math.min(indice, usuarios.size() - 1)));
    }

    private static void carregarFornecedoresDaAPI() {
        try {
            URL url = new URL("https://gist.githubusercontent.com/V1ctorMathias/1affa412297e2ec04163e9038cc706ef/raw/c1fadef499d0eacaabaa1e9e213869d00ee8078c/planos.json");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(br, JsonObject.class);

            JsonArray fornecedoresArray = jsonObject.getAsJsonArray("fornecedores_de_energia");
            for (JsonElement fornecedorElement : fornecedoresArray) {
                JsonObject fornecedorJson = fornecedorElement.getAsJsonObject();
                String nome = fornecedorJson.get("nome").getAsString();
                List<Tarifa> tarifas = new ArrayList<>();

                JsonArray planosArray = fornecedorJson.getAsJsonArray("planos");
                for (JsonElement planoElement : planosArray) {
                    JsonObject planoJson = planoElement.getAsJsonObject();
                    String nomePlano = planoJson.get("nome_do_plano").getAsString();
                    if (planoJson.has("preco_por_kWh")) {
                        float preco = planoJson.get("preco_por_kWh").getAsFloat();
                        tarifas.add(new TarifaFixa(nomePlano, preco));
                    } else {
                        float precoPico = planoJson.get("preco_pico_por_kWh").getAsFloat();
                        float precoForaPico = planoJson.get("preco_fora_pico_por_kWh").getAsFloat();
                        tarifas.add(new TarifaVariavel(nomePlano, precoPico, precoForaPico));
                    }
                }

                fornecedores.add(new FornecedorEnergia(nome, tarifas));
            }

            System.out.println("Fornecedores e tarifas carregados com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao carregar fornecedores: " + e.getMessage());
        }
    }

    private static int obterEntradaInt(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número inteiro.");
            }
        }
    }

    private static float obterEntradaFloat(Scanner scanner, String mensagem) {
        while (true) {
            try {
                System.out.print(mensagem);
                return Float.parseFloat(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número válido.");
            }
        }
    }
}

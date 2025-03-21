import java.sql.SQLException;
import java.util.Scanner;

public class InterfaceMonitoramento {

    private Scanner sc = new Scanner(System.in);
    private Relatorio relatorio = new Relatorio();
    private double valorFinal = 0;
    Conexao conexaoClasse = new Conexao();

    public double getValorFinal() {
        return valorFinal;
    }

    public void setValorFinal(double valorFinal) {
        this.valorFinal = valorFinal;
    }

    public void exibirDados(int tamanhoLista, double[] cotacao, double valorInicial, int tipoAlerta) throws SQLException {

        this.valorFinal = valorInicial;

        for (int i = 0; i <= tamanhoLista; i++) {
            this.valorFinal = this.valorFinal * cotacao[i];
        }

        this.conexaoClasse.investimento(this.valorFinal);

        this.alertaPersonalizado(valorInicial, this.valorFinal, tipoAlerta);

        System.out.println("Deseja visualizar o relatório: Não [0] Sim [1]: ");
        int visualizarRelatorio = sc.nextInt();

        if (visualizarRelatorio == 1) {
            this.valorFinal = valorInicial;
            for (int i = 0; i <= tamanhoLista; i++) {
                this.valorFinal = this.valorFinal * cotacao[i];
                this.relatorio.relatorio(valorFinal, i);
            }
        }
    }

    private void alertaPersonalizado(double valorInicial, double valorFinal, int tipoAlerta) {
        Alerta alerta = new Alerta();
        alerta.alerta(valorInicial, valorFinal, tipoAlerta);
    }
}

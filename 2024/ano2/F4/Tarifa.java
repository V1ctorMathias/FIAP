import java.util.Date;

public class Tarifa {

    private int id;
    private int fornecedorId;
    private String nome;
    private Date dataInicio;
    private Date dataFim;
    private double valorKWh;

    public double obterCusto(double consumo) {
        return consumo;
    }

}

import java.lang.Math;

public class Alerta {
    
    private String tipoAlerta = "";
    private String mensagem = "";
    private String frequencia = "";

    // Getters e Setters para tipoAlerta
    public String getTipoAlerta() {
        return tipoAlerta;
    }

    public void setTipoAlerta(String tipoAlerta) {
        this.tipoAlerta = tipoAlerta;
    }

    // Getters e Setters para mensagem
    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    // Getters e Setters para frequencia
    public String getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(String frequencia) {
        this.frequencia = frequencia;
    }

    public void alerta(double valorInicial, double valorFinal, int tipoAlerta) {

        double diferenca = Math.abs(valorFinal - valorInicial);
        int margem = (int)((diferenca / valorInicial) * 100);

        if (tipoAlerta == 0) {
            this.tipoAlerta = "mostrarMargem";
        } else if (tipoAlerta == 1) {
            this.tipoAlerta = "mostrarValorFinal";
        }

        if (this.tipoAlerta.equals("mostrarMargem")) {

            if (valorFinal > valorInicial) {
                this.mensagem = "A margem de lucro foi de " + margem + "%" + " indo para R$ " + valorFinal;
            } else if (valorFinal < valorInicial) {
                this.mensagem = "O déficit foi de " + margem + "%" + " indo para R$ " + valorFinal;
            } else {
                this.mensagem = "Sua margem se manteve a mesma, permanecendo R$ " + valorFinal; 
            }

        } else if (this.tipoAlerta.equals("mostrarValorFinal")) {

            if (valorFinal > valorInicial) {
                this.mensagem = "O lucro foi de R$ " + diferenca + " indo para R$ " + valorFinal;
            } else if (valorFinal < valorInicial) {
                this.mensagem = "O déficit foi de R$ " + diferenca + " indo para R$ " + valorFinal;
            } else {
                this.mensagem = "O valor se manteve R$ " + valorFinal; 
            }
        
        }
        
        System.out.println(this.mensagem);
        
    }
}

import java.lang.Math;

public class Alerta {
    
    private String tipoAlerta = "";
    private String mensagem = "";
    private String frequencia = "";

    public void alerta(double valorInicial, double valorFinal, int tipoAlerta) {

        double diferenca = Math.abs(valorFinal - valorInicial);
        int margem = (int)((diferenca / valorInicial) * 100);

        if (tipoAlerta == 0) {
            this.tipoAlerta = "mostrarMargem";
        } else if (tipoAlerta == 1) {
            this.tipoAlerta = "mostrarValorFinal";
        }

        if (this.tipoAlerta == "mostrarMargem") {

            if (valorFinal > valorInicial) {
                this.mensagem = "A margem de lucro foi de "+margem+"%"+" indo para R$ "+valorFinal;
            } else if (valorFinal < valorInicial) {
                this.mensagem = "O déficit foi de "+margem+"%"+" indo para R$ "+valorFinal;
            } else {
                this.mensagem = "Sua margem se manteve a mesma, permanecendo R$ "+valorFinal; 
            }

        } else if (this.tipoAlerta == "mostrarValorFinal") {

            if (valorFinal > valorInicial) {
                this.mensagem = "O lucro foi de R$ "+diferenca+" indo para R$ "+valorFinal;
            } else if (valorFinal < valorInicial) {
                this.mensagem = "O déficit foi de R$ "+diferenca+" indo para R$ "+valorFinal;
            } else {
                this.mensagem = "O valor se manteve R$ "+valorFinal; 
            }
        
        }
        
        System.out.println(this.mensagem);
        
    }

}

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AlertaDAO {
    private Connection conexao;

    public AlertaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirAlerta(double valorInicial, double valorFinal, int tipoAlerta, String mensagem) throws SQLException {
        String sql = "INSERT INTO alerta (valor_inicial, valor_final, tipo_alerta, mensagem) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, valorInicial);
            stmt.setDouble(2, valorFinal);
            stmt.setInt(3, tipoAlerta);
            stmt.setString(4, mensagem);
            stmt.executeUpdate();
        }
    }

    public void listarAlertas() throws SQLException {
        String sql = "SELECT * FROM alerta";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println(
                    "ID: " + rs.getInt("id") +
                    ", Valor Inicial: " + rs.getDouble("valor_inicial") +
                    ", Valor Final: " + rs.getDouble("valor_final") +
                    ", Tipo Alerta: " + rs.getInt("tipo_alerta") +
                    ", Mensagem: " + rs.getString("mensagem")
                );
            }
        }
    }
}

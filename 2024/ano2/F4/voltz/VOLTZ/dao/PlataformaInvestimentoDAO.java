import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlataformaInvestimentoDAO {
    private Connection conexao;

    public PlataformaInvestimentoDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirPlataforma(String nome) throws SQLException {
        String sql = "INSERT INTO plataforma_investimento (nome) VALUES (?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.executeUpdate();
        }
    }

    public void listarPlataformas() throws SQLException {
        String sql = "SELECT * FROM plataforma_investimento";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome"));
            }
        }
    }
}


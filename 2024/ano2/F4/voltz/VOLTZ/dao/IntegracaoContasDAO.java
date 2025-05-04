import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IntegracaoContasDAO {
    private Connection conexao;

    public IntegracaoContasDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirIntegracao(int idUsuario, int idEmpresa) throws SQLException {
        String sql = "INSERT INTO integracao_contas (id_usuario, id_empresa) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setInt(1, idUsuario);
            stmt.setInt(2, idEmpresa);
            stmt.executeUpdate();
        }
    }

    public void listarIntegracoes() throws SQLException {
        String sql = "SELECT * FROM integracao_contas";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", ID Usuário: " + rs.getInt("id_usuario") + ", ID Empresa: " + rs.getInt("id_empresa"));
            }
        }
    }
}


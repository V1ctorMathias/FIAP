import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EmpresaDAO {
    private Connection conexao;

    public EmpresaDAO(Connection conexao) {
        this.conexao = conexao;
    }

    public void inserirEmpresa(String nome, String cnpj) throws SQLException {
        String sql = "INSERT INTO empresa (nome, cnpj) VALUES (?, ?)";
        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            stmt.setString(2, cnpj);
            stmt.executeUpdate();
        }
    }

    public void listarEmpresas() throws SQLException {
        String sql = "SELECT * FROM empresa";
        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + ", Nome: " + rs.getString("nome") + ", CNPJ: " + rs.getString("cnpj"));
            }
        }
    }
}

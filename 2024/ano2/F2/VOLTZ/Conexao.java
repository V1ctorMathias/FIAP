import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    public Connection conexao;

    public void conectarDb() {
        try {
            // Carregar manualmente o driver do Oracle
            Class.forName("oracle.jdbc.OracleDriver");

            // Configuração da conexão
            String url = "jdbc:oracle:thin:@oracle.fiap.com.br:1521:orcl";
            String usuario = "rm554099";
            String senha = "291103";

            // Tentar conexão
            conexao = DriverManager.getConnection(url, usuario, senha);

        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC do Oracle não encontrado.");
            e.printStackTrace(); // Mostra detalhes do erro

        } catch (SQLException e) {
            System.err.println("Erro ao conectar: " + e.getMessage());
        }
    
    }

    public void cadastrar(String usuario, String senha, String email) throws SQLException {
        this.conectarDb();

        Statement stm = this.conexao.createStatement();
        stm.executeUpdate("INSERT INTO Usuarios (Nome, Email, Senha) VALUES ('"+usuario+"', '"+email+"', '"+senha+"')");
        stm.close();
    }

    public boolean login(String usuario, String senha) throws SQLException {
    this.conectarDb();
    
    String sql = "SELECT Nome FROM Usuarios WHERE nome = ? AND senha = ?";
    
    try (PreparedStatement stm = this.conexao.prepareStatement(sql)) {
        stm.setString(1, usuario);
        stm.setString(2, senha);
        
        try (ResultSet rs = stm.executeQuery()) {
            return rs.next(); // Retorna true se encontrou o usuário, false se não encontrou
        }
    }
}


    // public void cadastrarEmail(String usuario, String email) throws SQLException {
    //     this.conectarDb();

    //     Statement stm = this.conexao.createStatement();
    //     stm.executeUpdate("UPDATE Usuarios SET Email = '"+email+"' WHERE Nome = "+usuario);
    //     stm.close();
    // }

}

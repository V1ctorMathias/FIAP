import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexao {

    public Connection conexao;
    private static int idUsuario = 0;
    private static String nomeUsuario = "";
    public static boolean logado = false;
    // static Conexao conexaoClasse = new Conexao();

    public void setIdUsuario(int id, String nome) {
        Conexao.idUsuario = id;
        Conexao.nomeUsuario = nome;
    }

    public int getIdUsuario() {
        return Conexao.idUsuario;
    }

    public String getNomeUsuario() {
        return Conexao.nomeUsuario;
    }

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
        stm.executeUpdate("INSERT INTO Usuarios (Nome, Email, Senha) VALUES ('" + usuario + "', '" + email + "', '"
                + senha + "')");
        stm.close();
    }

    public boolean login(String usuario, String senha) throws SQLException {
        this.conectarDb();

        String sql = "SELECT UsuarioID, Nome FROM Usuarios WHERE nome = ? AND senha = ?";

        try (PreparedStatement stm = this.conexao.prepareStatement(sql)) {
            stm.setString(1, usuario);
            stm.setString(2, senha);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    this.setIdUsuario(rs.getInt("UsuarioID"), rs.getString("Nome"));
                    Conexao.logado = true;
                    this.logs();
                    return true;
                }
            }
        }
        return false; // Usuário não encontrado
    }

    public void investimento(double valor) throws SQLException {

        this.conectarDb();

        boolean existeValor = false;

        String sqlSelect = "SELECT valor FROM investimentos WHERE usuarioID = ?";
        try (PreparedStatement stm = this.conexao.prepareStatement(sqlSelect)) {
            stm.setInt(1, idUsuario);

            try (ResultSet rs = stm.executeQuery()) {
                if (rs.next()) {
                    existeValor = true;
                }
            }
        }

        if (existeValor == false) {
            String sqlInsert = "INSERT INTO Investimentos (usuarioID, tipoID, valor) VALUES (?, ?, ?)";
            try (PreparedStatement stm = this.conexao.prepareStatement(sqlInsert)) {
                stm.setInt(1, this.getIdUsuario());
                stm.setInt(2, 2);
                stm.setDouble(3, valor);
                stm.executeUpdate();
            }
        } else {

            System.out.println("Foi encontrado um antigo investimento, será trocado pelo novo valor!");

            String sqlInsert = "UPDATE Investimentos SET valor = ? WHERE usuarioID = ?";
            try (PreparedStatement stm = this.conexao.prepareStatement(sqlInsert)) {
                stm.setDouble(1, valor);
                stm.setInt(2, this.getIdUsuario());
                stm.executeUpdate();
            }
        }

    }

    public void logs() throws SQLException {

        this.conectarDb();

        if (Conexao.logado == true) {
            String sqlInsert = "INSERT INTO logsAcesso (usuarioID, dataLog) VALUES (?, ?)";
            try (PreparedStatement stm = this.conexao.prepareStatement(sqlInsert)) {
                stm.setInt(1, this.getIdUsuario());
                stm.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
                stm.executeUpdate();
            }

            // Conta o numero de rows na tabela de log e, caso exceder 3 registros, excluir o último
            String sqlCount = "SELECT COUNT(*) FROM logsAcesso";
            String sqlDelete = "DELETE FROM logsAcesso WHERE logId = (SELECT MAX(logId) FROM logsAcesso)";

            try (PreparedStatement stmCount = this.conexao.prepareStatement(sqlCount);
                    ResultSet rs = stmCount.executeQuery()) {

                if (rs.next()) {
                    int count = rs.getInt(1);
                    System.out.println("Total de registros: " + count);

                    if (count > 3) {
                        try (PreparedStatement stmDelete = this.conexao.prepareStatement(sqlDelete)) {
                            int rowsAffected = stmDelete.executeUpdate();
                            if (rowsAffected > 0) {
                                System.out.println("Último registro deletado.");
                            }
                        }
                    }
                }
            }

        }

    }

    // public void cadastrarEmail(String usuario, String email) throws SQLException
    // {
    // this.conectarDb();

    // Statement stm = this.conexao.createStatement();
    // stm.executeUpdate("UPDATE Usuarios SET Email = '"+email+"' WHERE Nome =
    // "+usuario);
    // stm.close();
    // }

}

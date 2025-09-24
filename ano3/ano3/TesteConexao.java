package ano3;

import java.sql.Connection;

public class TesteConexao {
    public static void main(String[] args) {
        try (Connection conn = ano3.AzureSQLConnection.getConnection()) {
            if (conn != null) {
                System.out.println(" Conexão com Azure SQL bem-sucedida!");
            } else {
                System.err.println(" Falha na conexão.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package ano3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AzureSQLConnection {

    public static Connection getConnection() {
        //
        String server = "fiapbanco.database.windows.net";
        String database = "fiapdb";
        String user = "administrador";
        String password = "Fiap@2025";

        String connectionUrl = String.format(
                "jdbc:sqlserver://%s:1433;database=%s;user=%s;password=%s;encrypt=true;trustServerCertificate=false;loginTimeout=30;",
                server, database, user, password
        );

        try {
            return DriverManager.getConnection(connectionUrl);
        } catch (SQLException e) {
            System.err.println("Falha ao conectar no Azure SQL:");
            e.printStackTrace();
            return null;
        }
    }
}

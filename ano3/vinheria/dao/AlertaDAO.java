package dao;

import model.Alerta;
import ano3.AzureSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlertaDAO {

    public AlertaDAO(Connection conexao) {
    }

    public void inserir(Alerta alerta) throws SQLException {
        String sql = "INSERT INTO Alertas (tipo, mensagem) VALUES (?, ?)";

        try (Connection conn = AzureSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, alerta.getTipo());
            stmt.setString(2, alerta.getMensagem());
            stmt.executeUpdate();
        }
    }

    public List<Alerta> listar() throws SQLException {
        String sql = "SELECT * FROM Alertas";
        List<Alerta> alertas = new ArrayList<>();

        try (Connection conn = AzureSQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                alertas.add(new Alerta(
                        rs.getInt("id"),
                        rs.getString("tipo"),
                        rs.getString("mensagem")
                ));
            }
        }
        return alertas;
    }
}

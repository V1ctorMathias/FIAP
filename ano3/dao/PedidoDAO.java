package dao;

import model.Pedido;
import ano3.AzureSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PedidoDAO {

    public PedidoDAO(Connection conexao) {
    }

    public void inserir(Pedido pedido) throws SQLException {
        String sql = "INSERT INTO Pedidos (usuarioId, produtoId, quantidade, dataPedido) VALUES (?, ?, ?, ?)";

        try (Connection conn = AzureSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, pedido.getUsuarioId());
            stmt.setInt(2, pedido.getProdutoId());
            stmt.setInt(3, pedido.getQuantidade());
            stmt.setDate(4, new java.sql.Date(pedido.getDataPedido().getTime()));
            stmt.executeUpdate();
        }
    }

    public List<Pedido> listar() throws SQLException {
        String sql = "SELECT * FROM Pedidos";
        List<Pedido> pedidos = new ArrayList<>();

        try (Connection conn = AzureSQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                pedidos.add(new Pedido(
                        rs.getInt("id"),
                        rs.getInt("usuarioId"),
                        rs.getInt("produtoId"),
                        rs.getInt("quantidade"),
                        rs.getDate("dataPedido")
                ));
            }
        }
        return pedidos;
    }
}

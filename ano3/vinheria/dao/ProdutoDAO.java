package dao;

import model.Produto;
import ano3.AzureSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public ProdutoDAO(Connection conexao) {
    }

    public void inserir(Produto produto) throws SQLException {
        String sql = "INSERT INTO Produtos (nome, preco, estoque) VALUES (?, ?, ?)";

        try (Connection conn = AzureSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, produto.getNome());
            stmt.setDouble(2, produto.getPreco());
            stmt.setInt(3, produto.getEstoque());
            stmt.executeUpdate();
        }
    }

    public List<Produto> listar() throws SQLException {
        String sql = "SELECT * FROM Produtos";
        List<Produto> produtos = new ArrayList<>();

        try (Connection conn = AzureSQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                produtos.add(new Produto(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getDouble("preco"),
                        rs.getInt("estoque")
                ));
            }
        }
        return produtos;
    }
}

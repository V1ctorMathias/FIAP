package ano3;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class PopulaBanco {

    public static void main(String[] args) {
        try (Connection conn = ano3.AzureSQLConnection.getConnection()) {
            if (conn == null) {
                System.err.println(" Conexão falhou!");
                return;
            }

            Statement stmt = conn.createStatement();

            // ===== CRIAÇÃO DE TABELAS =====
            stmt.executeUpdate(
                    "IF OBJECT_ID('Usuario', 'U') IS NULL " +
                            "CREATE TABLE Usuario (" +
                            "id INT IDENTITY(1,1) PRIMARY KEY, " +
                            "nome NVARCHAR(100) NOT NULL, " +
                            "email NVARCHAR(100) NOT NULL UNIQUE, " +
                            "senha NVARCHAR(50) NOT NULL)"
            );

            stmt.executeUpdate(
                    "IF OBJECT_ID('Produto', 'U') IS NULL " +
                            "CREATE TABLE Produto (" +
                            "id INT IDENTITY(1,1) PRIMARY KEY, " +
                            "nome NVARCHAR(100) NOT NULL, " +
                            "preco DECIMAL(10,2) NOT NULL, " +
                            "estoque INT NOT NULL)"
            );

            stmt.executeUpdate(
                    "IF OBJECT_ID('Pedido', 'U') IS NULL " +
                            "CREATE TABLE Pedido (" +
                            "id INT IDENTITY(1,1) PRIMARY KEY, " +
                            "usuario_id INT NOT NULL, " +
                            "produto_id INT NOT NULL, " +
                            "quantidade INT NOT NULL, " +
                            "data_pedido DATETIME NOT NULL, " +
                            "FOREIGN KEY (usuario_id) REFERENCES Usuario(id), " +
                            "FOREIGN KEY (produto_id) REFERENCES Produto(id))"
            );

            stmt.executeUpdate(
                    "IF OBJECT_ID('Alerta', 'U') IS NULL " +
                            "CREATE TABLE Alerta (" +
                            "id INT IDENTITY(1,1) PRIMARY KEY, " +
                            "tipo NVARCHAR(50) NOT NULL, " +
                            "mensagem NVARCHAR(255) NOT NULL)"
            );

            System.out.println(" Tabelas criadas ou já existentes.");

            // ===== INSERÇÃO DE DADOS =====
            stmt.executeUpdate(
                    "INSERT INTO Usuario (nome, email, senha) VALUES " +
                            "('João Silva','joao@email.com','1234')," +
                            "('Maria Souza','maria@email.com','abcd')"
            );

            stmt.executeUpdate(
                    "INSERT INTO Produto (nome, preco, estoque) VALUES " +
                            "('Vinho Tinto', 89.90, 10)," +
                            "('Queijo Brie', 49.50, 20)"
            );

            stmt.executeUpdate(
                    "INSERT INTO Pedido (usuario_id, produto_id, quantidade, data_pedido) VALUES " +
                            "(1,1,2,GETDATE())," +
                            "(2,2,1,GETDATE())"
            );

            stmt.executeUpdate(
                    "INSERT INTO Alerta (tipo, mensagem) VALUES " +
                            "('Estoque Baixo','Vinho Tinto está acabando')," +
                            "('Promoção','Queijo Brie com desconto')"
            );

            System.out.println("✅ Dados de teste inseridos.");

            // ===== CONSULTA PARA VALIDAR =====
            System.out.println("\n📋 Usuários:");
            ResultSet rs = stmt.executeQuery("SELECT * FROM Usuario");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Email: %s%n",
                        rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
            }

            System.out.println("\n📋 Produtos:");
            rs = stmt.executeQuery("SELECT * FROM Produto");
            while (rs.next()) {
                System.out.printf("ID: %d | Nome: %s | Preço: %.2f | Estoque: %d%n",
                        rs.getInt("id"), rs.getString("nome"), rs.getDouble("preco"), rs.getInt("estoque"));
            }

            System.out.println("\n📋 Pedidos:");
            rs = stmt.executeQuery("SELECT * FROM Pedido");
            while (rs.next()) {
                System.out.printf("ID: %d | Usuário ID: %d | Produto ID: %d | Quantidade: %d%n",
                        rs.getInt("id"), rs.getInt("usuario_id"), rs.getInt("produto_id"), rs.getInt("quantidade"));
            }

            System.out.println("\n📋 Alertas:");
            rs = stmt.executeQuery("SELECT * FROM Alerta");
            while (rs.next()) {
                System.out.printf("ID: %d | Tipo: %s | Mensagem: %s%n",
                        rs.getInt("id"), rs.getString("tipo"), rs.getString("mensagem"));
            }

        } catch (SQLException e) {
            System.err.println("❌ Erro durante execução:");
            e.printStackTrace();
        }
    }
}

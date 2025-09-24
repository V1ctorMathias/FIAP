package dao;

import model.Usuario;
import ano3.AzureSQLConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {

    public UsuarioDAO(Connection conexao) {
    }

    public void inserir(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO Usuarios (nome, email, senha) VALUES (?, ?, ?)";

        try (Connection conn = AzureSQLConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getNome());
            stmt.setString(2, usuario.getEmail());
            stmt.setString(3, usuario.getSenha());
            stmt.executeUpdate();
        }
    }

    public List<Usuario> listar() throws SQLException {
        String sql = "SELECT * FROM Usuarios";
        List<Usuario> usuarios = new ArrayList<>();

        try (Connection conn = AzureSQLConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("senha")
                ));
            }
        }
        return usuarios;
    }
}

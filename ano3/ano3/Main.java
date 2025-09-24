package ano3;

import model.*;
import dao.*;

import java.sql.Connection;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Tenta abrir a conexão com Azure SQL
        try (Connection conexao = ano3.AzureSQLConnection.getConnection()) {

            if (conexao == null) {
                System.err.println("❌ Não foi possível conectar ao banco de dados.");
                return;
            }

            UsuarioDAO usuarioDAO = new UsuarioDAO(conexao);
            ProdutoDAO produtoDAO = new ProdutoDAO(conexao);
            PedidoDAO pedidoDAO = new PedidoDAO(conexao);
            AlertaDAO alertaDAO = new AlertaDAO(conexao);

            // ======== TESTE: INSERIR USUÁRIO ========
            Usuario u = new Usuario(0, "João da Silva", "joao@email.com", "123456");
            usuarioDAO.inserir(u);
            System.out.println("✅ Usuário inserido com sucesso!");

            List<Usuario> usuarios = usuarioDAO.listar();
            System.out.println("👤 Usuários no banco:");
            for (Usuario user : usuarios) {
                System.out.println("ID: " + user.getId() + " | Nome: " + user.getNome());
            }

            // ======== TESTE: INSERIR PRODUTO ========
            Produto p = new Produto(0, "Vinho Tinto Reserva", 89.90, 10);
            produtoDAO.inserir(p);
            System.out.println("✅ Produto inserido com sucesso!");

            List<Produto> produtos = produtoDAO.listar();
            System.out.println("🍷 Produtos no banco:");
            for (Produto prod : produtos) {
                System.out.println("ID: " + prod.getId() + " | Nome: " + prod.getNome() + " | Preço: " + prod.getPreco());
            }

            // ======== TESTE: INSERIR PEDIDO ========
            if (!usuarios.isEmpty() && !produtos.isEmpty()) {
                Pedido pedido = new Pedido(0,
                        usuarios.get(0).getId(),
                        produtos.get(0).getId(),
                        2,
                        new Date());
                pedidoDAO.inserir(pedido);
                System.out.println("✅ Pedido inserido com sucesso!");

                List<Pedido> pedidos = pedidoDAO.listar();
                System.out.println("📦 Pedidos no banco:");
                for (Pedido ped : pedidos) {
                    System.out.println("ID: " + ped.getId() + " | Usuário: " + ped.getUsuarioId() +
                            " | Produto: " + ped.getProdutoId() +
                            " | Qtd: " + ped.getQuantidade());
                }
            }

            // ======== TESTE: INSERIR ALERTA ========
            Alerta alerta = new Alerta(0, "Estoque Baixo", "O produto Vinho Tinto Reserva está acabando.");
            alertaDAO.inserir(alerta);
            System.out.println("✅ Alerta inserido com sucesso!");

            List<Alerta> alertas = alertaDAO.listar();
            System.out.println("⚠️ Alertas no banco:");
            for (Alerta a : alertas) {
                System.out.println("ID: " + a.getId() + " | Tipo: " + a.getTipo() + " | Msg: " + a.getMensagem());
            }

        } catch (Exception e) {
            System.err.println("❌ Erro durante execução:");
            e.printStackTrace();
        }
    }
}

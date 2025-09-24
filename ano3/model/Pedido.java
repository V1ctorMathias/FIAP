package model;

import java.util.Date;

public class Pedido {
    private int id;
    private int usuarioId;
    private int produtoId;
    private int quantidade;
    private Date dataPedido;

    public Pedido() {}

    public Pedido(int id, int usuarioId, int produtoId, int quantidade, Date dataPedido) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.produtoId = produtoId;
        this.quantidade = quantidade;
        this.dataPedido = dataPedido;
    }

    // Getters e Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUsuarioId() { return usuarioId; }
    public void setUsuarioId(int usuarioId) { this.usuarioId = usuarioId; }

    public int getProdutoId() { return produtoId; }
    public void setProdutoId(int produtoId) { this.produtoId = produtoId; }

    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

    public Date getDataPedido() { return dataPedido; }
    public void setDataPedido(Date dataPedido) { this.dataPedido = dataPedido; }
}

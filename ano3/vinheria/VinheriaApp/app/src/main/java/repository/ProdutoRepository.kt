package com.vinheriaagnello.repository

import com.vinheriaagnello.data.Produto
import com.vinheriaagnello.data.ProdutoDao

class ProdutoRepository(private val dao: ProdutoDao) {

    suspend fun inserir(produto: Produto) = dao.inserir(produto)

    suspend fun atualizar(produto: Produto) = dao.atualizar(produto)

    suspend fun deletar(produto: Produto) = dao.deletar(produto)

    suspend fun listarTodos() = dao.listarTodos()
}

package com.vinheriaagnello.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vinheriaagnello.data.Produto
import com.vinheriaagnello.repository.ProdutoRepository
import kotlinx.coroutines.launch

class ProdutoViewModel(private val repository: ProdutoRepository) : ViewModel() {

    fun adicionar(produto: Produto) {
        viewModelScope.launch { repository.inserir(produto) }
    }

    fun atualizar(produto: Produto) {
        viewModelScope.launch { repository.atualizar(produto) }
    }

    fun deletar(produto: Produto) {
        viewModelScope.launch { repository.deletar(produto) }
    }

    suspend fun listar() = repository.listarTodos()
}

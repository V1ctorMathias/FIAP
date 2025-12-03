package br.com.fiap.vinheria

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.vinheriaagnello.data.VinheriaDatabase
import com.vinheriaagnello.data.Produto
import kotlinx.coroutines.launch
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@ExperimentalMaterial3Api
class MainActivity : ComponentActivity() {

    private lateinit var db: VinheriaDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        db = Room.databaseBuilder(
            applicationContext,
            VinheriaDatabase::class.java,
            "vinheria.db"
        ).build()

        setContent {
            ProdutoCrudScreen(
                carregarProdutos = { loadProducts() },
                inserir = { insertProduct(it) },
                atualizar = { updateProduct(it) },
                deletar = { deleteProduct(it) }
            )
        }
    }

    private suspend fun loadProducts(): List<Produto> =
        withContext(Dispatchers.IO) { db.produtoDao().listarTodos() }

    private fun insertProduct(produto: Produto) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.produtoDao().inserir(produto)
        }
    }

    private fun updateProduct(produto: Produto) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.produtoDao().atualizar(produto)
        }
    }

    private fun deleteProduct(produto: Produto) {
        lifecycleScope.launch(Dispatchers.IO) {
            db.produtoDao().deletar(produto)
        }
    }
}

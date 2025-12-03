package br.com.fiap.vinheria

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vinheriaagnello.data.Produto
import com.vinheriaagnello.data.ProdutoDao
import com.vinheriaagnello.data.VinheriaDatabase
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ProdutoDaoTest {

    private lateinit var db: VinheriaDatabase
    private lateinit var dao: ProdutoDao

    @Before
    fun setup() {
        db = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            VinheriaDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = db.produtoDao()
    }

    @After
    fun teardown() {
        db.close()
    }

    @Test
    fun testInserirEListarProduto() = runBlocking {
        val produto = Produto(
            nome = "Vinho Tinto",
            tipo = "Seco",
            uva = "Cabernet",
            pais = "Chile",
            valor = "59.99"
        )
        dao.inserir(produto)

        val produtos = dao.listarTodos()

        println("Produtos listados: $produtos")

        assertEquals(1, produtos.size)
        assertEquals("Vinho Tinto", produtos[0].nome)
    }
}

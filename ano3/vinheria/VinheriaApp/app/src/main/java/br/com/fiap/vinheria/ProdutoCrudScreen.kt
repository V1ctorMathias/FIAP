package br.com.fiap.vinheria

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vinheriaagnello.data.Produto
import kotlinx.coroutines.launch

@ExperimentalMaterial3Api
@Composable
fun ProdutoCrudScreen(
    carregarProdutos: suspend () -> List<Produto>,
    inserir: (Produto) -> Unit,
    atualizar: (Produto) -> Unit,
    deletar: (Produto) -> Unit
) {
    val scope = rememberCoroutineScope()

    var produtos by remember { mutableStateOf(listOf<Produto>()) }
    var dialogAberto by remember { mutableStateOf(false) }
    var dialogEditarAberto by remember { mutableStateOf<Produto?>(null) }

    LaunchedEffect(Unit) {
        produtos = carregarProdutos()
    }

    fun atualizarLista() {
        scope.launch {
            produtos = carregarProdutos()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Vinheria - Produtos") })
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { dialogAberto = true }) {
                Text("+")
            }
        }
    ) { padding ->

        LazyColumn(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            items(produtos) { produto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .clickable { dialogEditarAberto = produto },
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(16.dp)) {
                        Text(text = produto.nome, style = MaterialTheme.typography.titleMedium)
                        Text("Tipo: ${produto.tipo}")
                        Text("Uva: ${produto.uva}")
                        Text("País: ${produto.pais}")
                        Text("Valor: R$ ${produto.valor}")
                    }
                }
            }
        }
    }


    if (dialogAberto) {
        ProdutoDialog(
            titulo = "Adicionar Produto",
            onConfirm = { novoProduto ->
                inserir(novoProduto)
                atualizarLista()
                dialogAberto = false
            },
            onDismiss = { dialogAberto = false }
        )
    }


    dialogEditarAberto?.let { produto ->
        ProdutoDialog(
            produtoEdicao = produto,
            titulo = "Editar Produto",
            onConfirm = { atualizado ->
                atualizar(atualizado)
                atualizarLista()
                dialogEditarAberto = null
            },
            onDelete = {
                deletar(produto)
                atualizarLista()
                dialogEditarAberto = null
            },
            onDismiss = { dialogEditarAberto = null }
        )
    }
}

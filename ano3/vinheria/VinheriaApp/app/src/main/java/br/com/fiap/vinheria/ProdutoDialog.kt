package br.com.fiap.vinheria

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.vinheriaagnello.data.Produto

@Composable
fun ProdutoDialog(
    titulo: String,
    onConfirm: (Produto) -> Unit,
    onDismiss: () -> Unit,
    produtoEdicao: Produto? = null,
    onDelete: (() -> Unit)? = null
) {
    var nome by remember { mutableStateOf(produtoEdicao?.nome ?: "") }
    var tipo by remember { mutableStateOf(produtoEdicao?.tipo ?: "") }
    var uva by remember { mutableStateOf(produtoEdicao?.uva ?: "") }
    var pais by remember { mutableStateOf(produtoEdicao?.pais ?: "") }
    var valor by remember { mutableStateOf(produtoEdicao?.valor ?: "") }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = { Text(titulo) },
        text = {
            Column {
                OutlinedTextField(value = nome, onValueChange = { nome = it }, label = { Text("Nome") })
                OutlinedTextField(value = tipo, onValueChange = { tipo = it }, label = { Text("Tipo") })
                OutlinedTextField(value = uva, onValueChange = { uva = it }, label = { Text("Uva") })
                OutlinedTextField(value = pais, onValueChange = { pais = it }, label = { Text("País") })
                OutlinedTextField(value = valor, onValueChange = { valor = it }, label = { Text("Valor") })
            }
        },
        confirmButton = {
            TextButton(onClick = {
                onConfirm(
                    Produto(
                        id = produtoEdicao?.id ?: 0,
                        nome = nome,
                        tipo = tipo,
                        uva = uva,
                        pais = pais,
                        valor = valor
                    )
                )
            }) {
                Text("Salvar")
            }
        },
        dismissButton = {
            Row {
                if (onDelete != null) {
                    TextButton(onClick = { onDelete() }) {
                        Text("Excluir")
                    }
                }
                TextButton(onClick = onDismiss) {
                    Text("Cancelar")
                }
            }
        }
    )
}

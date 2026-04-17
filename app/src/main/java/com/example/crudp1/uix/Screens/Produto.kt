package com.example.crudp1.uix.Screens
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crudp1.model.Produto
import com.example.crudp1.viewModel.ProductViewModel

@Composable
fun TelaProduto(navController: NavController) {
    val viewModel: ProductViewModel = viewModel()
    var nomeProduto by remember { mutableStateOf("") }
    var descricaoProduto by remember { mutableStateOf("") }
    var preco by remember { mutableStateOf("") }
    var estoque by remember { mutableStateOf("") }
    var nomeProdutoErro by remember { mutableStateOf(false) }
    var descricaoProdutoErro by remember { mutableStateOf(false) }
    var precoErro by remember { mutableStateOf(false) }
    var estoqueErro by remember { mutableStateOf(false) }

    // 🔥 Carrega produtos ao abrir a tela
    LaunchedEffect(Unit) {
        viewModel.carregarProduto()
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        IconButton(
            onClick = {
                navController.navigate("Home")
            }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier
                    .size(400.dp)
                    .padding(top = 20.dp)
            )
        }

        Text(
            text = "Cadastro de Produto",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // FORM
        TextField(
            value = nomeProduto,
            onValueChange = {
                nomeProduto = it
                nomeProdutoErro = false
            },
            label = { Text("Nome") },
            isError = nomeProdutoErro,
            modifier = Modifier.fillMaxWidth()
        )

        if (nomeProdutoErro) {
            Text("Nome obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = descricaoProduto,
            onValueChange = {
                descricaoProduto = it
                descricaoProdutoErro = false
            },
            label = { Text("Descrição") },
            isError = descricaoProdutoErro,
            modifier = Modifier.fillMaxWidth()
        )

        if (descricaoProdutoErro) {
            Text("Descrição obrigatória", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = preco,
            onValueChange = {
                preco = it
                precoErro = false
            },
            label = { Text("Preço") },
            isError = precoErro,
            modifier = Modifier.fillMaxWidth()
        )

        if (precoErro) {
            Text("Preço obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = estoque,
            onValueChange = {
                estoque = it
                estoqueErro = false
            },
            label = { Text("Estoque") },
            isError = estoqueErro,
            modifier = Modifier.fillMaxWidth()
        )

        if (estoqueErro) {
            Text("Estoque obrigatório", color = Color.Red)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = {

                nomeProdutoErro = nomeProduto.isBlank()
                descricaoProdutoErro = descricaoProduto.isBlank()
                precoErro = preco.isBlank()
                estoqueErro = estoque.isBlank()

                if (nomeProdutoErro || descricaoProdutoErro || precoErro || estoqueErro) return@Button

                val precoDouble = preco.toDoubleOrNull()
                val estoqueInt = estoque.toIntOrNull()

                if (precoDouble == null) {
                    precoErro = true
                    return@Button
                }

                if (estoqueInt == null) {
                    estoqueErro = true
                    return@Button
                }

                val produto = Produto(
                    nome = nomeProduto,
                    descricao = descricaoProduto,
                    preco = precoDouble,
                    estoque = estoqueInt
                )

                viewModel.adicionar(
                    produto,
                    onSuccess = {
                        println("Salvou no firebase")
                    },
                    onError = {
                        println("ERRO: ${it.message}")
                    }
                )

                nomeProduto = ""
                descricaoProduto = ""
                preco = ""
                estoque = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text("Produtos cadastrados:", fontWeight = FontWeight.Bold)

        LazyColumn {
            items(viewModel.listaProdutos) { produto ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {
                        Text(produto.nome, fontWeight = FontWeight.Bold)
                        Text(produto.descricao)
                        Text("R$ ${produto.preco}")
                        Text("Estoque: ${produto.estoque}")

                        Button(
                            onClick = {
                                viewModel.deletar(
                                    produto.id,
                                    onSuccess = { println("Editado com sucesso!") },
                                    onError = { println("Erro ao deletar") }
                                )
                            }
                        ) {
                            Text("Editar")
                        }


                        Button(
                            onClick = {
                                viewModel.deletar(
                                    produto.id,
                                    onSuccess = { println("Deletado") },
                                    onError = { println("Erro ao deletar") }
                                )
                            }
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }
    }
}
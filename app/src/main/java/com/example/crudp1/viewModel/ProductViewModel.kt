package com.example.crudp1.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.crudp1.model.Produto
import com.example.crudp1.repository.ProdutoRepository

class ProductViewModel : ViewModel() {

    private val api = ProdutoRepository()

    var listaProdutos = mutableStateListOf<Produto>()
        private set

    // CREATE
    fun adicionar(
        produto: Produto,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.adicionar(produto, {
            carregarProduto()
            onSuccess()
        }, onError)
    }

    // READ
    fun carregarProduto() {
        api.listar(
            onResult = {
                listaProdutos.clear()
                listaProdutos.addAll(it)
            },
            onError = {
                println("Erro ao carregar: ${it.message}")
            }
        )
    }

    fun deletar(
        id: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.deletar(id, {
            carregarProduto()
            onSuccess()
        }, onError)
    }


    fun atualizar(
        produto: Produto,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.atualizar(produto, {
            carregarProduto()
            onSuccess()
        }, onError)
    }
}
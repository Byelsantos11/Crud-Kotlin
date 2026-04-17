package com.example.crudp1.viewModel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.crudp1.model.Cliente
import com.example.crudp1.repository.ClienteRepository

class ClientViewModel : ViewModel() {

    private val api = ClienteRepository()

    var listaCliente = mutableStateListOf<Cliente>()
        private set

    // CREATE
    fun adicionar(
        cliente: Cliente,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.adicionar(cliente, {
            carregarCliente()
            onSuccess()
        }, onError)
    }

    // READ
    fun carregarCliente() {
        api.listar(
            onResult = {
                listaCliente.clear()
                listaCliente.addAll(it)
            },
            onError = {
                println("Erro ao carregar: ${it.message}")
            }
        )
    }

    // DELETE
    fun deletar(
        id: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.deletar(id, {
            carregarCliente()
            onSuccess()
        }, onError)
    }

    // UPDATE
    fun atualizar(
        cliente: Cliente,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.atualizar(cliente, {
            carregarCliente()
            onSuccess()
        }, onError)
    }
}
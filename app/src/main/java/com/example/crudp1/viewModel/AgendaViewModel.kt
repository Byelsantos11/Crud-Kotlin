package com.example.crudp1.viewModel
import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.crudp1.model.Agenda
import com.example.crudp1.repository.AgendaRepository

class AgendaViewModel : ViewModel() {

    private val api = AgendaRepository()

    var listaAgenda = mutableStateListOf<Agenda>()
        private set

    // CREATE
    fun adicionar(
        agenda: Agenda,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.adicionar(agenda, {
            carregarAgenda()
            onSuccess()
        }, onError)
    }

    // READ
    fun carregarAgenda() {
        api.listar(
            onResult = {
                listaAgenda.clear()
                listaAgenda.addAll(it)
            },
            onError = {
                println("Erro ao carregar agenda: ${it.message}")
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
            carregarAgenda()
            onSuccess()
        }, onError)
    }

    // UPDATE
    fun atualizar(
        agenda: Agenda,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        api.atualizar(agenda, {
            carregarAgenda()
            onSuccess()
        }, onError)
    }
}
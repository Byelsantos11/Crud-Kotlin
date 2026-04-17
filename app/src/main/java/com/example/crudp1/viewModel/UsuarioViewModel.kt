package com.example.crudp1.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.crudp1.model.Usuario
import com.example.crudp1.repository.AuthRepository

class UsuarioViewModel : ViewModel() {

    private val repository = AuthRepository()

    var isLoading = mutableStateOf(false)
    var erroMessage = mutableStateOf<String?>(null)

    fun registrar(nome: String, email: String, senha: String, onSuccess: () -> Unit){
        isLoading.value= true

        repository.cadastrar(nome, email, senha){success, error ->
            isLoading.value = false

            if(success){
                onSuccess()
            }else{
                erroMessage.value = error
            }
    }}


    fun sair(){
        repository.logout()
    }
}
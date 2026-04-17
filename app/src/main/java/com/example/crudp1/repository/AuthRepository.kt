package com.example.crudp1.repository

import com.example.crudp1.model.Usuario
import com.google.firebase.auth.FirebaseAuth

class AuthRepository {

    private val auth = FirebaseAuth.getInstance()

    fun cadastrar(nome: String, email: String, senha: String, onResult: (Boolean, String) -> Unit){

        auth.createUserWithEmailAndPassword(email, senha)
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    onResult(true, "Cadastro realizado com sucesso!")
                } else {
                    onResult(false, task.exception?.message ?: "Erro desconhecido")
                }
            }
    }

    fun logout(){
        auth.signOut()
    }
}
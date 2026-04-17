package com.example.crudp1.model
import kotlinx.serialization.Serializable

@Serializable
data class Usuario(
    val id: String = "",
    val nome: String= "",
    val email: String = "",
    val senha: String = ""
)
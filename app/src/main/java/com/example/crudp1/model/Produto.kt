package com.example.crudp1.model
import kotlinx.serialization.Serializable

@Serializable
data class Produto(
    var id: String = "",
    var nome: String = "",
    var descricao: String = "",
    var preco: Double = 0.0,
    var estoque: Int = 0
)



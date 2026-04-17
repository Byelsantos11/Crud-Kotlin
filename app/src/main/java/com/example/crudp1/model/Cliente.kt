package com.example.crudp1.model

data class Cliente(
    var id: String = "",
    var nomecliente: String = "",
    var cpfcliente: String = "",
    var idadecliente: Int = 0,
    var telefonecliente: String = "",
    var verificadocliente: Boolean = false
)


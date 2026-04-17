package com.example.crudp1.model

data class Agenda(
    var id: String = "",
    var titulo: String = "",
    var descricao: String = "",
    var dataInicio: String = "",
    var concluida: Boolean = false
)
package com.example.crudp1.repository

import com.example.crudp1.model.Cliente
import com.google.firebase.firestore.FirebaseFirestore

class ClienteRepository {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Cliente")

    // CREATE
    fun adicionar(
        cliente: Cliente,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val doc = collection.document()

        cliente.id = doc.id // 🔥 define o ID antes de salvar

        doc.set(cliente)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    // READ
    fun listar(
        onResult: (List<Cliente>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.get()
            .addOnSuccessListener { result ->
                val lista = result.map { doc ->
                    val cliente = doc.toObject(Cliente::class.java)
                    cliente.id = doc.id // 🔥 garante que o ID venha
                    cliente
                }
                onResult(lista)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    // UPDATE
    fun atualizar(
        cliente: Cliente,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(cliente.id)
            .set(cliente)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    // DELETE
    fun deletar(
        id: String,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(id)
            .delete()
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }
}
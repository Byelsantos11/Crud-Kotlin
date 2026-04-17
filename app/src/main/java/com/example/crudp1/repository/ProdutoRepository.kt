package com.example.crudp1.repository

import com.example.crudp1.model.Produto
import com.google.firebase.firestore.FirebaseFirestore

class ProdutoRepository {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Produto") // 🔥 padrão correto

    // CREATE
    fun adicionar(
        produto: Produto,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val doc = collection.document()
        produto.id = doc.id

        doc.set(produto)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    // READ
    fun listar(
        onResult: (List<Produto>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.get()
            .addOnSuccessListener { result ->
                val lista = result.map { it.toObject(Produto::class.java) }
                onResult(lista)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    // UPDATE
    fun atualizar(
        produto: Produto,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(produto.id)
            .set(produto)
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
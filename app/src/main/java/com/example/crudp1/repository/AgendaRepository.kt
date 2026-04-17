package com.example.crudp1.repository

import com.example.crudp1.model.Agenda
import com.example.crudp1.model.Cliente
import com.google.firebase.firestore.FirebaseFirestore


class AgendaRepository {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Agenda")

    // CREATE
    fun adicionar(
        agenda: Agenda,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        val doc = collection.document()

        agenda.id = doc.id // 🔥 define o ID antes de salvar

        doc.set(agenda)
            .addOnSuccessListener { onSuccess() }
            .addOnFailureListener { onError(it) }
    }

    // READ
    fun listar(
        onResult: (List<Agenda>) -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.get()
            .addOnSuccessListener { result ->
                val lista = result.map { doc ->
                    val agenda = doc.toObject(Agenda::class.java)
                    agenda.id = doc.id
                    agenda
                }
                onResult(lista)
            }
            .addOnFailureListener {
                onError(it)
            }
    }

    // UPDATE
    fun atualizar(
        agenda: Agenda,
        onSuccess: () -> Unit,
        onError: (Exception) -> Unit
    ) {
        collection.document(agenda.id)
            .set(agenda)
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
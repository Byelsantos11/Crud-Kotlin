package com.example.crudp1.repository
import com.example.crudp1.model.Produto
import com.google.firebase.firestore.FirebaseFirestore

class RepositoryRemote {

    private val db = FirebaseFirestore.getInstance()
    private val collection = db.collection("Produto")

    // 🔹 LISTAR
    fun listar(onResult: (List<Produto>) -> Unit) {
        collection.get().addOnSuccessListener { result ->
            val lista = result.map { doc ->
                doc.toObject(Produto::class.java)
            }
            onResult(lista)
        }
    }

    // 🔹 SALVAR
    fun salvar(produto: Produto) {
        val id = collection.document().id
        collection.document(id).set(produto.copy(id = id))
    }

    // 🔹 ATUALIZAR
    fun atualizar(produto: Produto) {
        collection.document(produto.id.toString()).set(produto)
    }

    // 🔹 DELETAR
    fun deletar(id: String) {
        collection.document(id).delete()
    }
}
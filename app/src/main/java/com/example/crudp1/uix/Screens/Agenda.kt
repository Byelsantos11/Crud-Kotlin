package com.example.crudp1.uix.Screens
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.crudp1.model.Agenda
import com.example.crudp1.viewModel.AgendaViewModel

@Composable
fun TelaAgenda(navController: NavController) {

    val viewModel: AgendaViewModel = viewModel()

    var titulo by remember { mutableStateOf("") }
    var descricao by remember { mutableStateOf("") }
    var dataInicio by remember { mutableStateOf("") }

    var tituloErro by remember { mutableStateOf(false) }
    var descricaoErro by remember { mutableStateOf(false) }
    var dataErro by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.carregarAgenda()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        // 🔙 Voltar
        IconButton(
            onClick = { navController.popBackStack() }
        ) {
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Voltar",
                modifier = Modifier.size(32.dp)
            )
        }

        Text(
            text = "Cadastro de Agenda",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Título
        TextField(
            value = titulo,
            onValueChange = {
                titulo = it
                tituloErro = false
            },
            label = { Text("Título") },
            isError = tituloErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (tituloErro) Text("Título obrigatório", color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Descrição
        TextField(
            value = descricao,
            onValueChange = {
                descricao = it
                descricaoErro = false
            },
            label = { Text("Descrição") },
            isError = descricaoErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (descricaoErro) Text("Descrição obrigatória", color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Data início
        TextField(
            value = dataInicio,
            onValueChange = {
                dataInicio = it
                dataErro = false
            },
            label = { Text("Data Início") },
            isError = dataErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (dataErro) Text("Data obrigatória", color = Color.Red)

        Spacer(modifier = Modifier.height(12.dp))

        // Botão salvar
        Button(
            onClick = {

                tituloErro = titulo.isBlank()
                descricaoErro = descricao.isBlank()
                dataErro = dataInicio.isBlank()

                if (tituloErro || descricaoErro || dataErro) return@Button

                val agenda = Agenda(
                    titulo = titulo,
                    descricao = descricao,
                    dataInicio = dataInicio,
                    concluida = false
                )

                viewModel.adicionar(
                    agenda,
                    onSuccess = { println("Agenda salva") },
                    onError = { println("Erro: ${it.message}") }
                )

                titulo = ""
                descricao = ""
                dataInicio = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Agendas cadastradas:",
            fontWeight = FontWeight.Bold
        )

        LazyColumn {
            items(viewModel.listaAgenda) { agenda ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text(agenda.titulo, fontWeight = FontWeight.Bold)
                        Text(agenda.descricao)
                        Text("Data: ${agenda.dataInicio}")

                        Text(
                            "Status: ${
                                if (agenda.concluida) "Concluída"
                                else "Pendente"
                            }"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                viewModel.deletar(
                                    agenda.id,
                                    onSuccess = { println("Deletado") },
                                    onError = { println("Erro ao deletar") }
                                )
                            }
                        ) {
                            Text("Excluir")
                        }
                    }
                }
            }
        }
    }
}
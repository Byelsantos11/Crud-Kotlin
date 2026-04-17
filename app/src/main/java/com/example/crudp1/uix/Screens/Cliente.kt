package com.example.crudp1.uix.Screens

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
import androidx.navigation.NavController
import com.example.crudp1.model.Cliente
import com.example.crudp1.viewModel.ClientViewModel

@Composable
fun TelaCliente(navController: NavController) {

    val viewModel: ClientViewModel = viewModel()

    var nome by remember { mutableStateOf("") }
    var cpf by remember { mutableStateOf("") }
    var idade by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }

    var nomeErro by remember { mutableStateOf(false) }
    var cpfErro by remember { mutableStateOf(false) }
    var idadeErro by remember { mutableStateOf(false) }
    var telefoneErro by remember { mutableStateOf(false) }

    // 🔥 Carrega só uma vez corretamente
    LaunchedEffect(Unit) {
        viewModel.carregarCliente()
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
            text = "Cadastro de Cliente",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Nome
        TextField(
            value = nome,
            onValueChange = {
                nome = it
                nomeErro = false
            },
            label = { Text("Nome") },
            isError = nomeErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (nomeErro) Text("Nome obrigatório", color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // CPF
        TextField(
            value = cpf,
            onValueChange = {
                cpf = it
                cpfErro = false
            },
            label = { Text("CPF") },
            isError = cpfErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (cpfErro) Text("CPF obrigatório", color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Idade
        TextField(
            value = idade,
            onValueChange = {
                idade = it.filter { char -> char.isDigit() }
                idadeErro = false
            },
            label = { Text("Idade") },
            isError = idadeErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (idadeErro) Text("Idade inválida", color = Color.Red)

        Spacer(modifier = Modifier.height(8.dp))

        // Telefone
        TextField(
            value = telefone,
            onValueChange = {
                telefone = it
                telefoneErro = false
            },
            label = { Text("Telefone") },
            isError = telefoneErro,
            modifier = Modifier.fillMaxWidth()
        )
        if (telefoneErro) Text("Telefone obrigatório", color = Color.Red)

        Spacer(modifier = Modifier.height(12.dp))

        // Botão salvar
        Button(
            onClick = {

                nomeErro = nome.isBlank()
                cpfErro = cpf.isBlank()
                idadeErro = idade.isBlank()
                telefoneErro = telefone.isBlank()

                if (nomeErro || cpfErro || idadeErro || telefoneErro) return@Button

                val idadeInt = idade.toIntOrNull()
                if (idadeInt == null) {
                    idadeErro = true
                    return@Button
                }

                val cliente = Cliente(
                    nomecliente = nome,
                    cpfcliente = cpf,
                    idadecliente = idadeInt,
                    telefonecliente = telefone,
                    verificadocliente = true
                )

                viewModel.adicionar(
                    cliente,
                    onSuccess = { println("Cliente salvo") },
                    onError = { println("Erro: ${it.message}") }
                )

                // limpa campos
                nome = ""
                cpf = ""
                idade = ""
                telefone = ""
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Text(
            text = "Clientes cadastrados:",
            fontWeight = FontWeight.Bold
        )

        LazyColumn {
            items(viewModel.listaCliente) { cliente ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    Column(modifier = Modifier.padding(8.dp)) {

                        Text(cliente.nomecliente, fontWeight = FontWeight.Bold)
                        Text("CPF: ${cliente.cpfcliente}")
                        Text("Idade: ${cliente.idadecliente}")
                        Text("Telefone: ${cliente.telefonecliente}")

                        Text(
                            "Verificação: ${
                                if (cliente.verificadocliente) "Verificado"
                                else "Não verificado"
                            }"
                        )

                        Spacer(modifier = Modifier.height(8.dp))

                        Button(
                            onClick = {
                                viewModel.deletar(
                                    cliente.id,
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
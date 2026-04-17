package com.example.crudp1.uix.Screens
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.crudp1.viewModel.UsuarioViewModel
import androidx.compose.runtime.LaunchedEffect

@Composable
fun Cadastro(navController: NavController){

    val viewModel: UsuarioViewModel = viewModel()
    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var nomeErro by remember { mutableStateOf(false) }
    var emailErro by remember { mutableStateOf(false) }
    var senhaErro by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text("Cadastro", fontSize = 28.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = nome,
            onValueChange = { nome = it; nomeErro = false },
            label = { Text("Nome") },
            isError = nomeErro
        )

        if (nomeErro) Text("Nome obrigatório", color = Color.Red)

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = email,
            onValueChange = { email = it; emailErro = false },
            label = { Text("Email") },
            isError = emailErro
        )

        if (emailErro) Text("Email obrigatório", color = Color.Red)

        Spacer(modifier = Modifier.height(10.dp))

        TextField(
            value = senha,
            onValueChange = { senha = it; senhaErro = false },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            isError = senhaErro
        )

        if (senhaErro) Text("Senha obrigatória", color = Color.Red)

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                nomeErro = nome.isBlank()
                emailErro = email.isBlank()
                senhaErro = senha.isBlank()

                if (nomeErro || emailErro || senhaErro) {
                    return@Button
                }

                viewModel.registrar(
                    nome = nome,
                    email = email,
                    senha = senha,
                    onSuccess = {
                        navController.navigate("login")
                    }
                )
            }
        ) {
            Text("Registrar")
        }

        Spacer(modifier = Modifier.height(10.dp))

        Button(
            onClick = {
                navController.navigate("login")
            }
        ) {
            Text("Voltar")
        }
        }
    }
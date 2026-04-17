package com.example.crudp1.uix.Screens
import androidx.compose.foundation.gestures.snapping.SnapPosition
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


@Composable
fun Login(navController: NavController){

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var emailErro by remember { mutableStateOf(false) }
    var senhaErro by remember { mutableStateOf(false) }

    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text(
            text = "Crud",
            fontSize = 30.sp,
            fontWeight = FontWeight.Medium,
        )

        Text(
            text = "Deivid Santos, Flávio Dias, Gabryel Rodrigues",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
        )


        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = email,
            shape = RoundedCornerShape(5.dp),
            onValueChange = {
                email = it
                emailErro = false

            },
            label = { Text("Email") },
            isError = emailErro
        )

        if (emailErro) {
            Text("Email obrigatório", color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(15.dp))

        TextField(
            value = senha,
            onValueChange = {
                senha = it
                senhaErro = false
            },
            label = { Text("Senha") },
            visualTransformation = PasswordVisualTransformation(),
            isError = senhaErro
        )

        if (senhaErro) {
            Text("Senha obrigatória", color = androidx.compose.ui.graphics.Color.Red)
        }

        Spacer(modifier = Modifier.height(15.dp))

        Button(
            onClick = {
                emailErro = email.isBlank()
                senhaErro = senha.isBlank()

                if (!emailErro && !senhaErro) {
                    navController.navigate("Home")
                }
            }
        ) {
            Text("Login")
        }

        Button(
            onClick = {
                navController.navigate("cadastro")
            }
        ) {
            Text("Cadastrar")
        }
    }
}
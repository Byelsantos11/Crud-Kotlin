package com.example.crudp1.uix.Screens
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun Home(navController: NavController){


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){

        Text(
            text = "Selecionar tipo de Crud:",
            fontWeight = FontWeight.Medium,
            fontSize = 26.sp
        )

        Spacer(modifier = Modifier.height(5.dp))

        Button(onClick = {
            navController.navigate("produto")
        }
                ) {
            Text("Produtos")
        }


        Button(onClick = {
            navController.navigate("Cliente")
        }
        ) {
            Text("Clietes")
        }


        Button(onClick = {
            navController.navigate("Agenda")
        }
        ) {
            Text("Agenda")
        }

    }
}



package com.example.crudp1.navigation
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.crudp1.uix.Screens.Cadastro
import com.example.crudp1.uix.Screens.Home
import com.example.crudp1.uix.Screens.Login
import com.example.crudp1.uix.Screens.TelaAgenda
import com.example.crudp1.uix.Screens.TelaCliente
import com.example.crudp1.uix.Screens.TelaProduto

@Composable
fun appNavigation() {

    val navController = rememberNavController()
    NavHost(navController, startDestination = "login") {

        composable("login") { Login(navController) }
        composable("Cadastro") { Cadastro(navController) }
        composable("Home") { Home(navController) }
        composable("produto") { TelaProduto(navController) }
        composable("Cliente") { TelaCliente(navController) }
        composable("Agenda") { TelaAgenda(navController) }
    }

}
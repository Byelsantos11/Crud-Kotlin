package com.example.crudp1
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.crudp1.navigation.appNavigation
import com.example.crudp1.ui.theme.Crudp1Theme
import com.google.firebase.FirebaseApp

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
                Crudp1Theme {
                    appNavigation()
                    FirebaseApp.initializeApp(this)
                }
            }
        }
    }


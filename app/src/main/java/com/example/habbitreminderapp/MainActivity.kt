package com.example.habbitreminderapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habbitreminderapp.Componentes.MainContent
import com.example.habbitreminderapp.Model.Configuracion
import com.example.habbitreminderapp.Model.Inicio
import com.example.habbitreminderapp.Model.MiPerfil
import com.example.habbitreminderapp.Model.MisMetas
import com.example.habbitreminderapp.Model.NuevaMeta
import com.example.habbitreminderapp.Model.Rutas
import com.example.habbitreminderapp.Navigations.Screen1
import com.example.habbitreminderapp.Navigations.Screen2
import com.example.habbitreminderapp.Navigations.Screen3
import com.example.habbitreminderapp.Navigations.Screen4
import com.example.habbitreminderapp.Navigations.Screen5
import com.example.habbitreminderapp.ui.theme.HabbitReminderAppTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HabbitReminderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                  val navigationController = rememberNavController()

                    NavHost(navController = navigationController, startDestination = Inicio.ruta){
                        composable(Inicio.ruta) { Screen1(navigationController) }
                        composable(MiPerfil.ruta) { Screen2(navigationController) }
                        composable(NuevaMeta.ruta) { Screen3(navigationController) }
                        composable(MisMetas.ruta) { Screen4(navigationController) }
                        composable(Configuracion.ruta) { Screen5(navigationController) }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}


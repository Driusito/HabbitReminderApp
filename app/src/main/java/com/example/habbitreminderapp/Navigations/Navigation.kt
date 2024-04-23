package com.example.habbitreminderapp.Navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.example.habbitreminderapp.Features.MainContent
import com.example.habbitreminderapp.Model.Configuracion
import com.example.habbitreminderapp.Model.Inicio
import com.example.habbitreminderapp.Model.MiPerfil
import com.example.habbitreminderapp.Model.MisMetas
import com.example.habbitreminderapp.Model.NuevaMeta

@Composable
fun Screen1(navigationController: NavHostController) {
    Box(modifier = Modifier.clickable { navigationController.navigate(MiPerfil.ruta) }
        .fillMaxSize()
        .background(Color.Green)){
        MainContent()
        Text(text = "Pantalla 1")
    }
}
@Composable
fun Screen2(navigationController: NavHostController) {
    Box(modifier = Modifier.clickable { navigationController.navigate(NuevaMeta.ruta) }
        .fillMaxSize()
        .background(Color.Red)){
        Text(text = "Pantalla 2")
    }
}
@Composable
fun Screen3(navigationController: NavHostController) {
    Box(modifier = Modifier.clickable { navigationController.navigate(MisMetas.ruta) }
        .fillMaxSize()
        .background(Color.Cyan)){
        Text(text = "Pantalla 3")
    }
}

@Composable
fun Screen4(navigationController: NavHostController) {
    Box(modifier = Modifier.clickable { navigationController.navigate(Configuracion.ruta) }
        .fillMaxSize()
        .background(Color.Yellow)){
        Text(text = "Pantalla 4")
    }
}

@Composable
fun Screen5(navigationController: NavHostController) {
    Box(modifier = Modifier.clickable { navigationController.navigate(Inicio.ruta) }
        .fillMaxSize()
        .background(Color.Magenta)){
        Text(text = "Pantalla 5")
    }
}
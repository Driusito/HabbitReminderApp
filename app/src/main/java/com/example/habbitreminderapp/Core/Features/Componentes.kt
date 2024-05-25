package com.example.habbitreminderapp.Core.Features

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import com.example.habbitreminderapp.Navigations.AppScreen
import com.example.habbitreminderapp.ui.theme.HabbitReminderAppTheme


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabbitReminderAppTheme {
        //AppBar()
        //MyOpcion(Icons.Filled.Home,"Mi perfil")
    }
}

@Composable
fun BotonNuevoHabito(navController: NavController) {
    var context = LocalContext.current
    IconButton(
        onClick = { navController.navigate(AppScreen.newTaskScreen.route) },
        modifier = Modifier.size(100.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(
                    Color(3, 169, 244, 255)
                )
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonConfirmar(createTask: () -> Unit) {
    var context = LocalContext.current

    IconButton(
        onClick = { createTask() },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Done,
            contentDescription = "",
            tint = Color.Green,
            modifier = Modifier
                .border(
                    2.dp,
                    Color.Green, CircleShape
                )
                .size(50.dp)
                .clip(CircleShape)
                .background(
                    Color(255, 255, 255, 255)
                )
        )

    }

}

@Composable
fun MyRutinaCalendario() {
    Box(
        modifier = Modifier
            .background(Color(224, 164, 118, 255))
            .fillMaxWidth()
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Filled.Face,
                tint = Color.White,
                contentDescription = "Otros",
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            Text(text = "Vacuna", modifier = Modifier.weight(5f))
            Icon(
                imageVector = Icons.Filled.Done,
                contentDescription = "",
                tint = Color.Green,
                modifier = Modifier
                    .border(
                        2.dp,
                        Color.Green, CircleShape
                    )
                    .size(50.dp)
                    .clip(CircleShape)
                    .background(
                        Color(255, 255, 255, 255)
                    )
            )

        }
    }
}

@Composable
fun ChooseColorDialogue(
    mostrar: Boolean,
    onColorSelected: (Long) -> Unit,
    onDismiss: () -> Unit
) {
    if (mostrar) {
        val colors = listOf(
            Color(0xFFE93939), // Rojo
            Color(0xFF36B1E4), // Azul cielo
            Color(0xFFFFA07A), // Naranja suave
            Color(0xFF98FB98), // Verde suave
            Color(0xFFD8BFD8), // Morado suave
            Color(0xFFDDC64A), // Amarillo dorado
            Color(0xFF40E0D0), // Turquesa
            Color(0xFFC96496), // Rosa caliente
            Color(0xFFA52A2A)  // Marrón
        )

        Dialog(onDismissRequest = { onDismiss() }) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(3),
                horizontalArrangement = Arrangement.Center,
                verticalArrangement = Arrangement.SpaceBetween,
                content = {
                    items(colors.size) { index ->
                        Box(
                            modifier = Modifier
                                .padding(10.dp)
                                .size(50.dp)
                                .background(colors[index])
                                .clickable {
                                    val selectedColor = colors[index].toArgb().toLong() and 0xFFFFFFFFL
                                    onColorSelected(selectedColor)
                                    onDismiss()
                                }
                        )
                    }
                }
            )
        }
    }
}
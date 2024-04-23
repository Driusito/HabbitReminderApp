package com.example.habbitreminderapp.Componentes

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habbitreminderapp.Greeting
import com.example.habbitreminderapp.ui.theme.HabbitReminderAppTheme


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HabbitReminderAppTheme {
       MyTopBar()
        //MyOpcion(Icons.Filled.Home,"Mi perfil")
    }
}

@Composable
fun BotonNuevoHabito() {
    var context = LocalContext.current
    IconButton(
        onClick = { Toast.makeText(context, "Nueva tarea", Toast.LENGTH_SHORT).show() },
        modifier = Modifier.size(50.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = "",
            tint = Color.White,
            modifier = Modifier
                .size(50.dp)
                .clip(CircleShape)
                .background(
                    Color(3, 169, 244, 255)
                )
        )

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BotonConfirmar() {
    var context = LocalContext.current

    IconButton(
        onClick = { Toast.makeText(context, "Confirmar", Toast.LENGTH_SHORT).show() },
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
                imageVector = Icons.Filled.Face, tint = Color.White, contentDescription = "Otros", modifier = Modifier
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
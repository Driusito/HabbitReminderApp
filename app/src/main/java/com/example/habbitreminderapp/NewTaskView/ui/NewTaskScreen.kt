package com.example.habbitreminderapp.NewTaskView.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbitreminderapp.Core.Features.BotonConfirmar
import com.example.habbitreminderapp.Core.Features.MyCalendar


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
fun NewTaskScreen() {
    var abrirCalendario by remember { mutableStateOf(false) }
    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(
                text = "Nueva Meta",
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
        }, navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue
        )
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)

        ) {
            // Spacer(modifier = Modifier.weight(.1f))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Nombre",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(
                        2f
                    )
                )
                Spacer(modifier = Modifier.weight(.25f))
                Text(
                    text = "Categoria",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(
                        2f
                    )
                )
            }
            Spacer(modifier = Modifier.weight(.25f))

            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = "", onValueChange = {}, modifier = Modifier.weight(
                        2f
                    )
                )
                Spacer(modifier = Modifier.weight(.25f))

                Box(modifier = Modifier.weight(2f)) {
                    MyDropDownMenu()
                }


            }
            Spacer(modifier = Modifier.weight(.25f))

            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Descripcion",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(
                        2f
                    )
                )
            }
            Spacer(modifier = Modifier.weight(.25f))
            OutlinedTextField(
                value = "", onValueChange = {}, modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.weight(.25f))

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Fecha de inicio",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(
                        2f
                    )
                )
                Spacer(modifier = Modifier.weight(.05f))
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.weight(.45f))

                Text(
                    text = "Color",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.weight(
                        1f
                    )
                )
                Box(
                    modifier = Modifier
                        .background(Color.Red)
                        .size(20.dp)
                )
                Spacer(modifier = Modifier.weight(.5f))

            }
            Spacer(modifier = Modifier.weight(.25f))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                Button(onClick = { abrirCalendario = true }) {
                    Text(text = "Abrir Calendario")

                }

            }
            Spacer(modifier = Modifier.weight(.25f))

            Text(text = "Fecha seleccionada: ",fontSize = 20.sp, modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally))

            Spacer(modifier = Modifier.weight(.25f))
            if (abrirCalendario) {
                MyCalendar()
            }

            Text(
                text = "Recordatorio cada",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,

                )
            Spacer(modifier = Modifier.weight(.25f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(.5f))
                Text(
                    text = "Minutos", color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, modifier = Modifier.weight(1.5f)
                )
                Spacer(modifier = Modifier.weight(2.5f))

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(.5f))
                Text(
                    text = "Horas", color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, modifier = Modifier.weight(1.5f)
                )
                Spacer(modifier = Modifier.weight(2.5f))

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = "",
                    onValueChange = {},
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(.5f))
                Text(
                    text = "Días", color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, modifier = Modifier.weight(1.5f)
                )
                Spacer(modifier = Modifier.weight(2.5f))

            }
            Spacer(modifier = Modifier.weight(.25f))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                BotonConfirmar()

            }

        }
    }


}

@Composable
fun MyDropDownMenu() {
    var selectedText by remember { mutableStateOf("") }
    var clicked by remember { mutableStateOf(false) }
    val lista = listOf("Ejercicio", "Ocio", "Salud")

    OutlinedTextField(value = selectedText,
        onValueChange = { selectedText = it },
        enabled = false,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = ""
            )
        },
        modifier = Modifier
            .clickable { clicked = true }
    )
    DropdownMenu(
        expanded = clicked, onDismissRequest = { clicked = false },
    ) {
        lista.forEach { texto ->
            DropdownMenuItem(trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = " "
                )
            }, text = { Text(text = texto) }, onClick = {
                clicked = false
                selectedText = texto
            })
        }
    }
}
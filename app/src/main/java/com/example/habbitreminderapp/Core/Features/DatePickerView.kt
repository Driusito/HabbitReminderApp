package com.example.habbitreminderapp.Core.Features

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.habbitreminderapp.NewTaskView.ui.NewTaskScreenViewModel
import java.text.SimpleDateFormat
import java.util.Date

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCalendar(show:Boolean, newTaskScreenViewModel: NewTaskScreenViewModel): String {
    val state = rememberDatePickerState()
    val showed: Boolean by newTaskScreenViewModel.openCalendar.observeAsState(initial = show)
    var mostrarHora by remember { mutableStateOf(false) }
    var hora by remember { mutableStateOf("") }
    var fechaSeleccionada = ""

    if (showed) {
        DatePickerDialog(
            onDismissRequest = {
                mostrarHora = false
                newTaskScreenViewModel.showCalendar(false)
            },
            confirmButton = {
                Button(onClick = {
                    mostrarHora = true
                    // newTaskScreenViewModel.showCalendar(false)
                }) {
                    Text(text = "Confirmar")
                }
            }
        ) {
            DatePicker(
                modifier = Modifier.padding(20.dp),
                headline = { Text(text = "Introduce fecha") },
                title = null,
                state = state,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.Green,
                    selectedDayContainerColor = Color(64, 141, 201, 255)
                ),
                dateValidator = {date ->
                    date > System.currentTimeMillis()- 86400000
                }
            )
        }
    }


    if (mostrarHora) {
        // Mostrar diálogo de selección de tiempo
        MyTimePicker({ horaRetornada ->
            // Callback para cuando se selecciona una hora
            hora = horaRetornada
            mostrarHora = false // Cerrar el diálogo después de seleccionar la hora

            // Formatear la fecha como una cadena de texto
            val selectedDateMillis = state.selectedDateMillis
            val selectedDate = selectedDateMillis?.let { Date(it) }
            val dateFormatter = SimpleDateFormat("dd/MM/yyyy")
            fechaSeleccionada = selectedDate?.let { dateFormatter.format(it) } + " " + hora
            Log.i("CalendarioSelected", fechaSeleccionada)
            newTaskScreenViewModel.stringToLong(fechaSeleccionada)
            newTaskScreenViewModel.showCalendar(false)

        }, {
            if (fechaSeleccionada.isNotEmpty()) {
                Log.i("CalendarioYes", fechaSeleccionada)
            }
            newTaskScreenViewModel.showCalendar(false)
            mostrarHora = false

        }) {
            mostrarHora = false
            newTaskScreenViewModel.showCalendar(false)
        }

    }

    return fechaSeleccionada
}


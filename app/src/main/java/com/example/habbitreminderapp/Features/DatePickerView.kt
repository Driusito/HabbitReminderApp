package com.example.habbitreminderapp.Features

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
@Preview
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCalendar() {
    val state = rememberDatePickerState()
    var mostrarCalendario by remember {
        mutableStateOf(true)
    }
    var mostrarHora by remember {
        mutableStateOf(false)
    }

    if (mostrarCalendario) {
        DatePickerDialog( onDismissRequest = { mostrarCalendario = false
                                            mostrarHora=false}, confirmButton = {
            Button(onClick = {
                mostrarCalendario = false
                mostrarHora = true
            }) {
                Text(text = "Confirmar")


            }
        }) {
            DatePicker(
                modifier = Modifier.padding(20.dp), headline = { Text(text = "Introduce fecha") },
                title = null,
                state = state,
                colors = DatePickerDefaults.colors(
                    todayContentColor = Color.Green,
                    selectedDayContainerColor = Color(64, 141, 201, 255)


                )
            )
        }
    }
    if(mostrarHora){
        MyTimePicker()
        mostrarHora=false

    }


}

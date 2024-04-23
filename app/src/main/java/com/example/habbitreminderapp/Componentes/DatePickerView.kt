package com.example.habbitreminderapp.Componentes

import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyCalendar() {
    val state = rememberDatePickerState()
    DatePicker(state =state , colors = DatePickerDefaults.colors(
        todayContentColor = Color.Green

    ))
}

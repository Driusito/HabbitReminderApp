package com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.habbitreminderapp.Features.CustomCalendar

@Composable
fun MyCalendarFlat(){
Box(modifier = Modifier
    .fillMaxSize()
    .background(Color.Yellow)){
    CustomCalendar(numeroDelMes = 0)
}
}
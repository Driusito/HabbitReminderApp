package com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import com.example.habbitreminderapp.Core.Features.CustomCalendar
import com.example.habbitreminderapp.Core.Features.ItemCalendario
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableUiState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@Composable
fun CustomCalendar(myTaskCalendarViewModel: MyTaskCalendarViewModel) {
    val calendar = Calendar.getInstance()
    var mes by remember { mutableStateOf(calendar.get(Calendar.MONTH)) }
    val fechaSeleccionada: Long by myTaskCalendarViewModel.startTime.observeAsState(initial = 0L)
    val uiStateForDay by myTaskCalendarViewModel.uiStateForDay.observeAsState(initial = MyTaskTableUiState.Loading)
    val allTasks by myTaskCalendarViewModel.allTasks.observeAsState(initial = emptyList())

    var showDialog by remember { mutableStateOf(false) } // Controla si se debe mostrar el diálogo

    when (uiStateForDay) {
        is MyTaskTableUiState.Error -> Text(text = "Error al cargar calendario")
        is MyTaskTableUiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }

        is MyTaskTableUiState.Success -> {
            val listaTask = (uiStateForDay as MyTaskTableUiState.Success).tasks
            Log.e("Lista", listaTask.toString())
            val calendar = Calendar.getInstance().apply {
                set(Calendar.MONTH, mes)
                firstDayOfWeek = Calendar.MONDAY
            }
            val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
            val firstDayOfMonth = calendar.apply { set(Calendar.DAY_OF_MONTH, 1) }
            val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK)
            val year = firstDayOfMonth.get(Calendar.YEAR)
            val meses = listOf(
                "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"
            )
            val daysOfWeek = listOf("L", "M", "M", "J", "V", "S", "D")

            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .background(Color.White)
                    .padding(20.dp)
            ) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${meses[firstDayOfMonth.get(Calendar.MONTH)]} $year",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        color = Color.Black
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {
                        IconButton(onClick = { mes-- }) {
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.ChevronLeft,
                                contentDescription = "Mes anterior"
                            )
                        }

                        IconButton(onClick = { mes++ }) {
                            Icon(
                                modifier = Modifier.size(50.dp),
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = "Mes siguiente"
                            )
                        }
                    }
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    daysOfWeek.forEach { day ->
                        Text(
                            textAlign = TextAlign.Center,
                            text = day,
                            fontSize = 20.sp,
                            modifier = Modifier
                                .padding(4.dp)
                                .size(40.dp),
                            color = Color.Black
                        )
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

                val startingDay = (firstDayOfWeek - 2 + 7) % 7
                val totalDays = daysInMonth + startingDay
                val rows = (totalDays / 7) + if (totalDays % 7 > 0) 1 else 0

                (0 until rows).forEach { rowIndex ->
                    Row(modifier = Modifier.fillMaxWidth()) {
                        for (colIndex in 0 until 7) {
                            val day = rowIndex * 7 + colIndex - startingDay + 1
                            if (((colIndex < firstDayOfWeek - 2 && day < firstDayOfWeek - 2) && rowIndex == 0) || (firstDayOfWeek == 1 && rowIndex == 0 && colIndex != 6))
                                EmptySpace(day)
                            else if (day in 1..daysInMonth) {
                                DayItem(day, mes, allTasks, fechaSeleccionada) { fecha ->
                                    showDialog = false
                                    myTaskCalendarViewModel.setDay(fecha * 1000)
                                    val formattedDate =
                                        SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(
                                            Date(fecha * 1000)
                                        )
                                    showDialog =
                                        true // Mostrar el diálogo cuando se hace clic en un día
                                }
                            }
                        }
                    }
                }



                        listTask(task = listaTask, show = showDialog) {
                            myTaskCalendarViewModel.setDay(0)
                            showDialog = false // Ocultar el diálogo cuando se cierra
                        }
                    }

            }
        }
    }


@Composable
fun EmptySpace(day: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color(162, 221, 248, 255))
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {

    }
}

@Composable
fun DayItem(
    day: Int,
    month: Int,
    taskList: List<TaskModel>,
    fechaSeleccionada: Long?,
    onDateSelected: (Long) -> Unit
) {
    val selectedDateInMillis = Calendar.getInstance().apply {
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, 0)
        set(Calendar.MINUTE, 0)
        set(Calendar.SECOND, 0)
        set(Calendar.MILLISECOND, 0)
    }.timeInMillis

    val endDateInMillis = Calendar.getInstance().apply {
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
        set(Calendar.HOUR_OF_DAY, 23)
        set(Calendar.MINUTE, 59)
        set(Calendar.SECOND, 59)
        set(Calendar.MILLISECOND, 999)
    }.timeInMillis

    val hasTasks = taskList.any { task ->
        val taskTime =
            task.fecha * 1000 // Assuming startTime is in seconds, convert to milliseconds
        taskTime in selectedDateInMillis..endDateInMillis
    }

    val filteredTasks = taskList.filter { task ->
        val taskTime =
            task.fecha * 1000 // Assuming startTime is in seconds, convert to milliseconds
        taskTime in selectedDateInMillis..endDateInMillis
    }
    val numTaskCompleted = filteredTasks.count { it.cumplida == 1 }
    val numTaskForDo = filteredTasks.count { it.cumplida == 0 }


    Box(
        modifier = Modifier
            .clip(CutCornerShape(10.dp))
            .padding(4.dp)
            .size(40.dp)
            .border(BorderStroke(2.dp, Color.Black))
            .background(
                color = if (hasTasks && numTaskCompleted == filteredTasks.size) {
                    Color.Green
                } else if (hasTasks && (numTaskCompleted > 0 && numTaskCompleted < filteredTasks.size)) {

                    Color.Yellow
                } else if (hasTasks && numTaskCompleted ==0) {

                    Color.Red
                } else {
                    Color.Transparent
                },
                shape = CutCornerShape(10.dp)
            )
            .clickable {
                // Primero, cierra el diálogo
                onDateSelected(selectedDateInMillis / 1000)
                // Luego, actualiza el estado de la fecha seleccionada
                onDateSelected(selectedDateInMillis / 1000)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            textAlign = TextAlign.Center,
            fontSize = 20.sp,
            color = Color.Black
        )
    }
}

@Composable
fun listTask(task: List<TaskModel>, show: Boolean, onShow: () -> Unit) {
    if (show && task.isNotEmpty()) {
        Dialog(
            onDismissRequest = { onShow() },
            content = {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .background(Color.White)
                        .height(500.dp)
                        .padding(horizontal = 16.dp)
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            IconButton(
                                onClick = { onShow() },
                                modifier = Modifier.padding(end = 8.dp)
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Cerrar"
                                )
                            }
                        }
                        LazyColumn(modifier = Modifier.fillMaxSize().border(1.dp,MaterialTheme.colorScheme.primary,
                            RoundedCornerShape(10.dp)
                        ),
                            contentPadding = PaddingValues(vertical = 5.dp, horizontal = 5.dp),
                            content = {
                                items(task, key = { it.id }) { task ->
                                    ItemCalendario(task)
                                    Spacer(modifier = Modifier.size(10.dp))
                                }
                                item() {
                                    Box(modifier = Modifier.background(Color.Red))
                                }
                            }
                        )
                    }
                }
            }
        )
    }
}
package com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui

import android.util.Log
import android.widget.Toast
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
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


    val lifeCycle = LocalLifecycleOwner.current.lifecycle



    when (uiStateForDay) {
        is MyTaskTableUiState.Error -> Text(text = "Error al cargar calendario")
        is MyTaskTableUiState.Loading -> CircularProgressIndicator()
        is MyTaskTableUiState.Success -> {
            val listaTask = (uiStateForDay as MyTaskTableUiState.Success).tasks
            val calendar = Calendar.getInstance().apply {
                set(Calendar.MONTH, mes)
                firstDayOfWeek = Calendar.MONDAY // Establecer el primer día de la semana en lunes
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
                //Cabecera(mes)
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp), verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = meses[firstDayOfMonth.get(Calendar.MONTH)] + " $year",
                        style = MaterialTheme.typography.headlineLarge,
                        modifier = Modifier
                            .padding(vertical = 8.dp),
                        color = Color.Black
                    )
                    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceAround) {


                        IconButton(onClick = { mes-- }) {
                            Icon(
                                modifier = Modifier
                                    .size(50.dp),
                                imageVector = Icons.Default.ChevronLeft,
                                contentDescription = "Mes anterior"
                            )
                        }

                        IconButton(onClick = { mes++ }) {
                            Icon(
                                modifier = Modifier
                                    .size(50.dp),
                                imageVector = Icons.Default.ChevronRight,
                                contentDescription = "Mes anterior"
                            )
                        }

                    }


                }


                //Dias de la semana
                Row(
                    modifier = Modifier.fillMaxWidth(),

                    ) {
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

                //Dias del mes
                val startingDay =
                    (firstDayOfWeek - 2 + 7) % 7 // Ajustado para que el primer día sea el día 1 del mes
                val totalDays = daysInMonth + startingDay
                val rows = (totalDays / 7) + if (totalDays % 7 > 0) 1 else 0
                (0 until 31).forEach { rowIndex ->
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        //Hardcodeado para que muestre a partir del segundo día
                        //pero basta con que se consiga el primer día de la semana, del mes
                        // Hay que tener en cuenta que la cuenta se empieza desde el domingo
                        //Y tambien empieza por 1, por lo que para que señale al lunes, deberia ser el 2
                        for (colIndex in 0 until 7) {
                            val day = rowIndex * 7 + colIndex - startingDay + 1
                            //Si no empieza el mes, un "hueco", si no el day
                            if (((colIndex < firstDayOfWeek - 2 && day < firstDayOfWeek - 2) && rowIndex == 0) || (firstDayOfWeek == 1 && rowIndex == 0 && colIndex != 6))
                                EmptySpace(
                                    day
                                ) else if (day in 1..daysInMonth) {
                                DayItem(day, mes, fechaSeleccionada) { fecha ->
                                    myTaskCalendarViewModel.setDay(fecha * 1000)
                                    Log.e("Tamaño", listaTask.size.toString())
                                    Log.e("Dia seleccionado", fecha.toString())

                                }

                            }
                        }
                    }
                }
                Text(text = "Tareas")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .border(1.dp, color = Color.Black)
                ) {

                    Column(modifier = Modifier.fillMaxWidth()) {

                        Row(horizontalArrangement = Arrangement.SpaceAround) {
                            Text(
                                text = "Nombre",
                                Modifier.weight(2.5f),
                                fontWeight = FontWeight.Bold
                            )
                            Text(
                                text = "Hora de inicio",
                                Modifier.weight(2f),
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = "Cumplida", fontWeight = FontWeight.Bold)
                        }

                        listTask(task = listaTask)

                    }
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

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(CutCornerShape(10.dp))
            .padding(4.dp)
            .background(
                if (selectedDateInMillis / 1000 == fechaSeleccionada) Color(
                    63,
                    81,
                    181,
                    255
                ) else Color(
                    33,
                    150,
                    243,
                    255
                )
            )
            .size(40.dp)
            .clickable {
                onDateSelected(selectedDateInMillis / 1000)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dateString = dateFormat.format(selectedDateInMillis)
            },
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            color = Color.White,
            fontSize = 16.sp,
            fontFamily = FontFamily.Monospace
        )
    }
}

@Composable
fun listTask(task: List<TaskModel>) {
    LazyColumn(contentPadding = PaddingValues(vertical = 5.dp), content = {
        items(task, key = { it.id }) { task ->
            ItemCalendario(task)
            Spacer(modifier = Modifier.size(10.dp))
        }
        item() {
            Box(modifier = Modifier.background(Color.Red))

        }


    })
}

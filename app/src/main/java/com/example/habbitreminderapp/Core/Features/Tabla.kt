package com.example.habbitreminderapp.Core.Features

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit





@Preview
@Composable
fun ItemListaPreview() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(12.dp))
            .background(Color(64, 110, 180, 255))
            .fillMaxWidth(.9f)
    ) {
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(.5f))
            Box(modifier = Modifier.weight(2.5f)) {
                Column() {
                    Text(
                        text = "Nombre Tarea",
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = "Hora de la tarea",
                        color = Color(221, 89, 49, 255),
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(text = "Siguiente en: ")
                }
            }
            Spacer(modifier = Modifier.weight(.5f))

            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .padding(10.dp)
                    .weight(1f)
            ) {
                Icon(imageVector = Icons.Default.CropSquare, contentDescription = "")
            }


        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemLista(taskModel: TaskModel, tipoFormato: Int, setDone: () -> Unit, newTask:() ->Unit) {
    val currentTime = System.currentTimeMillis()
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background( if(taskModel.fecha<=currentTime/1000)Color(64, 110, 180, 255) else Color.Blue)
            .fillMaxWidth(.9f)
    ) {
        Row(
            Modifier
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(10.dp)) {
                Icon(imageVector = Icons.Default.CropSquare, contentDescription = "")
            }
            Box() {
                Column() {
                    val fechaComienzo = taskModel.fecha
                    val siguienteFecha = taskModel.proximaFecha

                    Text(text = taskModel.nombre, color = Color.White)
                    val timestamp = fechaComienzo * 1000 // Multiplica por 1000 para convertir segundos a milisegundos
                    val date = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(timestamp),
                        ZoneId.systemDefault() // Usa la zona horaria del sistema
                    )

                    val timestamp2 = siguienteFecha * 1000 // Multiplica por 1000 para convertir segundos a milisegundos
                    val date2 = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(timestamp2),
                        ZoneId.systemDefault() // Usa la zona horaria del sistema
                    )

                    val formatter = if (tipoFormato == 0) {
                        DateTimeFormatter.ofPattern("HH:mm")
                    } else {
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    }
                    val formattedTime = date.format(formatter)
                    val formattedTime2 = date2.format(formatter)

                    Text(text = "Inicio: $formattedTime", color = Color(238, 229, 217, 255))
                    Text(text = "Próxima: $formattedTime2", color = Color(238, 229, 217, 255))





                    if (fechaComienzo  >= currentTime/1000) {  // Comprobar si la fecha de la tarea es mayor o igual a la hora actual
                        Text(
                            text = "Tiempo restante: ",
                            color = Color(218, 134, 7, 255)
                        )

                        val tiempoRestante = TimeDisplay(
                            targetTimeMilliseconds = fechaComienzo * 1000 // Multiplica por 1000 para convertir segundos a milisegundos
                        )

                        if (tiempoRestante <= 0){
                            setDone()
                            newTask()
                        }
                    }
                }
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Pagina(myTaskTableViewModel: MyTaskTableViewModel, tasksToday: List<TaskModel>, tasksTomorrow: List<TaskModel>, tasksComing: List<TaskModel>) {
    val coroutineScope = rememberCoroutineScope()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn {
            itemsIndexed(listOf("Atrasados", "Hoy", "Mañana", "Proximos")) { index, categoria ->
                Text(text = categoria, modifier = Modifier.padding(20.dp))
                when (categoria) {
                    "Atrasados" -> {

                    }

                    "Hoy" -> {
                        tasksToday.forEach { task ->
                            ItemLista(taskModel = task, tipoFormato = 0,
                                newTask = {
                                    val nuevaFecha = task.proximaFecha
                                    val siguienteFecha = nuevaFecha + task.margen
                                    val nuevaTarea = task.copy(id = task.id+1, nombre = task.nombre,
                                        color = task.color,
                                        descripcion = task.color,
                                        fecha = nuevaFecha,
                                        margen = task.margen,
                                        proximaFecha = siguienteFecha,
                                        cumplida = 0,
                                        categoriaId = task.categoriaId
                                    )
                                    coroutineScope.launch {
                                        myTaskTableViewModel.addTask(nuevaTarea)
                                    }
                                },
                                setDone = {
                                    coroutineScope.launch {
                                        myTaskTableViewModel.setDoneTask(task.id)
                                    }
                                }
                            )
                        }

                    }

                    "Mañana" -> {
                        tasksTomorrow.forEach { task ->
                            ItemLista(taskModel = task, tipoFormato = 0, setDone = {}) {
                                coroutineScope.launch {
                                    myTaskTableViewModel.setDoneTask(task.id)
                                }
                            }
                        }

                    }

                    "Proximos" -> {
                        tasksComing.forEach { task ->
                            ItemLista(taskModel = task, tipoFormato = 1, setDone = {}) {
                                coroutineScope.launch {
                                    myTaskTableViewModel.setDoneTask(task.id)
                                }
                            }
                        }
                    }
                }
            }
        }
    }


}

@Composable
fun TimeDisplay(targetTimeMilliseconds: Long): Long {
    val currentTime = System.currentTimeMillis()  // Hora actual del dispositivo
    var remainingTime by remember { mutableStateOf(targetTimeMilliseconds - currentTime) }

    LaunchedEffect(key1 = targetTimeMilliseconds) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime -= 1000L  // Reducir en 1 segundo cada vez que pasa un segundo
        }
    }

    val days = TimeUnit.MILLISECONDS.toDays(remainingTime)
    val hours = TimeUnit.MILLISECONDS.toHours(remainingTime) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60

    val formattedTime = when {
        days > 0 -> String.format("%d días, %02d horas, %02d minutos y %02d segundos", days, hours, minutes, seconds)
        hours > 0 -> String.format("%02d horas, %02d minutos y %02d segundos", hours, minutes, seconds)
        minutes > 0 -> String.format("%02d minutos y %02d segundos", minutes, seconds)
        else -> String.format("%02d segundos", seconds)
    }

    Text(
        text = if (remainingTime > 0) formattedTime else "Fin",
        color = if (remainingTime > 0) Color.Green else Color.Red,
        overflow = TextOverflow.Ellipsis
    )
    return remainingTime
}


fun formatTime(milliseconds: Long): String {
    val days = TimeUnit.MILLISECONDS.toDays(milliseconds)
    val hours = TimeUnit.MILLISECONDS.toHours(milliseconds) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(milliseconds) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(milliseconds) % 60

    return String.format("%d días, %02d horas, %02d minutos y %02d segundos", days, hours, minutes, seconds)
}
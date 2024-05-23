package com.example.habbitreminderapp.Core.Features

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.concurrent.TimeUnit


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemListaPreview(taskModel: TaskModel, viewModel: MyTaskTableViewModel,tipoFormato: Int) {
    val coroutineScope = rememberCoroutineScope()
    var currentTime by remember { mutableStateOf(System.currentTimeMillis()) }

    var showError by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                val currentTimestampSeconds = currentTime / 1000
                if (currentTimestampSeconds >= taskModel.fecha &&currentTimestampSeconds <= taskModel.proximaFecha) {
                    coroutineScope.launch {
                        viewModel.setDoneTask(taskModel.id)
                        val lastID = viewModel.getLastID()
                        viewModel.addTask(
                            taskModel.copy(
                                id = lastID,
                                fecha = taskModel.proximaFecha,
                                proximaFecha = taskModel.proximaFecha + taskModel.margen
                            )
                        )
                    }
                } else {
                    showError = true
                }

            }
            .clip(RoundedCornerShape(12.dp))
            .background(Color(64, 110, 180, 255))
            .fillMaxWidth(.9f)
    ) {
        if (showError) {
            Snackbar(
                action = {
                    TextButton(onClick = { showError = false }) {
                        Text("OK")
                    }
                },
                modifier = Modifier.padding(8.dp)
            ) {
                Text(text = "Aún no puedes editar")
            }
        }
        Row(
            Modifier
                .padding(10.dp)
                .fillMaxWidth(), verticalAlignment = Alignment.CenterVertically
        ) {
            Box(modifier = Modifier.weight(2.5f)) {
                Column {
                    Text(
                        text = taskModel.nombre,
                        color = Color.White,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
            Spacer(modifier = Modifier.weight(.5f))

            Icon(
                imageVector = Icons.Default.Face,
                contentDescription = "",
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.weight(.5f))

            val fechaComienzo = taskModel.fecha
            val timestamp = fechaComienzo * 1000
            val date = LocalDateTime.ofInstant(
                Instant.ofEpochMilli(timestamp),
                ZoneId.systemDefault()
            )

            val formatter = if (tipoFormato == 0) {
                DateTimeFormatter.ofPattern("HH:mm")
            } else {
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
            }
            val formattedTime = date.format(formatter)


            Box(
                contentAlignment = Alignment.Center, modifier = Modifier
                    .padding(10.dp)
                    .weight(2.5f)
            ) {
                Text(text = formattedTime)
            }
        }
    }
}



@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemLista(
    taskModel: TaskModel,
    tipoFormato: Int,
    myTaskTableViewModel: MyTaskTableViewModel
) {
    var currentTime by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }

    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(
                if (currentTime / 1000 < taskModel.proximaFecha) Color(
                    64,
                    110,
                    180,
                    255
                ) else Color.Blue
            )
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
            Box {
                Column {
                    val fechaComienzo = taskModel.fecha
                    val siguienteFecha = taskModel.proximaFecha

                    Text(text = taskModel.nombre, color = Color.White)
                    val timestamp = fechaComienzo * 1000
                    val date = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(timestamp),
                        ZoneId.systemDefault()
                    )

                    val timestamp2 = siguienteFecha * 1000
                    val date2 = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(timestamp2),
                        ZoneId.systemDefault()
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

                    val active = remember { mutableStateOf(true) }

                    if (siguienteFecha >= currentTime / 1000) {
                        Text(
                            text = "Tiempo restante para confirmar: ",
                            color = Color(218, 134, 7, 255)
                        )
                        if (active.value) {
                            LaunchedEffect(siguienteFecha) {
                                while (true) {
                                    delay(1000L)
                                    val tiempoRestante =
                                        siguienteFecha * 1000 - System.currentTimeMillis()
                                    if (tiempoRestante <= 0) {
                                        myTaskTableViewModel.setDoneTask(taskModel.id)
                                        myTaskTableViewModel.addTask(
                                            taskModel.copy(
                                                id = myTaskTableViewModel.getLastID(),  // Asegúrate de incrementar el ID
                                                nombre = taskModel.nombre,
                                                color = taskModel.color,
                                                descripcion = taskModel.descripcion,
                                                fecha = taskModel.proximaFecha,
                                                margen = taskModel.margen,
                                                proximaFecha = taskModel.proximaFecha + taskModel.margen,
                                                cumplida = 0,
                                                categoriaId = taskModel.categoriaId
                                            )
                                        )
                                        active.value = false
                                        break
                                    }
                                }
                            }
                            TimeDisplay(targetTimeMilliseconds = siguienteFecha * 1000)
                        }
                    }
                }
            }
        }
    }
}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Pagina(
    myTaskTableViewModel: MyTaskTableViewModel,
    tasksToday: List<TaskModel>,
    tasksTomorrow: List<TaskModel>,
    tasksComing: List<TaskModel>
) {
    val listState = rememberLazyListState()
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(state = listState) {
            itemsIndexed(listOf("Atrasados", "Hoy", "Mañana", "Próximos")) { index, categoria ->
                Text(text = categoria, modifier = Modifier.padding(20.dp))
                when (categoria) {
                    "Atrasados" -> {
                        // ...
                    }

                    "Hoy" -> {
                        tasksToday.forEach { task ->
                            ItemListaPreview(task,myTaskTableViewModel,0)

                        }
                    }

                    "Mañana" -> {
                        tasksTomorrow.forEach { task ->
                            ItemListaPreview(task,myTaskTableViewModel,0)
                        }
                    }

                    "Próximos" -> {
                        tasksComing.forEach { task ->
                            ItemListaPreview(task,myTaskTableViewModel,1)
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TimeDisplay(targetTimeMilliseconds: Long): Long {
    val currentTime by remember {
        mutableLongStateOf(System.currentTimeMillis())
    }
    var remainingTime by remember {
        mutableLongStateOf(
            targetTimeMilliseconds - currentTime
        )
    }

    LaunchedEffect(key1 = targetTimeMilliseconds) {
        while (remainingTime > 0) {
            delay(1000L)
            remainingTime = targetTimeMilliseconds - System.currentTimeMillis()
        }
    }

    val days = TimeUnit.MILLISECONDS.toDays(remainingTime)
    val hours = TimeUnit.MILLISECONDS.toHours(remainingTime) % 24
    val minutes = TimeUnit.MILLISECONDS.toMinutes(remainingTime) % 60
    val seconds = TimeUnit.MILLISECONDS.toSeconds(remainingTime) % 60

    val formattedTime = when {
        days > 0 -> String.format(
            "%d días, %02d horas, %02d minutos y %02d segundos",
            days,
            hours,
            minutes,
            seconds
        )

        hours > 0 -> String.format(
            "%02d horas, %02d minutos y %02d segundos",
            hours,
            minutes,
            seconds
        )

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

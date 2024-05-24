package com.example.habbitreminderapp.Core.Features

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.DismissDirection
import androidx.compose.material3.DismissState
import androidx.compose.material3.DismissValue
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SwipeToDismiss
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDismissState
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
fun ItemListaPreview(taskModel: TaskModel, viewModel: MyTaskTableViewModel, tipoFormato: Int) {
    val coroutineScope = rememberCoroutineScope()
    var currentTime by remember { mutableLongStateOf(System.currentTimeMillis()) }

    var showError by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = System.currentTimeMillis()
            delay(1000) // Update every second
        }
    }
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable {
                val currentTimestampSeconds = currentTime / 1000
                Log.i("Hora", currentTimestampSeconds.toString())
                Log.i("Hora 2", taskModel.fecha.toString())
                Log.i("Hora 3", taskModel.proximaFecha.toString())
                if (currentTimestampSeconds >= taskModel.fecha && currentTimestampSeconds <= taskModel.proximaFecha) {
                    coroutineScope.launch {

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
                modifier = Modifier.padding(8.dp), dismissAction = { showError = false }) {
                Text(text = "¡Aún no ha llegado la hora!")
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


//@RequiresApi(Build.VERSION_CODES.O)
//@Composable
//fun ItemLista(
//    taskModel: TaskModel,
//    tipoFormato: Int,
//    myTaskTableViewModel: MyTaskTableViewModel
//) {
//    var currentTime by remember {
//        mutableLongStateOf(System.currentTimeMillis())
//    }
//
//    Box(
//        modifier = Modifier
//            .padding(10.dp)
//            .clip(RoundedCornerShape(15.dp))
//            .background(
//                if (currentTime / 1000 < taskModel.proximaFecha) Color(
//                    64,
//                    110,
//                    180,
//                    255
//                ) else Color.Blue
//            )
//            .fillMaxWidth(.9f)
//    ) {
//        Row(
//            Modifier
//                .padding(20.dp)
//                .fillMaxWidth()
//        ) {
//            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(10.dp)) {
//                Icon(imageVector = Icons.Default.CropSquare, contentDescription = "")
//            }
//            Box {
//                Column {
//                    val fechaComienzo = taskModel.fecha
//                    val siguienteFecha = taskModel.proximaFecha
//
//                    Text(text = taskModel.nombre, color = Color.White)
//                    val timestamp = fechaComienzo * 1000
//                    val date = LocalDateTime.ofInstant(
//                        Instant.ofEpochMilli(timestamp),
//                        ZoneId.systemDefault()
//                    )
//
//                    val timestamp2 = siguienteFecha * 1000
//                    val date2 = LocalDateTime.ofInstant(
//                        Instant.ofEpochMilli(timestamp2),
//                        ZoneId.systemDefault()
//                    )
//
//                    val formatter = if (tipoFormato == 0) {
//                        DateTimeFormatter.ofPattern("HH:mm")
//                    } else {
//                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
//                    }
//                    val formattedTime = date.format(formatter)
//                    val formattedTime2 = date2.format(formatter)
//
//                    Text(text = "Inicio: $formattedTime", color = Color(238, 229, 217, 255))
//                    Text(text = "Próxima: $formattedTime2", color = Color(238, 229, 217, 255))
//
//                    val active = remember { mutableStateOf(true) }
//
//                    if (siguienteFecha >= currentTime / 1000) {
//                        Text(
//                            text = "Tiempo restante para confirmar: ",
//                            color = Color(218, 134, 7, 255)
//                        )
//                        if (active.value) {
//                            LaunchedEffect(siguienteFecha) {
//                                while (true) {
//                                    delay(1000L)
//                                    val tiempoRestante =
//                                        siguienteFecha * 1000 - System.currentTimeMillis()
//                                    if (tiempoRestante <= 0) {
//                                        myTaskTableViewModel.setDoneTask(taskModel.id)
//                                        myTaskTableViewModel.addTask(
//                                            taskModel.copy(
//                                                id = myTaskTableViewModel.getLastID(),  // Asegúrate de incrementar el ID
//                                                nombre = taskModel.nombre,
//                                                color = taskModel.color,
//                                                descripcion = taskModel.descripcion,
//                                                fecha = taskModel.proximaFecha,
//                                                margen = taskModel.margen,
//                                                proximaFecha = taskModel.proximaFecha + taskModel.margen,
//                                                cumplida = 0,
//                                                categoriaId = taskModel.categoriaId
//                                            )
//                                        )
//                                        active.value = false
//                                        break
//                                    }
//                                }
//                            }
//                            TimeDisplay(targetTimeMilliseconds = siguienteFecha * 1000)
//                        }
//                    }
//                }
//            }
//        }
//    }
//}


@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Pagina(
    myTaskTableViewModel: MyTaskTableViewModel,
    tasksToday: List<TaskModel>,
    tasksTomorrow: List<TaskModel>,
    tasksComing: List<TaskModel>
) {
    val listState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(state = listState) {
            item {
                Text(text = "Atrasados", modifier = Modifier.padding(20.dp))
                // Aquí puedes manejar los elementos atrasados
            }

            item {
                Text(text = "Hoy", modifier = Modifier.padding(20.dp))
            }
            items(tasksToday, key = { it.id }) { task ->
                var isRemoved by remember(task.id) { mutableStateOf(false) }
                var isDone by remember(task.id) { mutableStateOf(false) }

                if (!isRemoved && !isDone) {
                    SwipeToDeleteOrCompleteItem(
                        item = task,
                        onDelete = {
                            isRemoved = true
                            coroutineScope.launch {
                                myTaskTableViewModel.deleteTask(task)
                            }
                        },
                        onDone = {
                            isDone = true
                            coroutineScope.launch {
                                myTaskTableViewModel.setDoneTask(task.id, task)
                            }
                        },
                        content = { item -> ItemListaPreview(item, myTaskTableViewModel, 0) }
                    )
                }
            }

            item {
                Text(text = "Mañana", modifier = Modifier.padding(20.dp))
            }
            items(tasksTomorrow, key = { it.id }) { task ->
                ItemListaPreview(task, myTaskTableViewModel, 0)
            }

            item {
                Text(text = "Próximos", modifier = Modifier.padding(20.dp))
            }
            items(tasksComing, key = { it.id }) { task ->
                ItemListaPreview(task, myTaskTableViewModel, 1)
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
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeleteBackground(swipeDismissState: DismissState) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
        Color.Red
    } else Color.Transparent
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompleteBackground(swipeDismissState: DismissState) {
    val color = if (swipeDismissState.dismissDirection == DismissDirection.StartToEnd) {
        Color.Green
    } else Color.Transparent
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color)
            .padding(16.dp).clip(RoundedCornerShape(5.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        if (swipeDismissState.dismissDirection == DismissDirection.StartToEnd) {
            Icon(imageVector = Icons.Default.DoneOutline, contentDescription = "Done", tint = Color.White)
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteOrCompleteItem(
    item: T,
    onDelete: (T) -> Unit,
    onDone: (T) -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {
    var isRemoved by remember { mutableStateOf(false) }
    var isDone by remember { mutableStateOf(false) }
    val state = rememberDismissState(
        confirmValueChange = { value ->
            when (value) {
                DismissValue.DismissedToStart -> {
                    isRemoved = true
                    true
                }
                DismissValue.DismissedToEnd -> {
                    isDone = true
                    true
                }
                else -> false
            }
        }
    )

    LaunchedEffect(isRemoved, isDone) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
        if (isDone) {
            delay(animationDuration.toLong())
            onDone(item)
        }
    }

    AnimatedVisibility(
        visible = !isRemoved && !isDone,
        exit = shrinkVertically(
            animationSpec = tween(animationDuration),
            shrinkTowards = Alignment.Top
        ) + fadeOut()
    ) {
        SwipeToDismiss(
            state = state,
            background = {
                if (state.dismissDirection == DismissDirection.EndToStart) {
                    DeleteBackground(swipeDismissState = state)
                } else if (state.dismissDirection == DismissDirection.StartToEnd) {
                    CompleteBackground(swipeDismissState = state)
                }
            },
            dismissContent = { content(item) },
            directions = setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd)
        )
    }
}
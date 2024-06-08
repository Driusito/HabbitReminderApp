package com.example.habbitreminderapp.Core.Features

import android.annotation.SuppressLint
import android.content.Context
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
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.LockOpen
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import com.example.habbitreminderapp.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.UUID
import java.util.concurrent.TimeUnit


@SuppressLint("CoroutineCreationDuringComposition")
@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun ItemLista(taskModel: TaskModel, viewModel: MyTaskTableViewModel, tipoFormato: Int) {
    val coroutineScope = rememberCoroutineScope()
    var currentTime by remember { mutableLongStateOf(System.currentTimeMillis()) }
    val context = LocalContext.current
    var comenzar by remember { mutableStateOf(false) }
    var showError by remember { mutableStateOf(false) }

    // Variable para almacenar los segundos actuales
    var currentTimestampSeconds by remember { mutableStateOf(System.currentTimeMillis() / 1000) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTimestampSeconds = System.currentTimeMillis() / 1000
            Log.i("Current Timestamp Seconds", currentTimestampSeconds.toString())
            delay(1000) // Update every second
        }
    }
    if (!comenzar && currentTimestampSeconds >= taskModel.fecha && currentTimestampSeconds <= taskModel.proximaFecha &&taskModel.notificada==0) {
        //viewModel.sendNotification(context = context, taskModel)
        val delayInMillis = ((taskModel.proximaFecha * 1000) - System.currentTimeMillis()).coerceAtLeast(0L)
        Log.i("id",taskModel.id.toString())
        //viewModel.scheduleNotification(context, taskModel,delayInMillis/1000 )
        //viewModel.setNotificated(taskModel.id)
        comenzar = true
    }
    val icon=if(currentTimestampSeconds >= taskModel.fecha&&currentTimestampSeconds<taskModel.proximaFecha){Icons.Default.LockOpen}else if(currentTimestampSeconds>=taskModel.proximaFecha){Icons.Default.Cancel}
    else Icons.Default.Lock



    Box(
        modifier = Modifier
            .padding(10.dp)
            .clickable {}
            .clip(RoundedCornerShape(12.dp))
            .background(Color(taskModel.color))
            .fillMaxWidth(.9f)
    ) {
        // Icono en la esquina superior derecha
        Box(
            modifier = Modifier
                .fillMaxSize(),
            contentAlignment = Alignment.TopEnd
        ) {
            Icon(
                imageVector = icon, // Puedes cambiar este ícono por el que prefieras
                contentDescription = "Icono en la esquina superior derecha",
                modifier = Modifier
                    .padding(8.dp)
                    .size(24.dp),
                tint = Color.White // Cambia el color del ícono si es necesario
            )
        }

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
                Text(
                    text = taskModel.nombre,
                    color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily(Font(R.font.robotoblack))
                )
            }
            Spacer(modifier = Modifier.weight(.5f))

            Text(text = taskModel.categoriaId, fontSize = 26.sp, modifier = Modifier.weight(1f))
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
                Text(text = formattedTime, color = Color.White,
                    overflow = TextOverflow.Ellipsis,
                    fontFamily = FontFamily(Font(R.font.lato_bold)))
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
    val coroutineScope = rememberCoroutineScope()
    val context= LocalContext.current
    Log.d("Pagina", "tasksToday: ${tasksToday.size}, tasksTomorrow: ${tasksTomorrow.size}, tasksComing: ${tasksComing.size}")
    var numero by remember {
        mutableStateOf(0)
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(state = listState) {
            item {
                Text(
                    color = Color.Black,
                    text = "Atrasados",
                    modifier = Modifier.padding(20.dp),
                    fontFamily = FontFamily(
                        Font(R.font.lato_bold)
                    )
                )
                // Aquí puedes manejar los elementos atrasados
            }

            item {
                Text(
                    color = Color.Black,
                    text = "Hoy",
                    modifier = Modifier.padding(20.dp),
                    fontFamily = FontFamily(
                        Font(R.font.lato_bold)
                    )
                )
            }
            items(tasksToday, key = { it.id }) { task ->
                var isRemoved by remember(task.id) { mutableStateOf(false) }
                var isDone by remember(task.id) { mutableStateOf(false) }
                var isPassed by remember(task.id) { mutableStateOf(false) }

                if (!isRemoved && !isDone && !isPassed) {
                    SwipeToDeleteOrCompleteItem(
                        item = task,
                        onDelete = {
                            isRemoved = true
                            coroutineScope.launch {
                                myTaskTableViewModel.cancelNotification(context = context, taskModelId = task.id)
                                myTaskTableViewModel.deleteTask(task)
                            }
                        },
                        onDone = {
                            isDone = true
                            coroutineScope.launch {
                                Log.i("Info","El id de la task es ${task.id}")
                                myTaskTableViewModel.setDoneTask(task.id, task)
                            }
                        }, timeToDone = task.fecha,
                        timeToPass = task.proximaFecha,
                        onPassed = {
                            isPassed = true
                            coroutineScope.launch {
                                Log.i("Cronologia", "Era ${it.toString()}")
                                myTaskTableViewModel.setOverDueTasks(task.id, taskModel = task )
                                numero++
                                Log.i("Cronologia", "Soy ${it.toString()}")

                            }
                        }, myTaskTableViewModel = myTaskTableViewModel,
                        content = { ItemLista(task, myTaskTableViewModel, 0) }
                    )
                }
            }

            item {
                Text(
                    color = Color.Black,
                    text = "Mañana",
                    modifier = Modifier.padding(20.dp),
                    fontFamily = FontFamily(
                        Font(R.font.lato_bold)
                    )
                )
            }
            items(tasksTomorrow, key = { it.id }) { task ->
                ItemLista(task, myTaskTableViewModel, 0)
            }

            item {
                Text(
                    color = Color.Black,
                    text = "Próximos",
                    modifier = Modifier.padding(20.dp),
                    fontFamily = FontFamily(
                        Font(R.font.lato_bold)
                    )
                )
            }
            items(tasksComing, key = { it.id }) { task ->
                ItemLista(task, myTaskTableViewModel, 1)
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
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = Color.White
            )
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
            .clip(RoundedCornerShape(12.dp))
            .background(color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        if (swipeDismissState.dismissDirection == DismissDirection.StartToEnd) {
            Icon(
                imageVector = Icons.Default.DoneOutline,
                contentDescription = "Done",
                tint = Color.White
            )
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun <T> SwipeToDeleteOrCompleteItem(
    item: T,
    onDelete: (T) -> Unit,
    onDone: (T) -> Unit,
    onPassed: (T) -> Unit,
    timeToPass: Long,
    timeToDone: Long, // Parámtero que contiene la fecha límite para permitir el swipe a la derecha
    animationDuration: Int = 500,
    myTaskTableViewModel: MyTaskTableViewModel,
    content: @Composable (T) -> Unit
) {
    var currentTimestampSeconds by remember { mutableStateOf(System.currentTimeMillis() / 1000) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTimestampSeconds = System.currentTimeMillis() / 1000
            Log.i("Current Timestamp Seconds", currentTimestampSeconds.toString())
            delay(1000) // Update every second
        }
    }
    var isRemoved by remember { mutableStateOf(false) }
    var isDone by remember { mutableStateOf(false) }
    var isPassed by remember {
        mutableStateOf(false)
    }
    var isScheduled by remember {
        mutableStateOf(false)
    }

//    if (currentTimestampSeconds >= timeToPass && !isPassed) {
//        onPassed(item)
//        isPassed = true
//    }

    val dismissDirections = if (currentTimestampSeconds >= timeToDone) {
        setOf(DismissDirection.EndToStart, DismissDirection.StartToEnd)
    } else {
        setOf(DismissDirection.EndToStart) // Only allow swipe to delete
    }
    val context = LocalContext.current

    val currentTimestampMillis = System.currentTimeMillis()

    if (item is TaskModel) {
        if (!isScheduled && item.notificada == 0 && currentTimestampMillis <= item.fecha * 1000) {
            val delayInMillis = ((item.fecha * 1000) - currentTimestampMillis).coerceAtLeast(0L)
            val workRequestId = myTaskTableViewModel.scheduleNotification(context, item, delayInMillis)
            myTaskTableViewModel.setNotificated(item.id)
            isScheduled = true

            saveWorkRequestId(item.id, workRequestId, context = context)
        }
    }


    val state = rememberDismissState(
        confirmValueChange = { value ->
            when (value) {
                DismissValue.DismissedToStart -> {
                    isRemoved = true
                    true
                }

                DismissValue.DismissedToEnd -> {
                    if (currentTimestampSeconds in timeToDone..<timeToPass) {
                        isDone = true
                        true
                    } else if (currentTimestampSeconds >= timeToPass) {
                        isPassed = true
                        true
                    } else {
                        false
                    }
                }

                else -> false
            }
        }
    )

    LaunchedEffect(isRemoved, isDone,isPassed) {
        if (isRemoved) {
            delay(animationDuration.toLong())
            onDelete(item)
        }
        if (isDone) {
            delay(animationDuration.toLong())
            onDone(item)
        }
        if (isPassed) {
            delay(animationDuration.toLong())
            onPassed(item)
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
            directions = dismissDirections
        )
    }
}

fun saveWorkRequestId(taskModelId: Int, workRequestId: UUID,context:Context) {
    val sharedPreferences = context.getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE)
    with(sharedPreferences.edit()) {
        putString("WorkRequestId_$taskModelId", workRequestId.toString())
        apply()
    }
}

fun getWorkRequestId(taskModelId: Int,context: Context): UUID? {
    val sharedPreferences = context.getSharedPreferences("NotificationPrefs", Context.MODE_PRIVATE)
    val workRequestIdString = sharedPreferences.getString("WorkRequestId_$taskModelId", null)
    return if (workRequestIdString != null) UUID.fromString(workRequestIdString) else null
}
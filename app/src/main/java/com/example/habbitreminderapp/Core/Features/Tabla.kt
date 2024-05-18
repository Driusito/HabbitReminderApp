package com.example.habbitreminderapp.Core.Features

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Face
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import kotlinx.coroutines.delay
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun TableScreen() {
    // Just a fake data... a Pair of Int and String
    val tableData = (1..100).mapIndexed { index, item ->
        index to "Item $index"
    }
    // Each cell of a column must have the same weight.
    val column1Weight = .3f // 30%
    val column2Weight = .7f // 70%
    // The LazyColumn will be our table. Notice the use of the weights below
    LazyColumn(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // Here is the header
        item {
            Header()
        }
        // Here are all the lines of your table.
        items(1) {
            Fila(
                nombre = "Pasear perro",
                categoria = "Hogar",
                fecha = "20/03/2024",
                intervalo = "5 minutos",
                completado = false
            )

        }
    }
}


@Composable
fun Header() {
    Row(Modifier.background(Color.Gray)) {
        Text(
            text = "Nombre", textAlign = TextAlign.Center, modifier = Modifier
                .border(
                    1.dp,
                    Color.Black
                )
                .weight(2.5f)
        )
        Text(
            text = "Categoria", textAlign = TextAlign.Center, modifier = Modifier
                .weight(2.5f)
                .border(
                    1.dp,
                    Color.Black
                )
        )
        Text(
            maxLines = 1, text = "Fecha inicio", textAlign = TextAlign.Center, modifier = Modifier
                .weight(2.5f)
                .border(
                    1.dp,
                    Color.Black
                )
        )
        Text(
            text = "Intervalo", textAlign = TextAlign.Center, modifier = Modifier
                .weight(2.5f)
                .border(
                    1.dp,
                    Color.Black
                )
        )
        Text(
            maxLines = 1, text = "Completado", textAlign = TextAlign.Center, modifier = Modifier
                .weight(3f)
                .border(
                    1.dp,
                    Color.Black
                )
        )

    }
}

@Composable
fun Fila(nombre: String, categoria: String, fecha: String, intervalo: String, completado: Boolean) {
    Row(Modifier.fillMaxWidth()) {
        Text(
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            text = nombre, textAlign = TextAlign.Center, modifier = Modifier
                .border(
                    1.dp,
                    Color.Black
                )
                .weight(2.5f)
        )
        Text(
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            text = categoria, textAlign = TextAlign.Center, modifier = Modifier
                .border(
                    1.dp,
                    Color.Black
                )
                .weight(2.5f)
        )
        Text(
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            text = fecha, textAlign = TextAlign.Center, modifier = Modifier
                .border(
                    1.dp,
                    Color.Black
                )
                .weight(2.5f)
        )
        Text(
            maxLines = 1, overflow = TextOverflow.Ellipsis,
            text = intervalo, textAlign = TextAlign.Center, modifier = Modifier
                .border(
                    1.dp,
                    Color.Black
                )
                .weight(2.5f)
        )
        if (completado)
            Icon(
                imageVector = Icons.Default.CheckCircle,
                contentDescription = "",
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Black
                    )
                    .weight(3f)
                    .size(19.dp)
            )
        else
            Icon(
                imageVector = Icons.Default.CheckCircleOutline,
                contentDescription = "",
                modifier = Modifier
                    .border(
                        1.dp,
                        Color.Black
                    )
                    .weight(3f)
                    .size(19.dp)
            )


    }

}

@Composable
fun RowScope.TableCell(
    text: String,
    weight: Float
) {
    Text(
        text = text,
        Modifier
            .border(1.dp, Color.Black)
            .weight(weight)
            .padding(8.dp)
    )
}

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
fun ItemLista(taskModel: TaskModel,tipoFormato:Int) {
    Box(
        modifier = Modifier
            .padding(10.dp)
            .clip(RoundedCornerShape(15.dp))
            .background(Color(64, 110, 180, 255))
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
                    val timestamp = taskModel.fecha * 1000 // Multiplica por 1000 para convertir segundos a milisegundos
                    val date = LocalDateTime.ofInstant(
                        Instant.ofEpochMilli(timestamp),
                        ZoneId.systemDefault()
                    )

                    val formatter = if (tipoFormato == 0) {
                        DateTimeFormatter.ofPattern("HH:mm")
                    } else {
                        DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")
                    }
                    val formattedTime = formatter.format(date)



                    Text(text = "Inicio: $formattedTime", color = Color(238, 229, 217, 255))
                    Text(
                        text = "Siguiente fecha en: " + TimeDisplay(
                            targetMilliseconds = taskModel.margen * 1000,
                            startTime = fechaComienzo,
                            endTime = siguienteFecha
                        ),
                        color = Color(218, 134, 7, 255)
                    )
                }
            }


        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Pagina(myTaskTableViewModel: MyTaskTableViewModel, tasks: List<TaskModel>,tasksTomorrow: List<TaskModel>,tasksComing: List<TaskModel>) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        LazyColumn(content = {
            itemsIndexed(listOf("Atrasados", "Hoy", "Mañana", "Proximos")) { index, categoria ->
                Text(text = categoria, modifier = Modifier.padding(20.dp))
                when (categoria) {
                    "Atrasados" -> {

                    }

                    "Hoy" -> {
                        tasks.forEach { task ->
                            ItemLista(taskModel = task,0)
                        }


                    }

                    "Mañana" -> {
                        tasksTomorrow.forEach{task ->
                            ItemLista(taskModel = task,0)
                        }

                    }

                    "Proximos" -> {
                        tasksComing.forEach{task ->
                            ItemLista(taskModel = task,1)
                        }
                    }
                }
            }
        })
    }


}


@Composable
fun TimeDisplay(targetMilliseconds: Long, startTime: Long, endTime: Long): String {
    // Estado para almacenar el tiempo restante
    var remainingTime by remember { mutableStateOf(endTime - System.currentTimeMillis()) }

    // LaunchedEffect para actualizar el tiempo cada segundo
    LaunchedEffect(targetMilliseconds, startTime, endTime) {
        while (remainingTime > 0) {
            remainingTime = (endTime - System.currentTimeMillis()).coerceAtLeast(0L)
            delay(1000L)  // Actualizar cada segundo
        }
    }

    // Formatear el tiempo restante
    val formattedTime = formatTime(remainingTime)

    return formattedTime
}

fun formatTime(milliseconds: Long): String {
    val totalSeconds = milliseconds / 1000
    val seconds = totalSeconds % 60
    val totalMinutes = totalSeconds / 60
    val minutes = totalMinutes % 60
    val totalHours = totalMinutes / 60
    val hours = totalHours % 24
    val days = totalHours / 24

    return "${days} días, ${hours} horas, ${minutes} minutos y ${seconds} segundos"
}
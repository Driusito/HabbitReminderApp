package com.example.habbitreminderapp.Features

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.CropSquare
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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
                .padding(20.dp)
                .fillMaxWidth()
        ) {
            Box(contentAlignment = Alignment.Center, modifier = Modifier.padding(10.dp)) {
                Icon(imageVector = Icons.Default.CropSquare, contentDescription = "")
            }
            Box() {
                Column() {
                    Text(text = "Nombre Tarea", color = Color.White)
                    Text(text = "Hora de la tarea", color = Color(221, 89, 49, 255))
                }
            }

        }
    }
}


@Composable
fun ItemLista(nombre: String) {
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
                    Text(text = nombre, color = Color.White)
                    Text(text = "Hora de la tarea", color = Color(218, 134, 7, 255))
                }
            }

        }
    }
}

@Preview
@Composable
fun Pagina() {
    var stateScrollState = rememberScrollState()
    LazyColumn(content = {
        itemsIndexed(listOf("Atrasados", "Hoy", "Mañana", "Esta semana")) { index, categoria ->
            Text(text = categoria, modifier = Modifier.padding(20.dp))
            ItemLista(nombre = "Tomar la pastilla")
            ItemLista(nombre = "Sacar al perro")
        }
    })
}
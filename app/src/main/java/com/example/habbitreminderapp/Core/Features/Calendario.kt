package com.example.habbitreminderapp.Core.Features

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun CustomCalendar() {
    var mes by remember { mutableStateOf(0) }
    var fechaSeleccionada by remember { mutableStateOf<Date?>(null) }


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
                .padding(4.dp), verticalAlignment = Alignment.CenterVertically) {
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
                            fechaSeleccionada = fecha
                        }
                    }
                }
            }
        }
        Column(Modifier.fillMaxSize(), verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.Start) {

                Box(modifier = Modifier.background(Color.Red).fillMaxWidth().size(50.dp))

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
    fechaSeleccionada: Date?,
    onDateSelected: (Date) -> Unit
) {
    val selectedDate = Calendar.getInstance().apply {
        set(Calendar.MONTH, month)
        set(Calendar.DAY_OF_MONTH, day)
    }.time

    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clip(CutCornerShape(10.dp))
            .padding(4.dp)
            .background(
                if (selectedDate == fechaSeleccionada) Color(63, 81, 181, 255) else Color(
                    33,
                    150,
                    243,
                    255
                )
            )
            .size(40.dp)
            .clickable {
                onDateSelected(selectedDate)
                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                val dateString = dateFormat.format(selectedDate)
                Toast
                    .makeText(
                        context,
                        "Fecha seleccionada: $dateString",
                        Toast.LENGTH_SHORT
                    )
                    .show()

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

fun showDateToast(date: Date) {

}

package com.example.habbitreminderapp.Features

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.*

@Composable
fun CustomCalendar(numeroDelMes: Int) {
    var mes by remember{ mutableStateOf(numeroDelMes) }
    val calendar = Calendar.getInstance().apply {
        set(Calendar.MONTH, mes)
        firstDayOfWeek = Calendar.MONDAY// Establecer el primer día de la semana en lunes
    }
    val daysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
    val firstDayOfMonth = calendar.apply { set(Calendar.DAY_OF_MONTH, 1) }
    val firstDayOfWeek = firstDayOfMonth.get(Calendar.DAY_OF_WEEK)
    val meses = listOf(
        "Enero",
        "Febrero",
        "Marzo",
        "Abril",
        "Mayo",
        "Junio",
        "Julio",
        "Agosto",
        "Septiembre",
        "Octubre",
        "Noviembre",
        "Diciembre"
    )
    val daysOfWeek = listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        //Cabecera(mes)
        Text(
            text = meses[firstDayOfMonth.get(Calendar.MONTH)],
            style = MaterialTheme.typography.displayMedium,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            color = Color.Black
        )

        //Dias de la semana
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            daysOfWeek.forEach { day ->
                Text(
                    text = day,
                    fontSize = 20.sp,
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 4.dp),
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
                for (colIndex in 0 until 7) {
                    val day = rowIndex * 7 + colIndex - startingDay + 1
                    //Si no empieza el mes, un "hueco", si no el day
                    if ((colIndex < firstDayOfWeek - 2 && day < firstDayOfWeek - 2) && rowIndex == 0)
                        EmptySpace(day = day) else
                        if (day in 1..daysInMonth) {
                            DayItem(day)
                        }
                }
            }
        }
        //Hay que tener en cuenta que la cuenta se empieza desde el domingo
        //Y tambien empieza por 1, por lo que para que señale al lunes, deberia ser el 2

        Box(modifier = Modifier.size(50.dp).clickable { mes++ }.background(Color.Red))
    }
}


@Composable
fun DayItem(day: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color.LightGray)
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = day.toString(),
            color = Color.Black,
            style = MaterialTheme.typography.headlineLarge

        )
    }
}

@Composable
fun EmptySpace(day: Int) {
    Box(
        modifier = Modifier
            .padding(4.dp)
            .background(Color(100, 100, 100))
            .size(40.dp),
        contentAlignment = Alignment.Center
    ) {

    }
}

@Preview
@Composable
fun PreviewCalendarView() {
}


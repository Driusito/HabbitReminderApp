package com.example.habbitreminderapp.Core.Features

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.DoneOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.R
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ItemCalendario(taskModel: TaskModel) {
    val backgroundColor = taskModel.color?.let { Color(it) } ?: Color.White
    val icon= if (taskModel.cumplida==0){
       Icons.Default.AccessTime
    }
    else if (taskModel.cumplida==1){
       Icons.Default.DoneOutline
    }
    else{
        Icons.Default.Close

    }

    // Formatea la fecha en horas y minutos
    val dateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
    val formattedDate =
        dateFormat.format(Date(taskModel.fecha * 1000L)) // Asumiendo que taskModel.fecha está en segundos

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .background(backgroundColor), horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = taskModel.nombre, fontFamily = FontFamily(Font(R.font.lato_regular)),
            modifier = Modifier
                .weight(3f)
                .padding(10.dp)
        )
        Text(
            text = taskModel.categoriaId, fontSize = 26.sp,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        )
        Text(
            text = formattedDate, fontFamily = FontFamily(Font(R.font.poetsenone_regular)),
            modifier = Modifier
                .weight(2f)
                .padding(10.dp)
        )

        Icon(
            imageVector = icon,
            tint = Color(105, 221, 110, 255),
            contentDescription = "",
            modifier = Modifier
                .padding(10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(Color.White)
                .weight(1f)
        )
    }
}


package com.example.habbitreminderapp.NewTaskView.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccessTime
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Category
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LeadingIconTab
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habbitreminderapp.Core.Features.BotonConfirmar
import com.example.habbitreminderapp.Core.Features.MyCalendar


@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun NewTaskScreen2(
    //navController: NavController, newTaskScreenViewModel: NewTaskScreenViewModel
) {

    Column(Modifier.fillMaxSize()) {
        TopAppBar(colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(63, 81, 181, 255),
navigationIconContentColor = Color.White, titleContentColor = Color.White
        ),
            title = { Text(text = "Nueva Tarea")},
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            })

        Column(modifier = Modifier.fillMaxWidth()){
            Box(){
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "", modifier = Modifier
                        .size(40.dp)
                        .align(Alignment.TopStart)
                )
            }
            Row (Modifier.fillMaxWidth()){

            }
        }
Row (
    Modifier
        .fillMaxWidth()
        .background(Color(63, 81, 181, 255))){
    OutlinedTextField(placeholder = { Text(text = "Título")}, value = "", onValueChange = {},modifier= Modifier
        .fillMaxWidth()
        )
}

        OutlinedTextField(placeholder = { Text(text = "Descripción")}, value = "", onValueChange = {},modifier= Modifier
            .fillMaxWidth()
            .background(Color(63, 81, 181, 255)))
IconTextEndTextRow(icon = Icons.Default.CalendarMonth, text = "Fecha de inicio", endText ="Fecha" )
IconTextEndTextRow(icon = Icons.Default.AccessTime, text = "Hora", endText ="Hora" )
IconTextEndTextRow(icon = Icons.Default.CalendarToday, text = "Fecha de finalizacion", endText ="Fecha fin" )
IconTextEndTextRow(icon = Icons.Default.Category, text = "Categoria", endText ="Categoria" )
IconTextEndTextRow(icon = Icons.Default.Repeat, text = "Repetir cada:", endText ="Repetido" )


    }


}

@Composable
fun MyDropDownMenu2(): String {
    var selectedText by remember { mutableStateOf("") }
    var clicked by remember { mutableStateOf(false) }
    val lista = listOf("Ejercicio", "Ocio", "Salud")

    OutlinedTextField(value = selectedText,
        onValueChange = { selectedText = it },
        enabled = false,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = ""
            )
        },
        modifier = Modifier
            .clickable { clicked = true }
    )
    DropdownMenu(
        expanded = clicked, onDismissRequest = { clicked = false },
    ) {
        lista.forEach { texto ->
            DropdownMenuItem(trailingIcon = {
                Icon(
                    imageVector = Icons.Default.Face,
                    contentDescription = " "
                )
            }, text = { Text(text = texto) }, onClick = {
                clicked = false
                selectedText = texto
            })
        }
    }
    return selectedText
}

@Composable
fun IconTextEndTextRow(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    contentDescription: String? = null,
    text: String,
    endText: String
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 10.dp)
            .clickable { },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Icon(
            imageVector = icon,
            contentDescription = contentDescription,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = text,
            modifier = Modifier
                .weight(1f)
                .padding(10.dp)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text = endText)
    }
    Divider()
}
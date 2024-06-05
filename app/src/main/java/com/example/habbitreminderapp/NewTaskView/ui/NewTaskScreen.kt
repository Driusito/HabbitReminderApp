package com.example.habbitreminderapp.NewTaskView.ui

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Colorize
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.habbitreminderapp.Core.Features.BotonConfirmar
import com.example.habbitreminderapp.Core.Features.ChooseColorDialog
import com.example.habbitreminderapp.Core.Features.EmojiSelector
import com.example.habbitreminderapp.Core.Features.MyCalendar
import com.example.habbitreminderapp.R


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun NewTaskScreen(navController: NavController, newTaskScreenViewModel: NewTaskScreenViewModel) {


    val nombreTarea: String by newTaskScreenViewModel.nameTask.observeAsState(initial = "")
    val descripcionTarea: String by newTaskScreenViewModel.descriptionTask.observeAsState(initial = "")
    val colorTarea: Long by newTaskScreenViewModel.colorTask.observeAsState(initial = 0L)
    val fechaTarea: String by newTaskScreenViewModel.fechaUi.observeAsState(initial = "")
    val margenTarea: Long by newTaskScreenViewModel.marginTask.observeAsState(initial = 0L)
    val cumplidaTarea: Int by newTaskScreenViewModel.doneTask.observeAsState(initial = 0)

    var minutos by remember {
        mutableStateOf("")
    }
    var horas by remember {
        mutableStateOf("")
    }
    var dias by remember {
        mutableStateOf("")
    }
    var showColorSelector by remember {
        mutableStateOf(false)
    }

    var showCategory by remember {
        mutableStateOf(false)
    }
    var categorySelected by remember {
        mutableStateOf("😀")
    }

    val abrirCalendario: Boolean by newTaskScreenViewModel.openCalendar.observeAsState(initial = false)
    var fecha by remember { mutableStateOf("") }

    val customFont = FontFamily(Font(R.font.lato_regular))
    val primaryColor = Color(0xFF6200EA)
    val secondaryColor = Color(0xFF03DAC5)

    Column(
        Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Spacer(modifier = Modifier.weight(0.25f))

        // Nombre de la tarea
        OutlinedTextField(
            value = nombreTarea,
            onValueChange = { newTaskScreenViewModel.onNameChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            label = { Text(text = "Nombre de la tarea", color = primaryColor) },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryColor
            )
        )

        Spacer(modifier = Modifier.weight(0.25f))

        // Categoría y color
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Selección de categoría",
                modifier = Modifier.weight(4f),
                fontSize = 16.sp,
                color = primaryColor,
                fontFamily = customFont
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clickable { showCategory = true },
                contentAlignment = Alignment.Center
            ) {
                OutlinedTextField(
                    value = categorySelected,
                    onValueChange = { categorySelected = it },
                    enabled = false,
                    readOnly = true,
                    colors = OutlinedTextFieldDefaults.colors(
                        disabledContainerColor = Color.White
                    ),
                    textStyle = LocalTextStyle.current.copy(textAlign = TextAlign.Center),
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Elige color",
                color = primaryColor,
                fontWeight = FontWeight.Bold,
                fontFamily = customFont,
                fontSize = 20.sp,
                modifier = Modifier.weight(1f)
            )
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clickable { showColorSelector = true }
            ) {
                Icon(
                    imageVector = Icons.Default.Colorize,
                    contentDescription = "",
                    modifier = Modifier.fillMaxSize(),
                    tint = if (colorTarea != 0L) Color(colorTarea) else primaryColor
                )
            }
        }

        if (showCategory) {
            EmojiSelector(
                mostrar = showCategory,
                onEmojiSelected = { emoji ->
                    newTaskScreenViewModel.selectCategory(emoji)
                    categorySelected = emoji
                },
                onDismiss = { showCategory = false }
            )
        }

        if (showColorSelector) {
            ChooseColorDialog(
                mostrar = showColorSelector,
                onColorSelected = { selectedColor ->
                    if (selectedColor != null) {
                        newTaskScreenViewModel.selectColor(selectedColor)
                    }
                },
                onDismiss = { showColorSelector = false }
            )
        }

        Spacer(modifier = Modifier.weight(0.25f))

        // Descripción
        Text(
            text = "Descripción",
            color = primaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = customFont,
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedTextField(
            value = descripcionTarea,
            onValueChange = { newTaskScreenViewModel.onDescriptionChanged(it) },
            modifier = Modifier.fillMaxWidth(),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Fecha de inicio
        Row(
            Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Fecha de inicio",
                color = primaryColor,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp,
                fontFamily = customFont,
                modifier = Modifier.weight(2f)
            )
            Spacer(modifier = Modifier.weight(0.05f))
            Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
            Spacer(modifier = Modifier.weight(0.45f))
            Spacer(modifier = Modifier.weight(0.5f))
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Calendario
        Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
            IconButton(
                onClick = { newTaskScreenViewModel.showCalendar(true) },
                Modifier.size(100.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.CalendarMonth,
                    contentDescription = "",
                    Modifier.fillMaxSize()
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        MyCalendar(abrirCalendario, newTaskScreenViewModel)
        Text(text = "La fecha seleccionada es $fechaTarea")

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Siguiente fecha cada:",
            color = primaryColor,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp,
            fontFamily = customFont
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Siguiente fecha cada
        OutlinedTextField(
            value = minutos,
            onValueChange = {
                minutos = it
                if (it.isNotEmpty()) newTaskScreenViewModel.minToLong(it)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Minutos") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryColor
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = horas,
            onValueChange = {
                horas = it
                if (it.isNotEmpty()) newTaskScreenViewModel.hourToLong(it)
                Log.i("Rango horas", it)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Horas") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryColor
            )
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = dias,
            onValueChange = {
                dias = it
                if (it.isNotEmpty()) newTaskScreenViewModel.dayToLong(it)
                Log.i("Rango días", it)
            },
            modifier = Modifier.fillMaxWidth(),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            label = { Text("Días") },
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = primaryColor,
                unfocusedBorderColor = primaryColor
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botón de confirmar


        Spacer(modifier = Modifier.height(16.dp))
    }
    val context= LocalContext.current
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomEnd) {
        FloatingActionButton(
            onClick = {
                if (newTaskScreenViewModel.camposVacios()) {
                    // Mostrar un mensaje de error o tomar alguna acción
                    // Por ejemplo, mostrar un Toast
                    Toast.makeText(context, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show()
                }
                else if (newTaskScreenViewModel.fechaIncorrecta()){
                    Toast.makeText(context, "La fecha no puede ser anterior a la actual", Toast.LENGTH_SHORT).show()

                }
                    else {
                    newTaskScreenViewModel.addTask(minutos, horas, dias)
                    navController.popBackStack()
                }
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(15.dp)
        ) {
            Icon(imageVector = Icons.Default.Done, contentDescription = "")
        }
    }
}
@Composable
fun MyDropDownMenu(): String {
    var selectedText by remember { mutableStateOf("") }
    var clicked by remember { mutableStateOf(false) }
    val lista = listOf("Ejercicio", "Ocio", "Salud")

    OutlinedTextField(
        value = selectedText,
        onValueChange = { selectedText = it },
        enabled = false,
        readOnly = true,
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = ""
            )
        },
        modifier = Modifier.clickable { clicked = true }
    )
    DropdownMenu(
        expanded = clicked, onDismissRequest = { clicked = false },
    ) {
        lista.forEach { texto ->
            DropdownMenuItem(
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Face,
                        contentDescription = " "
                    )
                },
                text = { Text(text = texto) },
                onClick = {
                    clicked = false
                    selectedText = texto
                }
            )
        }
    }
    return selectedText
}
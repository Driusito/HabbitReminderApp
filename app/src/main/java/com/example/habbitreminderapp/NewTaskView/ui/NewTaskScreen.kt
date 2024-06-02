package com.example.habbitreminderapp.NewTaskView.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
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
        mutableStateOf("")
    }



    val abrirCalendario: Boolean by newTaskScreenViewModel.openCalendar.observeAsState(initial = false)

    var fecha by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize()) {
        TopAppBar(title = {
            Text(
                text = "Nueva Meta",
                color = Color.White,
                fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.lato_regular)),
            )
        }, navigationIcon = {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "",
                    tint = Color.White
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Blue
        )
        )
        Column(
            Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(20.dp)

        ) {
            // Spacer(modifier = Modifier.weight(.1f))
            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Nombre",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.weight(
                        2f
                    )
                )
                Spacer(modifier = Modifier.weight(.25f))
                Text(
                    text = "Categoria",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.weight(
                        2f
                    )
                )
            }
            Spacer(modifier = Modifier.weight(.25f))

            Row(Modifier.fillMaxWidth()) {
                OutlinedTextField(
                    value = nombreTarea,
                    onValueChange = { newTaskScreenViewModel.onNameChanged(it) },
                    modifier = Modifier.weight(
                        2f
                    )
                )
                Spacer(modifier = Modifier.weight(.25f))

                Box(modifier = Modifier
                    .weight(2f)
                    .align(Alignment.CenterVertically)) {
                    OutlinedTextField( value = categorySelected,
                        onValueChange = { categorySelected = it },
                        enabled = false,
                        readOnly = true,
                        trailingIcon = {
                            Icon(
                                imageVector = Icons.Default.KeyboardArrowDown,
                                contentDescription = ""
                            )
                        }, colors = OutlinedTextFieldDefaults.colors(
                            disabledContainerColor = Color.White
                        ),
                        modifier = Modifier
                            .clickable { showCategory = true })
                }
                if (showCategory) {
                    EmojiSelector(
                        mostrar = showCategory,
                        onEmojiSelected = { emoji ->
                            newTaskScreenViewModel.selectCategory(emoji)
                            categorySelected=emoji
                        },
                        onDismiss = { showCategory = false }
                    )
                }


            }
            Spacer(modifier = Modifier.weight(.25f))

            Row(Modifier.fillMaxWidth()) {
                Text(
                    text = "Descripcion",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.weight(
                        2f
                    )
                )
            }
            Spacer(modifier = Modifier.weight(.25f))
            OutlinedTextField(
                value = descripcionTarea,
                onValueChange = { newTaskScreenViewModel.onDescriptionChanged(it) },
                modifier = Modifier.fillMaxWidth()

            )
            Spacer(modifier = Modifier.weight(.25f))

            Row(Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Fecha de inicio",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    modifier = Modifier.weight(
                        2f
                    )
                )
                Spacer(modifier = Modifier.weight(.05f))
                Icon(imageVector = Icons.Default.DateRange, contentDescription = "")
                Spacer(modifier = Modifier.weight(.45f))

                Text(
                    text = "Elige color",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontSize = 20.sp,
                    modifier = Modifier.weight(
                        1f
                    )
                )
                Box(
                    modifier = Modifier
                        .size(40.dp) // Ajusta el tamaño del Box para hacerlo más grande
                        .clickable { showColorSelector = true }
                ) {
                    Icon(
                        imageVector = Icons.Default.Colorize,
                        contentDescription = "",
                        modifier = Modifier.fillMaxSize(), // Ajusta el tamaño del Icon para que ocupe todo el Box
                        tint = if (colorTarea != 0L) Color(colorTarea) else Color.Black
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

                Spacer(modifier = Modifier.weight(.5f))

            }
            Spacer(modifier = Modifier.weight(.25f))
            Box(Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                IconButton(onClick = {newTaskScreenViewModel.showCalendar(true) },Modifier.size(100.dp)) {
                    Icon(imageVector = Icons.Default.CalendarMonth, contentDescription ="",Modifier.fillMaxSize() )
                }


            }
            Spacer(modifier = Modifier.weight(.25f))

            Text(
                text = "Fecha seleccionada: \n$fechaTarea ",
                fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.poetsenone_regular)),
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.weight(.25f))


            MyCalendar(abrirCalendario, newTaskScreenViewModel)




            Text(
                text = "Siguiente fecha cada:",
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp, fontFamily = FontFamily(Font(R.font.lato_regular))

                )
            Spacer(modifier = Modifier.weight(.25f))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = minutos,
                    onValueChange = {
                        minutos = it
                        if (it.isNotEmpty())
                            newTaskScreenViewModel.minToLong(it)


                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(.5f))
                Text(
                    text = "Minutos", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontSize = 20.sp, modifier = Modifier.weight(1.5f)
                )
                Spacer(modifier = Modifier.weight(2.5f))

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = horas,
                    onValueChange = {
                        horas = it
                        if (it.isNotEmpty())
                            newTaskScreenViewModel.hourToLong(it)
                        Log.i("Rango horas", it)
                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(.5f))
                Text(
                    text = "Horas", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontSize = 20.sp, modifier = Modifier.weight(1.5f)
                )
                Spacer(modifier = Modifier.weight(2.5f))

            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(vertical = 10.dp)
            ) {
                OutlinedTextField(
                    value = dias,
                    onValueChange = {
                        dias = it
                        if (it.isNotEmpty())
                            newTaskScreenViewModel.dayToLong(it)
                        Log.i("Rango dias", it)

                    },
                    modifier = Modifier.weight(1f),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
                )
                Spacer(modifier = Modifier.weight(.5f))
                Text(
                    text = "Días", color = Color.Black,
                    fontWeight = FontWeight.Bold, fontFamily = FontFamily(Font(R.font.lato_regular)),
                    fontSize = 20.sp, modifier = Modifier.weight(1.5f)
                )
                Spacer(modifier = Modifier.weight(2.5f))

            }
            Spacer(modifier = Modifier.weight(.25f))

            Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center) {
                FloatingActionButton(onClick = { newTaskScreenViewModel.addTask(minutos,horas, dias)
                                               navController.popBackStack()}, modifier = Modifier.align(
                    Alignment.BottomEnd)) {
                    Icon(imageVector = Icons.Default.Done, contentDescription = "")

                }

            }
            Spacer(modifier = Modifier.weight(.25f))


        }
    }


}

@Composable
fun MyDropDownMenu(): String {
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
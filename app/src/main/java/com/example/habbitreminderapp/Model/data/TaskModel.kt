package com.example.habbitreminderapp.Model.data

import androidx.compose.ui.graphics.Color
import java.util.Date


data class TaskModel(
    val id: Int,

    val nombre: String,

    val color: Long,

    val descripcion: String,

    val fecha: Long,

    val margen: Long,

    val proximaFecha: Long,

    val cumplida: Int,

    val notificada: Int,

    val categoriaId: String


)
package com.example.habbitreminderapp.Model.data

import androidx.compose.ui.graphics.Color


data class TaskModel(
    val id: Int,

    val nombre: String,

    val color: String,

    val descripcion: String,

    val margen: Long,

    val cumplida: Int,

    val fechaId: Int,

    val categoriaId: Int


)
package com.example.habbitreminderapp.Database.Task

import androidx.compose.ui.graphics.Color
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_tarea")
    val id:Int,
    @ColumnInfo(name = "nombre_tarea")
    val nombre:String,
    @ColumnInfo(name = "color_tarea")
    val color:Color,
    @ColumnInfo(name = "descripcion_tarea")
    val descripcion:String,
    @ColumnInfo(name = "margen_tarea")
    val margen:Long,
    @ColumnInfo(name = "cumplida_tarea")
    val cumplida:Int,
    @ColumnInfo(name = "fechaId_tarea")
    val fechaId:Int,
    @ColumnInfo(name = "categoriaId_tarea")
    val categoriaId:Int


)

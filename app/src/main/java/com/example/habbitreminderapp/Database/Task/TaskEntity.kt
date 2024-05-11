package com.example.habbitreminderapp.Database.Task

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Int=0,
    @ColumnInfo(name = "nombreTarea")
    var nombre:String="",
    @ColumnInfo(name = "colorTarea")
    var color:String="",
    @ColumnInfo(name = "descripcionTarea")
    var descripcion:String="",
    @ColumnInfo(name = "fechaTarea")
    var fecha:Long=0L,
    @ColumnInfo(name = "margenTarea")
    var margen:Long=0L,
    @ColumnInfo(name = "siguienteFecha")
    var proximaFecha:Long=0L,
    @ColumnInfo(name = "cumplidaTarea")
    var cumplida:Int=0,
    @ColumnInfo(name = "categoriaIdTarea")
    var categoriaId:Int=0
)
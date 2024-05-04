package com.example.habbitreminderapp.Database.Category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id_categoria")
    val id:Int,
    @ColumnInfo(name = "nombre_categoria")
    val nombre:String

)

package com.example.habbitreminderapp.Database.Task_Category

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import com.example.habbitreminderapp.Database.Category.CategoryEntity
import com.example.habbitreminderapp.Database.Task.TaskEntity

@Entity(
    tableName = "TAREA_CATEGORIA",
    primaryKeys = ["id_tarea", "id_categoria"],
    foreignKeys = [
        ForeignKey(
            entity = TaskEntity::class,
            parentColumns = ["id"],
            childColumns = ["id_tarea"]
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id_categoria"],
            childColumns = ["id_categoria"]
        )

    ]
)
data class Task_Category_Entity(
    @ColumnInfo(name = "id_tarea")
    val idTarea: Long,

    @ColumnInfo(name = "id_categoria")
    val idCategoria: Long
)
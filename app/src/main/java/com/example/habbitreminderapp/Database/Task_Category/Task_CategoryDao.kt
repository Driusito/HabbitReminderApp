package com.example.habbitreminderapp.Database.Task_Category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.habbitreminderapp.Database.Task.TaskEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface Task_CategoryDao {
    @Query("Select * from TAREA_CATEGORIA")
    fun getAllTaskCategory(): Flow<List<Task_Category>>

    @Insert
    suspend fun addTask_Category(item: Task_Category)
}
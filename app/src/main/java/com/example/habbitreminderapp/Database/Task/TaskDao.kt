package com.example.habbitreminderapp.Database.Task
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.habbitreminderapp.Database.Task.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Query("Select * from TaskEntity")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', date('now'))")
    fun getTaskOfToday(): Flow<List<TaskEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addTask(taskEntity: TaskEntity)

}
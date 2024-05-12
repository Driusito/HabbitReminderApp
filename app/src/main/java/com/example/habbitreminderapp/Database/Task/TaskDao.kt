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

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= (select strftime('%s', date('now')))" +
            " and fechaTarea < (select strftime('%s', date('now', '+1 day')))")
    fun getTaskOfToday(): Flow<List<TaskEntity>>

   	//1716206400
    //1715472000
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addTask(taskEntity: TaskEntity)

}
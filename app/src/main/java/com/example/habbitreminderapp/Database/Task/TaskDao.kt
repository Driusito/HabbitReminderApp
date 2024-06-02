package com.example.habbitreminderapp.Database.Task
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.habbitreminderapp.Database.Task.TaskEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface TaskDao {

    @Query("Select * from TaskEntity")
    fun getAllTask(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', 'now', 'localtime', 'start of day') " +
            "AND fechaTarea < strftime('%s', 'now', 'localtime', 'start of day', '+1 day') " +
            "AND cumplidaTarea=0")fun getTaskOfToday(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', 'now', 'localtime', 'start of day', '+1 day') " +
            "AND fechaTarea < strftime('%s', 'now', 'localtime', 'start of day', '+2 day') " +
            "AND cumplidaTarea=0")fun getTaskOfTomorrow(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', date('now', '+2 day', 'localtime'))and cumplidaTarea=0")
    fun getTaskComing(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= :startOfDay AND fechaTarea < :endOfDay")
    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>>

    @Query("UPDATE TaskEntity SET cumplidaTarea = 1 where id=:id")
    fun setDone(id:Int)


    @Query("Select Max(id)+1 from TaskEntity")
    fun getNextId():Int

    @Query("UPDATE TaskEntity SET cumplidaTarea = 2 WHERE id=:id AND cumplidaTarea = 0")
    suspend fun updateOverdueTasks(id: Int)

    @Query("UPDATE TaskEntity SET notiEnvidada = 1 WHERE id=:id AND cumplidaTarea = 0")
    suspend fun setNofiticated(id: Int)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addTask(taskEntity: TaskEntity)

    @Delete
    suspend fun deleteTask(taskEntity: TaskEntity)

}
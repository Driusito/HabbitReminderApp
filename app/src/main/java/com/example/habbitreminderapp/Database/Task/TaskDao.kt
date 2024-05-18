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

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', 'now', 'localtime', 'start of day') " +
            "AND fechaTarea < strftime('%s', 'now', 'localtime', 'start of day', '+1 day') " +
            "AND cumplidaTarea=0")fun getTaskOfToday(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', 'now', 'localtime', 'start of day', '+1 day') " +
            "AND fechaTarea < strftime('%s', 'now', 'localtime', 'start of day', '+2 day') " +
            "AND cumplidaTarea=0")fun getTaskOfTomorrow(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= strftime('%s', date('now', '+2 day', 'localtime'))and cumplidaTarea=0")
    fun getTaskComing(): Flow<List<TaskEntity>>

    @Query("SELECT * FROM TaskEntity WHERE fechaTarea >= :startOfDay AND fechaTarea < :endOfDay AND cumplidaTarea = 0")
    fun getTasksForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskEntity>>

    @Query("UPDATE TaskEntity SET cumplidaTarea = 1 where id=:id")
    fun setDone(id:Int)


    //1716206400
    //1715472000
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  addTask(taskEntity: TaskEntity)

}
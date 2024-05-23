package com.example.habbitreminderapp.Core.Data

import android.util.Log
import com.example.habbitreminderapp.Database.Task.TaskDao
import com.example.habbitreminderapp.Database.Task.TaskEntity
import com.example.habbitreminderapp.Model.data.TaskModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TaskRepository @Inject constructor(private val taskDao: TaskDao) {


    val getTasks: Flow<List<TaskModel>> = taskDao.getAllTask().map { items ->
        items.map {
            TaskModel(
                it.id,
                it.nombre,
                it.color,
                it.descripcion,
                it.fecha,
                it.margen,
                it.proximaFecha,
                it.cumplida,
                it.categoriaId
            )
        }
    }

    val getTasksOfToday: Flow<List<TaskModel>> = taskDao.getTaskOfToday().map { items ->
        items.map {
            TaskModel(
                it.id,
                it.nombre,
                it.color,
                it.descripcion,
                it.fecha,
                it.margen,
                it.proximaFecha,
                it.cumplida,
                it.categoriaId
            )
        }
    }

    val getTasktOfTomorrow: Flow<List<TaskModel>> = taskDao.getTaskOfTomorrow().map { items ->
        items.map {
            TaskModel(
                it.id,
                it.nombre,
                it.color,
                it.descripcion,
                it.fecha,
                it.margen,
                it.proximaFecha,
                it.cumplida,
                it.categoriaId
            )
        }
    }
    val getTaskComing: Flow<List<TaskModel>> = taskDao.getTaskComing().map { items ->
        items.map {
            TaskModel(
                it.id,
                it.nombre,
                it.color,
                it.descripcion,
                it.fecha,
                it.margen,
                it.proximaFecha,
                it.cumplida,
                it.categoriaId
            )
        }
    }

    fun getTaskForDay(startOfDay: Long, endOfDay: Long): Flow<List<TaskModel>> =
        taskDao.getTasksForDay(startOfDay, endOfDay).map { items ->
            items.map {
                TaskModel(
                    it.id,
                    it.nombre,
                    it.color,
                    it.descripcion,
                    it.fecha,
                    it.margen,
                    it.proximaFecha,
                    it.cumplida,
                    it.categoriaId
                )
            }
        }

    fun getLastId():Int{
        return taskDao.getNextId()
    }

    suspend fun updateOverdueTasks(){
        val endOfDay = System.currentTimeMillis()*1000
        Log.i("mimo",endOfDay.toString())
        taskDao.updateOverdueTasks(endOfDay)
    }

    suspend fun addTask(taskModel: TaskModel) {
        taskDao.addTask(
            TaskEntity(
                taskModel.id,
                taskModel.nombre,
                taskModel.color,
                taskModel.descripcion,
                taskModel.fecha,
                taskModel.margen,
                taskModel.proximaFecha,
                taskModel.cumplida,
                taskModel.categoriaId
            )
        )
    }

    suspend fun deleteTask(taskModel: TaskModel) {
        taskDao.deleteTask(
            TaskEntity(
                taskModel.id,
                taskModel.nombre,
                taskModel.color,
                taskModel.descripcion,
                taskModel.fecha,
                taskModel.margen,
                taskModel.proximaFecha,
                taskModel.cumplida,
                taskModel.categoriaId
            )
        )
    }




    suspend fun setTaskDone(id:Int) {
        taskDao.setDone(id)
    }
}

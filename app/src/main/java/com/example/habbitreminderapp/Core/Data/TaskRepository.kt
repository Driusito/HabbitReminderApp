package com.example.habbitreminderapp.Core.Data

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
}

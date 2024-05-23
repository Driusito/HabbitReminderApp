package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import com.example.habbitreminderapp.Database.Task.TaskEntity
import com.example.habbitreminderapp.Model.data.TaskModel
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(val taskRepository: TaskRepository) {
    suspend operator fun invoke(taskEntity: TaskModel){
        taskRepository.deleteTask(taskEntity)
    }
}
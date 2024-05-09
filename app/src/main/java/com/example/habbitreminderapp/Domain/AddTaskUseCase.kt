package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import com.example.habbitreminderapp.Model.data.TaskModel
import javax.inject.Inject

class AddTaskUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    suspend operator fun invoke(taskModel: TaskModel) {
        taskRepository.addTask(taskModel)
    }

}
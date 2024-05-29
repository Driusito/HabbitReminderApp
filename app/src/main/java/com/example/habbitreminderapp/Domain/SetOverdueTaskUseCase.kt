package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import com.example.habbitreminderapp.Model.data.TaskModel
import javax.inject.Inject

class SetOverdueTaskUseCase @Inject constructor(val taskRepository: TaskRepository) {

    suspend operator fun invoke(id:Int){
        taskRepository.updateOverdueTasks(id)
    }
}
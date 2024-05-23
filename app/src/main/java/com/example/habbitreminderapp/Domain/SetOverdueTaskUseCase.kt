package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import javax.inject.Inject

class SetOverdueTaskUseCase @Inject constructor(val taskRepository: TaskRepository) {

    suspend operator fun invoke(){
        taskRepository.updateOverdueTasks()
    }
}
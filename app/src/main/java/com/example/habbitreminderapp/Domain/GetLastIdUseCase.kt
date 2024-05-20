package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import javax.inject.Inject

class GetLastIdUseCase @Inject constructor(val taskRepository: TaskRepository) {

    operator fun invoke():Int{
        return taskRepository.getLastId()
    }
}
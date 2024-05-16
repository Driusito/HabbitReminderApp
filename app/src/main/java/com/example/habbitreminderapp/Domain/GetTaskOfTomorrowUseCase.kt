package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import com.example.habbitreminderapp.Model.data.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTaskOfTomorrowUseCase @Inject constructor(private val taskRepository: TaskRepository) {

    operator fun invoke(): Flow<List<TaskModel>> = taskRepository.getTasktOfTomorrow
}
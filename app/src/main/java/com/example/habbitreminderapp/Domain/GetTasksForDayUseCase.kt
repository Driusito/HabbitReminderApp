package com.example.habbitreminderapp.Domain

import com.example.habbitreminderapp.Core.Data.TaskRepository
import com.example.habbitreminderapp.Model.data.TaskModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named
import javax.inject.Singleton

@Singleton
class GetTasksForDayUseCase @Inject constructor(
    private val taskRepository: TaskRepository,

) {
    operator fun invoke(startOfDay: Long, endOfDay: Long): Flow<List<TaskModel>>{
        return taskRepository.getTaskForDay(startOfDay,endOfDay)
    }
}
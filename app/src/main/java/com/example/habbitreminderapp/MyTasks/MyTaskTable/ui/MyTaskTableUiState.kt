package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import com.example.habbitreminderapp.Model.data.TaskModel

sealed interface MyTaskTableUiState {
    object Loading:MyTaskTableUiState

    data class Error(val throwable: Throwable):MyTaskTableUiState

    data class Success(val tasks: List<TaskModel>):MyTaskTableUiState

}
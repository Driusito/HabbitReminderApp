package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.Core.DI.DataBaseModule
import com.example.habbitreminderapp.Core.Data.TaskRepository
import com.example.habbitreminderapp.Database.HabbitReminderDataBase
import com.example.habbitreminderapp.Database.Task.TaskEntity
import com.example.habbitreminderapp.Domain.AddTaskUseCase
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTaskTableViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase
) : ViewModel() {


    private val uiState =
        MutableStateFlow(MyTaskTableUiState::Success).catch { Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000), Loading
            )


    init {

    }
    fun addTask(){
        viewModelScope.launch {
            addTaskUseCase(
                TaskModel(  0,
                    "nombre",
                    "Rojo",
                    "3gesfv",
                    margen = 0L,
                    cumplida = 1,
                    categoriaId = 1,
                    fechaId = 1)
            )

        }
    }


}
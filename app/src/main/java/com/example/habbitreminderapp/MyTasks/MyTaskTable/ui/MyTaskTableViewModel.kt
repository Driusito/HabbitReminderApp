package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.Domain.AddTaskUseCase
import com.example.habbitreminderapp.Domain.GetLastIdUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTodayUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTomorrowUseCase
import com.example.habbitreminderapp.Domain.GetTasksComing
import com.example.habbitreminderapp.Domain.SetDoneTaskUseCase
import com.example.habbitreminderapp.Model.data.TaskModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MyTaskTableViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase, getTaskOfTodayUseCase: GetTaskOfTodayUseCase,
    getTaskOfTomorrowUseCase: GetTaskOfTomorrowUseCase,
    getTasksComing: GetTasksComing,var setDoneTaskUseCase: SetDoneTaskUseCase,val getLastIdUseCase: GetLastIdUseCase
) : ViewModel() {





    val uiStateToday: StateFlow<MyTaskTableUiState> = getTaskOfTodayUseCase().map(MyTaskTableUiState::Success)
        .catch { MyTaskTableUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyTaskTableUiState.Loading)


    val uiStateTomorrow: StateFlow<MyTaskTableUiState> = getTaskOfTomorrowUseCase().map(MyTaskTableUiState::Success)
        .catch { MyTaskTableUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyTaskTableUiState.Loading)


    val uiStateComing: StateFlow<MyTaskTableUiState> = getTasksComing().map(MyTaskTableUiState::Success)
        .catch { MyTaskTableUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyTaskTableUiState.Loading)


    suspend fun getLastID(): Int {
        return withContext(Dispatchers.IO) {
            getLastIdUseCase()
        }
    }

    fun addTask(taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            addTaskUseCase(taskModel)
        }
    }

    fun setDoneTask(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            setDoneTaskUseCase(id)
        }
    }


}
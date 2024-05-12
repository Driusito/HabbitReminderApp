package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.Domain.AddTaskUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTodayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyTaskTableViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase, getTaskOfTodayUseCase: GetTaskOfTodayUseCase,
) : ViewModel() {





    val uiState: StateFlow<MyTaskTableUiState> = getTaskOfTodayUseCase().map(MyTaskTableUiState::Success)
        .catch { MyTaskTableUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyTaskTableUiState.Loading)




    init {

    }
    fun addTask(){
        viewModelScope.launch {


        }
    }


}
package com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.Domain.GetTasksForDayUseCase
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyTaskCalendarViewModel @Inject constructor(getTasksForDayUseCase: GetTasksForDayUseCase):ViewModel() {

    private var _startDay = MutableLiveData<Long>(0L)
    val starDay: LiveData<Long> = _startDay

    private var _endDay = MutableLiveData<Long>(0L)
    val endDay: LiveData<Long> = _endDay

    val uiStateForDay: StateFlow<MyTaskTableUiState> = getTasksForDayUseCase(_startDay.value!!,_endDay.value!!).map(MyTaskTableUiState::Success)
        .catch { MyTaskTableUiState.Error(it) }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), MyTaskTableUiState.Loading)

}
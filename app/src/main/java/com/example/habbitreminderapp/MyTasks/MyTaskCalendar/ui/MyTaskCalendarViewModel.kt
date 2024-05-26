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
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject


    @HiltViewModel
    class MyTaskCalendarViewModel @Inject constructor(
        private val getTasksForDayUseCase: GetTasksForDayUseCase
    ) : ViewModel() {

        private val _startTime = MutableLiveData<Long>(0L)
        val startTime: LiveData<Long> = _startTime

        private val _endDay = MutableLiveData<Long>(0L)
        val endDay: LiveData<Long> = _endDay

        private val _uiStateForDay = MutableLiveData<MyTaskTableUiState>()
        val uiStateForDay: LiveData<MyTaskTableUiState> = _uiStateForDay

        init {
            _startTime.observeForever {
                fetchTasks()
            }
            _endDay.observeForever {
                fetchTasks()
            }
        }

        private fun fetchTasks() {
            val startTimeValue = _startTime.value ?: return
            val endDayValue = _endDay.value ?: return

            viewModelScope.launch {
                getTasksForDayUseCase(startTimeValue, endDayValue)
                    .catch { e ->
                        _uiStateForDay.value = MyTaskTableUiState.Error(e)
                    }
                    .collect { tasks ->
                        _uiStateForDay.value = MyTaskTableUiState.Success(tasks)
                    }
            }
        }

        fun setDay(dayInMillis: Long) {
            val startCalendar = Calendar.getInstance().apply {
                timeInMillis = dayInMillis
                set(Calendar.HOUR_OF_DAY, 0)
                set(Calendar.MINUTE, 0)
                set(Calendar.SECOND, 0)
                set(Calendar.MILLISECOND, 0)
            }
            _startTime.value = startCalendar.timeInMillis / 1000

            val endCalendar = Calendar.getInstance().apply {
                timeInMillis = dayInMillis
                set(Calendar.HOUR_OF_DAY, 23)
                set(Calendar.MINUTE, 59)
                set(Calendar.SECOND, 59)
                set(Calendar.MILLISECOND, 999)
            }
            _endDay.value = endCalendar.timeInMillis / 1000

            Log.i("Principio dia", _startTime.value.toString())
            Log.i("Fin dia", _endDay.value.toString())
        }
    }


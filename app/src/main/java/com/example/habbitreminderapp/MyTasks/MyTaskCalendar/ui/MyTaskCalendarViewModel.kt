package com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui

import androidx.lifecycle.ViewModel
import com.example.habbitreminderapp.Domain.GetTasksForDayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyTaskCalendarViewModel @Inject constructor(getTasksForDayUseCase: GetTasksForDayUseCase):ViewModel() {

}
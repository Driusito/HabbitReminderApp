package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableUiState.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
class MyTaskTableViewModel @Inject constructor() : ViewModel() {
    private val uiState =
        MutableStateFlow(MyTaskTableUiState::Success).catch { Error(it) }
            .stateIn(
                viewModelScope,
                SharingStarted.WhileSubscribed(5000), Loading
            )


}
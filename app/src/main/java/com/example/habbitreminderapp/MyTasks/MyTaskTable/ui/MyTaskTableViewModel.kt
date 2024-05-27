package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habbitreminderapp.Domain.AddTaskUseCase
import com.example.habbitreminderapp.Domain.DeleteTaskUseCase
import com.example.habbitreminderapp.Domain.GetLastIdUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTodayUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTomorrowUseCase
import com.example.habbitreminderapp.Domain.GetTasksComing
import com.example.habbitreminderapp.Domain.SetDoneTaskUseCase
import com.example.habbitreminderapp.Domain.SetOverdueTaskUseCase
import com.example.habbitreminderapp.HabbitReminderApp
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.R
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
    getTasksComing: GetTasksComing,
    var setDoneTaskUseCase: SetDoneTaskUseCase,
    val getLastIdUseCase: GetLastIdUseCase,
    val setOverdueTaskUseCase: SetOverdueTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase
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


    fun sendNotification(context: Context,taskModel: TaskModel){
        val notificationManager=context.getSystemService(NotificationManager::class.java)
        val notification=NotificationCompat.Builder(context,HabbitReminderApp.CHANNEL_ID)
            .setContentTitle(taskModel.nombre)
            .setContentText(taskModel.descripcion)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setAutoCancel(true)
            .build()
        notificationManager.notify(taskModel.nombre.hashCode(),notification)

    }
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

    fun setDoneTask(id: Int,taskModel: TaskModel) {
        viewModelScope.launch(Dispatchers.IO) {
            setDoneTaskUseCase(id)
           val id = getLastID()
           addTask(
                taskModel.copy(
                    id = id,
                    fecha = taskModel.proximaFecha,
                    proximaFecha = taskModel.proximaFecha + taskModel.margen
                )
            )
        }
    }

    suspend fun setOverDueTasks(){
        setOverdueTaskUseCase()
    }

    suspend fun deleteTask(taskModel: TaskModel){
        deleteTaskUseCase(taskModel)
    }


}
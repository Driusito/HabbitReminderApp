package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import NotificationWorker
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.workDataOf
import com.example.habbitreminderapp.Core.Features.getWorkRequestId
import com.example.habbitreminderapp.Domain.AddTaskUseCase
import com.example.habbitreminderapp.Domain.DeleteTaskUseCase
import com.example.habbitreminderapp.Domain.GetLastIdUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTodayUseCase
import com.example.habbitreminderapp.Domain.GetTaskOfTomorrowUseCase
import com.example.habbitreminderapp.Domain.GetTasksComing
import com.example.habbitreminderapp.Domain.SetDoneTaskUseCase
import com.example.habbitreminderapp.Domain.SetNotificatedUseCase
import com.example.habbitreminderapp.Domain.SetOverdueTaskUseCase
import com.example.habbitreminderapp.HabbitReminderApp
import com.example.habbitreminderapp.MainActivity
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.R
import com.google.gson.Gson
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.UUID
import java.util.concurrent.TimeUnit
import javax.inject.Inject

@HiltViewModel
class MyTaskTableViewModel @Inject constructor(
    private val addTaskUseCase: AddTaskUseCase, getTaskOfTodayUseCase: GetTaskOfTodayUseCase,
    getTaskOfTomorrowUseCase: GetTaskOfTomorrowUseCase,
    getTasksComing: GetTasksComing,
    var setDoneTaskUseCase: SetDoneTaskUseCase,
    val getLastIdUseCase: GetLastIdUseCase,
    val setOverdueTaskUseCase: SetOverdueTaskUseCase,
    val deleteTaskUseCase: DeleteTaskUseCase,
    val setNotificatedUseCase: SetNotificatedUseCase
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
        val intent = Intent(context, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }
        val pendingIntent = PendingIntent.getActivity(
            context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        val notification = NotificationCompat.Builder(context, HabbitReminderApp.CHANNEL_ID)
            .setContentTitle(taskModel.nombre)
            .setContentText(taskModel.descripcion)
            .setSmallIcon(R.drawable.notas)
            .setAutoCancel(true)
            .setContentIntent(pendingIntent)  // Set the PendingIntent
            .build()


        // Get the NotificationManager and notify
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(taskModel.nombre.hashCode(), notification)

    }
    suspend fun getLastID(): Int {
        return withContext(Dispatchers.IO) {
            getLastIdUseCase()
        }
    }

     fun setNotificated(id :Int){
        viewModelScope.launch(Dispatchers.IO) {
            setNotificatedUseCase(id)

        }
    }

    fun scheduleNotification(context: Context, taskModel: TaskModel, delayInMillis: Long): UUID {
        val taskModelJson = Gson().toJson(taskModel)
        val data = workDataOf("taskModelJson" to taskModelJson)

        Log.d("NotificationWorker", "Scheduling notification for task: ${taskModel.nombre} with delay: $delayInMillis ms")

        val notificationWorkRequest = OneTimeWorkRequestBuilder<NotificationWorker>()
            .setInitialDelay(delayInMillis, TimeUnit.MILLISECONDS)
            .setInputData(data)
            .build()

        WorkManager.getInstance(context).enqueue(notificationWorkRequest)

        return notificationWorkRequest.id
    }
    fun cancelNotification(context: Context, taskModelId: Int) {
        val workRequestId = getWorkRequestId(taskModelId,context) // Recupera el ID del WorkRequest

        if (workRequestId != null) {
            WorkManager.getInstance(context).cancelWorkById(workRequestId)
            Log.d("NotificationWorker", "Notification cancelled for task ID: $taskModelId")
        } else {
            Log.d("NotificationWorker", "No WorkRequest ID found for task ID: $taskModelId")
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
                    proximaFecha = taskModel.proximaFecha + taskModel.margen,
                    notificada = 0
                )
            )
        }
    }

    suspend fun setOverDueTasks(id:Int,taskModel: TaskModel){
        viewModelScope.launch(Dispatchers.IO) {
            setOverdueTaskUseCase(id)
            val id = getLastID()
            addTask(
                taskModel.copy(
                    id = id,
                    fecha = taskModel.proximaFecha,
                    proximaFecha = taskModel.proximaFecha + taskModel.margen,
                    notificada = 0
                )
            )
        }
    }

    suspend fun deleteTask(taskModel: TaskModel){
        deleteTaskUseCase(taskModel)
    }


}
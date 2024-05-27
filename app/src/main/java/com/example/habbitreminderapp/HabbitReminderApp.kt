package com.example.habbitreminderapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class HabbitReminderApp : Application() {
    companion object{
        const val CHANNEL_ID="myChannel"
    }
    override fun onCreate() {
        super.onCreate()
        createNotificationChannel()
    }

    // id del Canal de la notificacion
    //Nombre del canal
    //Nivel de importancia
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel=NotificationChannel(
                CHANNEL_ID,
                "Recordatorio de tarea",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager=this.getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }
}
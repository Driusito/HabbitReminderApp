import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.habbitreminderapp.HabbitReminderApp
import com.example.habbitreminderapp.MainActivity
import com.example.habbitreminderapp.Model.data.TaskModel
import com.example.habbitreminderapp.R
import com.google.gson.Gson
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class NotificationWorker(appContext: Context, workerParams: WorkerParameters) :
    CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result {
        val taskModelJson = inputData.getString("taskModelJson") ?: return Result.failure()
        val taskModel = Gson().fromJson(taskModelJson, TaskModel::class.java)

        Log.d("NotificationWorker", "Sending notification for task: ${taskModel.nombre}")

        sendNotification(applicationContext, taskModel)

        return Result.success()
    }

    private fun sendNotification(context: Context, taskModel: TaskModel) {
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
            .setContentIntent(pendingIntent)
            .build()

        val notificationManager = context.getSystemService(NotificationManager::class.java)
        notificationManager.notify(taskModel.nombre.hashCode(), notification)
    }
}
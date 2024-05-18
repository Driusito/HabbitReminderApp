package com.example.habbitreminderapp

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.Navigation
import com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui.MyTaskCalendarViewModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import com.example.habbitreminderapp.Navigations.AppNavigation
import com.example.habbitreminderapp.NewTaskView.ui.NewTaskScreenViewModel
import com.example.habbitreminderapp.ui.theme.HabbitReminderAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {

        val tasksViewModel:MyTaskTableViewModel by viewModels()
        val taskCalendarViewModel:MyTaskCalendarViewModel by viewModels()
        val newTaskScreenViewModel:NewTaskScreenViewModel by viewModels()


        super.onCreate(savedInstanceState)
        setContent {
            HabbitReminderAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    AppNavigation(tasksViewModel,newTaskScreenViewModel, myTaskCalendarViewModel = taskCalendarViewModel)
                }
            }
        }
    }
}


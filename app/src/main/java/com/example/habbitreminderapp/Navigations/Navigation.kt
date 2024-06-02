package com.example.habbitreminderapp.Navigations

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui.MyTaskCalendarViewModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskScreens
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import com.example.habbitreminderapp.NewTaskView.ui.NewTaskScreen
import com.example.habbitreminderapp.NewTaskView.ui.NewTaskScreenViewModel
import com.example.habbitreminderapp.WelcomeView.ui.WelcomeScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun AppNavigation(myTaskTableViewModel: MyTaskTableViewModel,newTaskScreenViewModel: NewTaskScreenViewModel,myTaskCalendarViewModel: MyTaskCalendarViewModel){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination =AppScreen.welcomeScreen.route ){
        composable(route=AppScreen.welcomeScreen.route){
            WelcomeScreen(navController,myTaskTableViewModel,myTaskCalendarViewModel)
        }
        composable(route=AppScreen.newTaskScreen.route){
            NewTaskScreen(navController, newTaskScreenViewModel = newTaskScreenViewModel)
        }
        composable(route=AppScreen.myTaskScreens.route){
            MyTaskScreens(navController=navController, myTaskTableViewModel = myTaskTableViewModel, myTaskCalendarViewModel = myTaskCalendarViewModel)
        }
    }
}
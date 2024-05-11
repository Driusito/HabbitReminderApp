package com.example.habbitreminderapp.Navigations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.habbitreminderapp.Model.Configuracion
import com.example.habbitreminderapp.Model.Inicio
import com.example.habbitreminderapp.Model.MiPerfil
import com.example.habbitreminderapp.Model.MisMetas
import com.example.habbitreminderapp.Model.NuevaMeta
import com.example.habbitreminderapp.MyTasks.MyTaskScreens
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import com.example.habbitreminderapp.NewTaskView.ui.NewTaskScreen
import com.example.habbitreminderapp.NewTaskView.ui.NewTaskScreenViewModel
import com.example.habbitreminderapp.WelcomeView.ui.WelcomeScreen
import javax.inject.Inject

@Composable
fun AppNavigation(myTaskTableViewModel: MyTaskTableViewModel,newTaskScreenViewModel: NewTaskScreenViewModel){
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination =AppScreen.welcomeScreen.route ){
        composable(route=AppScreen.welcomeScreen.route){
            WelcomeScreen(navController)
        }
        composable(route=AppScreen.newTaskScreen.route){
            NewTaskScreen(navController, newTaskScreenViewModel = newTaskScreenViewModel)
        }
        composable(route=AppScreen.myTaskScreens.route){
            MyTaskScreens(navController=navController, myTaskTableViewModel = myTaskTableViewModel)
        }
    }
}
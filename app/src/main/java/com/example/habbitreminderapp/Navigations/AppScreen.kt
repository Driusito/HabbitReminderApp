package com.example.habbitreminderapp.Navigations

sealed class AppScreen (val route:String){
    object welcomeScreen:AppScreen("welcomeScreen")

    object newTaskScreen:AppScreen("newTaskScreen")

    object myTaskScreens:AppScreen("myTaskScreen")

}
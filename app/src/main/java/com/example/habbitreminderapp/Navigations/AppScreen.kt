package com.example.habbitreminderapp.Navigations

sealed class AppScreen (route:String){
    object welcomeScreen:AppScreen("welcomeScreen")

    object newTaskScreen:AppScreen("newTaskScreen")

}
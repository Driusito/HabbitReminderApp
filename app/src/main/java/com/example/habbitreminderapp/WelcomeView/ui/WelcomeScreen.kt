package com.example.habbitreminderapp.WelcomeView.ui

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.habbitreminderapp.Core.Features.AppBar
import com.example.habbitreminderapp.Core.Features.BotonNuevoHabito
import com.example.habbitreminderapp.Core.Features.DrawerBody
import com.example.habbitreminderapp.Core.Features.DrawerHeader
import com.example.habbitreminderapp.Model.data.MenuItem
import com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui.MyTaskCalendarViewModel
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskScreens
import com.example.habbitreminderapp.MyTasks.MyTaskTable.ui.MyTaskTableViewModel
import com.example.habbitreminderapp.Navigations.AppScreen
import kotlinx.coroutines.launch

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(
    navController: NavController,
    myTaskTableViewModel: MyTaskTableViewModel,
    myTaskCalendarViewModel: MyTaskCalendarViewModel
) {
    val scope = rememberCoroutineScope()
    val drawerState = rememberDrawerState(DrawerValue.Closed)

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerHeader()
                DrawerBody(
                    items = listOf(
                        MenuItem(
                            "0",
                            "Inicio",
                            "Inicio",
                            icon = Icons.Default.Home,
                        ),
                        MenuItem(
                            "1",
                            "Mi perfil",
                            "Mi perfil",
                            icon = Icons.Default.Face,
                        ),
                        MenuItem(
                            "2",
                            "Configuracion",
                            "Configuracion",
                            icon = Icons.Default.Settings,
                        )
                    ),
                    onItemClick = {
                        when (it.title) {
                            "Inicio" -> navController.navigate(AppScreen.welcomeScreen.route)
                           // "Mis metas" -> navController.navigate(AppScreen.myTaskScreens.route)
                        }
                    }
                )
            }
        }
    ) {
        Scaffold(
            topBar = {
                AppBar(onNavigationIconClick = { scope.launch { drawerState.open() } })
            },
            content = { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    MyTaskScreens(
                        myTaskTableViewModel = myTaskTableViewModel,
                        myTaskCalendarViewModel = myTaskCalendarViewModel,
                        navController = navController
                    )

                    FloatingActionButton(
                        onClick = { navController.navigate(AppScreen.newTaskScreen.route) },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(16.dp)
                    ) {
                        Icon(Icons.Default.Add, contentDescription = "Add Task", tint = MaterialTheme.colorScheme.primary)
                    }
                }
            }
        )
    }
}
package com.example.habbitreminderapp.WelcomeView.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.habbitreminderapp.Core.Features.AppBar
import com.example.habbitreminderapp.Core.Features.BotonNuevoHabito
import com.example.habbitreminderapp.Core.Features.DrawerBody
import com.example.habbitreminderapp.Core.Features.DrawerHeader
import com.example.habbitreminderapp.Model.data.MenuItem
import com.example.habbitreminderapp.Navigations.AppScreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun WelcomeScreen(navController: NavController) {
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
                            "Mis metas",
                            "Mis metas",
                            Icons.Default.Settings,
                        )


                    ),
                    onItemClick = {
                        if (it.title == "Inicio")
                            navController.navigate(
                                AppScreen.welcomeScreen.route
                            )
                        if (it.title == "Mis metas")
                            navController.navigate(
                                AppScreen.myTaskScreens.route
                            )
                    }
                )
            }

        }
    ) {
        Scaffold(
            topBar = {
                AppBar(onNavigationIconClick = { scope.launch { drawerState.open() } })

            },
            content = {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    BotonNuevoHabito(navController = navController)
                }


            }
        )
    }
}
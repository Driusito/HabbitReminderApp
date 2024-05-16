package com.example.habbitreminderapp.MyTasks.MyTaskTable.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.produceState
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import com.example.habbitreminderapp.Core.Features.Pagina
import com.example.habbitreminderapp.MyTasks.MyTaskCalendar.ui.CustomCalendar

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MyTaskScreens(myTaskTableViewModel: MyTaskTableViewModel, navController: NavController) {

    val lifeCycle = LocalLifecycleOwner.current.lifecycle

    val uiStateToday by produceState<MyTaskTableUiState>(
        initialValue = MyTaskTableUiState.Loading,
        key1 = lifeCycle,
        key2 = myTaskTableViewModel
    ) {
        lifeCycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            myTaskTableViewModel.uiStateToday.collect { value = it }
        }
    }
    val uiStateTomorrow by produceState<MyTaskTableUiState>(
        initialValue = MyTaskTableUiState.Loading,
        key1 = lifeCycle,
        key2 = myTaskTableViewModel
    ) {
        lifeCycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            myTaskTableViewModel.uiStateTomorrow.collect { value = it }
        }
    }
    val uiStateComing by produceState<MyTaskTableUiState>(
        initialValue = MyTaskTableUiState.Loading,
        key1 = lifeCycle,
        key2 = myTaskTableViewModel
    ) {
        lifeCycle.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            myTaskTableViewModel.uiStateComing.collect { value = it }
        }
    }

    when {
        uiStateToday is MyTaskTableUiState.Error || uiStateTomorrow is MyTaskTableUiState.Error || uiStateComing is MyTaskTableUiState.Error -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(text = "Fallo de carga")
            }
        }

        uiStateToday == MyTaskTableUiState.Loading || uiStateTomorrow == MyTaskTableUiState.Loading || uiStateComing == MyTaskTableUiState.Loading -> {
            CircularProgressIndicator()
        }

        uiStateToday is MyTaskTableUiState.Success || uiStateTomorrow is MyTaskTableUiState.Success || uiStateComing is MyTaskTableUiState.Success -> {
            val tabItems = listOf(
                TabItem(
                    title = "Lista",
                    selectedIcon = Icons.AutoMirrored.Filled.List
                ),
                TabItem(
                    title = "Calendario",
                    selectedIcon = Icons.Default.CalendarMonth
                )
            )
            val pantallas = listOf(
                MyTaskTable(myTaskTableViewModel), CustomCalendar()
            )
            var selectedTabIndex by remember { mutableIntStateOf(0) }
            val pagerState = rememberPagerState { pantallas.size }

            LaunchedEffect(selectedTabIndex) {
                pagerState.animateScrollToPage(selectedTabIndex)
            }

            LaunchedEffect(pagerState.currentPage, pagerState.isScrollInProgress) {
                if (!pagerState.isScrollInProgress)
                    selectedTabIndex = pagerState.currentPage
            }



            Column(Modifier.fillMaxSize()) {
                TabRow(selectedTabIndex = selectedTabIndex) {
                    tabItems.forEachIndexed { index, item ->
                        Tab(
                            selected = (index == selectedTabIndex),
                            onClick = { selectedTabIndex = index },
                            text = { Text(text = item.title) },
                            icon = {
                                Icon(
                                    imageVector = item.selectedIcon,
                                    contentDescription = ""
                                )
                            }
                        )
                    }
                }
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) { page ->
                    when (page) {
                        //Cambiar aqui las pantallas
                        0 -> Pagina(
                            myTaskTableViewModel,
                            (uiStateToday as MyTaskTableUiState.Success).tasks,
                            (uiStateTomorrow as MyTaskTableUiState.Success).tasks,
                            (uiStateComing as MyTaskTableUiState.Success).tasks
                        )

                        1 -> CustomCalendar()
                        else -> throw IllegalStateException("Invalid page index")
                    }
                }
            }
        }
    }



}


data class TabItem(val title: String, val selectedIcon: ImageVector)